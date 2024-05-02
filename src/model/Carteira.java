/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Carteira {
    private Moedas reais,ethereum,ripple,bitcoin;

    public Carteira() {
        reais = new Reais();
        ethereum = new Ethereum();
        ripple = new Ripple();
        bitcoin = new Bitcoin();
        
    }
    
}
