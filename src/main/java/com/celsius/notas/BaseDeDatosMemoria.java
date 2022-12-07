package com.celsius.notas;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatosMemoria implements IBaseDeDatos {
    private List<Nota> notas = new ArrayList<>();

    // Buscamos una nota usando busqueda secuencial
    private int buscar(Nota nota) {
        // Comparamos el id de la nota pasada por parametro
        // la lista
        for (int i = 0; i < notas.size(); i++) {// size tamaño de la lista
            // notas.get(i) obtiene la nota en la posicion i
            // notas.get(i).getId() obtiene el id de la nota
            // equals compara dos strings
            // nota.getId() obtiene el id de la nota pasada
            if (notas.get(i).getId().equals(nota.getId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void guardar(Nota nota) {
        int indice = buscar(nota);
        if (indice >= 0) { // si es mayor o igual a 0 entonces existe ya que -1 significa que no exixste
            // La nota ya existe
            notas.set(indice, nota);// notas.set sirve para actualizar un elemento de la lista en la posicion indice
                                    // el valor nota al cual estoy cambiando

        } else {
            // la nota no existe
            nota.add(nota);
        }
        // TODO Auto-generated method stub

    }

    @Override
    public void eliminar(Nota nota) {
        int indice = buscar(nota);
        notas.remove(indice);
        // TODO Auto-generated method stub

    }

    @Override
    public List<Nota> listar() {
        // TODO Auto-generated method stub
        return notas;
    }

}
