package com.gc.convertor;

import java.util.Scanner;

public class OperacionesMenu {

    private int numero = 0;
    private double cantidad = 0;
    private Scanner sc = new Scanner(System.in);
    private CalculoConversor cc = new CalculoConversor();

    private String valorInvalido = "Valor invalido, intente nuevamente";
    private ManejoArchivo mArchivo;

    public OperacionesMenu(ManejoArchivo mArchivo) {
        this.mArchivo = mArchivo;
    }

    public void menu(){
        System.out.println("""
                    ***********************************
                    Bienvenido/a al Conversor de monedas

                    Ingrese un número para elegir una conversion:

                    1)Dolar >>>> Peso argentino
                    2)Peso argentino >>>> Dolar
                    3)Dolar >>>> Real brasileño
                    4)Real brasileño >>>> Dolar
                    5)Dolar >>>> Peso colombiano
                    6)Peso colombiano >>>> Dolar
                    7)Ver ultimas conversiones
                    8)Salir
                    ***********************************
                    """);

        numero = 0;
        cantidad = 0;

        if (sc.hasNextInt()) {
            numero = sc.nextInt();
            if (numero > 8 || numero < 1){
                System.out.println(valorInvalido);
                menu();
            } else if (numero == 7){
                mostrarLista();
            } else  if(numero < 8 && numero > 0){
                operacion();
            } else if (numero == 8) {
                this.mArchivo.escribirArchivo();
            }
        } else {
            System.out.println(valorInvalido);
            sc.next();
            menu();
        }
    }

    public void operacion(){
        cantidad = 0;
        System.out.println("ingrese un monto:");
        if (sc.hasNextDouble()){
            cantidad = sc.nextDouble();
            if (cc.checkearMonto(cantidad)) {
                cantidad = 0;
                operacion();
            } else {
                String s = cc.convertir(numero, cantidad);
                this.mArchivo.agregarLista(s);
                System.out.println(s);
                continuar();
            }
        } else {
            System.out.println(valorInvalido);
            sc.next();
            operacion();
        }
    }

    public void mostrarLista(){
        if (this.mArchivo.getLista().size() != 0) {
            System.out.println("Tus ultimas conversiones:");
            for (String s : this.mArchivo.getLista()){
                System.out.println(s);
            }
            continuar();
        } else {
            System.out.println("Todavia no realizaste conversiones");
            continuar();
        }
    }

    public void continuar(){
        numero = 0;
        System.out.println("""
                Desea continuar?
                Ingrese un numero:
                1)Volver
                2)Salir
                """);
        if (sc.hasNextInt()){
            numero = sc.nextInt();
            if (numero < 1 || numero > 2){
                System.out.println(valorInvalido);
                continuar();
            } else {
                if(numero == 1)menu();
                else this.mArchivo.escribirArchivo();
            }
        } else {
            System.out.println(valorInvalido);
            sc.next();
            continuar();
        }
    }
}
