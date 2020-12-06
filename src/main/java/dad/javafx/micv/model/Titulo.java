package dad.javafx.micv.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Titulo {
		
	ObjectProperty<LocalDate> desde = new SimpleObjectProperty<LocalDate>();
	ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<LocalDate>();
	
	StringProperty denomicacion = new SimpleStringProperty();
	StringProperty organizador = new SimpleStringProperty();
	
	public final ObjectProperty<LocalDate> desdeProperty() {
		return this.desde;
	}
	
	public final LocalDate getDesde() {
		return this.desdeProperty().get();
	}
	
	public final void setDesde(final LocalDate desde) {
		this.desdeProperty().set(desde);
	}
	
	public final ObjectProperty<LocalDate> hastaProperty() {
		return this.hasta;
	}
	
	public final LocalDate getHasta() {
		return this.hastaProperty().get();
	}
	
	public final void setHasta(final LocalDate hasta) {
		this.hastaProperty().set(hasta);
	}
	
	public final StringProperty denomicacionProperty() {
		return this.denomicacion;
	}
	
	public final String getDenomicacion() {
		return this.denomicacionProperty().get();
	}
	
	public final void setDenomicacion(final String denomicacion) {
		this.denomicacionProperty().set(denomicacion);
	}
	
	public final StringProperty organizadorProperty() {
		return this.organizador;
	}
	
	public final String getOrganizador() {
		return this.organizadorProperty().get();
	}
	
	public final void setOrganizador(final String organizador) {
		this.organizadorProperty().set(organizador);
	}
	
	
	

}
