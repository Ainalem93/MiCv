package dad.javafx.micv.personal;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Conocimiento;
import dad.javafx.micv.model.Idioma;
import dad.javafx.micv.model.Nivel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConocimientoController implements Initializable{
	
	private ListProperty<Conocimiento> conocimiento = new SimpleListProperty<Conocimiento>(FXCollections.observableArrayList());
	
	public ConocimientoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	 @FXML
	    private SplitPane view;
	 @FXML
	    private TableView<Conocimiento> conoTab;
	 
	 @FXML
	    private TableColumn<Conocimiento, String> denoColumn;

	    @FXML
	    private TableColumn<Conocimiento, Nivel> nivelColumn;

	    @FXML
	    private VBox botonera;

	    @FXML
	    private Button addConoBoton;

	    @FXML
	    private Button addIdiBoton;

	    @FXML
	    private Button elimnarBoton;
	    
	    Stage stage;
	    CV model = new CV();

	    @FXML
	    void conocimientoOnAction(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewConocimiento.fxml"));
            loader.setController(this);
            loader.load();
            stage = new Stage();
            Scene scene = new Scene(loader.getRoot(), 480, 360);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(view.getScene().getWindow());
            stage.setScene(scene);
            ((Stage) stage.getScene().getWindow()).getIcons().add(new Image("/images/cv64x64.png"));
            stage.setResizable(true);
            stage.show();

	    }

	    @FXML
	    void eliminarOnAction(ActionEvent event) {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Eliminar Conocimiento");
	        alert.setContentText("¿Desea borrar este Conocimiento?");
	        
	        Optional<ButtonType> opcion = alert.showAndWait();
	         if(opcion.get().getText().equals("OK") || opcion.get().getText().equals("Aceptar")) {
	        	 model.getConocimiento().remove(conoTab.getSelectionModel().getSelectedIndex());
	         }

	    }

	    @FXML
	    void idiomaOnAction(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewConocimiento2.fxml"));
            loader.setController(this);
            loader.load();
            stage = new Stage();
            Scene scene = new Scene(loader.getRoot(), 480, 360);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(view.getScene().getWindow());
            stage.setScene(scene);
            ((Stage) stage.getScene().getWindow()).getIcons().add(new Image("/images/cv64x64.png"));
            stage.setResizable(true);
            stage.show();

	    }
	    
	    @FXML
	    private TextField denominacionText;

	    @FXML
	    private HBox botoneraNivel;

	    @FXML
	    private ComboBox<Nivel> comboNivel;

	    @FXML
	    private Button xBoton;

	    @FXML
	    private HBox botoneraCrear;

	    @FXML
	    private Button crearBoton;

	    @FXML
	    private Button cancelarBoton;

	    @FXML
	    void cancelarBotonOnAction(ActionEvent event) {
	    	stage.close();

	    }

	    @FXML
	    void comboNivelOnAction(ActionEvent event) {

	    }

	    @FXML
	    void crearBotonOnAction(ActionEvent event) {
	    	
	    	Conocimiento insert = new Conocimiento();
	    	insert.setDenominacion(denominacionText.getText());
	    	insert.setNivel(comboNivel.getValue());
	    	
	    	
	    	conocimiento.add(insert);

	    }

	    @FXML
	    void xBotonOnAction(ActionEvent event) {
	    	comboNivel.getSelectionModel().clearSelection();

	    }
	    
	    @FXML
	    private TextField denominacionText2;

	    @FXML
	    private HBox botoneraNivel2;

	    @FXML
	    private ComboBox<Nivel> comboNivel2;

	    @FXML
	    private Button xBoton2;

	    @FXML
	    private HBox botoneraCrear2;

	    @FXML
	    private Button crearBoton2;

	    @FXML
	    private Button cancelarBoton2;

	    @FXML
	    private TextField certificacionText2;

	    @FXML
	    void cancelarBotonOnAction2(ActionEvent event) {
	    	stage.close();

	    }

	    @FXML
	    void comboNivelOnAction2(ActionEvent event) {

	    }

	    @FXML
	    void crearBotonOnAction2(ActionEvent event) {
	    	Idioma idi = new Idioma();
	    	idi.setCertificacion(certificacionText2.getText());
	    	Conocimiento cono = new Conocimiento();
	    	cono.setDenominacion(denominacionText2.getText());
	    	cono.setNivel(comboNivel2.getValue());
	    	cono.setIdioma(idi);
	    	
	    	conocimiento.add(cono);

	    }

	    @FXML
	    void xBotonOnAction2(ActionEvent event) {
	    	comboNivel2.getSelectionModel().clearSelection();

	    }
	    
	    public SplitPane getView() {
			return view;
		}

	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			conocimiento.addListener((o, ov, nv) -> onConocimientoChanged(o, ov, nv));
			
			conoTab.itemsProperty().bindBidirectional(model.conocimientoProperty());
			
			denoColumn.setCellValueFactory(v->v.getValue().denominacionProperty());
			nivelColumn.setCellValueFactory(v->v.getValue().nivelProperty());
		
			
			denoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn());
			
			try {
				comboNivel.getItems().addAll(Nivel.values());
			} catch (Exception e) {
				
			}
			try {
				comboNivel2.getItems().addAll(Nivel.values());
			} catch (Exception e) {
				
			}
			
		}
		
		private void onConocimientoChanged(ObservableValue<? extends ObservableList<Conocimiento>> o, ObservableList<Conocimiento> ov,
	            ObservableList<Conocimiento> nv) {
	        if(ov != null) {
	            conoTab.itemsProperty().unbindBidirectional(conocimiento);
	        }
	        
	        if(nv != null) {
	        	conoTab.itemsProperty().bindBidirectional(conocimiento);
	        }
	        
	    }
}
