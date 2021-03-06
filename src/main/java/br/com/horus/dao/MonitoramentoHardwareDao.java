package br.com.horus.dao;

import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import java.io.IOException;
import org.springframework.jdbc.core.JdbcTemplate;

public class MonitoramentoHardwareDao extends Dao {

    JdbcTemplate con;

    public MonitoramentoHardwareDao() {

        this.con = new JdbcTemplate(getDataSource());

    }

    public void enviar(MonitoramentoHardware ocorrencia) throws IOException {

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO MonitoramentoHardware (");
        sql.append("fkMaquina, cpuUso, cpuTemperatura, disco, ram, uptime, fkFuncionario)");
        sql.append("VALUES (");
        sql.append(ocorrencia.getFkMaquina() + ",");
        sql.append(ocorrencia.getCpuUso() + ",");
        sql.append(ocorrencia.getCpuTemperatura() + ",");
        sql.append(ocorrencia.getDisco() + ",");
        sql.append(ocorrencia.getRam() + ",");
        sql.append(ocorrencia.getUptime() + ",");
        sql.append(Session.getIdFuncionario());
        sql.append(");");
        con.execute(sql.toString());

    }
}
