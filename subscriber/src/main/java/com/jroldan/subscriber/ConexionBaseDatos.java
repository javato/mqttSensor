package com.jroldan.subscriber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBaseDatos {
	private Connection conexion;
	
	String url;
	String usuario;
	String password;
	
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String usuario = "aplicacion";
//	String password = "mqttmola";
	
	public ConexionBaseDatos() {
		setUrl("jdbc:oracle:thin:@mqttmola.cf7ddkfg31tu.eu-west-3.rds.amazonaws.com:1521:ORCL");
		setUsuario("jroldan");
		setPassword("mqttmola");
		generarConexion();
	}
	
	public ConexionBaseDatos(String url, String usuario, String password) {
		setUrl(url);
		setUsuario(usuario);
		setPassword(password);
		generarConexion();
	}
	
	private void generarConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
            e.printStackTrace();
        }
        
        try {
			setConexion(DriverManager.getConnection(url, usuario, password));
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al generar la conexion " + e.getMessage());
		}
	}
	
	public void insertValor(String valor, Long id_modulo) {
		Statement sentencia;		
		String insertTableSQL = "INSERT INTO ESTADO "
				 + "VALUES"
				+ "(seq_id_estado.NextVal," + "'" + valor + "'" + ", " + "CURRENT_TIMESTAMP," + " " + id_modulo +")";
		
//		String insertTableSQL = "INSERT INTO ESTADO "
//		 + "VALUES"
//		+ "(seq_id_estado.NextVal," + "'" + valor + "'" + ", " + "null," + " '" + topic +"')";

		try {
			sentencia = getConexion().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			sentencia.executeUpdate(insertTableSQL);
			getConexion().commit();

			System.out.println("Dato insertado");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	
}
