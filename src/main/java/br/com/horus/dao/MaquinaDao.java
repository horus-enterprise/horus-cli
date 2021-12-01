package br.com.horus.dao;

import br.com.horus.model.Maquina;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao extends Dao {

    JdbcTemplate con;

    public MaquinaDao() {
        this.con = new JdbcTemplate(getDataSource());
    }

    public Maquina listar(String hostname, Integer fkEmpresa) {
        String sql = "";
        try {
            sql = "SELECT * FROM Maquina WHERE hostname = '" + hostname
                    + "' AND fkEmpresa = " + fkEmpresa;
            Logger.escreverLogger("Select da máquina ok. - " + Logger.geradorDatas());
        } catch (IOException e) {
            Logger.loggerException(e);
        }
        List<Maquina> maquina = con.query(sql,
                new BeanPropertyRowMapper(Maquina.class));
        try {
            Logger.escreverLogger("Nome da máquina: " + Hostname.getHostname() + " ok. -" + Logger.geradorDatas());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MaquinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maquina.isEmpty() ? null : maquina.get(0);
    }

    public Boolean validaMaquina() {
        Maquina maquina = listar(Hostname.getHostname(), Session.getFkEmpresa());

        if (maquina != null) {
            System.out.println("validada");
            return true;
        } else {
            System.out.println("Vai ser cadastrada");
            cadastraMaquina();
            return true;
        }
    }

    public void cadastraMaquina() {
         try {
            Looca looca = new Looca();
            Double memoriaRam = looca.getMemoria().getTotal()/ Math.pow(1024, 3);
            Double tamanhoDisco = looca.getGrupoDeDiscos().getTamanhoTotal() / Math.pow(1024, 3);
            String insertStatement = "insert into Maquina (hostname,fkEmpresa,nomeCpu,modeloDisco,tamanhoDisco,tamanhoRam)"
                    + " values(?,?,?,?,?,?);";
            con.update(insertStatement, Hostname.getHostname(), Session.getFkEmpresa(), looca.getProcessador().getNome(),
                    looca.getGrupoDeDiscos().getDiscos().get(0).getModelo(), tamanhoDisco, memoriaRam);
            System.out.println("Nova maquina cadastrada!");
             Logger.escreverLogger("> Nova máquina cadastrada."+ Logger.geradorDatas());
        } catch (IOException e) {
            Logger.loggerException(e);
        }
    }
}
