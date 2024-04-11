package com.gc.convertor;

import java.text.DecimalFormat;

public class CalculoConversor {

    ConsultaApi ca = new ConsultaApi();
    Divisas divisas = ca.conseguirDivisa();
    double pesoArg = divisas.getConversion_rates().get("ARS");
    double pesoCol = divisas.getConversion_rates().get("COP");
    double realBr = divisas.getConversion_rates().get("BRL");


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

        String resultado = "";

        DecimalFormat df = new DecimalFormat(".##");

        if (input == 1)resultado = cantidad + " Dolares son " + df.format(cantidad * pesoArg) + " Pesos Argentinos";

        else if (input == 2)resultado = cantidad + " Pesos argentinos son " + df.format(cantidad / pesoArg) + " Dolares";

        else if (input == 3)resultado = cantidad + " Dolares son " + df.format(cantidad * realBr) + " Pesos colombianos";

        else if (input == 4)resultado = cantidad + " Pesos colombianos son " + df.format(cantidad / realBr) + " Dolares";

        else if (input == 5)resultado = cantidad + " Dolares son " + df.format(cantidad * pesoCol) + " Reales brasileros";

        else if (input == 6)resultado = cantidad + " Reales brasileros " + df.format(cantidad / pesoCol) + " Dolares";

        return resultado;
    }
}
