package com.gc.convertor;

import java.text.DecimalFormat;

public class CalculoConversor {

    private ConsultaApi ca = new ConsultaApi();
    private Divisas divisas = ca.conseguirDivisa();

    private double pesoArg = divisas.getConversion_rates().get("ARS");
    private double pesoCol = divisas.getConversion_rates().get("COP");
    private double realBr = divisas.getConversion_rates().get("BRL");

    private DecimalFormat df = new DecimalFormat("#.##");


    public boolean checkearMonto(double cantidad){
        boolean repetir = false;

        if (cantidad > 3800000000.0){
            repetir = true;
            System.out.println("Ingrese un monto menor");
        } else if ( cantidad < 0) {
            repetir = true;
            System.out.println("Ingrese un monto mayor");
        }

        return repetir;
    }

    public String convertir(int input, double cantidad){

        if (input == 1)return operacionConvertir(cantidad,pesoArg,1,"dolar", "pesoA");
        else if (input == 2)return operacionConvertir(cantidad,pesoArg,2,"pesoA", "dolar");
        else if (input == 3)return operacionConvertir(cantidad,realBr,1,"dolar", "realBr");
        else if (input == 4)return operacionConvertir(cantidad,realBr,2,"realBr", "dolar");
        else if (input == 5)return operacionConvertir(cantidad,pesoCol,1,"dolar", "pesoC");
        else return operacionConvertir(cantidad,pesoCol,2,"pesoC", "dolar");
    }

    public String operacionConvertir(double cantidad, double precio, int operacion, String divisa, String divisa2){
        if (operacion == 1)return texto(cantidad, divisa) + "son " + texto(cantidad * precio, divisa2);
        else return texto(cantidad, divisa) + "son " + texto(cantidad / precio, divisa2);
    }

    public String texto(double cantidad , String divisa){
        if (cantidad == 1 && divisa.equals("dolar"))return df.format(cantidad) + " Dolar ";
        else if (cantidad != 1 && divisa.equals("dolar")) return df.format(cantidad) + " Dolares ";
        else if (cantidad <= 1 && divisa.equals("pesoA")) return df.format(cantidad) + " Peso argentino ";
        else if (cantidad != 1 && divisa.equals("pesoA")) return df.format(cantidad) + " Pesos argentinos ";
        else if (cantidad == 1 && divisa.equals("realBr")) return df.format(cantidad) + " Real brasilero ";
        else if (cantidad != 1 && divisa.equals("realBr")) return df.format(cantidad) + " Reales brasileros ";
        else if (cantidad == 1 && divisa.equals("peroC")) return df.format(cantidad) + " Peso colombiano ";
        else return df.format(cantidad) + " Pesos colombianos ";
    }
}
