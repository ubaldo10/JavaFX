package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entidades.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class cdAlumno {
	
	private Conexion con = null;
	
	public cdAlumno(){
		con=new Conexion();
	}
	
	public String guardar(Alumno objAlumno){
		String mensaje="";
		try{
			if(con.Conectar()){
				String consulta = "insert into Alumno values (default,?,?,?,?,?,?)";
				PreparedStatement comando = con.getConexion().prepareStatement(consulta);
				comando.setString(1, objAlumno.getNombre());
				comando.setString(2, objAlumno.getApaterno());
				comando.setString(3, objAlumno.getAmaterno());
				comando.setString(4, objAlumno.getFechaNacimiento());
				comando.setString(5, objAlumno.getSexo());
				comando.setString(6, objAlumno.getCarrera());
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
	
	public ObservableList<Alumno> listarDatos(){
		try{
			ObservableList<Alumno> lista = FXCollections.observableArrayList();
			if(con.Conectar()){
				String consulta = "select * from Alumno";
				Statement comando = con.getConexion().createStatement();
				ResultSet resultado = comando.executeQuery(consulta);
				if(resultado!=null){
					while(resultado.next()){
						Alumno a = new Alumno();
						a.setIdAlumno(resultado.getInt("idAlumno"));
						a.setNombre(resultado.getString("nombre"));
						a.setApaterno(resultado.getString("apaterno"));
						a.setAmaterno(resultado.getString("amaterno"));
						a.setFechaNacimiento(resultado.getString("fechaNacimiento"));
						a.setSexo(resultado.getString("sexo"));
						a.setCarrera(resultado.getString("carrera"));
						lista.add(a);
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
	
	public String actualizar(Alumno objAlumno){
		String mensaje="";
		try{
			if(con.Conectar()){
				String consulta = "update Alumno set nombre = ?,apaterno = ?,amaterno = ?,fechaNacimiento = ?,sexo = ?,carrera = ? where idAlumno = "+objAlumno.getIdAlumno();
				PreparedStatement comando = con.getConexion().prepareStatement(consulta);
				comando.setString(1, objAlumno.getNombre());
				comando.setString(2, objAlumno.getApaterno());
				comando.setString(3, objAlumno.getAmaterno());
				comando.setString(4, objAlumno.getFechaNacimiento());
				comando.setString(5, objAlumno.getSexo());
				comando.setString(6, objAlumno.getCarrera());
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
	
	public String Eliminar(Alumno alumno){
		String mensaje="";
		try{
			if(con.Conectar()){
		}
		String consulta="delete from alumno where idAlumno="+alumno.getIdAlumno();
		PreparedStatement comando = con.getConexion().prepareStatement(consulta);
		comando.executeUpdate();
		mensaje="Los datos fueron eliminados correctamente";
		}
		catch(Exception ex){
			System.out.println("Error al eliminar "+ex.getMessage());
			mensaje = "No se eliminaron los registros.";
		}finally{
			con.Desconectar();
		}
		return mensaje;
	}

}
