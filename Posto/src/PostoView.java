/**
 *
 * @author Vinnicius Brito; André Cruz; Andre Augusto
 */

import javax.swing.*;//pacote de elementos graficos

public class PostoView {
    
    public static PostoVO objPosto = new PostoVO();
     
    public PostoView(){
        int opcao;
        String msgMenu = "";
        msgMenu += "Entre com a opção desejada: \n";
        msgMenu += "1 - Fornecimento\n";
        msgMenu += "2 - Vendas\n";
        msgMenu += "3 - Relatório\n";
        msgMenu += "4 - Sair\n";
         do{
            opcao = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
            switch(opcao){
                case 1:
                    //fornecimento
                    fornecimento();
                    break;
                case 2:
                    vendas();//pronto                   
                    break;
                case 3: 
                    //relatorio
                    String relatorio = objPosto.relatorio();
                    JOptionPane.showMessageDialog(null, relatorio);
                    break;
                case 4:
                    //sair
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!!! ");
                    break;
            }//fechamento switch
        }while(opcao!=4);//fechamento do while
        
    }//fechamento postoView
    
    public static void fornecimento(){
        int tmpComb,forneca, tmpLitros;
        tmpComb = Integer.parseInt(JOptionPane.showInputDialog
        ( "Selecione o combustível a ser reposto: \n"
        + "1 - Etanol - R$ 1,19\n"
        + "2 - Gasolina Comum - R$ 2,19\n"
        + "3 - Gasolina Aditivada - R$ 2,29\n"
        + "4 - Diesel - R$ 1,39"));
        tmpLitros=Integer.parseInt(JOptionPane.showInputDialog
        ( "Digite a quantidade de litros: "));
        forneca = objPosto.fornecer(tmpComb-1,tmpLitros);
        
        if(forneca == 0){
            JOptionPane.showMessageDialog
            (null, "Limite de capacidade excecida !");
        }else{
            JOptionPane.showMessageDialog(null, 
            "Litros: " + tmpLitros + "\nCombustível: " + objPosto.tipos[tmpComb-1]
            + String.format("\nTotal: R$ %.2f", objPosto.custo[tmpComb-1]*tmpLitros));
        }
        
    }
    
    public static void vendas(){
        int opcaoV;
        String msgMenu = "";
        msgMenu += "Selecione o serviço desejado: \n";
        msgMenu += "1 - Abastecimento\n";
        msgMenu += "2 - Serviços Adicionais\n";
        msgMenu += "3 - Sair\n";
        do{
            opcaoV = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
            switch(opcaoV){
                case 1:
                    //abastecimento
                    abastecimento();
                    break;
                case 2:
                    //serviços adicionais
                    adicionais();
                    break;
                case 3:
                    //sair
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!!! ");
                    break;
            }//fechamento switch
        }while(opcaoV!=3);//fechamento do while
    }
    
    public static void abastecimento(){
        int abasteca,tmpComb, tmpLitros;
        
        tmpComb=Integer.parseInt(JOptionPane.showInputDialog
        ( "Selecione o combústivel desejado: \n"
        + "1 - Etanol - R$ 2,39\n"
        + "2 - Gasolina Comum - R$ 3,49\n"
        + "3 - Gasolina Aditivada - R$ 3,69\n"
        + "4 - Diesel - R$ 2,89"));
        tmpLitros=Integer.parseInt(JOptionPane.showInputDialog
        ( "Digite a quantidade de litros: "));
        abasteca = objPosto.abastecer(tmpComb-1,tmpLitros);
        
        if (abasteca==0){
            JOptionPane.showMessageDialog(null, "Não foi possível o abastecimento "
            +"pois não possuimos combustível suficiente");
        }else{
            JOptionPane.showMessageDialog(null, "Litros: " + tmpLitros+
            "\nCombustível: "+ objPosto.tipos[tmpComb-1] 
            + String.format("\nTotal: R$ %.2f", objPosto.venda[tmpComb-1]*tmpLitros));
        }   
    }
   
    public static void adicionais(){
        int tmpServico, servico;
        tmpServico=Integer.parseInt(JOptionPane.showInputDialog
        ( "Selecione o serviço desejado: \n"
        + "1 - Ducha Ecológica - R$ 8,00\n"
        + "2 - Troca de Óleo - R$ 50,00\n"
        + "3 - Balanceamento - R$ 35,00\n"
        + "4 - Café - R$ 2,00"));
        servico = objPosto.servicos(tmpServico-1);
        
        JOptionPane.showMessageDialog(null, "Serviço: "
        + objPosto.tiposServ[tmpServico-1] + String.format("\nTotal: R$ " +
        objPosto.servicos[tmpServico-1]) + ",00");
    }
    
    
}//fechamento classe

