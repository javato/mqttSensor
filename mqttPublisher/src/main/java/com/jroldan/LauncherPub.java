package com.jroldan;

/*

/home/pi/Desktop/readScripts/dht22_temperatura.py
/home/pi/Desktop/readScripts/dht22_humedad.py


 */

public class LauncherPub 
{
	
    	public static void main(String[] args) throws Exception{
    		
    		if(args.length < 2) {
    			System.out.println("Se requieren especificar los siguientes argumentos: idModulo, nombreScript, topic(opcional)");
    		}
    		//args[0] --> idModulo
    		//args[1] --> rutaScript
    		else if(args.length > 1) {
    			String topic;
    			Sensor sensor = new Sensor(args[1]);
    			ConexionMQTT conexionMqtt = new ConexionMQTT(args[0]);
    			//ConexionMQTT conexionHumedad = new ConexionMQTT(args[1]);
    			
    			if(args.length == 3) {
    				conexionMqtt.setTopic(args[2]);
    			}
    			else {
    				conexionMqtt.setTopic("/");
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
        				// Si la lectura ha fallado
        				System.out.println("No se ha podido leer el sensor...");
        			}
        			else {
        				//lanzarMQTT
        				conexionMqtt.enviarMensaje(sensor.getValor(), args[0]);
        				
        				//conexionMqtt.enviarMensaje(sensor.getHumedad(), args[1]);
        				
        			}
        			//TODO obtener sleep delay 
        			Thread.sleep(3000);
        		}
	    			//TODO permitir que se desconecte al parar de publicar
//	    			conexionMqtt.getClienteMQTT().disconnect();
//    		    	System.out.println("Desconectado");
//    		    	System.out.println("");
    			//} validar id con bdd
    		}
    		
    		
            }
}
