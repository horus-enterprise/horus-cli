/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import com.github.britooo.looca.api.core.Looca;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONObject;

public class Logger {

    static FileOutputStream arquivo;
    static String timeStamp;
    static Integer caminho;

    public static void criarLogger() throws IOException {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Looca looca = new Looca();
        String caminhoPasta = String.format("%s/horus-loggers", System.getProperty("user.home"));

        if (looca.getSistema().getSistemaOperacional().equals("Linux")
                || looca.getSistema().getSistemaOperacional().equals("Ubuntu")) {

            File horus = new File(caminhoPasta);
            if (!horus.exists()) {
                horus.mkdirs();
            }
            arquivo = new FileOutputStream(caminhoPasta + "/" + timeStamp + ".txt");
            caminho = 1;
        } else if (looca.getSistema().getSistemaOperacional().equals("Windows")) {
            File horus = new File(caminhoPasta);
            if (!horus.exists()) {
                horus.mkdirs();
            }
            arquivo = new FileOutputStream(caminhoPasta + "/" + timeStamp + ".txt");
            caminho = 2;
        }
    }

    public static void criarJson() throws FileNotFoundException, UnsupportedEncodingException {
        String caminho = null;
        if (System.getProperty("os.name").startsWith("Windows")) {
            caminho = String.format("%s\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\horus-web-monitor", System.getProperty("user.home"));
        } else if (System.getProperty("os.name").startsWith("Linux")) {
            caminho = String.format("~/.config/google-chrome/Default/Extensions/");
        }
        
        JSONObject json = new JSONObject();
        json.put("idFuncionario", Session.getIdFuncionario());
        json.put("idMaquina", Session.getIdMaquina());

        File horus = new File(caminho);
        if (!horus.exists()) {
            horus.mkdirs();
        }
        PrintWriter writer = new PrintWriter(caminho + "\\data.json", "UTF-8");
        writer.println(json);
        writer.close();
    }

    public static void escreverLogger(String texto) {
        String caminhoPasta = String.format("%s\\horus-loggers\\", System.getProperty("user.home"));

        if (caminho >= 1) {
            try (
                     FileWriter caminhoTxt = new FileWriter(caminhoPasta + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
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
    }

    public static String geradorDatas() {
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        return dataFormatada;
    }
}
