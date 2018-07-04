package com.jroldan;


public class App 
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
//	    			conexionMqtt.getClienteMQTT().disconnect();
//    		    	System.out.println("Desconectado");
//    		    	System.out.println("");
    			//} validar id con bdd
    		}
    		
    		
    		
//    		System.out.println("Comienza main()");
//    		
//    		Runtime rt= Runtime.getRuntime();
//    		System.out.println("Lanzando script de lectura");
//    		//Process p=rt.exec("python /home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py 22 5");
//    		Process p=rt.exec("python /home/pi/Desktop/pigDHT22/pig.py");
//    		BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
//    		line = bri.readLine();
//    		if(line != null){
//    			 
//				data=line.split("--");
//				
//				temperatura=data[0];
//				humedad=data[1];
//    				 
//    		}
//
//    	    bri.close();
//          	p.waitFor();
//          	
//          	if(temperatura.equals("-999.0") || humedad.equals("-999.0")) {
//          		System.out.println("No se ha podido leer, vuelva a intentarlo...");
//          	}
//          	else {
//          		System.out.println("Temperatura: "+temperatura+" 'C Humedad:"+ humedad+" %RH");
//          	}

            /*String topic        = "/salon/temperatura";
            String content;
            int qos             = 2;
            String broker       = "tcp://m23.cloudmqtt.com:15672";
            String clientId     = "kmnmxxzk";
            String clientPwd	= "fPawFLuAJgn7";
            MemoryPersistence persistence = new MemoryPersistence();
            ConexionJDBC conexion = new ConexionJDBC();
            conexion.generarConexion("jdbc:oracle:thin:@mqttmola.cf7ddkfg31tu.eu-west-3.rds.amazonaws.com:1521:ORCL", "mqttTFG", "mqttmola");
            */
            
            //while(true) {
            	/*try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
            	/*try {
            		content = "mensajeV2";
            		content = "4--" + content;
            		
            		//conexion.insertValor(content, new Long(4));
                    MqttClient sampleClient = new MqttClient(broker, "3", persistence);
                    MqttConnectOptions connOpts = new MqttConnectOptions();
                    connOpts.setCleanSession(true);
                    connOpts.setUserName(clientId);
                    connOpts.setPassword(clientPwd.toCharArray());
                    System.out.println("Conectando al broker: "+broker);
                    
                    sampleClient.connect(connOpts);
                    System.out.println("Conectado");
                    System.out.println("Publicando: "+content);
                    MqttMessage message = new MqttMessage(content.getBytes());
                    message.setQos(2);
                    
                    
                    //sampleClient.publish(topic, message);
                    sampleClient.publish(topic, message.getPayload(), 2, false);
                    System.out.println("Mensaje publicado");
                    sampleClient.disconnect();
                    System.out.println("Desconectado");
                    System.exit(0);
                } catch(MqttException me) {
//                    System.out.println("reason "+me.getReasonCode());
//                    System.out.println("msg "+me.getMessage());
//                    System.out.println("loc "+me.getLocalizedMessage());
//                    System.out.println("cause "+me.getCause());
//                    System.out.println("excep "+me);
                    me.printStackTrace();
                }*/
            }
}
