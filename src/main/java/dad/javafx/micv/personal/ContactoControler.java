package dad.javafx.micv.personal;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Contacto;
import dad.javafx.micv.model.Email;
import dad.javafx.micv.model.Telefono;
import dad.javafx.micv.model.TipoDeTelefono;
import dad.javafx.micv.model.Web;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;


public class ContactoControler  implements Initializable{
	
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>();
	
	public ContactoControler() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	  @FXML
	    private SplitPane view;
	  
	  
	  @FXML
	    private TableView<Telefono> telefono;

	    @FXML
	    private TableColumn<Telefono, String> numero;

	    @FXML
	    private TableColumn<Telefono, TipoDeTelefono> tipo;

	    @FXML
	    private TableView<Email> correo;
	    
	    @FXML
	    private TableColumn<Email, String> email;

	    @FXML
	    private TableView<Web> web;

	    @FXML
	    private TableColumn<Web, String> url;
	    
	    CV model = new CV();

	    @FXML
	    void añadirDosOnAction(ActionEvent event) {
	    	
	    	TextInputDialog dialogCorreo = new TextInputDialog();
	        dialogCorreo.setTitle("Nuevo e-mail");
	        dialogCorreo.setHeaderText("Crea una nueva dirección de correo");
	        dialogCorreo.setContentText("E-mail");
	        
	        Optional<String> a = dialogCorreo.showAndWait();
	        
	        if(!a.get().equals("")) {
	        	Email email = new Email();
	        	email.setEmail(a.get());
	        	
	        	model.getContacto().getUrl().add(email);
	        }
	        

	    }

	    @FXML
	    void añadirTresOnAction(ActionEvent event) {
	    	
	    	TextInputDialog dialogURL = new TextInputDialog();
	        dialogURL.setTitle("Nuevo URL");
	        dialogURL.setHeaderText("Crea una nueva direccion web");
	        dialogURL.setContentText("URL");
	        dialogURL.showAndWait();
	        
	        Optional<String> a = dialogURL.showAndWait();
	        
	        if(!a.get().equals("")) {
	        	Web web = new Web();
	        	web.setUrl(a.get());
	        
	        	model.getContacto().getWeb().add(web);
	        }

	    }

	    @FXML
	    void añadirUnoOnAction(ActionEvent event) {
	    	TextField insertNumero = new TextField();
	    	insertNumero.setPrefWidth(150);
	    
	    	
	    	ComboBox<TipoDeTelefono> combo = new ComboBox<>();
	    	combo.getItems().addAll(TipoDeTelefono.values());
	    	combo.setPromptText("Seleccione un tipo");
	    	
	    	
	    	GridPane grid = new GridPane();
	    	grid.setHgap(10);
	    	grid.setVgap(10);
	    	grid.setPadding(new Insets(20, 150, 10, 10));
	    	grid.add(new Label("Numero"), 0, 0);
	    	grid.add(insertNumero, 1, 0);
	    	grid.add(new Label("Tipo"), 0, 1);
	    	grid.add(combo, 1, 1);
	    	
	    	Dialog<Pair<String, TipoDeTelefono>> nuevoTel = new Dialog<>();
	    	nuevoTel.setTitle("Nuevo Telefono");
	    	nuevoTel.setHeaderText("Introduzca el nuevo numero de telefono");
	    	((Stage)nuevoTel.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/images/cv64x64.png"));
	    	nuevoTel.getDialogPane().setContent(grid);
	    	nuevoTel.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
	        Node closeButton = nuevoTel.getDialogPane().lookupButton(ButtonType.CLOSE);
	        closeButton.managedProperty().bind(closeButton.visibleProperty());
	        closeButton.setVisible(false);
	        
	        ButtonType anadirButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);
	        nuevoTel.getDialogPane().getButtonTypes().addAll(anadirButtonType, ButtonType.CANCEL);
	        
	        nuevoTel.setResultConverter(dialogButton ->{
	        	 try {
	                 Integer.parseInt(insertNumero.getText());
	             } catch (NumberFormatException e) {
	                 return null;
	             }
	             if (combo.getValue() == null)
	                 return null;
	             return new Pair<>(insertNumero.getText(), combo.getValue());
	        });
	        
	        Optional<Pair<String, TipoDeTelefono>> resultado = nuevoTel.showAndWait();
	        
	        if(!resultado.isEmpty()) {
	        	Telefono tel = new Telefono();
	        	tel.setNumero(resultado.get().getKey());
	        	tel.setTipo(resultado.get().getValue());
	        	model.getContacto().getTelefono().add(tel);
	        }
	        

	    }

	    @FXML
	    void eliminarDosOnAction(ActionEvent event) {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Eliminar Email");
	        alert.setContentText("¿Desea borrar este email?");
	        
	        Optional<ButtonType> opcion = alert.showAndWait();
	         if(opcion.get().getText().equals("OK")|| opcion.get().getText().equals("Aceptar")) {
	        	 model.getContacto().getUrl().remove(correo.getSelectionModel().getSelectedIndex());
	         }

	    }

	    @FXML
	    void eliminarTresOnAction(ActionEvent event) {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Eliminar Url");
	        alert.setContentText("¿Desea borrar esta Url?");
	        
	        Optional<ButtonType> opcion = alert.showAndWait();
	         if(opcion.get().getText().equals("OK")|| opcion.get().getText().equals("Aceptar")) {
	        	 model.getContacto().getWeb().remove(web.getSelectionModel().getSelectedIndex());
	         }

	    }

	    @FXML
	    void eliminarUnoOnAction(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Eliminar Teléfono");
	        alert.setContentText("¿Desea borrar este teléfono?");
	        
	        Optional<ButtonType> opcion = alert.showAndWait();
	         if(opcion.get().getText().equals("OK")|| opcion.get().getText().equals("Aceptar")) {
	        	 model.getContacto().getTelefono().remove(telefono.getSelectionModel().getSelectedIndex());
	         }

	    }
	    
	 
	    public SplitPane getView() {
			return view;
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			contacto.addListener((o, ov, nv) -> onContactoChanged(o, ov, nv));
			
			telefono.itemsProperty().bindBidirectional(model.getContacto().telefonoProperty());
			correo.itemsProperty().bindBidirectional(model.getContacto().urlProperty());
			web.itemsProperty().bindBidirectional(model.getContacto().webProperty());
			
			numero.setCellValueFactory(v->v.getValue().numeroProperty());
			tipo.setCellValueFactory(v->v.getValue().tipoProperty());
			
			email.setCellValueFactory(v->v.getValue().emailProperty());
			
			url.setCellValueFactory(v->v.getValue().urlProperty());
			
			numero.setCellFactory(TextFieldTableCell.forTableColumn());
			tipo.setCellFactory(ComboBoxTableCell.forTableColumn(TipoDeTelefono.values()));
			
			email.setCellFactory(TextFieldTableCell.forTableColumn());
			
			url.setCellFactory(TextFieldTableCell.forTableColumn());
			
			
		}
		
		private void onContactoChanged(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {

			
			if (ov != null) {
				
				telefono.itemsProperty().unbindBidirectional(ov.telefonoProperty());
				correo.itemsProperty().unbindBidirectional(ov.urlProperty());
				web.itemsProperty().unbindBidirectional(ov.webProperty());
				
			}
			
			if (nv != null) {
				
				telefono.itemsProperty().bindBidirectional(nv.telefonoProperty());
				correo.itemsProperty().bindBidirectional(nv.urlProperty());
				web.itemsProperty().bindBidirectional(nv.webProperty());
				
			}
			
		}


	
}
