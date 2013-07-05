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
package org.pidster.mqtt.shell.event;

/**
 * @author pidster
 *
 */
public class DisconnectionEvent extends ConnectionEvent {

	private static final long serialVersionUID = 1L;

	public DisconnectionEvent(Throwable throwable) {
		super(throwable, false);
	}

	public Throwable getThrowable() {
		return (Throwable) getSource();
	}

}
