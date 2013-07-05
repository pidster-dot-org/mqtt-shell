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
package org.pidster.mqtt.shell;

import org.pidster.mqtt.shell.config.MqttConsoleBannerProvider;
import org.pidster.mqtt.shell.config.MqttConsoleHistoryFileNameProvider;
import org.pidster.mqtt.shell.config.MqttConsolePromptProvider;
import org.pidster.mqtt.shell.event.ConnectionEventListener;
import org.pidster.mqtt.shell.event.MqttMessageEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.shell.plugin.HistoryFileNameProvider;

/**
 * @author pidster
 *
 */
@Configuration
@ComponentScan("org.pidster.mqtt.shell.cmd")
@EnableAsync
@EnableScheduling
public class ConsoleConfiguration {

    @Bean
    @Scope
    public BannerProvider bannerProvider() {
        return new MqttConsoleBannerProvider();
    }

    @Bean
    @Scope
    public HistoryFileNameProvider historyFileProvider() {
        return new MqttConsoleHistoryFileNameProvider();
    }

    @Bean
    @Scope
    public MqttConsolePromptProvider promptProvider() {
        return new MqttConsolePromptProvider();
    }

    @Bean
    public ConnectionEventListener connectionEventListener() {
        return new ConnectionEventListener(promptProvider());
    }

    @Bean
    @Scope
    public ConsoleReceiver consoleReceiver() {
        return new ConsoleReceiver();
    }

    @Bean
    public MqttMessageEventListener mqttMessageEventListener() {
        return new MqttMessageEventListener(consoleReceiver());
    }

}
