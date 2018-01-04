import javax.swing.*;
public class MenuView {
    public MenuView(){
        //instanciando objeto da classe Conta Vo para
        //acesso a seus métodos e atributos (dadosConta)
        ContaVO dadosConta = new ContaVO();
        String msgMenu = "Entre com a opção desejada: \n\n";
        int opcao = 0;
        msgMenu+="1 - Consultar saldo\n";
        msgMenu+="2 - Visualizar extrato\n";
        msgMenu+="3 - Efetuar depósito\n";
        msgMenu+="4 - Realizar saque\n";
        msgMenu+="5 - Sair\n";
        do{
            opcao = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
            if (opcao == 1){
                //saldo
                JOptionPane.showMessageDialog(null, "Saldo Atual: R$" 
                        + dadosConta.fmtMoeda.format(dadosConta.consultarSaldo()), 
                        "BANCO CIC",
                        JOptionPane.WARNING_MESSAGE);
            }else if(opcao==2){
                //extrato
                JOptionPane.showMessageDialog
                (null, dadosConta.visualizarExtrato(), "BANCO CIC", JOptionPane.WARNING_MESSAGE);
            }else if(opcao==3){
                //depósito
                float vDeposito = Float.parseFloat(JOptionPane.showInputDialog(
                        "Entre com o valor do depósito: "));
                
                //efetuando chamada do método depósito (ContaVO)
                dadosConta.efetuarDeposito(vDeposito);
                JOptionPane.showMessageDialog(null, "Depósito de R$ " + 
                        dadosConta.fmtMoeda.format(vDeposito));
                        
            }else if(opcao==4){
                //saque
                float vSaque = Float.parseFloat(
                        JOptionPane.showInputDialog(
                        "Entre com o valor do saque: "));
                int statusSaque = dadosConta.efetuarSaque(vSaque);
                
                if (statusSaque==1){
                    JOptionPane.showMessageDialog(null, "Saque efetuado de R$ " + 
                            dadosConta.fmtMoeda.format(vSaque));
                }
                else if(statusSaque == 2){
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
                }else{
                     JOptionPane.showMessageDialog(null, "Limite de Saque excedido!");
                }
            }else if(opcao > 5 || opcao < 1){
                JOptionPane.showMessageDialog(null, "Opção Inválida !");
            }
        }while(opcao != 5);
    }//fechando construtor
    
}//fechando classe
