package dad.javafx.micv.personal;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Titulo;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
public class FormacionControlles implements Initializable{
	private ListProperty<Titulo> titulo = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());
	
	public FormacionControlles() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	
	 @FXML
	    private SplitPane view;
	 
	 @FXML
	    private TableView<Titulo> formacion;
	 
    @FXML
    private TableColumn<Titulo, LocalDate> desdeTab;

    @FXML
    private TableColumn<Titulo, LocalDate> hastaTab;

    @FXML
    private TableColumn<Titulo, String> denoTab;

    @FXML
    private TableColumn<Titulo, String> orgaTab;
    
    @FXML
    private VBox botonera;

    @FXML
    private Button añadirBoton;

    @FXML
    private Button eliminarBoton;
    
    CV model = new CV();
    Stage stage;

    @FXML
    void añadirBotonOnAction(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewTituloView.fxml"));
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
    void eliminarBotonOnAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Formacion");
        alert.setContentText("¿Desea borrar esta formacion?");
        
        Optional<ButtonType> opcion = alert.showAndWait();
         if(opcion.get().getText().equals("OK")|| opcion.get().getText().equals("Aceptar")) {
        	 model.getTitulo().remove(formacion.getSelectionModel().getSelectedIndex());
         }

    }

    
    @FXML
    private TextField denominacionText;

    @FXML
    private TextField organizadorText;

    @FXML
    private DatePicker desdeDate;

    @FXML
    private DatePicker hastaDate;

    @FXML
    private Button crearBoton;

    @FXML
    private Button cancelarBoton;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		titulo.addListener((o, ov, nv) -> onFormacionChanged(o, ov, nv));
		
		formacion.itemsProperty().bindBidirectional(model.tituloProperty());
		
		desdeTab.setCellValueFactory(v->v.getValue().desdeProperty());
		hastaTab.setCellValueFactory(v->v.getValue().hastaProperty());
		denoTab.setCellValueFactory(v->v.getValue().denomicacionProperty());
		orgaTab.setCellValueFactory(v->v.getValue().organizadorProperty());
		
		desdeTab.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaTab.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denoTab.setCellFactory(TextFieldTableCell.forTableColumn());
		orgaTab.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
	}
	
	private void onFormacionChanged(ObservableValue<? extends ObservableList<Titulo>> o, ObservableList<Titulo> ov,
            ObservableList<Titulo> nv) {
        if(ov != null) {
            formacion.itemsProperty().unbindBidirectional(titulo);
        }
        
        if(nv != null) {
            formacion.itemsProperty().bindBidirectional(titulo);
        }
        
    }
	
    @FXML
    void crearBotonOnAction(ActionEvent event) {
    	Titulo insert = new Titulo();
    	insert.setDenomicacion(denominacionText.getText());
    	insert.setDesde(desdeDate.getValue());
    	insert.setHasta(hastaDate.getValue());
    	insert.setOrganizador(organizadorText.getText()); 
    	
    	titulo.add(insert);

    }

    @FXML
    void cancelarBotonOnAction(ActionEvent event) {
    	stage.close();
    
    }

    
    public SplitPane getView() {
		return view;
	}

	
}
