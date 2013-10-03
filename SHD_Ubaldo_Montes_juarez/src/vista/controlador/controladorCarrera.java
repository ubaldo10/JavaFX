package vista.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import modelo.cdCarrera;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class controladorCarrera implements Initializable{
	@FXML private TextField txtCarrera,txtSiglas,txtJefe,txtMatricula;
	@FXML private ComboBox<String> cboAcreditada;
	@FXML private TableView<Carrera> tblCarrera;
	@FXML private Label lblMensaje;
	public Carrera carreraModificada = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		ObservableList<String> items = FXCollections.observableArrayList();
		items.add("Si");
		items.add("No");
		cboAcreditada.setItems(items);
		cboAcreditada.getSelectionModel().selectFirst();
		
		TableColumn<Carrera , String> nombre = new TableColumn<>("NOMBRE");
		nombre.setSortType(TableColumn.SortType.DESCENDING);
		nombre.setCellValueFactory(new PropertyValueFactory<Carrera,String>("nombre"));
		
		TableColumn<Carrera, String> siglas = new TableColumn<>("SIGLAS");
		siglas.setSortType(TableColumn.SortType.DESCENDING);
		siglas.setCellValueFactory(new PropertyValueFactory<Carrera,String>("siglas"));
		
		TableColumn<Carrera, String> jefe = new TableColumn<>("JEFE");
		jefe.setMinWidth(40);
		jefe.setSortType(TableColumn.SortType.DESCENDING);
		jefe.setCellValueFactory(new PropertyValueFactory<Carrera,String>("jefeCarrera"));
		
		TableColumn<Carrera, Integer> matricula = new TableColumn<>("MATRICULA");
		matricula.setSortType(TableColumn.SortType.DESCENDING);
		matricula.setCellValueFactory(new PropertyValueFactory<Carrera,Integer>("matricula"));
		
		TableColumn<Carrera, String> acreditada = new TableColumn<>("ACREDITADA");
		acreditada.setSortType(TableColumn.SortType.DESCENDING);
		acreditada.setCellValueFactory(new PropertyValueFactory<Carrera,String>("acreditada"));
		
		tblCarrera.getColumns().addAll(nombre,siglas,jefe,matricula,acreditada);
		
		LLenarTabla();
		tblCarrera.setOnMouseClicked(new ClickTabla());
		
		txtMatricula.addEventFilter(KeyEvent.KEY_TYPED, new soloNumeros());
	}
	
	@FXML protected void Guardar(ActionEvent event){
		if(txtCarrera.getText().trim().isEmpty() | txtJefe.getText().trim().isEmpty() | txtMatricula.getText().trim().isEmpty()
				| txtSiglas.getText().trim().isEmpty()){
			lblMensaje.setText("Campos vacios");
		}else{
			Carrera nuevaCarrera = 
					new Carrera(txtCarrera.getText(), txtSiglas.getText(), txtJefe.getText(),Integer.valueOf(txtMatricula.getText()),cboAcreditada.getValue());
			cdCarrera datos = new cdCarrera();
			lblMensaje.setText(datos.guardar(nuevaCarrera));
			Limpiar();
			LLenarTabla();
		}
		
	}
	
	public void Limpiar(){
		txtCarrera.setText("");
		txtMatricula.setText("");
		txtJefe.setText("");
		txtSiglas.setText("");
		cboAcreditada.getSelectionModel().selectFirst();
		carreraModificada = null;
	}
	
	public void LLenarTabla(){
		cdCarrera datos = new cdCarrera();
		ObservableList<Carrera> lista = datos.listarDatos();
		tblCarrera.setItems(lista);
		tblCarrera.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
		carreraModificada = tblCarrera.getSelectionModel().getSelectedItem();
		if(carreraModificada!=null){
			txtCarrera.setText(carreraModificada.getNombre());
			txtSiglas.setText(carreraModificada.getSiglas());
			txtJefe.setText(carreraModificada.getJefeCarrera());
			txtMatricula.setText(String.valueOf(carreraModificada.getMatricula()));
			cboAcreditada.setValue(carreraModificada.getAcreditada());
		}
	}
	
	@FXML protected void Actualizar(ActionEvent evento){
		if(carreraModificada!=null){
			if(txtCarrera.getText().trim().isEmpty() | txtJefe.getText().trim().isEmpty() | txtMatricula.getText().trim().isEmpty()
					| txtSiglas.getText().trim().isEmpty()){
				lblMensaje.setText("Campos vacios");
			}else{
				carreraModificada.setNombre(txtCarrera.getText());
				carreraModificada.setSiglas(txtSiglas.getText());
				carreraModificada.setJefeCarrera(txtJefe.getText());
				carreraModificada.setMatricula(Integer.valueOf(txtMatricula.getText()));
				carreraModificada.setAcreditada(cboAcreditada.getValue());
				
				cdCarrera datos = new cdCarrera();
				lblMensaje.setText(datos.actualizar(carreraModificada));
				Limpiar();
				LLenarTabla();
			}
		}else{
			lblMensaje.setText("Seleccione una fila de la tabla");
		}
	}
	
	public class soloNumeros implements EventHandler<KeyEvent>{

		@Override
		public void handle(KeyEvent evt) {
			// TODO Auto-generated method stub
			if(!(evt.getCharacter().charAt(0)>='0' && evt.getCharacter().charAt(0)<='9') || txtMatricula.getText().length()==4){
				evt.consume();
			}
		}
	}
	
	@FXML protected void Eliminar(ActionEvent evento){
		if(carreraModificada!=null){
			cdCarrera datos = new cdCarrera();
			lblMensaje.setText(datos.eliminar(carreraModificada));
			Limpiar();
			LLenarTabla();
		}else{
			lblMensaje.setText("Seleccione una fila de la tabla");
		}
	}

}
