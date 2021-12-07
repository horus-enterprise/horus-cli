/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.model;

/**
 *
 * @author Anderson
 */
public class Slack {
    private Integer idSlack;
    private String urlSlack;
    private Integer fkEmpresa;

    public Integer getIdSlack() {
        return idSlack;
    }

    public void setIdSlack(Integer idSlack) {
        this.idSlack = idSlack;
    }

    public String getUrlSlack() {
        return urlSlack;
    }

    public void setUrlSlack(String urlSlack) {
        this.urlSlack = urlSlack;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
    
}
