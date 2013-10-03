package modelo;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
	
	private String usuario;
	private String contrasenia;
	private String baseDatos;
	private String driver;
	private Connection con;
	
	public Conexion(){
		usuario="postgres";
		contrasenia="mayo1693";
		baseDatos = "jdbc:postgresql://localhost:5432/SHD_Ubaldo_Montes_Juarez";
		driver = "org.postgresql.Driver";
	}
	
	public boolean Conectar(){
		try{
			Class.forName(driver);
			this.con = DriverManager.getConnection(baseDatos,usuario,contrasenia);
			System.out.println("Conexion realizada a BDD");
			return true;
		}catch(Exception ex){
			System.out.println("Error al conectar en la BDD "+ex.getMessage());
			return false;
		}
	}
	
	public boolean Desconectar(){
		try{
			this.con.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error al desconectar de la BDD "+ex.getMessage());
			return false;
		}
	}
	
	public Connection getConexion(){
		return this.con;
	}

}
