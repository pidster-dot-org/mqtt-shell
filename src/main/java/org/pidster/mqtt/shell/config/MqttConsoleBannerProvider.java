/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pidster.mqtt.shell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.plugin.BannerProvider;

/**
 * @author pidster
 *
 */
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
