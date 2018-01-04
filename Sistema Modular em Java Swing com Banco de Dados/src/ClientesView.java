import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class ClientesView extends JInternalFrame implements ActionListener{
    
    Clientes dadosCliente = new Clientes();
    public static String strLegenda[]={"Código", "Nome", "Cidade", "Telefone"};
    public static String strCampos[]={"Código:", "Nome da Empresa:", "Nome do Representante:",
    "Cargo:", "Endereço:", "Cidade:", "Estado:", "País:", "CEP:", "Telefone:", "E-mail:"};
    public static JLabel lblCampos[];//fazendo a parte esquerda da tela
    public static JTextField txtCampos[];//fazendo a parte esquerda da tela
    public static JTable tblClientes;
    public static JScrollPane scrClientes;
    public static DefaultTableModel mdlClientes;//import swing.table.*
    //campos de pesquisa 
    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca, btnRestaurar, btnNovo, btnSalvar, btnStatus;
    public static Container ctnClientes;//declarando container
    public static JComboBox cmbBusca;
    
    //iniciando metodo construtor
    public ClientesView(){
        super("Modulo CLIENTES");
        ctnClientes = new Container();
        ctnClientes.setLayout(null);
        this.add(ctnClientes);
        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];
        for(int i=0; i<strCampos.length; i++){
            lblCampos[i] = new JLabel(strCampos[i]);
            txtCampos[i] = new JTextField();
            lblCampos[i].setBounds(10,50+(i*40),150,20);
            txtCampos[i].setBounds(170,50+(i*40),200,20);
            ctnClientes.add(lblCampos[i]);
            ctnClientes.add(txtCampos[i]);
        }//fechando for
        
        tblClientes = new JTable();
        scrClientes = new JScrollPane(tblClientes);//estanciando scr Clientes
        
        mdlClientes = (DefaultTableModel) tblClientes.getModel();//gerencia o que esta na tabela
        scrClientes.setBounds(450,70,500,380);
        ctnClientes.add(scrClientes);
        
        for(int i=0;i<strLegenda.length; i++){
            mdlClientes.addColumn(strLegenda[i]);
        }//fechando for
        
        
        lblBusca = new JLabel("Busca ");
        lblBusca.setBounds(450,30,200,40);
        ctnClientes.add(lblBusca);
        
        txtBusca = new JTextField();
        txtBusca.setBounds(595,40,250,20);
        ctnClientes.add(txtBusca);
        
        String strBusca[]={"por Código:","por Nome:"};
        cmbBusca = new JComboBox(strBusca);
        cmbBusca.setBounds(490,40,100,20);
        ctnClientes.add(cmbBusca);
        
        btnBusca = new JButton("Buscar");
        btnBusca.addActionListener(this);
        btnBusca.setBounds(850,40,100,20);
        ctnClientes.add(btnBusca);
                
        btnRestaurar = new JButton("Restaurar Tabela");
        btnRestaurar.addActionListener(this);
        btnRestaurar.setBounds(800,450,150,20);
        ctnClientes.add(btnRestaurar);
                
        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setBounds(375, 70, 70, 40);
        ctnClientes.add(btnNovo);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(375, 120, 70, 40);
        ctnClientes.add(btnSalvar);
        
        btnStatus = new JButton("Desativar");
        btnStatus.addActionListener(this);
        btnStatus.setBounds(450, 450, 150, 20);
        ctnClientes.add(btnStatus);
        
        
        carregaLista(1);
        
        tblClientes.addMouseListener(new MouseAdapter(){//utilizando o mouse para preencher a tabela
            public void mouseClicked(MouseEvent evt){
                //JOptionPane.showMessageDialog(null, "oi");
                String tmpCodigo;
                tmpCodigo = tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString();
                try{
                    preencheCampos(ClientesDAO.consultaCliente(tmpCodigo, 1));                                        
                }catch(Exception erro){
                   JOptionPane.showMessageDialog(null, erro.getMessage());                    
                }//fechamento try catch.             
            }//fechamento void Mouse
        });//fechamento addMouse
        
        tblClientes.addKeyListener(new KeyAdapter(){//utilizando 
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_DOWN ||
                        evt.getKeyCode() == KeyEvent.VK_UP ||
                            evt.getKeyCode() == KeyEvent.VK_ENTER ||
                                evt.getKeyCode() == KeyEvent.VK_TAB){
                    String tmpCodigo;
                    tmpCodigo = tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString();
                    try{
                        preencheCampos(ClientesDAO.consultaCliente(tmpCodigo, 1));                                        
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
        
        
    }//fechando Clientes View
    
    
    private static void preencheCampos(Clientes tmpCliente){
        if(tmpCliente.getStatus() == 0){
            btnStatus.setText("Ativar");
            for(int i=0; i<strCampos.length; i++){
                txtCampos[i].setForeground(Color.red);
            }
        }else{
            btnStatus.setText("Desativar");
            for(int i=0; i<strCampos.length; i++){
                txtCampos[i].setForeground(Color.black);
            }
        }
        
        txtCampos[0].setText(tmpCliente.getCodigo());
        txtCampos[1].setText(tmpCliente.getNomeEmpresa());
        txtCampos[2].setText(tmpCliente.getNomeRepresentante());
        txtCampos[3].setText(tmpCliente.getCargo());
        txtCampos[4].setText(tmpCliente.getEndereco());
        txtCampos[5].setText(tmpCliente.getCidade());
        txtCampos[6].setText(tmpCliente.getEstado());
        txtCampos[7].setText(tmpCliente.getPais());
        txtCampos[8].setText(tmpCliente.getCep());
        txtCampos[9].setText(tmpCliente.getTelefone());
        txtCampos[10].setText(tmpCliente.getEmail());
        
    }//fechando preencheCampos
            
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnBusca){
            
            if(cmbBusca.getSelectedIndex()==0){//se o primeiro item da lista estiver selecionado
                //**BUSCA POR CODIGO**
            try{
                Clientes dadosCliente = ClientesDAO.consultaCliente(txtBusca.getText(),1);
                if(dadosCliente == null){
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                }else{
                    carregaLista(1);
                    preencheCampos(dadosCliente);
                }//fechado else
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
            }//fechando if combox
            else if(cmbBusca.getSelectedIndex()==1){
                //**BUSCA POR NOME**
            try{
                carregaLista(2);
                                
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
            }//fechando else if
            
            
        }//fechando if
        else if(evt.getSource()==btnRestaurar){
            carregaLista(1);
        }
        else if(evt.getSource() == btnNovo){
            limpaCampos();
        }//fechando if
        else if(evt.getSource() == btnSalvar){
            try{                
                if(validaCampos()==true){
                    gravaObjeto();
                    ClientesDAO.cadastraCliente(dadosCliente);
                    carregaLista(1);
                    JOptionPane.showMessageDialog(null, "Cliente " + dadosCliente.getNomeEmpresa() + " Cadastrado");
                    limpaCampos();
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
        }//fechando if
        else if(evt.getSource() == btnStatus){
            try{
                
                //Clientes dados;
                
                if(txtCampos[0].getText().trim().compareTo("")==0){
                    JOptionPane.showMessageDialog(null, "Não há cliente selecionado");                   
                }else{
                    Clientes dados = ClientesDAO.consultaCliente(txtCampos[0].getText(),1);
                    String codigo = dados.getCodigo();
                    int status = dados.getStatus();
                    
                    ClientesDAO.alteraStatus(codigo, status);                                      
                    JOptionPane.showMessageDialog(null, "Status Alterado para o cliente: " + dados.getNomeEmpresa());
                    
                    dados = ClientesDAO.consultaCliente(txtCampos[0].getText(),1);
                    preencheCampos(dados);
                    
                }
                
            }catch(Exception erro){
               JOptionPane.showMessageDialog(null, erro.getMessage()); 
            }//fechando try catch
        }//fechamento if
        
        
    }//fechando actionPerformed
    
    private static void carregaLista(int tmpTipo){
        while(mdlClientes.getRowCount()>0){//método p limpar a lista, já busca a lista atualizada
            mdlClientes.removeRow(0);            
        }
        try{
            java.util.List<Clientes>vetorClientes = new ArrayList<Clientes>();
            //para nao ocorrer problemas foi colocado java.util pois pode dar ambiguidade
            if(tmpTipo == 1){
                vetorClientes = ClientesDAO.listaClientes();            
            }else if(tmpTipo==2){
                vetorClientes = ClientesDAO.consultaNome(txtBusca.getText());
            }
            for(Clientes clienteAtual : vetorClientes){
                // ":" quer dizer existente, para cada cliente existente
                String dados[] = new String[4];
                dados[0] = clienteAtual.getCodigo();
                dados[1] = clienteAtual.getNomeEmpresa();
                dados[2] = clienteAtual.getCidade();
                dados[3] = clienteAtual.getEmail();
                mdlClientes.addRow(dados);
            }//fechando for
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }//fechando try
    }//fechando void carregaLista
    
    private void gravaObjeto(){
        dadosCliente.setCodigo(txtCampos[0].getText());
        dadosCliente.setNomeEmpresa(txtCampos[1].getText());
        dadosCliente.setNomeRepresentante(txtCampos[2].getText());
        dadosCliente.setCargo(txtCampos[3].getText());
        dadosCliente.setEndereco(txtCampos[4].getText());
        dadosCliente.setCidade(txtCampos[5].getText());
        dadosCliente.setEstado(txtCampos[6].getText());
        dadosCliente.setPais(txtCampos[7].getText());
        dadosCliente.setCep(txtCampos[8].getText());
        dadosCliente.setTelefone(txtCampos[9].getText());
        dadosCliente.setEmail(txtCampos[10].getText());               
        
    }//fechamento gravaObjeto
    
    private void limpaCampos(){
        for(int i=0; i<strCampos.length; i++){
            txtCampos[i].setText(null);
        }//fechamento for
        
    }//fechamento limpaCampos
    
    private static boolean validaCampos(){
        for(int i=0; i<txtCampos.length; i++){
            if(txtCampos[i].getText().trim().compareTo("")==0){
                JOptionPane.showMessageDialog(null, "Preencha o campo: " + strCampos[i]);
                txtCampos[i].grabFocus();//move o cursor
                return false;
            }        
        }        
        return true;
    }//fechamento validaCampos
    
}//fechando classe
