/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import com.github.britooo.looca.api.core.Looca;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author HP 420
 */
public class Logger {

    static FileOutputStream arquivo;
    static String timeStamp;
    static Integer caminho;

    public static void criarLogger() throws IOException {
        timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH-mm-ss").format(Calendar.getInstance().getTime());
        Looca looca = new Looca();

        if (looca.getSistema().getSistemaOperacional().equals("Windows")) {
            File horus = new File("D:\\horus-loggers");
            if (!horus.exists()) {
                horus.mkdirs();
            }
            arquivo = new FileOutputStream("D:\\horus-loggers\\" + timeStamp + ".txt");
            caminho = 1;
        } else if (looca.getSistema().getSistemaOperacional().equals("Windows") && arquivo == null) {
            File horus = new File("C:\\horus-loggers");
            if (!horus.exists()) {
                horus.mkdirs();
            }
            arquivo = new FileOutputStream("C:\\horus-loggers\\" + timeStamp + ".txt");
            caminho = 2;
        } else if (looca.getSistema().getSistemaOperacional().equals("Linux")
                || looca.getSistema().getSistemaOperacional().equals("Ubuntu")) {
            File horus = new File("/home/urubu100/horus-loggers");
            if (!horus.exists()) {
                horus.mkdirs();
            }
            arquivo = new FileOutputStream("/home/urubu100/horus-loggers/" + timeStamp + ".txt");
            caminho = 3;
        } else {
            System.out.println("Não temos suporte para esse sistema operacional.");
        }
    }

    public static void escreverLogger(String texto) throws IOException {
        if (caminho == 1) {
            try (
                     FileWriter caminhoTxt = new FileWriter("D:\\" + "horus-loggers\\" + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
                escreverTexto.println(texto);

            } catch (IOException e) {
                Logger.loggerException(e);
            }
        } else if (caminho == 2) {
            try (
                     FileWriter caminhoTxt = new FileWriter("C:\\" + "horus-loggers\\" + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
                escreverTexto.println(texto);

            } catch (IOException e) {
                Logger.loggerException(e);
            }
        } else if (caminho == 3) {
            try (
                     FileWriter caminhoTxt = new FileWriter("/home/urubu100/horus-loggers/" + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
                escreverTexto.println(texto);
            } catch (IOException e) {
                Logger.loggerException(e);
            }
        } else {
            System.out.println("Sem sucesso, ainda não temos suporte para esse sistema operacional");
        }
    }

    public static void fecharLogger() throws IOException {
        arquivo.close();
    }

    public static void loggerException(IOException e) {
        throw new UnsupportedOperationException("Not supported yet." + e);
//To change body of generated methods, choose Tools | Templates.
    }

    public static String geradorDatas() {
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        return dataFormatada;
    }
}
