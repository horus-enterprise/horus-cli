/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.dao;

import br.com.horus.model.Slack;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Anderson
 */
public class SlackDao extends Dao{
     JdbcTemplate con;

     public SlackDao(){
         this.con = new JdbcTemplate(getDataSource());
     }
     
     public Slack listar(Integer fkEmpresa){
         String sql = "";
        try {
            sql = "SELECT * FROM Maquina WHERE fkEmpresa = '" + Session.getFkEmpresa();
            
             Logger.escreverLogger("Select do Slack ok. - "+ Logger.geradorDatas());
        } catch (IOException e) {
            Logger.loggerException(e);
        }
        List<Slack> slack = con.query(sql,
                new BeanPropertyRowMapper(Slack.class));
        try {
            Logger.escreverLogger("URL Slack: "+ slack.get(0).getUrl() + " ok. -"+ Logger.geradorDatas());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(SlackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slack.isEmpty() ? null : slack.get(0);
     }
}
