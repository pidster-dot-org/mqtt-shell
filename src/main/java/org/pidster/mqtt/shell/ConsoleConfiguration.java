package org.pidster.mqtt.shell;

import org.pidster.mqtt.shell.config.MqttConsoleBannerProvider;
import org.pidster.mqtt.shell.config.MqttConsoleHistoryFileNameProvider;
import org.pidster.mqtt.shell.config.MqttConsolePromptProvider;
import org.pidster.mqtt.shell.event.ConnectionEventListener;
import org.pidster.mqtt.shell.event.MqttMessageEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.shell.plugin.HistoryFileNameProvider;

@Configuration
@EnableAsync
@EnableScheduling
public class ConsoleConfiguration {

    @Bean
    public BannerProvider bannerProvider() {
        return new MqttConsoleBannerProvider();
    }

    @Bean
    public HistoryFileNameProvider historyFileProvider() {
        return new MqttConsoleHistoryFileNameProvider();
    }

    @Bean
    public MqttConsolePromptProvider promptProvider() {
        return new MqttConsolePromptProvider();
    }

    @Bean
    public ConnectionEventListener connectionEventListener() {
        return new ConnectionEventListener(promptProvider());
    }

    private ConsoleReceiver consoleReceiver() {
        return new ConsoleReceiver();
    }

    @Bean
    public MqttMessageEventListener mqttMessageEventListener() {
        return new MqttMessageEventListener(consoleReceiver());
    }

}
