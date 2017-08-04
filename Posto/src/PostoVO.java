/**
 *
 * @author Vinnicius Brito; André Cruz; Andre Augusto
 */
public class PostoVO {
    //declaração de atributos
    public String tipos[] = {"Etanol","Gasolina Comum","Gasolina Aditivada","Diesel"};
    public double custo[] = {1.19, 2.19, 2.29, 1.39};
    public double venda[] = {2.39, 3.49, 3.69, 2.89};
    public String tiposServ[] = {"Ducha Ecológica","Troca de Óleo","Balanceamento","Café"};
    public int servicos[] = {8, 50, 35, 2};
    
    private int tanque[] = {20000, 20000, 20000, 20000};
    private float totalCusto = 0;
    private float totalVendaComb = 0;
    private float totalVendaServ = 0;
    private float totalLucro = 0;
    
    
    //FORNECIMENTO
    public int fornecer(int tmpComb, int tmpLitros){
        if (tanque[tmpComb]+tmpLitros > 20000){
            return 0;
        }else{
            tanque[tmpComb] += tmpLitros;
            totalCusto += custo[tmpComb]*tmpLitros;
            totalLucro -= custo[tmpComb]*tmpLitros;
            return 1;
        }
    }
    
    //VENDAS
    public int abastecer(int tmpComb, int tmpLitros){
        if(tanque[tmpComb]-tmpLitros <= 0){
            return 0;
        }else{
            tanque[tmpComb] -= tmpLitros;
            totalVendaComb += venda[tmpComb]*tmpLitros;
            totalLucro += venda[tmpComb]*tmpLitros;
            return 1;
        }
        
    }
    
    public int servicos(int tmpServico){
        totalVendaServ += servicos[tmpServico];
        totalLucro += servicos[tmpServico];
        return 1;
    }
    
    //RELATÓRIO
    public String relatorio(){
        String relatorio = "";
        for(int i=0; i<10;i++){
            relatorio += "   ";
        }
        relatorio += "RELATÓRIO\n";
        for(int i =0; i<3;i++){
            relatorio += "\nTotal de litros no tanque de " + tipos[i]
            + ": " + tanque[i];
        }
        relatorio += String.format("\n\nCusto total na compra de combustível: R$ %.2f", totalCusto);
        relatorio += String.format("\nCusto total na venda de combustível: R$ R$ %.2f", totalVendaComb);
        relatorio += String.format("\nCusto total na venda de serviços: R$ R$ %.2f", totalVendaServ);
        relatorio += String.format("\nTotal de lucros: R$ %.2f", totalLucro);     
        
        return relatorio;
    }
    
}
