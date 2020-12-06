package dad.javafx.micv.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telefono {
		ObjectProperty<TipoDeTelefono> tipo = new SimpleObjectProperty<TipoDeTelefono>();
		StringProperty numero = new SimpleStringProperty();
		
		
		public final ObjectProperty<TipoDeTelefono> tipoProperty() {
			return this.tipo;
		}
		
		public final TipoDeTelefono getTipo() {
			return this.tipoProperty().get();
		}
		
		public final void setTipo(final TipoDeTelefono tipo) {
			this.tipoProperty().set(tipo);
		}
		
		public final StringProperty numeroProperty() {
			return this.numero;
		}
		
		public final String getNumero() {
			return this.numeroProperty().get();
		}
		
		public final void setNumero(final String numero) {
			this.numeroProperty().set(numero);
		}
		
		
		
}
