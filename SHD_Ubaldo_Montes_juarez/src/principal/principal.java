package principal;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class principal extends Application {
	@FXML Button btnCarrera;
	@FXML Button btnAlumnos;
	@FXML Button btnSalir;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("MENU");
		Parent root=null;
		try{
			root = FXMLLoader.load(getClass().getResource("../vista/fxml/Menu.fxml"));
		}catch(Exception ex){
			System.out.println("Error al cargar archivo "+ex.getMessage());
		}
		
		Scene escena = new Scene(root);
		primaryStage.setScene(escena);
		
		try{
			escena.getStylesheets().add(getClass().getResource("../vista/estilo/MistSilverSkin.css").toExternalForm());
		}catch(Exception ex){
			System.out.println("Error con el css "+ex.getMessage());
		}
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		
		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML protected void clickCarrera(ActionEvent event){
		Stage estado = new Stage();
		estado.setTitle("Carrera");
		Parent root=null;
		try{
			root = FXMLLoader.load(getClass().getResource("../vista/fxml/Carrera.fxml"));
		}catch(Exception ex){
			System.out.println("Error al cargar archivo "+ex.getMessage());
		}
		
		Scene escena = new Scene(root);
		estado.setScene(escena);
		
		try{
			escena.getStylesheets().add(getClass().getResource("../vista/estilo/MistSilverSkin.css").toExternalForm());
		}catch(Exception ex){
			System.out.println("Error con el css "+ex.getMessage());
		}
		
		estado.initModality(Modality.WINDOW_MODAL);
		estado.initOwner(((Node)event.getSource()).getScene().getWindow());
		estado.show();
	}
	
	@FXML protected void clickAlumno(ActionEvent event){
		Stage estado = new Stage();
		estado.setTitle("Alumno");
		Parent root=null;
		try{
			root = FXMLLoader.load(getClass().getResource("../vista/fxml/Alumno.fxml"));
		}catch(Exception ex){
			System.out.println("Error al cargar archivo "+ex.getMessage());
		}
		
		Scene escena = new Scene(root);
		estado.setScene(escena);
		
		try{
			escena.getStylesheets().add(getClass().getResource("../vista/estilo/MistSilverSkin.css").toExternalForm());
		}catch(Exception ex){
			System.out.println("Error con el css "+ex.getMessage());
		}
		
		estado.initModality(Modality.WINDOW_MODAL);
		estado.initOwner(((Node)event.getSource()).getScene().getWindow());
		estado.show();
	}
	
	@FXML protected void clickSalir(ActionEvent event){
		System.exit(0);
	}
	
}
