package org.pidster.mqtt.shell.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.GenericApplicationListenerAdapter;

public class ConnectionEventListener extends GenericApplicationListenerAdapter {

	public ConnectionEventListener(ApplicationListener<ConnectionEvent> delegate) {
		super(delegate);
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return ConnectionEvent.class.isAssignableFrom(eventType);
	}

}
