/**
 * 
 */
package com.jroldan.subscriber;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/**
 * @author Jvt-WinLaptop
 *
 */
public class Subscriber implements MqttCallback{

	private MqttClient client;
	private MqttConnectOptions connOpts;
	private Mensaje mensaje;
	
	private ConexionBaseDatos conexion;
	
	/** Datos CloudMQTT **/
//    private String brokerUrl = "tcp://m23.cloudmqtt.com:15672";
//    private String clientId = "kmnmxxzk";
//    private String clientPwd = "fPawFLuAJgn7";
    
    private String brokerUrl;
    private String clientId;
    private String clientPwd;
    
    public Subscriber() {
    	setBrokerUrl("tcp://m23.cloudmqtt.com:15672");
    	setClientId("kmnmxxzk");
    	setClientPwd("fPawFLuAJgn7");
    	
    	conexion = new ConexionBaseDatos();
    }
    
    public Subscriber(String brokerUrl, String clientId, String clientPwd) {
    	setBrokerUrl(brokerUrl);
    	setClientId(clientId);
    	setClientPwd(clientPwd);
    	
    	conexion = new ConexionBaseDatos();
    }
	
	public void escucharMensajes(String topic) {
		
	    try {
	    	
	        client = new MqttClient(getBrokerUrl(), "Escuchando");
	        connOpts = new MqttConnectOptions();
	        
			connOpts.setUserName(getClientId());
			connOpts.setPassword(getClientPwd().toCharArray());
	        client.connect(connOpts);
	        client.setCallback(this);
	        client.subscribe(topic);

	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
	}

	public void connectionLost(Throwable cause) {
		throw new java.lang.UnsupportedOperationException();
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		throw new java.lang.UnsupportedOperationException();
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(message);
		this.setMensaje(new Mensaje(message));
		
		
		getConexion().insertValor(mensaje.getMensaje(), mensaje.getIdModulo());
		//getConexion().insertValor(message.toString(), topic);
		
	}

	public MqttClient getClient() {
		return client;
	}

	public void setClient(MqttClient client) {
		this.client = client;
	}

	public MqttConnectOptions getConnOpts() {
		return connOpts;
	}

	public void setConnOpts(MqttConnectOptions connOpts) {
		this.connOpts = connOpts;
	}

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	public ConexionBaseDatos getConexion() {
		return conexion;
	}

	public void setConexion(ConexionBaseDatos conexion) {
		this.conexion = conexion;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	
}
