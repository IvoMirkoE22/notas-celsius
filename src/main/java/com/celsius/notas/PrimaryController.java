package com.celsius.notas;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;

public class PrimaryController {
  @FXML
  private ListView<Nota> listaNotas;// (f1) creamos un tipo personalizado que tenga un titulo y contenido
  // Creo una clase llamada Clase.java por eso en <> pongo la clase Nota, mi

  @FXML
  private TextField textoTitulo;

  @FXML
  private TextArea textoContenido;

  @FXML
  private Button botonGuardar;

  private Nota notaSeleccionada;

  // para seleccionar la nota creada para poder eleminarla

  @FXML
  void salir(ActionEvent event) {
    // Para preguntarle al usuario si esta seguro salir, usare la clase Alert de jf
    Alert confirmacion = new Alert(
      // primero espera el tipo de alerta, segundo parametro el contructor de Alert
      // recibe un String es el mensaje que queremos mostrar,
      // como parametro extra le agregare un boton
        AlertType.CONFIRMATION,
        "¿Está seguro de que desea salir?",
        new ButtonType("Cancelar"),
        new ButtonType("Salir"));// con estas don opciones sobreescribo los botones que tienen el ese dialogo
    // Modificamos el título y texto de cabecera del Alert
    // Cambiaremos las palabras que estan en ingles de nuestra alerta

    confirmacion.setTitle("Confirmación");// modifique el titulo ingles a español con setTitle que nos provee alert
    confirmacion.setHeaderText("Confirmación");// encabezado en español, donde en este caso aparece un icono (? azul y
    // blanco)

// confirmacion.show();//esto quiere decir que llamo al metodo show de la
      // instancia de Alert confirmacion,metodo es una funcion que pertenece a una
      // clase o instancia de clase
      // respuesta -> {} es una funcion lambda, es una funcion anonima.Es un concepto
      // complejo
    // respuesta -> {} es una función lambda, que es una
    // función anónima. Es un concepto complejo que no vamos
    // a ver en el curso.
    confirmacion.showAndWait().ifPresent(respuesta -> {
       // System.out.println(respuesta.getText());
         // Quiero hacer que haga algo cuando le doy a salir,showAndWait muestra el
         // dialogo y espera una respuesta,usaremos
         // una funcion landam dentro de ifPresent() es una funcion anonima que se
         // implementa en una sola linea sin tipo ni nada, ifpresent si aprete algun
         // boton me dara la respuesta,
         // la respuesta sera el boton que aprete o salir o cancelar con
         // respuesta.getText
         // Ahora para chequear si es salir salga de la aplicacion o para hacer algo
         // usamos if

         // Si el ButtonType tiene el texto "Salir"
         // Cerramos la aplicacion
      // Si el ButtonType tiene el texto "Salir"
      // cerramos la aplicación
      if (respuesta.getText().equals("Salir")) {// equals es ==
        // Método para salir de una aplicación de JavaFX
        Platform.exit();

        // Método para terminar un proceso de Java
        // El parámetro es el código de error. Si no hay
        // error, va 0.
        System.out.println("Salir del programa");
        // Para salir efectivamente del programa es
        Platform.exit();// Método para salir de una aplicacion de JavaFX
        System.exit(0);// Metodo para terminar un proceso de java, el parametro es el codigo de error,
         // si no hay error, va 0
        // 0 es el codigo de error, si se coloca cualquier cosa distinta de cero
        // Quiere decir que se cerro con un error, ponemos cero porque cerraremos porque
       // queremos
      }
    });
  }

  @FXML
  void acercaDe(ActionEvent event) {
     Alert informacion = new Alert(
           AlertType.INFORMATION,
           "Celsius Notas v1.0 Desarrolado por Ivo Narvaez. El sistema operativo Windows y su interfaz de usuario están protegidos");
     informacion.show();// Llamo al metodo show de la instancia de Alerta (la clase) informacion
     // un metodo simplemente es una funcion que pertenece a una clase o instancia de
     // clase, es una funcion basicamente
     // Cambiaremos las palabras que estan en ingles de nuestra alerta

     informacion.setTitle("Acerca de bloc de notas");// modifique el titulo ingles a español con setTitle que nos
                                                     // provee alert
     informacion.setHeaderText("Windows ");// encabezado en español, donde en este caso aparece un icono (? azul y
                                           // blanco)
  }

  @FXML
  void eliminarNota(ActionEvent event) {
    // Mostrar un Alert pidiendo confirmación
    // (similar al botón Salir)

    BaseDeDatosSingleton.getInstancia().getBd().eliminar(notaSeleccionada);
    sincronizar();
    limpiarSeleccion();
  }

  @FXML
  void crearNota(ActionEvent event) {
    Nota nota = new Nota("Nota sin título", "");
      // listaNotas.getItems().add(nota);// Agrego una nota a mi lista de notas, //
      // ver a fondo en el curso
      // obtengo la implementacion de la IBasededatos
      // BaseDeDatosSingleton.getInstancia().getBd().guardar(nota);
      // BaseDeDatosSingleton.getInstancia().getBd().guardar(nota);
    BaseDeDatosSingleton instancia = BaseDeDatosSingleton.getInstancia();
    IBaseDeDatos bd = instancia.getBd();
    bd.guardar(nota);

    sincronizar();
  }

  @FXML
  void guardarNota(ActionEvent event) {
    notaSeleccionada.setTitulo(textoTitulo.getText());
    notaSeleccionada.setContenido(textoContenido.getText());

    BaseDeDatosSingleton.getInstancia().getBd().guardar(
        notaSeleccionada);
    sincronizar();
  }

  @FXML
  void seleccionarNota(MouseEvent event) {
     // se llamara cuando hagamos click a ala lista
    notaSeleccionada = listaNotas.getSelectionModel().getSelectedItem();
    botonGuardar.setDisable(false);
    textoTitulo.setText(notaSeleccionada.getTitulo());
    textoTitulo.setDisable(false);
    textoContenido.setText(notaSeleccionada.getContenido());
    textoContenido.setDisable(false);
  }

  private void sincronizar() {
    listaNotas.getItems().clear();
    listaNotas.getItems().addAll(
        BaseDeDatosSingleton.getInstancia().getBd().listar());
  }

  private void limpiarSeleccion() {
    notaSeleccionada = null;
    botonGuardar.setDisable(true);
    textoTitulo.setText("");
    textoTitulo.setDisable(true);
    textoContenido.setText("");
    textoContenido.setDisable(true);

    // Deshabilitar el botón de eliminar nota
  }
}

