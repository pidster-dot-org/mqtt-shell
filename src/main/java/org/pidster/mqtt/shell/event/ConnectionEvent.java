package org.pidster.mqtt.shell.event;

import org.springframework.context.ApplicationEvent;

public class ConnectionEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private boolean connected;

	private String hostname;

	private String username;

	public ConnectionEvent(Object source, boolean connected, String hostname, String username) {
		super(source);
		this.connected = connected;
		this.hostname = hostname;
		this.username = username;
	}

	public ConnectionEvent(Object source, boolean connected) {
		this(source, connected, null, null);
	}

	public boolean isConnected() {
		return connected;
	}

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

}
