package entidades;

public class Carrera {
	
	private int idCarrera;
	private String nombre;
	private String siglas;
	private String jefeCarrera;
	private int matricula;
	private String acreditada;
	
	public Carrera(){
		this.idCarrera=0;
		this.nombre = this.siglas = this.jefeCarrera = "";
		this.matricula = 0;
		this.acreditada = "";
	}
	
	public Carrera(String nombre,String siglas,String jefe,int matricula,String acreditada){
		this.nombre = nombre;
		this.siglas = siglas;
		this.jefeCarrera = jefe;
		this.matricula = matricula;
		this.acreditada = acreditada;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public String getJefeCarrera() {
		return jefeCarrera;
	}

	public void setJefeCarrera(String jefeCarrera) {
		this.jefeCarrera = jefeCarrera;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getAcreditada() {
		return acreditada;
	}

	public void setAcreditada(String acreditada) {
		this.acreditada = acreditada;
	}
	
	public String toString(){
		return this.siglas;
	}
}
