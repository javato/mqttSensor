/**
 * 
 */
package com.jroldan;

/**
 * @author Jvt-WinLaptop
 *
 */
public class Sensor {

	private String temperatura = "";
	private String humedad = "";
	private String pinGpio;
	private String rutaScript;
	private Mht22 mht22;
	
	
	public Sensor() throws Exception {
		setPinGpio("5");
		setRutaScript("python /home/pi/Desktop/pigDHT22/pig.py");
		
		setMht22(new Mht22(getPinGpio(), getRutaScript()));
	}
	
	public Sensor(String pinGpio, String rutaScript) throws Exception {
		setPinGpio(pinGpio);
		setRutaScript(rutaScript);
		
		setMht22(new Mht22(getPinGpio(), getRutaScript()));
	}
	
	public void cargarDatosLectura() throws Exception {
		String[] datos;
		
		datos = getMht22().lanzarLectura();
		
		if(datos != null) {
			setTemperatura(datos[0]);
			setHumedad(datos[1]);
		}
		else {
			setTemperatura("");
			setHumedad("");
		}
		
	}

	/**
	 * @return the temperatura
	 */
	public String getTemperatura() {
		return temperatura;
	}

	/**
	 * @param temperatura the temperatura to set
	 */
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	/**
	 * @return the humedad
	 */
	public String getHumedad() {
		return humedad;
	}

	/**
	 * @param humedad the humedad to set
	 */
	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	/**
	 * @return the pinGpio
	 */
	public String getPinGpio() {
		return pinGpio;
	}

	/**
	 * @param pinGpio the pinGpio to set
	 */
	public void setPinGpio(String pinGpio) {
		this.pinGpio = pinGpio;
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

	/**
	 * @return the mht22
	 */
	public Mht22 getMht22() {
		return mht22;
	}

	/**
	 * @param mht22 the mht22 to set
	 */
	public void setMht22(Mht22 mht22) {
		this.mht22 = mht22;
	}
	
}
