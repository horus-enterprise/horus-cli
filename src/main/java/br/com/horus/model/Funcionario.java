package br.com.horus.model;

public class Funcionario {
    private Integer idFuncionario;
    private String nomeFuncionario;
    private String email;
    private String senha;
    private Integer fkEmpresa;
    private Integer fkSupervisor;

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Integer getFkSupervisor() {
        return fkSupervisor;
    }

    public void setFkSupervisor(Integer fkSupervisor) {
        this.fkSupervisor = fkSupervisor;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFuncionario=" + idFuncionario + ", nomeFuncionario=" + nomeFuncionario + ", email=" + email + ", senha=" + senha + ", fkEmpresa=" + fkEmpresa + ", fkSupervisor=" + fkSupervisor + '}';
    }
}
