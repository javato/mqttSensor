package com.jroldan;


public class LauncherPub 
{
	
    	public static void main(String[] args) throws Exception{
    		
    		if(args.length < 1) {
    			System.out.println("Se requiere especificar el ID del modulo como parametro");
    		}
    		else if(args.length > 0) {
    			Sensor sensor = new Sensor();
    			ConexionMQTT conexionMqtt = new ConexionMQTT(args[0]);
    			//ConexionMQTT conexionHumedad = new ConexionMQTT(args[1]);
    			
    			//TODO Validar con bdd que es un ID existente
    			//if(existeID?) {
    			System.out.println("Conectado al broker...");
    			conexionMqtt.getClienteMQTT().connect(conexionMqtt.getConnOptions());
    			//conexionHumedad.getClienteMQTT().connect(conexionHumedad.getConnOptions());
    	    	System.out.println("Conectado al broker!");
    				
	    			while(1==1) {
	        			sensor.cargarDatosLectura();
	        			if(sensor.getTemperatura().equals("") || sensor.getHumedad().equals("")) {
	        				// Si la lectura ha fallado
	        				System.out.println("No se ha podido leer la temperatura y/o humedad...");
	        			}
	        			else {
	        				System.out.println("Temperatura: " + sensor.getTemperatura() + "ºC" + " || Humedad: " + sensor.getHumedad() + "HR%");
	        				//lanzarMQTT
	        				conexionMqtt.enviarMensaje(sensor.getTemperatura(), "/temperatura", "0");
	        				conexionMqtt.enviarMensaje(sensor.getHumedad(), "/humedad", "1");

	        				//conexionMqtt.enviarMensaje(sensor.getHumedad(), args[1]);
	        				
	        			}
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
