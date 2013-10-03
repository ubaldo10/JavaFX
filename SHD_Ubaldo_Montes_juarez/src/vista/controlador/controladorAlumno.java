package vista.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import modelo.cdAlumno;
import modelo.cdCarrera;
import entidades.Alumno;
import entidades.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class controladorAlumno implements Initializable{
	@FXML private TextField txtNombre,txtAmaterno,txtApaterno,txtFecha;
	@FXML private ComboBox<String> cboSexo;
	@FXML private ComboBox<Carrera> cboCarrera;
	@FXML private TableView<Alumno> tblAlumno;
	@FXML private Label lblMensaje;
	public Alumno alumnoModificado=null;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> items = FXCollections.observableArrayList();
		items.add("MASCULINO");
		items.add("FEMENINO");
		cboSexo.setItems(items);
		cboSexo.getSelectionModel().selectFirst();
		
		cdCarrera datosCarrera = new cdCarrera();
		ObservableList<Carrera> lista = datosCarrera.listarDatos();
		cboCarrera.setItems(lista);
		cboCarrera.getSelectionModel().selectFirst();
		
		TableColumn<Alumno , String> nombre = new TableColumn<>("NOMBRE");
		nombre.setSortType(TableColumn.SortType.DESCENDING);
		nombre.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre"));
		
		TableColumn<Alumno, String> paterno = new TableColumn<>("PATERNO");
		paterno.setSortType(TableColumn.SortType.DESCENDING);
		paterno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apaterno"));
		
		TableColumn<Alumno, String> materno = new TableColumn<>("MATERNO");
		materno.setSortType(TableColumn.SortType.DESCENDING);
		materno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("amaterno"));
		
		TableColumn<Alumno, String> fechaNac = new TableColumn<>("FECHA NAC");
		fechaNac.setSortType(TableColumn.SortType.DESCENDING);
		fechaNac.setCellValueFactory(new PropertyValueFactory<Alumno,String>("fechaNacimiento"));
		
		TableColumn<Alumno, String> sexo = new TableColumn<>("SEXO");
		sexo.setSortType(TableColumn.SortType.DESCENDING);
		sexo.setCellValueFactory(new PropertyValueFactory<Alumno,String>("sexo"));
		
		TableColumn<Alumno, String> carrera = new TableColumn<>("CARRERA");
		carrera.setSortType(TableColumn.SortType.DESCENDING);
		carrera.setCellValueFactory(new PropertyValueFactory<Alumno,String>("carrera"));
		
		tblAlumno.getColumns().addAll(nombre,paterno,materno,fechaNac,sexo,carrera);
		this.llenarTabla();
		
		tblAlumno.setOnMouseClicked(new ClickTabla());
	}
	
	public class ClickTabla implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getClickCount() == 2){
				subirElementos();
			}
		}
		
	}
	
	public void subirElementos(){
		alumnoModificado = tblAlumno.getSelectionModel().getSelectedItem();
		if(alumnoModificado!=null){
			txtNombre.setText(alumnoModificado.getNombre());
			txtApaterno.setText(alumnoModificado.getApaterno());
			txtAmaterno.setText(alumnoModificado.getAmaterno());
			txtFecha.setText(alumnoModificado.getFechaNacimiento());
			cboSexo.setValue(alumnoModificado.getSexo());
			Carrera c = new Carrera();
			c.setSiglas(alumnoModificado.getCarrera());
			cboCarrera.setValue(c);
		}
	}
	
	public void llenarTabla(){
		cdAlumno datosAlumnos = new cdAlumno();
		ObservableList<Alumno> lista = datosAlumnos.listarDatos();
		tblAlumno.setItems(lista);
		tblAlumno.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	@FXML protected void Actualizar(ActionEvent evento){
		if(alumnoModificado!=null){
			if(txtNombre.getText().trim().isEmpty()||txtApaterno.getText().trim().isEmpty()||
					txtAmaterno.getText().trim().isEmpty()||txtFecha.getText().trim().isEmpty()){
				
				lblMensaje.setText("fatan datos por ingresar");

			}else{
				alumnoModificado.setNombre(txtNombre.getText());
				alumnoModificado.setApaterno(txtApaterno.getText());
				alumnoModificado.setAmaterno(txtAmaterno.getText());
				alumnoModificado.setFechaNacimiento(txtFecha.getText());
				alumnoModificado.setSexo(cboSexo.getValue());
				Carrera c = (Carrera) cboCarrera.getValue();
				alumnoModificado.setCarrera(c.getSiglas());
				
				cdAlumno datosAlumno = new cdAlumno();
				lblMensaje.setText(datosAlumno.actualizar(alumnoModificado));
				limpiar();
				llenarTabla();
			}
		}else{
			lblMensaje.setText("Seleccione una fila de la tabla");
		}
	}
	
	@FXML protected void Guardar(ActionEvent evento){
		if(txtNombre.getText().trim().isEmpty()||txtApaterno.getText().trim().isEmpty()||
				txtAmaterno.getText().trim().isEmpty()||txtFecha.getText().trim().isEmpty()){
			
			lblMensaje.setText("fatan datos por ingresar");

		}else{
			cdAlumno datosAlumno = new cdAlumno();
			String Carrera=cboCarrera.getValue().getSiglas();
			Alumno alumno=new Alumno(txtNombre.getText(),txtApaterno.getText(),txtAmaterno.getText()
					      ,txtFecha.getText(),cboSexo.getValue(),Carrera);
			lblMensaje.setText(datosAlumno.guardar(alumno));
			limpiar();
			llenarTabla();
		}
	}
	
	private void limpiar(){
		txtNombre.setText("");
		txtApaterno.setText("");
		txtAmaterno.setText("");
		txtFecha.setText("");
		lblMensaje.setText("");
		cboSexo.getSelectionModel().selectFirst();
		cboCarrera.getSelectionModel().selectFirst();
		alumnoModificado=null;
	}
	
	@FXML protected void Eliminar(ActionEvent evento){
		if(alumnoModificado!=null){
			cdAlumno datos = new cdAlumno();
			lblMensaje.setText(datos.Eliminar(alumnoModificado));
			llenarTabla();
			limpiar();
		}else{
			lblMensaje.setText("Seleccione una fila de la tabla");
		}
	}
	
}
