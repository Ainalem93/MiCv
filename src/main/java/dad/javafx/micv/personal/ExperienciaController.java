package dad.javafx.micv.personal;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Experiencia;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class ExperienciaController implements Initializable{
	
	private ListProperty<Experiencia> expe = new SimpleListProperty<Experiencia>();
	
	public ExperienciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	
	 @FXML
	    private SplitPane view;
	 
	 @FXML
	    private TableView<Experiencia> experiencia;

	    @FXML
	    private TableColumn<Experiencia, LocalDate> desdeTab;

	    @FXML
	    private TableColumn<Experiencia, LocalDate> hastaTab;

	    @FXML
	    private TableColumn<Experiencia, String> denoTab;

	    @FXML
	    private TableColumn<Experiencia, String> empTab;

	    @FXML
	    private Button añadirBot;

	    @FXML
	    private Button eliminarBot;
	    
	    CV model = new CV();
	    Stage stage;

	    @FXML
	    void añadirOnAction(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewExperiencaView.fxml"));
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
	        alert.setTitle("Eliminar Experiencia");
	        alert.setContentText("¿Desea borrar esta experiencia?");
	        
	        Optional<ButtonType> opcion = alert.showAndWait();
	         if(opcion.get().getText().equals("OK")|| opcion.get().getText().equals("Aceptar")) {
	        	 model.getExperiencia().remove(experiencia.getSelectionModel().getSelectedIndex());
	         }

	    }
	    
	    @FXML
	    private TextField denominacionText;

	    @FXML
	    private TextField empleadorText;

	    @FXML
	    private DatePicker desdeDate;

	    @FXML
	    private DatePicker hastaDate;

	    @FXML
	    private Button crearBoton;

	    @FXML
	    private Button cancelarBoton;

	    @FXML
	    void cancelarBotonOnAction(ActionEvent event) {
	    	stage.close();

	    }

	    @FXML
	    void crearBotonOnAction(ActionEvent event) {
	    	Experiencia insert = new Experiencia();
	    	insert.setDenomicacion(denominacionText.getText());
	    	insert.setDesde(desdeDate.getValue());
	    	insert.setHasta(hastaDate.getValue());
	    	insert.setEmpleador(empleadorText.getText());
	    	
	    	expe.add(insert);

	    }

	    
	    public SplitPane getView() {
			return view;
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			expe.addListener((o, ov, nv) -> onExperienciaChanged(o, ov, nv));
			
			experiencia.itemsProperty().bindBidirectional(model.experienciaProperty());
			
			desdeTab.setCellValueFactory(v->v.getValue().desdeProperty());
			hastaTab.setCellValueFactory(v->v.getValue().hastaProperty());
			denoTab.setCellValueFactory(v->v.getValue().denomicacionProperty());
			empTab.setCellValueFactory(v->v.getValue().empleadorProperty());
			
			desdeTab.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
			hastaTab.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
			denoTab.setCellFactory(TextFieldTableCell.forTableColumn());
			empTab.setCellFactory(TextFieldTableCell.forTableColumn());
			
		}
		
		private void onExperienciaChanged(ObservableValue<? extends ObservableList<Experiencia>> o, ObservableList<Experiencia> ov,
	            ObservableList<Experiencia> nv) {
	        if(ov != null) {
	            experiencia.itemsProperty().unbindBidirectional(expe);
	        }
	        
	        if(nv != null) {
	            experiencia.itemsProperty().bindBidirectional(expe);
	        }
	        
	    }
}
