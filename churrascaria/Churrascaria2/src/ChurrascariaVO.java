
/**
 *
 * @author 28254438
 */

public class ChurrascariaVO {
    
    //declaração dos atibutos
    public float valorRodizio;
    public int qtdePessoas;
    
    public String strBebidas[] = {"Água", "Refrigerante", "Suco", "Cerveja", "Vinho","Batida","Caipirinha","Whiskey"};
    public float valorBebidas [] = {4.5f, 6f, 8f, 6.5f, 45f, 12f, 10f, 80f};
    public String strSobremesas[] = {"Açai", "Sorvete", "Bolo", "Pudim", "Mousse","Gelatina","Brigadeiro","Paçoca"};
    public float valorSobremesas [] = {22f, 13f, 7.5f, 5f, 7f, 5f, 4.5f, 3f};
    public int qtdBebidas[] = {0,0,0,0,0,0,0,0};
    public int qtdSobremesas[] = {0,0,0,0,0,0,0,0};
    public float totalAcompanhamento, totalServicos, totalConta, totalCaixa;
    public String cpf, textoNF;

    //metodo construtor
    public ChurrascariaVO(){
        valorRodizio = 39.9f;
        qtdePessoas = 1;
        totalAcompanhamento = 0;
        totalConta = calculaTotal();
        totalCaixa = 0;
        cpf = null;
        textoNF = null;
    }//fechamento construtor
    
    //metodos funcionais
    
    public float adicionarPessoas(){
        qtdePessoas++;
        calculaTotal();
        return valorRodizio * qtdePessoas;
        
    }//fechamento adcPessoas
    
    public float adicionarBebida(int tmpId){
        
        qtdBebidas[tmpId]++;
        totalAcompanhamento+=valorBebidas[tmpId];
        calculaTotal();
        return totalAcompanhamento;
    }//fechamento adcbebida
    
    
    public float adicionarSobremesa(int tmpId){
        
        qtdSobremesas[tmpId]++;
        totalAcompanhamento+=valorSobremesas[tmpId];
        calculaTotal();        
        return totalAcompanhamento;
    }//fechamento adcSobremesa
    
    public float calculaTotal(){
        totalConta = (valorRodizio * qtdePessoas) 
                + totalAcompanhamento;
        totalServicos = totalConta * (float)0.1;
        totalConta+= totalServicos;
        
        return totalConta;
    }//fechamento calculaTotal
    
    public String gerarNota(){
        
        textoNF = "##### CHURRASCARIA CIC #####\r\n\r\n";
        textoNF+="Av. Guarulhos, 145 - Centro - Guarulhos\r\n";
        textoNF+="Tel.: (11)2903-5689 - contato@churrascic.com.br\r\n\r\n\r\n";
        textoNF+="----------------- Dados do Pedido -----------------\r\n";
        textoNF+="Rodízio: " + (valorRodizio * qtdePessoas) + "\r\n";
        textoNF+="Acompanhamento: " + totalAcompanhamento + "\r\n";
        textoNF+="Serviços: " + totalServicos + "\r\n";
        textoNF+="TOTAL A PAGAR: " + totalConta + "\r\n";
                
        
        return textoNF;
    }//fechando gerarNota
    
    public void zerarCampos(){
        valorRodizio = 39.9f;
        qtdePessoas = 1;
        totalAcompanhamento = 0;
        totalConta = calculaTotal();
        totalCaixa = 0;
        cpf = null;
        textoNF = null;
        
        for(int i=0; i<qtdBebidas.length;i++){
            qtdBebidas[i]=0;
            qtdSobremesas[i]=0;
            
        }//fechando for
        
    }//fechando zerar campos
    
}//fechamento classe
