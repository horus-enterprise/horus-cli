package br.com.horus.model;

public class Maquina {
    private Integer idMaquina;
    private Integer fkEmpresa;
    private String hostname;
    private String nomeCpu;
    private String modeloDisco;
    private Double tamanhoDisco;
    private Double tamanhoRam;

    public Double getTamanhoDisco() {
        return tamanhoDisco;
    }

    public void setTamanhoDisco(Double tamanhoDisco) {
        this.tamanhoDisco = tamanhoDisco;
    }

    public Double getTamanhoRam() {
        return tamanhoRam;
    }

    public void setTamanhoRam(Double tamanhoRam) {
        this.tamanhoRam = tamanhoRam;
    }
    

    public String getNomeCpu() {
        return nomeCpu;
    }

    public void setNomeCpu(String nomeCpu) {
        this.nomeCpu = nomeCpu;
    }

    public String getModeloDisco() {
        return modeloDisco;
    }

    public void setModeloDisco(String modeloDisco) {
        this.modeloDisco = modeloDisco;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    @Override
    public String toString() {
        return "Maquina{" + "idMaquina=" + idMaquina + ", fkEmpresa=" + fkEmpresa + ", hostname=" + hostname + '}';
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
