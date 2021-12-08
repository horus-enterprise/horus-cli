package br.com.horus.main;

import br.com.horus.dao.FuncionarioDao;
import br.com.horus.dao.MaquinaDao;
import br.com.horus.dao.MonitoramentoHardwareDao;
import br.com.horus.dao.SlackDao;
import br.com.horus.model.Funcionario;
import br.com.horus.model.Maquina;
import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.model.Slack;
import br.com.horus.utils.ConexaoSlack;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        String email, senha;
        
        System.out.println("=====================");
        System.out.println("Horus Application CLI");
        System.out.println("=====================");

        Scanner input = new Scanner(System.in);
        System.out.println("Insira seu email:");
        email = input.nextLine();
        System.out.println("Insira sua senha:");
        senha = input.nextLine();

        FuncionarioDao funcionarioDAO = new FuncionarioDao();
        Funcionario funcionario = funcionarioDAO.listar(email, senha);
        MaquinaDao maquinaDAO = new MaquinaDao();

        if (funcionario != null) {

            Session.criarSessao(
                    funcionario.getNomeFuncionario(),
                    funcionario.getEmail(),
                    funcionario.getFkEmpresa(),
                    funcionario.getIdFuncionario()
            );

            SlackDao slackDAO = new SlackDao();
            Slack slc = slackDAO.listar(Session.getFkEmpresa());
            ConexaoSlack.setURL(slc.getUrlSlack());

            maquinaDAO.validaMaquina();
            System.out.println("Maquina validada!");

            try {
                System.out.println("mensagem slack");
                ConexaoSlack.mensagemInicial();

            } catch (Exception e) {
            }
            
            final long segundos = (1000);

            Timer tempo = new Timer();
            
  
            TimerTask monitoramento = new TimerTask() {
             
                @Override
                public void run() {
                    Session.setUptime(Session.getUptime() + 1);
                    

                    if (Session.getUptime() % 15 == 0) {
                        try {
                            
                            start();
                        } catch (IOException | InterruptedException ex) {

                        }
                    }
                }
            };

            tempo.scheduleAtFixedRate(monitoramento, 1, segundos);
            

        } else {
            System.out.println("E-mail ou senha incorretos!\nVerifique e tente novamente.");
            return;
        }

        System.out.println("Login bem sucedido!\nAperte CTRL + C para parar o programa.");
    }

    public static void start() throws IOException, InterruptedException {
        MaquinaDao maquinaDAO = new MaquinaDao();

        ConexaoSlack.enviarAlerta();

        Maquina maquina = maquinaDAO.listar(Hostname.getHostname(), Session.getFkEmpresa());

        Looca looca = new Looca();

        MonitoramentoHardware ocorrencia = new MonitoramentoHardware();

        ocorrencia.setFkMaquina(maquina.getIdMaquina());

        Double cpuUso = looca.getProcessador().getUso();

        ocorrencia.setCpuUso(cpuUso);

        ocorrencia.setCpuTemperatura(looca.getTemperatura().getTemperatura());

        Double volumeTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal() / Math.pow(1024, 3);
        Double volumeDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / Math.pow(1024, 3);
        Double volumeUso = volumeTotal - volumeDisponivel;

        Double percentVolumeUso = volumeUso * 100.0 / volumeTotal;
        ocorrencia.setDisco(percentVolumeUso);

        Double totalRam = looca.getMemoria().getTotal() / Math.pow(1024, 3);
        Double ramEmUso = looca.getMemoria().getEmUso() / Math.pow(1024, 3);

        Double usoRam = ((ramEmUso * 100) / totalRam);
        ocorrencia.setRam(usoRam);

        Long uptime = looca.getSistema().getTempoDeAtividade();
        ocorrencia.setUptime(uptime);

        MonitoramentoHardwareDao monitoramentoHardwareDAO = new MonitoramentoHardwareDao();
        monitoramentoHardwareDAO.enviar(ocorrencia);

    }
}
