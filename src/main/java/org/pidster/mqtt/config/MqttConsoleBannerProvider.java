package org.pidster.mqtt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.plugin.BannerProvider;

public class MqttConsoleBannerProvider implements BannerProvider {

	@Value("${mqtt.console.banner}")
	private String banner;

	@Value("${mqtt.console.version}")
	private String version;

	@Value("${mqtt.console.welcomeMessage}")
	private String welcomeMessage;

	public String name() {
		return "mqttConsoleBannerProvider";
	}

	public String getBanner() {
		// return banner;
		return null;
	}

	public String getVersion() {
		return version;
	}

	public String getWelcomeMessage() {
		// return welcomeMessage;
		return null;
	}

}
