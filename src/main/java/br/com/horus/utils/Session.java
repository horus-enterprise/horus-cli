package br.com.horus.utils;

public class Session {

    private static String nome;
    private static String email;
    private static Integer fkEmpresa;
    private static Integer uptime = 0;
    private static Integer idFuncionario;
    private static Integer idMaquina;

    public static Integer getIdMaquina() {
        return idMaquina;
    }

    public static void setIdMaquina(Integer idMaquina) {
        Session.idMaquina = idMaquina;
    }

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

            Logger.escreverLogger("> Usuário autenticado. - " + Logger.geradorDatas());

        } catch (Exception e) {
            Logger.escreverLogger("Impossível autenticar usuário :"
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }

    }

    public static void deletarSessao() {
        try {
            Session.nome = null;
            Session.email = null;
            Session.fkEmpresa = null;
            Session.idFuncionario = null;
            Session.idMaquina = null;
            Logger.criarJson();
            Logger.escreverLogger("Encerrou essa sessão. - " + Logger.geradorDatas());
        } catch (Exception e) {
            Logger.escreverLogger("Não foi possível encerrar sessão: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }

}
