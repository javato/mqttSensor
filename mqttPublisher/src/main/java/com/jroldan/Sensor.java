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
public class Sensor {

	private String valor = "";
	private String rutaScript;
	
	
	public Sensor(String rutaScript) throws Exception {
		setRutaScript(rutaScript);
	}
	
	public String leerSensor() throws Exception{
		Runtime rt= Runtime.getRuntime();
		//Process p=rt.exec(this.rutaScript + " " + this.pinGpio);
		Process p=rt.exec("python ./readScripts/" + getRutaScript());
		
		BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String data = bri.readLine();

	    bri.close();
      	p.waitFor();
      	
      	if(data.equals("read_error")) {
      		//System.out.println("No se ha podido leer");
      		setValor("");
      		return "";
      	}
      	else {
      		//System.out.println("Temperatura: "+data[0]+" 'C Humedad:"+ data[1]+" %RH");
      		setValor(data);
      		return data;	
      	}
	}
	

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the rutaScript
	 */
	public String getRutaScript() {
		return rutaScript;
	}

	/**
	 * @param rutaScript the rutaScript to set
	 */
	public void setRutaScript(String rutaScript) {
		this.rutaScript = rutaScript;
	}

	
}
