package br.com.horus.dao;

import br.com.horus.model.Funcionario;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class FuncionarioDao extends Dao {

    JdbcTemplate con;

    public FuncionarioDao() {

        this.con = new JdbcTemplate(getDataSource());
    }

    public Funcionario listar(String email, String senha) {
        List<Funcionario> funcionario = new ArrayList();

        String sql = "SELECT * FROM Funcionario WHERE email = ? AND senha = ?";
        funcionario = con.query(sql,
                new BeanPropertyRowMapper(Funcionario.class), email, senha);

        if (funcionario.isEmpty()) {
            return null;
        }
        return funcionario.get(0);
    }
}
