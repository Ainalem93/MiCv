package dad.javafx.micv.personal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaSize.NA;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import dad.javafx.micv.utils.CargarPaisyNacionalidad;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	// model

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	private ObjectProperty<Nacionalidad> selected = new SimpleObjectProperty<Nacionalidad>();

	// view

	@FXML
	private GridPane view;

	@FXML
	private TextField identificacionText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private DatePicker fechaNacimientoDate;

	@FXML
	private TextArea direccionText;

	@FXML
	private TextField codigoPostalText;

	@FXML
	private TextField localidadText;

	@FXML
	private ListView<Nacionalidad> nacionalidadesList;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private Button nuevaNacionalidadButton;

	@FXML
	private Button quitarNacionalidadButton;
	
	CV model = new CV();

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));
		
		paisCombo.getSelectionModel().select("Selecciona un tipo");;
		paisCombo.getItems().setAll(CargarPaisyNacionalidad.cargarPaises());
		nacionalidadesList.itemsProperty().bindBidirectional(model.getPersonal().nacionalidadesProperty());
		selected.bind(nacionalidadesList.getSelectionModel().selectedItemProperty());
		
		
		
		
	}
	
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);
		
		if (ov != null) {
			
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			direccionText.textProperty().unbindBidirectional(ov.direccionProperty());
			codigoPostalText.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			nacionalidadesList.itemsProperty().unbindBidirectional(ov.nacionalidadesProperty());
			paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
			
		}
		
		if (nv != null) {
			
			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			direccionText.textProperty().bindBidirectional(nv.direccionProperty());
			codigoPostalText.textProperty().bindBidirectional(nv.codigoPostalProperty());
			nacionalidadesList.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
			
		}
		
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) {
		
		List<String> opciones = new ArrayList<>();
		opciones.addAll(CargarPaisyNacionalidad.cargarNacionalidades());
		
		ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
		dialogo.setTitle("Nueva nacionalidad");
		dialogo.setHeaderText("Añadir nueva nacionalidad");
		dialogo.setContentText("Seleccione una nacionalidad");

		Optional<String> resul = dialogo.showAndWait();
		Nacionalidad uno = new Nacionalidad();
		uno.setDenominacion(resul.get());
		
		if(!model.getPersonal().getNacionalidades().contains(uno)) {
			model.getPersonal().getNacionalidades().add(uno);
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Algo no funca");
            alert.setContentText("Ya has añadido esa nacionalidad");
            alert.showAndWait();
		}
	}

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) {
		
		model.getPersonal().getNacionalidades().remove(selected.get());

	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

}
