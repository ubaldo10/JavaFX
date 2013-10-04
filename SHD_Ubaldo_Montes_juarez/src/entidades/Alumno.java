package entidades;

public class Alumno {
	private String nombre,apaterno,amaterno,fechaNacimiento,sexo,carrera;
	private Integer idAlumno;
	
	public Alumno(){
		this.nombre="";
		this.apaterno="";
		this.amaterno="";
		this.fechaNacimiento="";
		this.sexo="";
		this.carrera="";
		this.idAlumno=0;
	}
	
	/*
	public Alumno(String nombre, String apaterno, String amaterno,
			String fechaNacimiento, String sexo, String carrera) {
		this.nombre = nombre;
		this.apaterno = apaterno;
		this.amaterno = amaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.carrera = carrera;
	}XD*/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApaterno() {
		return apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getAmaterno() {
		return amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}
}
