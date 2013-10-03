package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Carrera;

public class cdCarrera {
	private Conexion con;
	
	public cdCarrera(){
		this.con = new Conexion();
	}
	
	public String guardar(Carrera objCarrera){
		String mensaje="";
		try{
			if(con.Conectar()){
				String consulta = "insert into Carrera values (default,?,?,?,?,?)";
				PreparedStatement comando = con.getConexion().prepareStatement(consulta);
				comando.setString(1, objCarrera.getNombre());
				comando.setString(2, objCarrera.getSiglas());
				comando.setString(3, objCarrera.getJefeCarrera());
				comando.setInt(4, objCarrera.getMatricula());
				comando.setString(5,objCarrera.getAcreditada());
				comando.executeUpdate();
				mensaje = "Datos insertados en la BDD";
			}
		}catch(Exception ex){
			System.out.println("Error al guardar "+ex.getMessage());
			mensaje = "No se insertaron los registros.";
		}finally{
			con.Desconectar();
		}
		return mensaje;
	}
	
	public ObservableList<Carrera> listarDatos(){
		try{
			ObservableList<Carrera> lista = FXCollections.observableArrayList();
			if(con.Conectar()){
				String consulta = "select * from Carrera";
				Statement comando = con.getConexion().createStatement();
				ResultSet resultado = comando.executeQuery(consulta);
				if(resultado!=null){
					while(resultado.next()){
						Carrera c = new Carrera();
						c.setIdCarrera(resultado.getInt("idCarrera"));
						c.setNombre(resultado.getString("nombre"));
						c.setSiglas(resultado.getString("siglas"));
						c.setJefeCarrera(resultado.getString("jefeCarrera"));
						c.setMatricula(resultado.getInt("matricula"));
						c.setAcreditada(resultado.getString("acreditada"));
						lista.add(c);
					}
				}
				resultado.close();
			}
			return lista;
		}catch(Exception ex){
			System.out.println("Error al lista los datos "+ex.getMessage());
			return null;
		}finally{
			con.Desconectar();
		}
	}
	
	public String actualizar(Carrera objCarrera){
		String mensaje="";
		try{
			if(con.Conectar()){
				String consulta = "update Carrera set nombre = ?,siglas = ?,jefeCarrera = ?,matricula = ?,acreditada = ? where idCarrera = "+objCarrera.getIdCarrera();
				PreparedStatement comando = con.getConexion().prepareStatement(consulta);
				comando.setString(1, objCarrera.getNombre());
				comando.setString(2, objCarrera.getSiglas());
				comando.setString(3, objCarrera.getJefeCarrera());
				comando.setInt(4, objCarrera.getMatricula());
				comando.setString(5, objCarrera.getAcreditada());
				comando.executeUpdate();
				mensaje = "Registros actualizados en la BDD";
			}
		}catch(Exception ex){
			System.out.println("Error al actualizar "+ex.getMessage());
			mensaje = "No se actualizaron los registros.";
		}finally{
			con.Desconectar();
		}
		return mensaje;
	}
	
	public String eliminar(Carrera objCarrera){
		String mensaje="";
		try{
			if(con.Conectar()){
				String consulta = "delete from Carrera where idCarrera = "+objCarrera.getIdCarrera();
				Statement comando = con.getConexion().createStatement();
				comando.executeUpdate(consulta);
				mensaje = "Registros eliminados en la BDD.";
			}
		}catch(Exception ex){
			System.out.println("Error al eliminar "+ex.getMessage());
			mensaje = "No se eliminaron los registros.";
		}finally{
			con.Desconectar();
		}
		return mensaje;
	}

}
