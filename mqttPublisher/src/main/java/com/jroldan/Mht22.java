/**
 * 
 */
package com.jroldan;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Jvt-WinLaptop
 *
 */
public class Mht22 {
	private String pinGpio;
	private String rutaScript;
	
	public Mht22() {
		this.pinGpio = "5";
		this.rutaScript = "python /home/pi/Desktop/pigDHT22/pig.py";
	}
	
	public Mht22(String pinGpio, String rutaScript) {
		this.pinGpio = pinGpio;
		this.rutaScript = rutaScript;
	}
	
	public String[] lanzarLectura() throws Exception{
		Runtime rt= Runtime.getRuntime();
		//TODO PARAMETRIZAR EL GPIO
		//Process p=rt.exec(this.rutaScript + " " + this.pinGpio);
		Process p=rt.exec(getRutaScript());
		String[] data;
		//String temperatura = "";
		//String humedad = "";
		
		BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = bri.readLine();
		data=line.split("--");
//		if(line != null){
//			
//			data=line.split("--");
//			if(data[0].equals("-999.0") || data[1].equals("-999.0")) {
//				temperatura = "";
//				humedad = "";
//			}
//			else {
//				temperatura=data[0];
//				humedad=data[1];
//			}
// 
//		}

	    bri.close();
      	p.waitFor();
      	
      	if(data[0].equals("-999.0") || data[1].equals("-999.0")) {
      		System.out.println("No se ha podido leer, vuelva a intentarlo...");
      		return null;
      	}
      	else {
      		//System.out.println("Temperatura: "+data[0]+" 'C Humedad:"+ data[1]+" %RH");
      		return data;
      	}
	}

	public String getPinGpio() {
		return pinGpio;
	}

	public void setPinGpio(String pinGpio) {
		this.pinGpio = pinGpio;
	}

	public String getRutaScript() {
		return rutaScript;
	}

	public void setRutaScript(String rutaScript) {
		this.rutaScript = rutaScript;
	}
	
}
