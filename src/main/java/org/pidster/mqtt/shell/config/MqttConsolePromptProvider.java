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

import org.pidster.mqtt.shell.event.ConnectionEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.util.StringUtils;

/**
 * @author pidster
 *
 */
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
