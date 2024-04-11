package com.gc.convertor;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivo {

    private List<String> lista = new ArrayList<>();

    public void escribirArchivo(){

        try {
            FileOutputStream fos = new FileOutputStream("archivo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void leerArchivo(){
        File file = new File("archivo.dat");

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                lista = (List<String>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void agregarLista(String s) {

        LocalDateTime ldt = LocalDateTime.now();
        DecimalFormat df = new DecimalFormat("##");

        String fechaHora = " "+ ldt.getDayOfMonth() + "/"
                + ldt.getDayOfMonth() + "/" + ldt.getYear() + " hora: "
                + ldt.getHour() + ":" + df.format(ldt.getMinute()) + ":" + ldt.getSecond();

        lista.add(0, s + fechaHora);
        if (lista.size() > 5)lista.remove(lista.size() - 1);
    }

    public List<String> getLista() {
        return lista;
    }
}
