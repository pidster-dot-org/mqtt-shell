package org.pidster.mqtt.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.GenericApplicationListenerAdapter;

public class MqttMessageEventListener extends GenericApplicationListenerAdapter {

	public MqttMessageEventListener(ApplicationListener<MqttMessageEvent> delegate) {
		super(delegate);
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return MqttMessageEvent.class.isAssignableFrom(eventType);
	}

}
