package br.com.horus.dao;

import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import java.io.IOException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;

public class MonitoramentoHardwareDao extends Dao {

    JdbcTemplate con;

    public MonitoramentoHardwareDao() {
        try {
            this.con = new JdbcTemplate(getDataSource());
            Logger.escreverLogger("> Monitoramento hardware conectado ao servidor: horusdb - " + Logger.geradorDatas());
        } catch (CannotGetJdbcConnectionException e) {
            Logger.escreverLogger("Impossível conectar o monitoramento hardware ao servidor: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }

    public void enviar(MonitoramentoHardware ocorrencia) throws IOException {
        try {
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
            Logger.escreverLogger("> Insert monitoramento ok. - " + Logger.geradorDatas());
        } catch (Exception e) {
            Logger.escreverLogger("Impossível fazer insert: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }
}
