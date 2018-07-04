/**
 * 
 */
package com.jroldan.subscriber;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author Jvt-WinLaptop
 *
 */
public class Mensaje {
	private String mensaje;
	private Long idModulo;
	
	public Mensaje(MqttMessage mensajeMQTT) {
		this.setIdModulo(obtenerIdModulo(mensajeMQTT.toString()));
		this.setMensaje(obtenerMessage(mensajeMQTT.toString()));
	}
	
	private String obtenerMessage(String mensajeMQTT) {
		mensajeMQTT.split("--");
		return mensajeMQTT.split("--")[1];
	}
	
	private Long obtenerIdModulo(String mensajeMQTT) {
		mensajeMQTT.split("--");
		return Long.parseLong(mensajeMQTT.split("--")[0]);
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Long getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}
	
	
}
