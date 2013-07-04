package org.pidster.mqtt.config;

import org.pidster.mqtt.event.ConnectionEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.util.StringUtils;

public class MqttConsolePromptProvider implements PromptProvider, ApplicationListener<ConnectionEvent> {

	@Value("${mqtt.console.prompt}")
	private String prompt;

	private transient boolean connected = false;

	private transient String hostname;

	private transient String username = "anonymous";

	public String name() {
		return "mqttConsolePromptProvider";
	}

	public String getPrompt() {
		if (connected) {
			return String.format("%s@%s> ", username, hostname);
		}
		return String.format("%s> ", prompt);
	}

	public void onApplicationEvent(ConnectionEvent event) {
		this.connected = event.isConnected();
		this.hostname = event.getHostname();
		if (StringUtils.hasText(event.getUsername())) {
			this.username = event.getUsername();			
		}
		else {
			this.username = "anonymous";
		}
	}

}
