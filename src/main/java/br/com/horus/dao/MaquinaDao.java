package br.com.horus.dao;

import br.com.horus.model.Maquina;
import br.com.horus.utils.Hostname;

import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao extends Dao {

    JdbcTemplate con;

    public MaquinaDao() {
        this.con = new JdbcTemplate(getDataSource());
    }

    public Maquina listar(String hostname, Integer fkEmpresa) {
        List<Maquina> maquina = new ArrayList();

        String sql = "SELECT * FROM Maquina WHERE hostname = '" + hostname
                + "' AND fkEmpresa = " + fkEmpresa;

        maquina = con.query(sql,
                new BeanPropertyRowMapper(Maquina.class));

        return maquina.isEmpty() ? null : maquina.get(0);
    }

    public Boolean validaMaquina() {
        Maquina maquina = listar(Hostname.getHostname(), Session.getFkEmpresa());

        if (maquina != null) {
            return true;
        } else {
            cadastraMaquina();
            return true;
        }
    }

    public void cadastraMaquina() {

        Looca looca = new Looca();
        Double memoriaRam = looca.getMemoria().getTotal() / Math.pow(1024, 3);
        Double tamanhoDisco = looca.getGrupoDeDiscos().getTamanhoTotal() / Math.pow(1024, 3);
        String insertStatement = "insert into Maquina (hostname,fkEmpresa,nomeCpu,modeloDisco,tamanhoDisco,tamanhoRam)"
                + " values(?,?,?,?,?,?);";
        con.update(insertStatement, Hostname.getHostname(), Session.getFkEmpresa(), looca.getProcessador().getNome(),
                looca.getGrupoDeDiscos().getDiscos().get(0).getModelo(), tamanhoDisco, memoriaRam);

    }
}
