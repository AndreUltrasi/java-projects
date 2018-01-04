import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class FuncionariosView extends JInternalFrame implements ActionListener{
    
    Funcionarios dadosFuncionario = new Funcionarios();
    private static final String strLegendaFuncionarios[]={"Código", "Nome", "Cargo", "País", "Status"};
    private static final String strCamposFuncionarios[]={"Código:", "Primeiro Nome:", "Sobrenome:",
    "Cargo:", "Data de Nascimento:", "Endereço:", "Cidade:", "Estado:", "CEP:", "Telefone:", "País:", "Status:"};
        
    private static JLabel lblCamposFuncionarios[];//fazendo a parte esquerda da tela
    private static JTextField txtCamposFuncionarios[];//fazendo a parte esquerda da tela
    private static JTable tblFuncionarios;
    private static JScrollPane scrFuncionarios;
    private static DefaultTableModel mdlFuncionarios;//import swing.table.*
    //campos de pesquisa 
    private static JLabel lblBuscaFuncionarios;
    private static JTextField txtBuscaFuncionarios;
    private static JButton btnBuscaFuncionarios, btnRestaurarFuncionarios, 
            btnNovoFuncionarios, btnSalvarFuncionarios, btnStatusFuncionarios;
    
    private static Container ctnFuncionarios;//declarando container
    private static JComboBox cmbBuscaFuncionarios;
    
    public FuncionariosView(){
        super("Modulo FUNCIONARIOS");
        ctnFuncionarios = new Container();
        ctnFuncionarios.setLayout(null);
        this.add(ctnFuncionarios);
        lblCamposFuncionarios = new JLabel[strCamposFuncionarios.length];
        txtCamposFuncionarios = new JTextField[strCamposFuncionarios.length];
        for(int i=0; i<strCamposFuncionarios.length; i++){
            lblCamposFuncionarios[i] = new JLabel(strCamposFuncionarios[i]);
            txtCamposFuncionarios[i] = new JTextField();
            lblCamposFuncionarios[i].setBounds(10,50+(i*40),150,20);
            txtCamposFuncionarios[i].setBounds(170,50+(i*40),200,20);
            ctnFuncionarios.add(lblCamposFuncionarios[i]);
            ctnFuncionarios.add(txtCamposFuncionarios[i]);
        }//fechando for
        
        tblFuncionarios = new JTable();
        scrFuncionarios = new JScrollPane(tblFuncionarios);//estanciando scrFuncionários
        
        mdlFuncionarios = (DefaultTableModel) tblFuncionarios.getModel();//gerencia o que esta na tabela
        scrFuncionarios.setBounds(450,70,500,380);
        ctnFuncionarios.add(scrFuncionarios);
        
        for (String strLegendaFuncionario : strLegendaFuncionarios) {
            mdlFuncionarios.addColumn(strLegendaFuncionario);
        } //fechando for
        
        
        lblBuscaFuncionarios = new JLabel("Busca ");
        lblBuscaFuncionarios.setBounds(450,30,200,40);
        ctnFuncionarios.add(lblBuscaFuncionarios);
        
        txtBuscaFuncionarios = new JTextField();
        txtBuscaFuncionarios.setBounds(595,40,250,20);
        ctnFuncionarios.add(txtBuscaFuncionarios);
        
        String strBuscaFuncionarios[]={"por Código:","por Nome:"};
        cmbBuscaFuncionarios = new JComboBox(strBuscaFuncionarios);
        cmbBuscaFuncionarios.setBounds(490,40,100,20);
        ctnFuncionarios.add(cmbBuscaFuncionarios);
        
        btnBuscaFuncionarios = new JButton("Buscar");
        btnBuscaFuncionarios.addActionListener(this);
        btnBuscaFuncionarios.setBounds(850,40,100,20);
        ctnFuncionarios.add(btnBuscaFuncionarios);
                
        btnRestaurarFuncionarios = new JButton("Restaurar Tabela");
        btnRestaurarFuncionarios.setBounds(800,450,150,20);
        btnRestaurarFuncionarios.addActionListener(this);
        ctnFuncionarios.add(btnRestaurarFuncionarios);
        
        
        btnNovoFuncionarios = new JButton("Novo");
        btnNovoFuncionarios.addActionListener(this);
        btnNovoFuncionarios.setBounds(375, 70, 70, 40);
        ctnFuncionarios.add(btnNovoFuncionarios);
        
        btnSalvarFuncionarios = new JButton("Salvar");
        btnSalvarFuncionarios.addActionListener(this);
        btnSalvarFuncionarios.setBounds(375, 120, 70, 40);
        ctnFuncionarios.add(btnSalvarFuncionarios);
        
        btnStatusFuncionarios = new JButton("Desativar");
        btnStatusFuncionarios.addActionListener(this);
        btnStatusFuncionarios.setBounds(450, 450, 150, 20);
        ctnFuncionarios.add(btnStatusFuncionarios);
        
        
        carregaLista(1);
        
        tblFuncionarios.addMouseListener(new MouseAdapter(){//utilizando o mouse para preencher a tabela
            public void mouseClicked(MouseEvent evt){
                //JOptionPane.showMessageDialog(null, "oi");
                String tmpCodigo;
                tmpCodigo = tblFuncionarios.getValueAt(tblFuncionarios.getSelectedRow(), 0).toString();
                try{
                    preencheCampos(FuncionariosDAO.consultaFuncionario(tmpCodigo, 1));                                        
                }catch(Exception erro){
                   JOptionPane.showMessageDialog(null, erro.getMessage());                    
                }//fechamento try catch.             
            }//fechamento void Mouse
        });//fechamento addMouse
        
        tblFuncionarios.addKeyListener(new KeyAdapter(){//utilizando 
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_DOWN ||
                        evt.getKeyCode() == KeyEvent.VK_UP ||
                            evt.getKeyCode() == KeyEvent.VK_ENTER ||
                                evt.getKeyCode() == KeyEvent.VK_TAB){
                    String tmpCodigo;
                    tmpCodigo = tblFuncionarios.getValueAt(tblFuncionarios.getSelectedRow(), 0).toString();
                    try{
                        preencheCampos(FuncionariosDAO.consultaFuncionario(tmpCodigo, 1));                                        
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
    
    private static void preencheCampos(Funcionarios tmpFuncionario){
        if(tmpFuncionario.getStatus() == 0){
            btnStatusFuncionarios.setText("Ativar");
            for(int i=0; i<strCamposFuncionarios.length; i++){
                txtCamposFuncionarios[i].setForeground(Color.red);
            }
        }else{
            btnStatusFuncionarios.setText("Desativar");
            for(int i=0; i<strCamposFuncionarios.length; i++){
                txtCamposFuncionarios[i].setForeground(Color.black);
            }
        }
        
        txtCamposFuncionarios[0].setText(tmpFuncionario.getEmployeeID());
        txtCamposFuncionarios[1].setText(tmpFuncionario.getFirstName());
        txtCamposFuncionarios[2].setText(tmpFuncionario.getLastName());
        txtCamposFuncionarios[3].setText(tmpFuncionario.getTitle());
        txtCamposFuncionarios[4].setText(tmpFuncionario.getHireDate());
        txtCamposFuncionarios[5].setText(tmpFuncionario.getAddress());
        txtCamposFuncionarios[6].setText(tmpFuncionario.getCity());
        txtCamposFuncionarios[7].setText(tmpFuncionario.getRegion());
        txtCamposFuncionarios[8].setText(tmpFuncionario.getPostalCode());
        txtCamposFuncionarios[9].setText(tmpFuncionario.getHomePhone());
        txtCamposFuncionarios[10].setText(tmpFuncionario.getCountry());
        txtCamposFuncionarios[11].setText(Integer.toString(tmpFuncionario.getStatus()));
        
    }//fechando preencheCampos
            
    
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnBuscaFuncionarios){
            
            if(cmbBuscaFuncionarios.getSelectedIndex()==0){//se o primeiro item da lista estiver selecionado
                //**BUSCA POR CODIGO**
            try{
                Funcionarios dadosFuncionario = FuncionariosDAO.consultaFuncionario(txtBuscaFuncionarios.getText(),1);
                if(dadosFuncionario == null){
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
                }else{
                    carregaLista(1);
                    preencheCampos(dadosFuncionario);
                }//fechado else
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
            }//fechando if combox
            else if(cmbBuscaFuncionarios.getSelectedIndex()==1){
                //**BUSCA POR NOME**
            try{
                carregaLista(2);
                                
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
            }//fechando else if
            
            
        }//fechando if
        else if(evt.getSource()==btnRestaurarFuncionarios){
            carregaLista(1);
        }
        else if(evt.getSource() == btnNovoFuncionarios){
            limpaCampos();
        }//fechando if
        else if(evt.getSource() == btnSalvarFuncionarios){
            try{                
                if(validaCampos()==true){
                    gravaObjeto();
                    FuncionariosDAO.cadastraFuncionario(dadosFuncionario);
                    carregaLista(1);
                    JOptionPane.showMessageDialog(null, "Funcionario " + dadosFuncionario.getFirstName()+ " Cadastrado");
                    limpaCampos();
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());                
            }//fechando try catch
        }//fechando if
        else if(evt.getSource() == btnStatusFuncionarios){
            try{
                
                //Funcionarios dados;
                
                if(txtCamposFuncionarios[0].getText().trim().compareTo("")==0){
                    JOptionPane.showMessageDialog(null, "Não há funcionário selecionado");                   
                }else{
                    Funcionarios dados = FuncionariosDAO.consultaFuncionario(txtCamposFuncionarios[0].getText(),1);
                    String codigo = dados.getEmployeeID();
                    int status = dados.getStatus();
                    
                    FuncionariosDAO.alteraStatus(codigo, status);                                      
                    JOptionPane.showMessageDialog(null, "Status Alterado para o funcionário: " + dados.getFirstName());
                    
                    dados = FuncionariosDAO.consultaFuncionario(txtCamposFuncionarios[0].getText(),1);
                    preencheCampos(dados);
                    carregaLista(1);
                    
                }
                
            }catch(Exception erro){
               JOptionPane.showMessageDialog(null, erro.getMessage()); 
            }//fechando try catch
        }//fechamento if
        
        
    }//fechando actionPerformed
    
    private static void carregaLista(int tmpTipo){
        while(mdlFuncionarios.getRowCount()>0){//método p limpar a lista, já busca a lista atualizada
            mdlFuncionarios.removeRow(0);            
        }
        try{
            java.util.List<Funcionarios>vetorFuncionarios = new ArrayList<>();
            //para nao ocorrer problemas foi colocado java.util pois pode dar ambiguidade
            if(tmpTipo == 1){
                vetorFuncionarios = FuncionariosDAO.listaFuncionarios();  
            }else if(tmpTipo==2){
                vetorFuncionarios = FuncionariosDAO.consultaNome(txtBuscaFuncionarios.getText());
            }
            // ":" quer dizer existente, para cada funcionário existente
            for(Funcionarios funcionarioAtual : vetorFuncionarios){
                String dados[] = new String[5];
                dados[0] = funcionarioAtual.getEmployeeID();
                dados[1] = funcionarioAtual.getFirstName() + " " + funcionarioAtual.getLastName();
                dados[2] = funcionarioAtual.getTitle();
                dados[3] = funcionarioAtual.getCountry();
                dados[4] = Integer.toString(funcionarioAtual.getStatus());
                
                mdlFuncionarios.addRow(dados);
                
            }//fechando for
            
                
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }//fechando try
    }//fechando void carregaLista
    
    private void gravaObjeto(){
        dadosFuncionario.setEmployeeID(txtCamposFuncionarios[0].getText());
        dadosFuncionario.setFirstName(txtCamposFuncionarios[1].getText());
        dadosFuncionario.setLastName(txtCamposFuncionarios[2].getText());
        dadosFuncionario.setTitle(txtCamposFuncionarios[3].getText());
        dadosFuncionario.setHireDate(txtCamposFuncionarios[4].getText());
        dadosFuncionario.setAddress(txtCamposFuncionarios[5].getText());
        dadosFuncionario.setCity(txtCamposFuncionarios[6].getText());
        dadosFuncionario.setRegion(txtCamposFuncionarios[7].getText());
        dadosFuncionario.setPostalCode(txtCamposFuncionarios[8].getText());
        dadosFuncionario.setHomePhone(txtCamposFuncionarios[9].getText());
        dadosFuncionario.setCountry(txtCamposFuncionarios[10].getText());    
        
        
        
        
    }//fechamento gravaObjeto
    
    private void limpaCampos(){
        for(int i=0; i<strCamposFuncionarios.length; i++){
            txtCamposFuncionarios[i].setText(null);
        }//fechamento for
        
    }//fechamento limpaCampos
    
    private static boolean validaCampos(){
        for(int i=0; i<txtCamposFuncionarios.length; i++){
            if(txtCamposFuncionarios[i].getText().trim().compareTo("")==0){
                JOptionPane.showMessageDialog(null, "Preencha o campo: " + strCamposFuncionarios[i]);
                txtCamposFuncionarios[i].grabFocus();//move o cursor
                return false;
            }        
        }        
        return true;
    }//fechamento validaCampos
    
}
