import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class ProdutosView extends JInternalFrame implements ActionListener{
    
    Produtos dadosProduto = new Produtos();
    private static final String strLegendaProdutos[]={"Código", "Nome do Produto", "Preço", "Unis. Em Estoque", "Discontinuado"};
    private static final String strCamposProdutos[]={"Código:", "Nome do Produto:", "FornecedorID:",
    "CategoriaID:", "Quantidade/Uni.:", "Preço/Uni.:", "Unis. Em Estoque:", "Unis. Pedidas:", "Nível de Reabastecimento:", "Discontinuado:"};
       
    private static JLabel lblCamposProdutos[];//fazendo a parte esquerda da tela
    private static JTextField txtCamposProdutos[];//fazendo a parte esquerda da tela
    private static JTable tblProdutos;
    private static JScrollPane scrProdutos;
    private static DefaultTableModel mdlProdutos;//import swing.table.*
    //campos de pesquisa 
    private static JLabel lblBuscaProdutos;
    private static JTextField txtBuscaProdutos;
    private static JButton btnBuscaProdutos, btnRestaurarProdutos, 
            btnNovoProdutos, btnSalvarProdutos, btnStatusProdutos;
    
    private static Container ctnProdutos;//declarando container
    private static JComboBox cmbBuscaProdutos;
    
    public ProdutosView(){
        super("Modulo PRODUTOS");
        ctnProdutos = new Container();
        ctnProdutos.setLayout(null);
        this.add(ctnProdutos);
        lblCamposProdutos = new JLabel[strCamposProdutos.length];
        txtCamposProdutos = new JTextField[strCamposProdutos.length];
        for(int i=0; i<strCamposProdutos.length; i++){
            lblCamposProdutos[i] = new JLabel(strCamposProdutos[i]);
            txtCamposProdutos[i] = new JTextField();
            lblCamposProdutos[i].setBounds(10,50+(i*40),150,20);
            txtCamposProdutos[i].setBounds(170,50+(i*40),200,20);
            ctnProdutos.add(lblCamposProdutos[i]);
            ctnProdutos.add(txtCamposProdutos[i]);
        }//fechando for
        
        tblProdutos = new JTable();
        scrProdutos = new JScrollPane(tblProdutos);//estanciando scrFuncionários
        
        mdlProdutos = (DefaultTableModel) tblProdutos.getModel();//gerencia o que esta na tabela
        scrProdutos.setBounds(450,70,500,380);
        ctnProdutos.add(scrProdutos);
        
        for (String strLegendaProduto : strLegendaProdutos) {
            mdlProdutos.addColumn(strLegendaProduto);
        } //fechando for
        
        
        lblBuscaProdutos = new JLabel("Busca ");
        lblBuscaProdutos.setBounds(450,30,200,40);
        ctnProdutos.add(lblBuscaProdutos);
        
        txtBuscaProdutos = new JTextField();
        txtBuscaProdutos.setBounds(595,40,250,20);
        ctnProdutos.add(txtBuscaProdutos);
        
        String strBuscaProdutos[]={"por Código:","por Nome:"};
        cmbBuscaProdutos = new JComboBox(strBuscaProdutos);
        cmbBuscaProdutos.setBounds(490,40,100,20);
        ctnProdutos.add(cmbBuscaProdutos);
        
        btnBuscaProdutos = new JButton("Buscar");
        btnBuscaProdutos.addActionListener(this);
        btnBuscaProdutos.setBounds(850,40,100,20);
        ctnProdutos.add(btnBuscaProdutos);
                
        btnRestaurarProdutos = new JButton("Restaurar Tabela");
        btnRestaurarProdutos.setBounds(800,450,150,20);
        btnRestaurarProdutos.addActionListener(this);
        ctnProdutos.add(btnRestaurarProdutos);
        
        
        btnNovoProdutos = new JButton("Novo");
        btnNovoProdutos.addActionListener(this);
        btnNovoProdutos.setBounds(375, 70, 70, 40);
        ctnProdutos.add(btnNovoProdutos);
        
        btnSalvarProdutos = new JButton("Salvar");
        btnSalvarProdutos.addActionListener(this);
        btnSalvarProdutos.setBounds(375, 120, 70, 40);
        ctnProdutos.add(btnSalvarProdutos);
        
        btnStatusProdutos = new JButton("Desativar");
        btnStatusProdutos.addActionListener(this);
        btnStatusProdutos.setBounds(450, 450, 150, 20);
        ctnProdutos.add(btnStatusProdutos);
        
        
        carregaLista(1);
        
        tblProdutos.addMouseListener(new MouseAdapter(){//utilizando o mouse para preencher a tabela
            public void mouseClicked(MouseEvent evt){
                //JOptionPane.showMessageDialog(null, "oi");
                String tmpCodigo;
                tmpCodigo = tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0).toString();
                try{
                    preencheCampos(ProdutosDAO.consultaProduto(tmpCodigo, 1));                                        
                }catch(Exception erro){
                   JOptionPane.showMessageDialog(null, erro.getMessage());                    
                }//fechamento try catch.             
            }//fechamento void Mouse
        });//fechamento addMouse
        
        tblProdutos.addKeyListener(new KeyAdapter(){//utilizando 
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_DOWN ||
                        evt.getKeyCode() == KeyEvent.VK_UP ||
                            evt.getKeyCode() == KeyEvent.VK_ENTER ||
                                evt.getKeyCode() == KeyEvent.VK_TAB){
                    String tmpCodigo;
                    tmpCodigo = tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0).toString();
                    try{
                        preencheCampos(ProdutosDAO.consultaProduto(tmpCodigo, 1));                                        
                    }catch(Exception erro){
                        JOptionPane.showMessageDialog(null, erro.getMessage());                    
                    }//fechamento try catch.
                }//fechamento if
            }//fechamento void key
        });//fechamento addkeylistener
        
        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(),SistemaView.dskJanelas.getHeight());
        this.show();
        this.setResizable(false);
        this.setMaximizable(false);
        
        this.setVisible(true);
        this.setClosable(true);
        this.setResizable(true); 
        
        
    }
    
    private static void preencheCampos(Produtos tmpProduto){
        if(tmpProduto.getDiscontinued()== 0){
            btnStatusProdutos.setText("Ativar");
            for(int i=0; i<strCamposProdutos.length; i++){
                txtCamposProdutos[i].setForeground(Color.red);
            }
        }else{
            btnStatusProdutos.setText("Desativar");
            for(int i=0; i<strCamposProdutos.length; i++){
                txtCamposProdutos[i].setForeground(Color.black);
            }
        }
        
        txtCamposProdutos[0].setText(tmpProduto.getProductID());
        txtCamposProdutos[1].setText(tmpProduto.getProductName());
        txtCamposProdutos[2].setText(tmpProduto.getSupplierID());
        txtCamposProdutos[3].setText(tmpProduto.getCategoryID());
        txtCamposProdutos[4].setText(tmpProduto.getQuantityPerUnit());
        txtCamposProdutos[5].setText(tmpProduto.getUnitPrice());
        txtCamposProdutos[6].setText(tmpProduto.getUnitsInStock());
        txtCamposProdutos[7].setText(tmpProduto.getUnitsOnOrder());
        txtCamposProdutos[8].setText(tmpProduto.getReorderLevel());
        txtCamposProdutos[9].setText(Integer.toString(tmpProduto.getDiscontinued()));
        
    }//fechando preencheCampos
            
    
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnBuscaProdutos){
            
            if(cmbBuscaProdutos.getSelectedIndex()==0){//se o primeiro item da lista estiver selecionado
                //**BUSCA POR CODIGO**
            try{
                Produtos dadosProduto = ProdutosDAO.consultaProduto(txtBuscaProdutos.getText(),1);
                if(dadosProduto == null){
                    JOptionPane.showMessageDialog(null, "Produto não encontrado");
                }else{
                    carregaLista(1);
                    preencheCampos(dadosProduto);
                }//fechado else
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
            }//fechando if combox
            else if(cmbBuscaProdutos.getSelectedIndex()==1){
                //**BUSCA POR NOME**
            try{
                carregaLista(2);
                                
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
            }//fechando else if
            
            
        }//fechando if
        else if(evt.getSource()==btnRestaurarProdutos){
            carregaLista(1);
        }
        else if(evt.getSource() == btnNovoProdutos){
            limpaCampos();
        }//fechando if
        else if(evt.getSource() == btnSalvarProdutos){
            try{                
                if(validaCampos()==true){
                    gravaObjeto();
                    ProdutosDAO.cadastraProduto(dadosProduto);
                    JOptionPane.showMessageDialog(null, "Produto " + dadosProduto.getProductName() + " Cadastrado");
                    limpaCampos();
                    carregaLista(1);
                       
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
        }//fechando if
        else if(evt.getSource() == btnStatusProdutos){
            try{
                
                //Produtos dados;
                
                if(txtCamposProdutos[0].getText().trim().compareTo("")==0){
                    JOptionPane.showMessageDialog(null, "Não há produto selecionado");                   
                }else{
                    Produtos dados = ProdutosDAO.consultaProduto(txtCamposProdutos[0].getText(),1);
                    String codigo = dados.getProductID();
                    int status = dados.getDiscontinued();
                    
                    ProdutosDAO.alteraStatus(codigo, status);                                      
                    JOptionPane.showMessageDialog(null, "Discontinuado alterado para o produto " + dados.getProductName());
                    
                    dados = ProdutosDAO.consultaProduto(txtCamposProdutos[0].getText(),1);
                    preencheCampos(dados);
                    carregaLista(1);
                    
                }
                
            }catch(Exception erro){
               JOptionPane.showMessageDialog(null, erro.getMessage()); 
            }//fechando try catch
        }//fechamento if
        
        
    }//fechando actionPerformed
    
    private static void carregaLista(int tmpTipo){
        while(mdlProdutos.getRowCount()>0){//método p limpar a lista, já busca a lista atualizada
            mdlProdutos.removeRow(0);            
        }
        try{
            java.util.List<Produtos>vetorProdutos = new ArrayList<>();
            //para nao ocorrer problemas foi colocado java.util pois pode dar ambiguidade
            if(tmpTipo == 1){
                vetorProdutos = ProdutosDAO.listaProdutos();  
            }else if(tmpTipo==2){
                vetorProdutos = ProdutosDAO.consultaNome(txtBuscaProdutos.getText());
            }
            // ":" quer dizer existente, para cada produto existente
            for(Produtos produtoAtual : vetorProdutos){
                String dados[] = new String[5];
                dados[0] = produtoAtual.getProductID();
                dados[1] = produtoAtual.getProductName();
                dados[2] = produtoAtual.getUnitPrice();
                dados[3] = produtoAtual.getUnitsInStock();
                dados[4] = Integer.toString(produtoAtual.getDiscontinued());
                
                mdlProdutos.addRow(dados);
            }//fechando for
            
                
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }//fechando try
    }//fechando void carregaLista
    
    private void gravaObjeto(){
        
        dadosProduto.setProductID(txtCamposProdutos[0].getText());
        dadosProduto.setProductName(txtCamposProdutos[1].getText());
        dadosProduto.setSupplierID(txtCamposProdutos[2].getText());
        dadosProduto.setCategoryID(txtCamposProdutos[3].getText());
        dadosProduto.setQuantityPerUnit(txtCamposProdutos[4].getText());
        dadosProduto.setUnitPrice(txtCamposProdutos[5].getText());
        dadosProduto.setUnitsInStock(txtCamposProdutos[6].getText());
        dadosProduto.setUnitsOnOrder(txtCamposProdutos[7].getText());
        dadosProduto.setReorderLevel(txtCamposProdutos[8].getText());
        dadosProduto.setDiscontinued(Integer.parseInt(txtCamposProdutos[9].getText()));
        
        
    }//fechamento gravaObjeto
    
    private void limpaCampos(){
        for(int i=0; i<strCamposProdutos.length; i++){
            txtCamposProdutos[i].setText(null);
        }//fechamento for
        
    }//fechamento limpaCampos
    
    private static boolean validaCampos(){
        for(int i=0; i<txtCamposProdutos.length; i++){
            if(txtCamposProdutos[i].getText().trim().compareTo("")==0){
                JOptionPane.showMessageDialog(null, "Preencha o campo: " + strCamposProdutos[i]);
                txtCamposProdutos[i].grabFocus();//move o cursor
                return false;
            }        
        }        
        return true;
    }//fechamento validaCampos
    
}
