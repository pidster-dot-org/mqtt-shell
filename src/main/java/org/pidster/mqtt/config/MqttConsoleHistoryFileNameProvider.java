package org.pidster.mqtt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.plugin.HistoryFileNameProvider;

public class MqttConsoleHistoryFileNameProvider implements
		HistoryFileNameProvider {

	@Value("${mqtt.console.historyFileName}")
	private String historyFileName;

	public String name() {
		return "mqttConsoleHistoryFileNameProvider";
	}

	public String getHistoryFileName() {
		return historyFileName;
	}

}
