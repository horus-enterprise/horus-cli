package br.com.horus.dao;

import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.utils.Logger;
import java.io.IOException;
import org.springframework.jdbc.core.JdbcTemplate;

public class MonitoramentoHardwareDao extends Dao {

    JdbcTemplate con;

    public MonitoramentoHardwareDao() {
        this.con = new JdbcTemplate(getDataSource());
    }

    public void enviar(MonitoramentoHardware ocorrencia) {
        StringBuilder sql = new StringBuilder();
        try{
        sql.append("INSERT INTO MonitoramentoHardware (");
        sql.append("fkMaquina, cpuUso, cpuTemperatura, disco, ram, uptime)");
        sql.append("VALUES (");
        sql.append(ocorrencia.getFkMaquina() + ",");
        sql.append(ocorrencia.getCpuUso() + ",");
        sql.append(ocorrencia.getCpuTemperatura() + ",");
        sql.append(ocorrencia.getDisco() + ",");
        sql.append(ocorrencia.getRam() + ",");
        sql.append(ocorrencia.getUptime());
        sql.append(");");
        Logger.escreverLogger("> Insert monitoramento ok. - INSERT INTO MonitoramentoHardware ( " + Logger.geradorDatas());
        }catch(IOException e){
            Logger.loggerException(e);
        }
        con.execute(sql.toString());
    }
}
