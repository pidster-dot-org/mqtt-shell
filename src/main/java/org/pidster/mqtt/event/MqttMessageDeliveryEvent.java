package org.pidster.mqtt.event;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.springframework.context.ApplicationEvent;

public class MqttMessageDeliveryEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public MqttMessageDeliveryEvent(IMqttDeliveryToken source) {
		super(source);
	}

	public IMqttDeliveryToken getToken() {
		return (IMqttDeliveryToken) getSource();
	}

}
