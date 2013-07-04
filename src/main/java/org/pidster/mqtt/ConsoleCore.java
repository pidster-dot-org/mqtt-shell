package org.pidster.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.pidster.mqtt.event.ConnectionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ConsoleCore implements CommandMarker, MqttCallback {

	private static final Logger LOG = LoggerFactory.getLogger(ConsoleCore.class);

	@Autowired
	SimpleApplicationEventMulticaster multicaster;
	
	private MqttClient client;

	private MqttConnectOptions options;

	@CliAvailabilityIndicator(value = { "connect" })
	public boolean connectCheck() {
		if (client == null) return true;
		return !client.isConnected();
	}

	@CliCommand(value = { "connect" }, help = "Connect to an MQTT Broker")
	public String connect(
			@CliOption(key = { "host" }, mandatory = true, specifiedDefaultValue = "localhost", help = "Host address") String hostname,
			@CliOption(key = { "port" }, mandatory = false, specifiedDefaultValue = "1883", unspecifiedDefaultValue = "1883") int port,
			@CliOption(key = { "ssl" }, mandatory = false, specifiedDefaultValue = "true", unspecifiedDefaultValue = "false") boolean ssl,
			@CliOption(key = { "username" }, mandatory = false, specifiedDefaultValue = "anonymous", unspecifiedDefaultValue = "") String username,
			@CliOption(key = { "password" }, mandatory = false, specifiedDefaultValue = "", unspecifiedDefaultValue = "") String password,
			@CliOption(key = { "client-id" }, mandatory = false, specifiedDefaultValue = "mqtt-cli", unspecifiedDefaultValue = "mqtt-cli") String clientId) {

		try {
			String tmpDir = System.getProperty("java.io.tmpdir");
			MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

			options = new MqttConnectOptions();
			options.setCleanSession(true);
			if (StringUtils.hasText(username)) {
				options.setUserName(username);
			}
			if (StringUtils.hasText(password)) {
				options.setPassword(password.toCharArray());
			}

			String scheme = (ssl ? "ssl" : "tcp");
			String uri = String.format("%s://%s:%s", scheme, hostname, port);

			this.client = new MqttClient(uri, clientId, dataStore);
			client.setCallback(this);
			client.connect(options);
			
			if (client.isConnected()) {
				multicaster.multicastEvent(new ConnectionEvent(client, true, hostname, username));
			}
			
			return String.format("%sConnected to %s", (client.isConnected() ? "" : "NOT "), hostname);

		} catch (MqttException e) {
			LOG.error("Connection error", e);
			return e.getMessage();
		}
	}

	@CliAvailabilityIndicator(value = { "disconnect" })
	public boolean disconnectCheck() {
		if (client == null) return false;
		return client.isConnected();
	}

	@CliCommand(value = { "disconnect" }, help = "Disconnect from an MQTT Broker")
	public String disconnect(@CliOption(key = { "timeout" }, mandatory = false, unspecifiedDefaultValue = "-1") long timeout) {
		if (client != null && client.isConnected()) {
			try {
				if (timeout > -1) {
					client.disconnect(timeout);
				}
				else {
					client.disconnect();
				}

				if (!client.isConnected()) {
					multicaster.multicastEvent(new ConnectionEvent(this, false));
				}

			} catch (MqttException e) {
				LOG.error("Disconnection error", e);
				return e.getMessage();
			}
		}
		
		return null;
	}
	
	@CliAvailabilityIndicator(value = { "publish" })
	public boolean publishCheck() {
		if (client == null) return false;
		return client.isConnected();
	}

	@CliCommand(value = { "publish" }, help = "Publish a message to an MQTT Broker")
	public String publish(
			@CliOption(key = { "topic" }, mandatory = true) String topic,
			@CliOption(key = { "message" }, mandatory = true) String payload,
			@CliOption(key = { "qos" }, mandatory = false, specifiedDefaultValue="1", unspecifiedDefaultValue="0") int qos,
			@CliOption(key = { "retained" }, mandatory = false, specifiedDefaultValue="true", unspecifiedDefaultValue="false") boolean retained)
					throws MqttPersistenceException, MqttException {

		MqttMessage.validateQos(qos);

		client.publish(topic, payload.getBytes(), qos, retained);
		
		return String.format("(sent %s)", payload);
	}

	public void connectionLost(Throwable throwable) {
		multicaster.multicastEvent(new ConnectionEvent(this, false));
		this.client = null;
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println(token.getMessageId());
	}

	public void messageArrived(String id, MqttMessage message) throws Exception {
		System.out.printf("%s %s %n", id, new String(message.getPayload()));
	}

}
