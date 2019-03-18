package com.jroldan;

import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ConexionMQTT {
	private String idModulo;
	private String topic;
	private int qos;
    private String broker;
    private String clientId;
    private String clientUsername;
    private String clientPwd;
    private MemoryPersistence persistence;
    private MqttClient clienteMQTT;
    private MqttConnectOptions connOptions;
    
    
    public ConexionMQTT(String idModulo) throws MqttException {
    	setIdModulo(idModulo);
    	setClientId(idModulo);
    	setQos(2);
    	setBroker("tcp://m23.cloudmqtt.com:15672");
    	setClientUsername("kmnmxxzk");
    	setClientPwd("fPawFLuAJgn7");
    	//setPersistence(new MemoryPersistence());
    	setClienteMQTT(new MqttClient(getBroker(), getClientId()));
    	setConnOptions(new MqttConnectOptions());
    	getConnOptions().setCleanSession(true);
    	getConnOptions().setUserName(getClientUsername());
        getConnOptions().setPassword(getClientPwd().toCharArray());
    }
    
    public void enviarMensaje(String mensaje, String idModulo) throws MqttSecurityException, MqttException {
    	Date fechaActual = new Date();
    	System.out.println("[" + fechaActual.toLocaleString() + "] " + "Publicando mensaje... " + idModulo + "--" + mensaje);
    	// en el mensaje publicado mandamos con el formato idModulo--Mensaje
    	MqttMessage message = new MqttMessage((idModulo + "--" + mensaje).getBytes());
    	//MqttMessage message = new MqttMessage(getIdModulo().getBytes());
    	
    	message.setQos(getQos());
    	clienteMQTT.publish(getTopic(), message);
    	System.out.println("Mensaje publicado!");
    	
    }


	/**
	 * @return the qos
	 */
	public int getQos() {
		return qos;
	}

	/**
	 * @param qos the qos to set
	 */
	public void setQos(int qos) {
		this.qos = qos;
	}

	/**
	 * @return the broker
	 */
	public String getBroker() {
		return broker;
	}

	/**
	 * @param broker the broker to set
	 */
	public void setBroker(String broker) {
		this.broker = broker;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientPwd
	 */
	public String getClientPwd() {
		return clientPwd;
	}

	/**
	 * @param clientPwd the clientPwd to set
	 */
	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	/**
	 * @return the persistence
	 */
	public MemoryPersistence getPersistence() {
		return persistence;
	}

	/**
	 * @param persistence the persistence to set
	 */
	public void setPersistence(MemoryPersistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * @return the clienteMQTT
	 */
	public MqttClient getClienteMQTT() {
		return clienteMQTT;
	}

	/**
	 * @param clienteMQTT the clienteMQTT to set
	 */
	public void setClienteMQTT(MqttClient clienteMQTT) {
		this.clienteMQTT = clienteMQTT;
	}

	/**
	 * @return the connOptions
	 */
	public MqttConnectOptions getConnOptions() {
		return connOptions;
	}

	/**
	 * @param connOptions the connOptions to set
	 */
	public void setConnOptions(MqttConnectOptions connOptions) {
		this.connOptions = connOptions;
	}

	/**
	 * @return the clientUsername
	 */
	public String getClientUsername() {
		return clientUsername;
	}

	/**
	 * @param clientUsername the clientUsername to set
	 */
	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the idModulo
	 */
	public String getIdModulo() {
		return idModulo;
	}

	/**
	 * @param idModulo the idModulo to set
	 */
	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}

}
