
/**
 *
 * @author Gabriel Eneas
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*; // importando pacote de ação dos botões
//Classe principal herdará as caracteristicas de uma janela
import java.text.DecimalFormat;
import java.io.*;//pacote de gravação em disco

public class PrincipalView extends JFrame implements ActionListener {
    
    public static DecimalFormat fmtMoeda = new DecimalFormat("0.00");

//DECLARAÇÃO DOS OBJETOS
//construção do objeto VO para acesso aos dados
    public static ChurrascariaVO objComanda = new ChurrascariaVO();
   
    //fontes
    Font fntLabels = new Font("TAHOMA", Font.PLAIN, 20);
    Font fntTexts = new Font("TAHOMA", Font.BOLD, 22);
    Font fntButtons = new Font("TAHOMA", Font.BOLD, 14);

    //Container
    public static Container ctnTela;
    //labels
    public static JLabel lblBanner;
    public static JLabel lblValorRodizio;
    public static JLabel lblValorParcial;
    public static JLabel lblValorAcompanhamento;
    public static JLabel lblServicos, lblTotal;
    //textfields
    public static JTextField txtValorRodizio, txtValorParcial, txtValorAcompanhamento, txtServicos, txtTotal;
    //botoes
    public static JButton btnAlterarValor, btnAdicionar, btnFecharConta, btnCaixa;
    public static JButton btnBebidas[], btnSobremesas[];
    //Tabela barra de rolagem e deftabmodel
    public static JScrollPane scrTabela;//barra de rolagem
    public static JTable tblAcomp;//tabela visuial
    //default table model é responsavel por gerenciar o conteudo da tabela
    public static DefaultTableModel mdlAcomp;
    
    //variaveis auxiliares para montagem da tabela
    
    //vetor que controla a variedade de itens pedidos 
    public static int itens[] = new int[16];
    public static int qtdeItens = 0;
    
    
    //metodo construtor
    public PrincipalView() {

        super("Churrascaria CIC - Sistema de Gerenciamento");
        //Construção dos objetos
        ctnTela = new Container();
        ctnTela.setLayout(null); //definindo layout da tela //ctnTela.setLayout(new BorderLayout()
        this.add(ctnTela);//add container na janela

        lblBanner = new JLabel(new ImageIcon("img/banner.png"));
        lblBanner.setBounds(0, 0, 1445, 200);//posicionando objeto
        ctnTela.add(lblBanner);

        lblValorRodizio = new JLabel("Valor Rodizio:");
        lblValorRodizio.setFont(fntLabels);
        lblValorRodizio.setBounds(10, 270, 200, 30);
        ctnTela.add(lblValorRodizio);

        lblValorParcial = new JLabel("Valor Parcial:");
        lblValorParcial.setFont(fntLabels);
        lblValorParcial.setBounds(10, 325, 200, 30);
        ctnTela.add(lblValorParcial);

        lblValorAcompanhamento = new JLabel("Acompanhamentos:");
        lblValorAcompanhamento.setFont(fntLabels);
        lblValorAcompanhamento.setBounds(10, 380, 200, 30);
        ctnTela.add(lblValorAcompanhamento);

        lblServicos = new JLabel("Serviços:");
        lblServicos.setFont(fntLabels);
        lblServicos.setBounds(10, 435, 200, 30);
        ctnTela.add(lblServicos);

        lblTotal = new JLabel("Valor Total:");
        lblTotal.setFont(fntLabels);
        lblTotal.setBounds(10, 490, 200, 30);
        ctnTela.add(lblTotal);

        //instancia das cxs de texto
        txtValorRodizio = new JTextField();
        txtValorRodizio.setEditable(false);
        txtValorRodizio.setFont(fntTexts);
        txtValorRodizio.setBounds(200, 270, 200, 30);
        ctnTela.add(txtValorRodizio);
        txtValorRodizio.setText("R$ " + fmtMoeda.format(objComanda.valorRodizio));

        txtValorParcial = new JTextField();
        txtValorParcial.setEditable(false);
        txtValorParcial.setFont(fntTexts);
        txtValorParcial.setBounds(200, 325, 200, 30);
        ctnTela.add(txtValorParcial);
        txtValorParcial.setText("R$ " + fmtMoeda.format(objComanda.valorRodizio * objComanda.qtdePessoas));

        txtValorAcompanhamento = new JTextField();
        txtValorAcompanhamento.setEditable(false);
        txtValorAcompanhamento.setFont(fntTexts);
        txtValorAcompanhamento.setBounds(200, 380, 200, 30);
        ctnTela.add(txtValorAcompanhamento);

        txtServicos = new JTextField();
        txtServicos.setEditable(false);
        txtServicos.setFont(fntTexts);
        txtServicos.setBounds(200, 435, 200, 30);
        ctnTela.add(txtServicos);

        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        txtTotal.setFont(fntTexts);
        txtTotal.setBounds(200, 490, 200, 30);
        ctnTela.add(txtTotal);

        //instancia dos botoes
        btnAlterarValor = new JButton("Alterar valor");
        btnAlterarValor.addActionListener(this); //tornando o botão ativo
        btnAlterarValor.setBounds(420, 270, 190, 30);
        btnAlterarValor.setFont(fntButtons);
        ctnTela.add(btnAlterarValor);

        btnAdicionar = new JButton("Adicionar: (" + objComanda.qtdePessoas + ")");
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(420, 325, 190, 30);
        btnAdicionar.setFont(fntButtons);
        ctnTela.add(btnAdicionar);

        btnFecharConta = new JButton("Fechar Conta");
        btnFecharConta.addActionListener(this);
        btnFecharConta.setBounds(420, 380, 190, 30);
        btnFecharConta.setFont(fntButtons);
        ctnTela.add(btnFecharConta);

        btnCaixa = new JButton("Caixa");
        btnCaixa.addActionListener(this);
        btnCaixa.setFont(fntButtons);
        btnCaixa.setBounds(420, 435, 190, 30);
        ctnTela.add(btnCaixa);

        btnBebidas = new JButton[8];
        for (int i = 0; i < btnBebidas.length; i++) {
            btnBebidas[i] = new JButton(new ImageIcon("img/b" + i + ".jpg"));
            btnBebidas[i].addActionListener(this);
            if (i < 4) {
                btnBebidas[i].setBounds(900 + (i * 130), 270, 100, 100);

            } else {
                btnBebidas[i].setBounds(900 + ((i - 4) * 130), 400, 100, 100);
            }
            ctnTela.add(btnBebidas[i]);
        }

        btnSobremesas = new JButton[8];
        for (int i = 0; i < btnSobremesas.length; i++) {
            btnSobremesas[i] = new JButton(new ImageIcon("img/s" + i + ".jpg"));
            btnSobremesas[i].addActionListener(this);
            if (i < 4) {
                btnSobremesas[i].setBounds(900 + (i * 130), 550, 100, 100);

            } else {
                btnSobremesas[i].setBounds(900 + ((i - 4) * 130), 680, 100, 100);
            }
            ctnTela.add(btnSobremesas[i]);
        }

//Construção da tabela
        tblAcomp = new JTable();
        //Construindo scroll vinculado a tebela
        scrTabela = new JScrollPane(tblAcomp);
        //vinculando model a tabela
        mdlAcomp = (DefaultTableModel) tblAcomp.getModel();
        //add scroll
        scrTabela.setBounds(630, 270, 250, 510);
        ctnTela.add(scrTabela);

        String legenda[] = {"Item", "Qtd"};
        mdlAcomp.addColumn(legenda[0]);
        mdlAcomp.addColumn(legenda[1]);
           
        atualizarCampos();
        inicilizarItens();
        
//Config da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//encerrando processo do java ao fechar a janela
        this.setSize(montarTela());//define o tamanho da janela
        this.setResizable(false);
        this.show();//exibe janela // this.setVisible(true)

    }//fechando construtor

    public static Dimension montarTela() {

        Toolkit info = Toolkit.getDefaultToolkit();//Criando objeto para acessar informações do sitema
        Dimension res = info.getScreenSize();//pegando resolução da tela e guardando na variavel 'res'
        return res;//retornando a resolução
    }//fechando montarTela()

    public void actionPerformed(ActionEvent evt) {
        //tratamendo de ação para cada botão
        if (evt.getSource() == btnAdicionar) {
            //adicionar pessoas ao rodizio
            txtValorParcial.setText("R$ " + fmtMoeda.format(objComanda.adicionarPessoas()));
            btnAdicionar.setText("Adicionar: (" +objComanda.qtdePessoas+ ")");
            atualizarCampos();
            
        } else if (evt.getSource() == btnAlterarValor) {
            //alterar preço do rodizio
            
        } else if (evt.getSource() == btnFecharConta){
            //emitir cpf e limpar dados para novo cliente
            gravarNotaTxt();
            
        } else if(evt.getSource() == btnCaixa){
            
        }
        
        //tratamento dos botoes de vetores
        for (int i = 0; i < btnBebidas.length; i++) {
            if(evt.getSource()==btnBebidas[i]){
                objComanda.adicionarBebida(i);
                adicionarItem(1,i);
                atualizarCampos();                
            }else if(evt.getSource()== btnSobremesas[i]){
                objComanda.adicionarSobremesa(i);
                adicionarItem(2,i);
                atualizarCampos();
            }
        }
    }//montarTela()
    
    public static void atualizarCampos(){
        
        txtValorRodizio.setText("R$ " + fmtMoeda.format(objComanda.valorRodizio));
        txtValorAcompanhamento.setText("R$ " + fmtMoeda.format(objComanda.totalAcompanhamento));
        txtServicos.setText("R$ " + fmtMoeda.format(objComanda.totalServicos));
        txtTotal.setText("R$ " + fmtMoeda.format(objComanda.totalConta));
        
    }//fechando atualizarCampos
    
    public static void gravarNotaTxt(){
        objComanda.cpf = JOptionPane.showInputDialog("Entre com o cpf do Cliente");
        
        try{//tentar
            
            //criando arquivo e objeto de "escrita"
            FileWriter arquivo = new FileWriter("docs/NF_" + objComanda.cpf + ".txt");
            PrintWriter conteudo = new PrintWriter(arquivo);
            
            //escrevendo no arquivo
            conteudo.println(objComanda.gerarNota());
            
            //fechando arquivo
            arquivo.close();
            
            JOptionPane.showMessageDialog(null, "Nota Fiscal gerada com sucesso.");
            
            objComanda.zerarCampos();
            atualizarCampos();
            
        }catch(Exception erro){//se não conseguir
            JOptionPane.showInputDialog(null, erro.getMessage());
        
    }
        
    }//fechando gravarNotaTxt
    
    public static void inicilizarItens(){
        for(int i=0; i<itens.length; i++){
            itens[i] = -1;
        }//"zerando vetor"
        while(mdlAcomp.getRowCount()>0){
            mdlAcomp.removeRow(0);
        }//limpando lista
    }//fechando inicializaItens
    
    public static int buscarItem(int tmpId){
        for (int i=0; i<itens.length;i++){
            if(itens[i] == tmpId){
                return i;//retorna a posição do item
            }
        }
        return -1;//nenhum item equivalente
    }//fechando buscarItem
    
    public static void adicionarItem(int tmpTipo, int tmpId){
        //verificar se acompanhamento ja foi adicionado
        int numLinha = -1;
        if(tmpTipo == 1){//bebidas
            numLinha = buscarItem(tmpId);
            
        }else if(tmpTipo == 2){//sobremesas
            numLinha = buscarItem(tmpId+8);
        }
        if (numLinha == -1){
            //adiciona nova linha na tabela
            String linha[] =  new String[2];
            if (tmpTipo == 1){
                linha[0] = objComanda.strBebidas[tmpId];
                linha[1] = "" + objComanda.qtdBebidas[tmpId];
            }else if(tmpTipo == 2){
                linha[0] = objComanda.strSobremesas[tmpId];
                linha[1] = "" + objComanda.qtdSobremesas[tmpId];
            }
            if(tmpTipo==1)
                itens[qtdeItens] = tmpId;
            else if(tmpTipo == 2)
                itens[qtdeItens] = tmpId+8;
            
            mdlAcomp.addRow(linha);
            qtdeItens++;
        }else{
            //verifica onde está o produto e soma 1 na qtde
            //acessando quantidade atual do acompanhamento desejado
            int qtdAtual = Integer.parseInt(tblAcomp.getValueAt(numLinha,1).toString());
            
            qtdAtual++; //acrescentando +1 na qtd;
            mdlAcomp.setValueAt(qtdAtual, numLinha, 1);//atualizando quantidade
        }
    }//fechando adicionarItem
}//fechando classe