package com.celsius.notas;

//(f1)
// Pojo = plain Old Java Object //una clase que representa un tipo con algunos atributos 
import java.util.UUID;

// POJO = Plain Old Java Object
public class Nota {
  // Ctrl + . -> Generar Getters y Setters
  private String id;
  private String titulo;
  private String contenido;

  // Contructores puedo seleccionar Nota, poner control + . , generate contructor
  // luego selecciono y presiono enter
  // y me genera los contrucctores
  public Nota(String id, String titulo, String contenido) {
    if (id == null) {
      this.id = UUID.randomUUID().toString();
    } else {
      this.id = id;
    }
    this.titulo = titulo;
    this.contenido = contenido;
  }

  public String getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    // this.titulo se refiere a la propiedad
    // titulo de la clase.
    // titulo sin this. se refiere al parámetro titulo
    this.titulo = titulo;
  }

  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  @Override
  public String toString() {// metodo publico que devolvera un tipo String
    // El control ListView llama automáticamente a este
    // método para obtener el texto a mostrar en la lista
    // return .super.toString();//llama al metodo toString del padre por eso usa
    // super que se usa para acceder a la clase padre de esta clase
    return titulo;// en este caso quiero que muestre el titulo de la nota
  }
}

// public void add(Nota nota) {
// }
// }

// public void add(Nota nota) {
// }
// }