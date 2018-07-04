package com.jroldan;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionJDBC {
	
	private Connection connection = null;
	
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String usuario = "aplicacion";
//	String password = "mqttmola";
	
	public void generarConexion(String url, String usuario, String password) {
		System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();

        }
		
		try {
			this.connection = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al generar la conexion " + e.getMessage());
		}
	}
	
	public void insertValor(String valor, Long id_modulo) {
		Statement sentencia;
//		String sql = "select seq_id_estado.nextval from DUAL";
//		PreparedStatement ps = this.connection.prepareStatement(sql);
//		ResultSet rs = ps.executeQuery();
//		if(rs.next())
//		    Long seq_id_estado = rs.getInt(1);
		
		String insertTableSQL = "INSERT INTO ESTADO "
				 + "VALUES"
				+ "(seq_id_estado.NextVal," + "'" + valor + "'" + ", " + "null," + " " + id_modulo +")";

		try {
			sentencia = this.connection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			sentencia.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}

