package com.jroldan;

/*
dht22_temperatura.py
dht22_humedad.py
 */

public class LauncherPub 

{	
	//TODO obtener sleep delay de bdd
	
	/**
	 * Tiempo de refresco en ms para leer los datos del sensor
	 */
	final static int delayRefrescoDefault = 3000;
	
    	public static void main(String[] args) throws Exception{
    		int delayRefresco = delayRefrescoDefault;
    		
    		if(args.length < 2) {
    			System.out.println("Se requieren especificar los siguientes argumentos: idModulo, nombreScript, topic(opcional), delay(opcional)");
    		}
    		//args[0] --> idModulo
    		//args[1] --> nombreScript
    		else if(args.length > 1) {
    			Sensor sensor = new Sensor(args[1]);
    			ConexionMQTT conexionMqtt = new ConexionMQTT(args[0]);
    			//ConexionMQTT conexionHumedad = new ConexionMQTT(args[1]);
    			
    			if(args.length > 2) {
    				conexionMqtt.setTopic(args[2]);
    				
    			}
    			else {
    				conexionMqtt.setTopic("/");
    			}
    			if(args.length > 3) {
    				delayRefresco = Integer.parseInt(args[3]);
    			}
    			
    			//TODO Validar con bdd que es un ID existente
    			//if(existeID?) {
    			System.out.println("Conectado al broker...");
    			conexionMqtt.getClienteMQTT().connect(conexionMqtt.getConnOptions());
    			//conexionHumedad.getClienteMQTT().connect(conexionHumedad.getConnOptions());
    	    	System.out.println("Conectado al broker!");
    				
    			while(1==1) {
    				// lee y guarda en objeto el valor
        			sensor.leerSensor();
        			if(sensor.getValor().equals("")) {
        				// Si la lectura ha fallado volvemos a intentar leer el sensor
        				System.out.println("No se ha podido leer el sensor...");
        				Thread.sleep(500);
        			}
        			else {
        				// Si la lectura ha sido correcta, se duerme el proceso
        				conexionMqtt.enviarMensaje(sensor.getValor(), args[0]);
        				Thread.sleep(delayRefresco);
        			}
        			
        		}
	    			//TODO permitir que se desconecte al parar de publicar
//	    			conexionMqtt.getClienteMQTT().disconnect();
//    		    	System.out.println("Desconectado");
//    		    	System.out.println("");
    			//} validar id con bdd
    		}
    		
    		
            }
}
