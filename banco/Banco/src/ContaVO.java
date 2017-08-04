import java.text.DecimalFormat;

public class ContaVO {
    //instanciando DecimalFormal para 2 casas decimais
    public static DecimalFormat fmtMoeda = new DecimalFormat("0.00");
    //Declaração dos atributos
    public static String nomeTitular, cpfTitular;
    public static String numeroConta, senha;
    public static float saldo, limiteSaldo, limiteSaque;
    public static String extrato;
    //Método Construtor
    public ContaVO(){
        nomeTitular = "João Ortiz";
        cpfTitular = "123.456.789-00";
        numeroConta = "1365-0";
        senha = "1234";
        saldo = 0;
        limiteSaldo = -1000;
        limiteSaque = 600;
        extrato = "Extrato Bancário: " + nomeTitular.toUpperCase() + "\n\n";
    }//Fechando construtor
    //Métodos Funcionais
    public static boolean validarUsuario(String tmpConta, String tmpSenha){
    /*As variáveis tnoConta e tmpSenha são os valores
    digitados pelo usuário nos campos de texto da tela*/
        if(tmpConta.compareTo(numeroConta) == 0 && tmpSenha.compareTo(senha) == 0){
            //Dados OK
            return true;
        }else{
            //Dados inválidos
            return false;
        }//fechando if...else
        
        
    }//fechando validarUsuario
    
    public static float consultarSaldo(){
        //tal método é responsável apenas por retornar
        //o valor do atributo saldo no momento de sua
        //chamada
        return saldo;
    }
    
    public static void efetuarDeposito(float tmpValor){
        saldo += tmpValor;
        extrato +="Depósito efetuado no valor de: R$ " + fmtMoeda.format(tmpValor) + "\n";
    }
    
    public static int efetuarSaque(float tmpValor){
        if(saldo-tmpValor<limiteSaldo){//saldo insuficiente
            return 2;
        }
        else if(tmpValor>limiteSaque){//valor de saque excedido
            return 3;
        }
        else{//rolou
            saldo-=tmpValor;
            extrato +="Saque efetuado no valor de: R$ " + fmtMoeda.format(tmpValor) + "\n";
            return 1;
        }
    }
    
    public static String visualizarExtrato(){
        return extrato;
    }
}//fechando classes
