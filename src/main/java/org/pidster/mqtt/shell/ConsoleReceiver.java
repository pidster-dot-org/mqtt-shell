package org.pidster.mqtt.shell;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.pidster.mqtt.shell.event.MqttMessageEvent;
import org.springframework.context.ApplicationListener;

public class ConsoleReceiver implements ApplicationListener<MqttMessageEvent> {

	public void onApplicationEvent(MqttMessageEvent event) {
		
		MqttMessage message = event.getMessage();

		String payload =  new String(message.getPayload());
		String topic = event.getTopic();
		int qos = message.getQos();

		System.out.printf("%n '%s' -> %s (%s)", payload, topic, qos);
	}

}
