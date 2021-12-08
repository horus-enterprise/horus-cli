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

        this.con = new JdbcTemplate(getDataSource());
    }

    public Slack listar(Integer fkEmpresa) {
        String sql = "";

        sql = "SELECT * FROM Slack WHERE fkEmpresa = " + fkEmpresa;

        List<Slack> slack = con.query(sql,
                new BeanPropertyRowMapper(Slack.class));

        return slack.get(0);
    }

    public String alertaOcorrencia(Integer idFuncionario) {
        MonitoramentoHardware m = new MonitoramentoHardware();
        List<MonitoramentoHardware> ocorrencia = new ArrayList();

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

            return String.valueOf(msg);
        }

        return null;
    }
}
