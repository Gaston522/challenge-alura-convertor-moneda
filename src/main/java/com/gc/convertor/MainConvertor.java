package com.gc.convertor;

public class MainConvertor {
    public static void main( String[] args ){

        ManejoArchivo ma = new ManejoArchivo();
        ma.leerArchivo();

        OperacionesMenu operaciones = new OperacionesMenu(ma);

        operaciones.menu();
    }
}
