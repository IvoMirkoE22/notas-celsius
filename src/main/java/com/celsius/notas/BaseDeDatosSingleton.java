package com.celsius.notas;

public class BaseDeDatosSingleton {// para que sirve el singelton? que  es? estudiar para el examen
    private static BaseDeDatosSingleton instancia;
    private IBaseDeDatos bd;

    private BaseDeDatosSingleton() {
        bd = new BaseDeDatosMemoria();
    }

    public static BaseDeDatosSingleton getInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatosSingleton();
        }

        return instancia;
    }

    public IBaseDeDatos getBd() {
        return bd;
    }
}
