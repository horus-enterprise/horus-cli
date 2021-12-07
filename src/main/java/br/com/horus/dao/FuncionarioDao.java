package br.com.horus.dao;

import br.com.horus.model.Funcionario;
import br.com.horus.utils.Logger;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class FuncionarioDao extends Dao {

    JdbcTemplate con;

    public FuncionarioDao() {
        try {
            this.con = new JdbcTemplate(getDataSource());
            Logger.escreverLogger("> Funcionário conectado ao servidor: horusdb - " + Logger.geradorDatas());
        } catch (CannotGetJdbcConnectionException | UnsupportedOperationException e) {
            Logger.escreverLogger("Impossível conectar o funcionário ao servidor: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }

    public Funcionario listar(String email, String senha) {
        List<Funcionario> funcionario = new ArrayList();

        try {
            String sql = "SELECT * FROM Funcionario WHERE email = ? AND senha = ?";
            funcionario = con.query(sql,
                    new BeanPropertyRowMapper(Funcionario.class), email, senha);
            Logger.escreverLogger("> Select Funcionário: " + funcionario.get(0).getNomeFuncionario() + " ok. - " + Logger.geradorDatas());
        } catch (Exception e) {
            Logger.escreverLogger("Impossível mostrar funcionário: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }

        if (funcionario.isEmpty()) {
            return null;
        }
        return funcionario.get(0);
    }

    public void redefinirSenha(String email, String senha, String novaSenha) {
        try {
            Funcionario funcionario = listar(email, senha);
            if (funcionario != null) {
                con.update("UPDATE Funcionario SET senha = ? WHERE idFuncionario = ?", novaSenha, funcionario.getIdFuncionario());
                showMessageDialog(null, "Senha atualizada com sucesso!");
            } else {
                showMessageDialog(null, "Os dados informados estão incorreto!\n"
                        + "Verifique e tente novamente ou contate seu adiministrador.");
            }
            Logger.escreverLogger("O funcionário: " + funcionario.getNomeFuncionario()
                    + "Redefiniu a senha. - " + Logger.geradorDatas());
        } catch (Exception e) {
            Logger.escreverLogger("Impossível redefinir senha: "
                    + e.getMessage() + " - " + Logger.geradorDatas());
        }
    }
}
