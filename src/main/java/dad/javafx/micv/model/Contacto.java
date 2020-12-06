package dad.javafx.micv.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {
		
	ListProperty<Telefono> telefono = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());
	ListProperty<Email> url = new SimpleListProperty<Email>(FXCollections.observableArrayList());
	ListProperty<Web> web = new SimpleListProperty<Web>(FXCollections.observableArrayList());
	
	public final ListProperty<Telefono> telefonoProperty() {
		return this.telefono;
	}
	
	public final ObservableList<Telefono> getTelefono() {
		return this.telefonoProperty().get();
	}
	
	public final void setTelefono(final ObservableList<Telefono> telefono) {
		this.telefonoProperty().set(telefono);
	}
	
	public final ListProperty<Email> urlProperty() {
		return this.url;
	}
	
	public final ObservableList<Email> getUrl() {
		return this.urlProperty().get();
	}
	
	public final void setUrl(final ObservableList<Email> url) {
		this.urlProperty().set(url);
	}
	
	public final ListProperty<Web> webProperty() {
		return this.web;
	}
	
	public final ObservableList<Web> getWeb() {
		return this.webProperty().get();
	}
	
	public final void setWeb(final ObservableList<Web> web) {
		this.webProperty().set(web);
	}
	
	
	
}
