package br.com.horus.utils;

import java.io.IOException;

public class Session {

    private static String nome;
    private static String email;
    private static Integer fkEmpresa;
    private static Integer uptime = 0;
    private static Integer idFuncionario;


    public static Integer getIdFuncionario() {
        return idFuncionario;
    }

    public static void setIdFuncionario(Integer idFuncionario) {
        Session.idFuncionario = idFuncionario;
    }
    
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

    public static void criarSessao(String nome, String email, Integer fkEmpresa, Integer idFuncionario) {

        try {
            Session.nome = nome;
            Session.email = email;
            Session.fkEmpresa = fkEmpresa;
            Session.idFuncionario = idFuncionario;

            Logger.escreverLogger("> Usuário autenticado.");

            System.out.println("Sessão validada");
           
            Logger.criarJson();
            System.out.println("json atualizado");
           
        } catch (IOException e) {
            Logger.loggerException(e);
        }

    }

    public static void deletarSessao() {
        try {
            Session.nome = null;
            Session.email = null;
            Session.fkEmpresa = null;
            Session.idFuncionario = null;
            Logger.criarJson();
            Logger.escreverLogger("Encerrou essa sessão.");
        } catch (IOException e) {
            Logger.loggerException(e);
        }
    }

    public static void criarSessao(String nomeFuncionario, String email, Integer fkEmpresa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
