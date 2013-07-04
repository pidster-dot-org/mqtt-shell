package org.pidster.mqtt.event;

public class DisconnectionEvent extends ConnectionEvent {

	private static final long serialVersionUID = 1L;

	public DisconnectionEvent(Throwable throwable) {
		super(throwable, false);
	}

	public Throwable getThrowable() {
		return (Throwable) getSource();
	}

}
