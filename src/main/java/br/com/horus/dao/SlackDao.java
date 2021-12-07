/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.dao;

import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.model.Slack;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SlackDao extends Dao {

    JdbcTemplate con;

    final Double MAX_USO_RAM = 80.0;
    final Double MAX_USO_DISCO = 80.0;
    final Double MAX_USO_CPU = 80.0;

    public SlackDao() {
        try {
            this.con = new JdbcTemplate(getDataSource());
            Logger.escreverLogger("> Slack conectado ao servidor: horusdb - " + Logger.geradorDatas());
        } catch (CannotGetJdbcConnectionException | UnsupportedOperationException e) {
            Logger.escreverLogger("Impossível conectar o Slack ao servidor: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }

    public Slack listar(Integer fkEmpresa) {
         String sql = "";
        try {
            sql = "SELECT * FROM Slack WHERE fkEmpresa = " + fkEmpresa;

            Logger.escreverLogger("Select do Slack ok. - " + Logger.geradorDatas());
        } catch (Exception e) {
            Logger.escreverLogger("Erro do select Slack: "
                    + e.getMessage() + Logger.geradorDatas());
        }
        List<Slack> slack = con.query(sql,
                new BeanPropertyRowMapper(Slack.class));
        try {
            Logger.escreverLogger("URL Slack: " + slack.get(0).getUrlSlack() + " ok. -" + Logger.geradorDatas());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SlackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slack.get(0);
    }

    public String alertaOcorrencia(Integer idFuncionario) {
        MonitoramentoHardware m = new MonitoramentoHardware();
        List<MonitoramentoHardware> ocorrencia = new ArrayList();
        try {
            String sql = " select * from monitoramentohardware where idOcorrencia ="
                    + "(select max(idOcorrencia) from monitoramentohardware where fkFuncionario =" + idFuncionario + ")";

            ocorrencia = con.query(sql,
                    new BeanPropertyRowMapper(MonitoramentoHardware.class));
            m = ocorrencia.get(0);

            if (m.getCpuUso() >= this.MAX_USO_CPU
                    || m.getDisco() >= this.MAX_USO_DISCO
                    || m.getRam() >= this.MAX_USO_RAM) {
                StringBuilder msg = new StringBuilder();
                msg.append("!!!ATENÇÃO!!!\n\n");
                msg.append("Funcionário: " + Session.getNome() + "\n");
                msg.append("Máquina: " + Hostname.getHostname() + "\n");
                msg.append("Memória utilizada: " + m.getRam() + "%\n");
                msg.append("Armazenamento utilizado: " + m.getDisco() + "%\n");
                msg.append("Uso da CPU: " + m.getCpuUso() + "%\n");

                Logger.escreverLogger("Select do Slack ok. - " + Logger.geradorDatas());
                return String.valueOf(msg);
            }
        } catch (Exception e) {
            Logger.escreverLogger("Impossível envia alerta: "
                    + e.getMessage() + " - "+ Logger.geradorDatas());
        }

        return null;
    }
}
