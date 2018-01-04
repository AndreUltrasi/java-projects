import javax.swing.*;//pacote de elementos graficos

public class estradaView {
    public static estradaVo objVo = new estradaVo();
    
    public estradaView(){
        
        
        int opcao;
        String msgMenu = "";
        msgMenu += "1 - Registro de Pagamento\n";
        msgMenu += "2 - Fechamento Diário\n";
        msgMenu += "3 - Sair";
        do{
        opcao = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
        switch(opcao){
        case 1:
            registro();
            break;
        case 2:
            fechamento();
            break;
        case 3:
            break;
        default:
            JOptionPane.showMessageDialog(null, "Opção Inválida!!! ");
            break;
        }
        }while(opcao!=3);
    
    }
    public static void registro(){
        String msgMenu = "";
        double tmpTroco = 0, tmpValor;
        int tmpTipo;
        msgMenu += "REGISTRO DE PAGAMENTO\n\n";
        msgMenu += "Informe o tipo de veículo: \n";
        msgMenu += "1 - Motos - R$ 2,30\n";
        msgMenu += "2 - Carros e Utilitários(2 eixos) - R$ 4,60\n";
        msgMenu += "3 - Caminhões Médios(3 eixos) - R$ 6,90\n";
        msgMenu += "4 - Caminhões Grandes(4 eixos) - R$ 9,20\n";
        msgMenu += "5 - Carretas(5 eixos) - R$ 12,50\n";
        msgMenu += "6 - Veículos acima de 5 eixos - R$ 2,30 * eixos\n";
        tmpTipo = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
        
        if(tmpTipo == 6){
            int tmpEixos;
            msgMenu = "";
            msgMenu += "Digite a quantidade de eixos: ";
            tmpEixos = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
            tmpValor = tmpEixos * 2.3;
            msgMenu = "";
            msgMenu += "Valor da Tarifa: \n" + objVo.tipos[tmpTipo-1] + " - R$ " + tmpValor;
            msgMenu += "\nDigite o valor recebido: ";
            tmpValor=Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
            tmpTroco = tmpValor - tmpValor;
            
            
            msgMenu = "";
            msgMenu += "Valor da Tarifa: \n" + objVo.tipos[tmpTipo-1] + " - R$ " + tmpEixos * 2.3;
            msgMenu += "\nValor recebido: R$ " + tmpValor;
            tmpTroco = tmpValor - tmpEixos * 2.3;
            msgMenu += String.format("\nTroco: R$ %.2f", tmpTroco);
            JOptionPane.showMessageDialog(null, msgMenu);
            objVo.totalArrecadado += tmpEixos * 2.3;
            objVo.totalCategoria[5] += 1;
        }
        else{
            msgMenu = "";
            msgMenu += "Valor da Tarifa: \n" + objVo.tipos[tmpTipo-1] + " - R$ " + objVo.valor[tmpTipo-1];
            msgMenu += "\nDigite o valor recebido: ";
            tmpValor=Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
            tmpTroco = tmpValor - objVo.valor[tmpTipo-1];
            msgMenu = "";
            msgMenu += "Valor da Tarifa: \n" + objVo.tipos[tmpTipo-1] + " - R$ " + objVo.valor[tmpTipo-1];
            msgMenu += "\nValor recebido: R$ " + tmpValor;
            msgMenu += String.format("\nTroco: R$ %.2f", tmpTroco);
            JOptionPane.showMessageDialog(null, msgMenu);
            objVo.totalArrecadado += objVo.valor[tmpTipo-1];
            objVo.totalCategoria[tmpTipo-1] += 1;
            
        }
    }
        public static void fechamento(){
            String msgMenu = "";
            int total = 0;
            msgMenu += "FECHAMENTO DIÁRIO\n\n";
            msgMenu += "Quantidade de veículos por categoria: \n";
            for (int i = 0; i<6; i++){
                msgMenu += objVo.tipos[i] + ": " + objVo.totalCategoria[i] + "\n";
                total += objVo.totalCategoria[i];
            }
            msgMenu += "\nTotal de veículos: " + total;
            msgMenu += "\nTotal arrecadado: R$ " + objVo.totalArrecadado;
            JOptionPane.showMessageDialog(null, msgMenu);
        }
        
        
    
    

    
}
