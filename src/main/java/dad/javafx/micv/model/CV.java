package dad.javafx.micv.model;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>(new Contacto());
	
	private ListProperty<Titulo> titulo = new SimpleListProperty<Titulo>();
	private ListProperty<Experiencia> experiencia = new SimpleListProperty<Experiencia>();
	private ListProperty<Conocimiento> conocimiento = new SimpleListProperty<Conocimiento>();
	

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public static void main(String[] args) {
		
		CV cv = new CV();
		cv.getPersonal().setNombre("Chuck");
		cv.getPersonal().setApellidos("Norris");
		cv.getPersonal().getNacionalidades().add(new Nacionalidad("estadounidense"));
		
		Gson gson = 
			FxGson.fullBuilder()
                .setPrettyPrinting()
                .create();
		
		String json = gson.toJson(cv); // convertir modelo de datos a json (marshalling)

		System.out.println(json);
		
		cv = gson.fromJson(json, CV.class); // convertir json a modelo de datos (unmarshalling) 
		
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}
	

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	

	public final ListProperty<Titulo> tituloProperty() {
		return this.titulo;
	}
	

	public final ObservableList<Titulo> getTitulo() {
		return this.tituloProperty().get();
	}
	

	public final void setTitulo(final ObservableList<Titulo> titulo) {
		this.tituloProperty().set(titulo);
	}
	

	public final ListProperty<Experiencia> experienciaProperty() {
		return this.experiencia;
	}
	

	public final ObservableList<Experiencia> getExperiencia() {
		return this.experienciaProperty().get();
	}
	

	public final void setExperiencia(final ObservableList<Experiencia> experiencia) {
		this.experienciaProperty().set(experiencia);
	}
	

	public final ListProperty<Conocimiento> conocimientoProperty() {
		return this.conocimiento;
	}
	

	public final ObservableList<Conocimiento> getConocimiento() {
		return this.conocimientoProperty().get();
	}
	

	public final void setConocimiento(final ObservableList<Conocimiento> conocimiento) {
		this.conocimientoProperty().set(conocimiento);
	}
	
	
}



