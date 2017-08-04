import javax.swing.*;
public class LoginView {
    public static ContaVO dadosLogin = new ContaVO();
    public LoginView(){
        String conta, senha;
        int erros = 0;
        JOptionPane.showMessageDialog(null, "Banco CIC", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        while(erros < 3){
            conta = JOptionPane.showInputDialog("Entre com o número da conta: ");
            senha = JOptionPane.showInputDialog("Entre com a senha da conta: ");
            if (dadosLogin.validarUsuario(conta, senha) == true){
                JOptionPane.showMessageDialog(null, "Seja bem vindo " + dadosLogin.nomeTitular);
                break;
            }else{
                erros++;
                JOptionPane.showMessageDialog(null, "Dados inválidos. Tentativas restantes: " + (3-erros), "ERRO", JOptionPane.ERROR_MESSAGE);
            }//Fechando if...else
        }//Fechando while
        if(erros == 3){
            JOptionPane.showMessageDialog(null, "Sistema bloqueado ", "ERRO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);//encerra o programa
        }
        //instanciando a tela das funções bancárias
        MenuView telaMenu = new MenuView();
        
    }//fechando construtor
}//fechando classe
