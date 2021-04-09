package com.example.practicasemana08;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

    public class TCPSingleton extends Thread {


    private static TCPSingleton unicainstancia;
    private BufferedWriter bwriter;

    public static TCPSingleton getInstance(){
        if(unicainstancia==null){

            unicainstancia= new TCPSingleton();
            unicainstancia.start();
        }

        return unicainstancia;
    }


    private TCPSingleton(){


    }

    private Socket socket;

    @Override
    public void run(){

        try {
            Log.e("----->", "claro");
            Socket socket = new Socket("10.0.2.2",5000);
            OutputStream os= socket.getOutputStream();
            OutputStreamWriter osw= new OutputStreamWriter(os);
            bwriter= new BufferedWriter(osw);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void accion(String hizo) {

        new Thread(
                () -> {

                    Gson gson = new Gson();
                    Acciones acc = new Acciones(hizo);
                    //Serialización
                    String tipocoord = gson.toJson(acc);

                    try {

                        bwriter.write(tipocoord + "\n");
                        bwriter.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ).start();

    }
}
