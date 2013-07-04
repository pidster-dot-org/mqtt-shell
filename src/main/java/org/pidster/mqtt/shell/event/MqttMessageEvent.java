package org.pidster.mqtt.shell.event;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.ApplicationEvent;

public class MqttMessageEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private MqttMessage message;
	private String topic;

	public MqttMessageEvent(Object source, MqttMessage message, String topic) {
		super(source);
		this.message = message;
		this.topic = topic;
	}

	public MqttMessage getMessage() {
		return message;
	}

	public String getTopic() {
		return topic;
	}

}
