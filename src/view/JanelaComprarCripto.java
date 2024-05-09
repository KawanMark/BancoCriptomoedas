    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ClienteDAO;
import controller.ControllerCriptomoeda;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Carteira;
import model.Cotacao;

/**
 *
 * @author kawan
 */
public class JanelaComprarCripto extends javax.swing.JFrame {
    
    private ClienteDAO clienteDAO;
    private Connection conn;
    private Carteira carteira;
     private Cotacao cotacao;
     
  


    /**
     * Creates new form JanelaComprarCripto
     */
    public JanelaComprarCripto(Connection conn, Carteira carteira, Cotacao cotacao) {
        this.conn = conn;
        this.carteira = carteira;
        this.cotacao = cotacao; // Aqui você inicializa cotacao
        c = new ControllerCriptomoeda(conn, this, carteira, cotacao); // Aqui você passa a instância de cotacao para o construtor de ControllerCriptomoeda
        initComponents();
}
    
    

    public ControllerCriptomoeda getC() {
        return c;
    }

    public void setC(ControllerCriptomoeda c) {
        this.c = c;
    }

    public ButtonGroup getCriptos() {
        return Criptos;
    }

    public void setCriptos(ButtonGroup Criptos) {
        this.Criptos = Criptos;
    }

    public JButton getBtComprar() {
        return btComprar;
    }

    public void setBtComprar(JButton btComprar) {
        this.btComprar = btComprar;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTextArea getLblComprar() {
        return lblComprar;
    }

    public void setLblComprar(JTextArea lblComprar) {
        this.lblComprar = lblComprar;
    }

    public JRadioButton getRbBitcoin() {
        return rbBitcoin;
    }

    public void setRbBitcoin(JRadioButton rbBitcoin) {
        this.rbBitcoin = rbBitcoin;
    }

    public JRadioButton getRbEthereum() {
        return rbEthereum;
    }

    public void setRbEthereum(JRadioButton rbEthereum) {
        this.rbEthereum = rbEthereum;
    }

    public JRadioButton getRbRipple() {
        return rbRipple;
    }

    public void setRbRipple(JRadioButton rbRipple) {
        this.rbRipple = rbRipple;
    }

    public JTextField getTxtComprar() {
        return txtComprar;
    }

    public void setTxtComprar(JTextField txtComprar) {
        this.txtComprar = txtComprar;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Criptos = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtComprar = new javax.swing.JTextField();
        btComprar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblComprar = new javax.swing.JTextArea();
        rbBitcoin = new javax.swing.JRadioButton();
        rbEthereum = new javax.swing.JRadioButton();
        rbRipple = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel1.setText("Comprar Criptomoedas");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Digite a quantidade que deseja comprar :");

        btComprar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btComprar.setText("Comprar");
        btComprar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComprarActionPerformed(evt);
            }
        });

        lblComprar.setColumns(20);
        lblComprar.setRows(5);
        jScrollPane1.setViewportView(lblComprar);

        rbBitcoin.setText("Bitcoin");
        rbBitcoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBitcoinActionPerformed(evt);
            }
        });

        rbEthereum.setText("Ethereum");

        rbRipple.setText("Ripple");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(btComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtComprar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbBitcoin)
                                    .addComponent(rbRipple)
                                    .addComponent(rbEthereum))
                                .addGap(0, 71, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtComprar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbBitcoin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbEthereum))
                    .addComponent(btComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbRipple)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btComprarActionPerformed
     if (!rbBitcoin.isSelected() && !rbEthereum.isSelected() && !rbRipple.isSelected()) {
        JOptionPane.showMessageDialog(this, "Selecione uma criptomoeda para comprar.");
        return;
    }


     

    // Obtém o valor digitado para compra
    double valorCompra;
    try {
        valorCompra = Double.parseDouble(txtComprar.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Digite um valor numérico válido para a compra.");
        return;
    }

    // Verifica se o valor digitado é válido
    if (valorCompra <= 0) {
        JOptionPane.showMessageDialog(this, "Digite um valor válido para a compra.");
        return;
    }
    
    
    // Obtém a criptomoeda selecionada
    String moedaSelecionada = "";
    if (rbBitcoin.isSelected()) {
        moedaSelecionada = "Bitcoin";
    } else if (rbEthereum.isSelected()) {
        moedaSelecionada = "Ethereum";
    } else if (rbRipple.isSelected()) {
        moedaSelecionada = "Ripple";
    }

    // Obtém a cotação atual da criptomoeda selecionada
    double cotacaoAtual = cotacao.getCotacao(moedaSelecionada);
    


    // Calcula a quantidade de criptomoeda a comprar
    double quantidade = valorCompra / cotacaoAtual;

    
    //JOptionPane.showMessageDialog(this, 
    //    String.format("Compra realizada com sucesso!\nData e Hora: %s\nMoeda: %s\nQuantidade: %.8f\nCotação Atual: %.2f",
    //        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
    //        moedaSelecionada,
    //        quantidade,
     //       cotacaoAtual));
    



    // Chama o método do controller para comprar a moeda
    c.comprarMoeda(quantidade, moedaSelecionada,"54181947807");

    }//GEN-LAST:event_btComprarActionPerformed

    private void rbBitcoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBitcoinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbBitcoinActionPerformed

    /**
     * @param args the command line arguments
     */
    ControllerCriptomoeda c;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Criptos;
    private javax.swing.JButton btComprar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lblComprar;
    private javax.swing.JRadioButton rbBitcoin;
    private javax.swing.JRadioButton rbEthereum;
    private javax.swing.JRadioButton rbRipple;
    private javax.swing.JTextField txtComprar;
    // End of variables declaration//GEN-END:variables
}
