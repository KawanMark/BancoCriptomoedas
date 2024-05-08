/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package banco;

import java.sql.SQLException;
import view.JanelaLogin;

/**
 *
 * @author kawan
 */
public class Banco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       JanelaLogin J = new JanelaLogin();
       J.setVisible(true);
    }
    
}
