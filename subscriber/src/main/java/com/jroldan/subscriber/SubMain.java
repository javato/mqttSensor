/**
 * 
 */
package com.jroldan.subscriber;

/**
 * @author jroldan
 *
 */
public class SubMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Subscriber lanzado");
		Subscriber suscriptor = new Subscriber();
		// # escucha TODOS los topics
		if(args.length == 0) {
			suscriptor.escucharMensajes("#");
		}
		else if(args.length == 1) {
			suscriptor.escucharMensajes(args[0]);
		}
		else {
			System.out.println("Parametros validos: topic al que suscribirse");
		}
		
		
	}

}
