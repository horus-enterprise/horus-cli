package br.com.horus.utils;

import java.io.IOException;

public class Session {

    private static String nome;
    private static String email;
    private static Integer fkEmpresa;
    private static Integer uptime = 0;

    public static Integer getUptime() {
        return uptime;
    }

    public static void setUptime(Integer uptime) {
        Session.uptime = uptime;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Session.nome = nome;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public static void setFkEmpresa(Integer fkEmpresa) {
        Session.fkEmpresa = fkEmpresa;
    }

    public static void criarSessao(String nome, String email, Integer fkEmpresa) {
        try {
            Session.nome = nome;
            Session.email = email;
            Session.fkEmpresa = fkEmpresa;
            Logger.escreverLogger("> Usuário autenticado.");
            
        } catch (IOException e) {
            Logger.loggerException(e);
        }
    }

    public static void deletarSessao() {
        try {
            Session.nome = null;
            Session.email = null;
            Session.fkEmpresa = null;
            Logger.escreverLogger("Encerrou essa sessão.");
        } catch (IOException e) {
            Logger.loggerException(e);
        }
    }

}
