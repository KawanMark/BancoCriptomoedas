/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.OperacoesDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import view.JanelaDepositar;
import view.JanelaLogin;
import view.JanelaMenu;
import view.JanelaSacar;
import view.JanelaSaldo;
import controller.ControllerSacar;
import model.Carteira;
import model.Cotacao;
import static model.Cotacao.obterCotacaoAtual;
import model.Extrato;
import view.JanelaComprarCripto;
import view.JanelaCotacao;
import view.JanelaVenderCripto;
import DAO.OperacoesDAO;
import view.JanelaExtrato;

/**
 *
 * @author kawan
 */
public class Controller {
    private JanelaLogin login;
    private ClienteDAO clienteDAO;
    private JanelaMenu menu;
    private JanelaSaldo saldo;
    private JanelaSacar sacar;
     private ControllerSacar controllerSacar;
     private Connection conn;
     private JanelaCotacao janelaCotacao;
     private OperacoesDAO operacoesDAO;
     

    public Controller(JanelaLogin login) throws SQLException {
        this.login = login;
        this.conn = Conexao.getConnection();

        this.clienteDAO = new ClienteDAO(Conexao.getConnection());
         this.operacoesDAO = new OperacoesDAO(Conexao.getConnection());
        saldo = null;
    }
    
    
    
     public Controller(JanelaMenu menu) throws SQLException {
        this.menu = menu;
        this.conn = Conexao.getConnection();
        this.clienteDAO = new ClienteDAO(Conexao.getConnection());
        this.janelaCotacao = janelaCotacao;
        this.operacoesDAO = new OperacoesDAO(Conexao.getConnection());
        
   
    }

    public void loginCliente() {
        String cpf = login.getTxtCPF().getText();
        String senha = login.getTxtSenha().getText();
        
        if (senha.length() != 9) {
        JOptionPane.showMessageDialog(login, "Senha necessária: 9 dígitos.");
        return;
    }
        
        try {
            
            ResultSet res = clienteDAO.consultarPorCPFESenha(cpf, senha);
            if (res.next()) {
                JOptionPane.showMessageDialog(login, "Login realizado com sucesso!");
                abrirJanelaMenu();
            } else {
                JOptionPane.showMessageDialog(login, "CPF ou senha incorretos!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(login, "Erro ao fazer login: " + e.getMessage());
        }
    }

    public void entrar() throws SQLException {
        try {
            String cpf = login.getTxtCPF().getText();
            String senha = login.getTxtSenha().getText();
            
            if (senha.length() != 9) {
            JOptionPane.showMessageDialog(login, "Senha necessária: 9 dígitos.");
            return;
        }
            long cpfLong = Long.parseLong(cpf);
            long senhaLong = Long.parseLong(senha);
            if (entrar(cpfLong, senhaLong)) {
                abrirJanelaMenu();
            } else {
                JOptionPane.showMessageDialog(login, "CPF ou senha incorretos!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(login, "Use apenas números para o CPF e senha!");
        }
    }

    private boolean entrar(long cpf, long senha) {
        
        return false;
    }
    
      public boolean verificarCredenciais(String cpf, String senha) {
        return clienteDAO.verificarCredenciais(cpf, senha);
    }

    public void abrirJanelaMenu() throws SQLException {
        if (menu == null) {
            menu = new JanelaMenu(conn);
        }
        menu.setVisible(true);
        login.setVisible(false);
    }

    public void fecharJanela() {
        if (menu != null) {
            menu.dispose();
        }
        if (login != null) {
            login.dispose();
        }
    }
    
    
    
public void abrirJanelaSaldo() {
        ControllerSaldo controllerSaldo = new ControllerSaldo(clienteDAO, new JanelaSaldo(clienteDAO));
        controllerSaldo.consultarSaldo();
}
public void abrirJanelaDeposito() {
    try {
        // Obtenha a conexão usando a classe Conexao
        Connection conn = Conexao.getConnection();
        

        // Crie a janela de depósito e o controlador de depósito
   
        JanelaDepositar janelaDepositar = new JanelaDepositar(conn);
        OperacoesDAO operacoesDAO = new OperacoesDAO(conn);
        ControllerDepositar controllerDepositar = new ControllerDepositar(janelaDepositar.clienteDAO, janelaDepositar, operacoesDAO);

        // Exiba a janela de depósito
        janelaDepositar.setVisible(true);
    } catch (SQLException e) {
        e.printStackTrace();
        // Trate o erro de conexão com o banco de dados adequadamente
    }
}


    // Outros métodos...
    
  public void abrirJanelaSaque() {
        if (controllerSacar == null) {
            // Se a instância de ControllerSacar ainda não foi criada, você pode criar aqui
            try {
                // Obtenha a conexão usando a classe Conexao
                Connection conn = Conexao.getConnection();

                // Crie a janela de saque
                JanelaSacar janelaSacar = new JanelaSacar(conn);

                // Crie a instância do ControllerSacar
                controllerSacar = new ControllerSacar(conn, janelaSacar);
            } catch (SQLException e) {
                e.printStackTrace();
                // Trate o erro de conexão com o banco de dados adequadamente
            }
        }

        // Agora, você pode chamar o método abrirSaque do ControllerSacar
        controllerSacar.abrirSaque();
    }
  
public void abrirJanelaComprarCripto(String cpf, String senha) {
    boolean credenciaisValidas = verificarCredenciais(cpf, senha);

    if (credenciaisValidas) {
        try {
              Cotacao cotacao = obterCotacaoAtual(conn);
            // Retrieve wallet balances from the database using ClienteDAO
            double saldoBitcoin = clienteDAO.consultarSaldo(cpf, "Bitcoin");
            double saldoEthereum = clienteDAO.consultarSaldo(cpf, "Ethereum");
            double saldoRipple = clienteDAO.consultarSaldo(cpf, "Ripple");
            double saldoReais = clienteDAO.consultarSaldo(cpf, "Reais");

            // Create an instance of the Carteira class with the balances retrieved from the database
            Carteira carteira = new Carteira(saldoBitcoin, saldoEthereum, saldoRipple, saldoReais);

            // Create an instance of the cryptocurrency purchase window, passing the connection, wallet, CPF, and password
            JanelaComprarCripto janelaComprarCripto = new JanelaComprarCripto(Conexao.getConnection(), carteira, cotacao);

            // Display the window
            janelaComprarCripto.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving wallet balances from the database.");
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Invalid CPF or password. Please try again.");
    }
}

    public void abrirJanelaCotacao() throws SQLException {
        JanelaCotacao janelaCotacao = new JanelaCotacao();
        janelaCotacao.setVisible(true);
    }
    
    public void abrirJanelaVenderCripto(String cpf, String senha) {
    boolean credenciaisValidas = verificarCredenciais(cpf, senha);

    if (credenciaisValidas) {
        try {
            // Obter a cotação atual
            Cotacao cotacao = obterCotacaoAtual(conn);
            
            // Obter os saldos das moedas e do saldo em reais do banco de dados usando o ClienteDAO
            double saldoBitcoin = clienteDAO.consultarSaldo(cpf, "Bitcoin");
            double saldoEthereum = clienteDAO.consultarSaldo(cpf, "Ethereum");
            double saldoRipple = clienteDAO.consultarSaldo(cpf, "Ripple");
            double saldoReais = clienteDAO.consultarSaldo(cpf, "Reais");

            // Criar uma instância da classe Carteira com os saldos obtidos do banco de dados
            Carteira carteira = new Carteira(saldoBitcoin, saldoEthereum, saldoRipple, saldoReais);

            // Criar uma instância da janela de venda de criptomoedas, passando a conexão, a carteira e a cotação
            JanelaVenderCripto janelaVenderCripto = new JanelaVenderCripto(Conexao.getConnection(), carteira, cotacao);

            // Exibir a janela
            janelaVenderCripto.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar os saldos da carteira do banco de dados.");
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "CPF ou senha inválidos. Por favor, tente novamente.");
    }
  
}
    
    public void abrirJanelaExtrato(String cpf, String senha) {
    boolean credenciaisValidas = verificarCredenciais(cpf, senha);

    if (credenciaisValidas) {
        try {
            // Consultar extrato do cliente
            Extrato extrato = operacoesDAO.consultarExtrato(cpf);

            // Criar uma instância da janela de extrato, passando o extrato como parâmetro
            JanelaExtrato janelaExtrato = new JanelaExtrato(extrato, cpf);
            
            // Exibir a janela de extrato
            janelaExtrato.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato do cliente.");
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "CPF ou senha inválidos. Por favor, tente novamente.");
    }
}


}

