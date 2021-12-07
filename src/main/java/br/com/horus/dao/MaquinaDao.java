package br.com.horus.dao;

import br.com.horus.model.Maquina;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao extends Dao {

    JdbcTemplate con;

    public MaquinaDao() {
        try {
            this.con = new JdbcTemplate(getDataSource());
            Logger.escreverLogger("> Máquina conectada ao servidor: horusdb - " + Logger.geradorDatas());
        } catch (CannotGetJdbcConnectionException | UnsupportedOperationException e) {
            Logger.escreverLogger("Impossível conectar a máquina ao servidor: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }

    public Maquina listar(String hostname, Integer fkEmpresa) {
        List<Maquina> maquina = new ArrayList();
        try {
            String sql = "SELECT * FROM Maquina WHERE hostname = '" + hostname
                    + "' AND fkEmpresa = " + fkEmpresa;
            Logger.escreverLogger("Select da máquina: " + Hostname.getHostname() + " ok. - " + Logger.geradorDatas());

            maquina = con.query(sql,
                    new BeanPropertyRowMapper(Maquina.class));
        } catch (Exception ex) {
            Logger.escreverLogger("Impossível buscar máquina: "
                    + ex.getMessage() + " - " + Logger.geradorDatas());
        }
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
        try {
            Looca looca = new Looca();
            Double memoriaRam = looca.getMemoria().getTotal() / Math.pow(1024, 3);
            Double tamanhoDisco = looca.getGrupoDeDiscos().getTamanhoTotal() / Math.pow(1024, 3);
            String insertStatement = "insert into Maquina (hostname,fkEmpresa,nomeCpu,modeloDisco,tamanhoDisco,tamanhoRam)"
                    + " values(?,?,?,?,?,?);";
            con.update(insertStatement, Hostname.getHostname(), Session.getFkEmpresa(), looca.getProcessador().getNome(),
                    looca.getGrupoDeDiscos().getDiscos().get(0).getModelo(), tamanhoDisco, memoriaRam);

            Logger.escreverLogger("> Nova máquina cadastrada." + Logger.geradorDatas());
        } catch (Exception e) {
            Logger.escreverLogger("Algum erro em cadastrar a máquina: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }
}
