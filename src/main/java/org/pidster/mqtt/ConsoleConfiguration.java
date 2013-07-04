package org.pidster.mqtt;

import org.pidster.mqtt.config.MqttConsoleBannerProvider;
import org.pidster.mqtt.config.MqttConsoleHistoryFileNameProvider;
import org.pidster.mqtt.config.MqttConsolePromptProvider;
import org.pidster.mqtt.event.ConnectionEventListener;
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

}
