import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;

public class ClientesView extends JInternalFrame implements ActionListener{
    Clientes dadosClientes = new Clientes();
    
    public static Container ctnClientes;
    public static String strLegenda[]={"código","Nome","Telefone"};
    public static String strCampos[]={"Código:", "Nome:", "Telefone:"};
    public static JLabel lblCampos[];
    public static JTextField txtCampos[];
    public static JTable tblClientes;
    public static JScrollPane scrClientes;
    public static DefaultTableModel mdlClientes;
    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca, btnRestaurar, btnExcluir,btnSalvar ;
    public ClientesView(){
        super ("Modulos Clientes");
        ctnClientes= new Container();//instancia do container
        ctnClientes.setLayout(null);//marcando aqui
        this.add(ctnClientes);
        //Construção dos objetos
        lblCampos= new JLabel[strCampos.length];
        txtCampos= new JTextField[strCampos.length];
        for(int i=0;i<strCampos.length;i++){
            //Construção das labels(rótulos)
            lblCampos[i]=new JLabel(strCampos[i]);
            txtCampos[i]= new JTextField();
            lblCampos[i].setBounds(10,40+(50*i),150,20);
            txtCampos[i].setBounds(170,40+(i*50),300,20);
            ctnClientes.add(lblCampos[i]);
            ctnClientes.add(txtCampos[i]);
            //construção
        }
        tblClientes=new JTable();
        scrClientes=new JScrollPane(tblClientes);
        
        mdlClientes=(DefaultTableModel)tblClientes.getModel();
        for(int i=0; i<strLegenda.length;i++){
            mdlClientes.addColumn(strLegenda[i]);
        }
        scrClientes.setBounds(900,100,500,500);
        ctnClientes.add(scrClientes);
        //botões
        
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this);
        btnExcluir.setBounds(550, 100, 100, 30);
        ctnClientes.add(btnExcluir);
        
        
        btnSalvar = new JButton("Cadastrar");
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(650, 100, 100, 30);
        ctnClientes.add(btnSalvar);
        
 
        
        btnBusca=new JButton("Busca");
        btnBusca.addActionListener(this);
        btnBusca.setBounds(1250,60,100,30);
        ctnClientes.add(btnBusca);
        txtBusca=new JTextField();
        txtBusca.setBounds(1100,60,150,30);
        ctnClientes.add(txtBusca);
        
        lblBusca=new JLabel("Busca do Código");
        lblBusca.setBounds(1000,60,200,30);
        ctnClientes.add(lblBusca);
        
        carregaLista();
        tblClientes.addMouseListener(
         new MouseAdapter(){
             public void mouseClicked(MouseEvent evt){
                 String tmpCodigo;
                 tmpCodigo =tblClientes.getValueAt(tblClientes.getSelectedRow(),0).toString();
                 try {
                     preencheCampos(ClientesDAO.consultaCliente(tmpCodigo,1));
                 }catch(Exception erro){
                     //JOptionPane.showMessageDialog(null,"cliente cadastrado");
                    // JOptionPane.showMessageDialog(null,erro.getMessage());
                 }    
             }
        }
        );
        
        tblClientes.addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent evt){
                if(evt.getKeyCode()== KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP
                    || evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
              
                    String tmpCodigo;
                    tmpCodigo =tblClientes.getValueAt(tblClientes.getSelectedRow(),0).toString();
                 try {
                    preencheCampos(ClientesDAO.consultaCliente(tmpCodigo,1));
                 }catch(Exception erro){
                     //JOptionPane.showMessageDialog(null,erro.getMessage());
                 }  
                }
            }
        }
        );
                
        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(),SistemaView.dskJanelas.getHeight());
        this.show();
        this.setMaximizable(false);
        this.setResizable(false);
    }
    public static void preencheCampos(Clientes tmpCliente){
        txtCampos[0].setText(tmpCliente.getCodigo());
        txtCampos[1].setText(tmpCliente.getNomeRepresentante());
        txtCampos[2].setText(tmpCliente.getTelefone());
        
    }
    
    
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnBusca){
            try{
                Clientes dadosCliente = ClientesDAO.consultaCliente(txtBusca.getText(), 1);
                if(dadosCliente == null){
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                }else{
                    preencheCampos(dadosCliente);
                    JOptionPane.showMessageDialog(null,"Cliente encontrado");
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
 
        }
        if(evt.getSource() == btnExcluir){
            
            try{
            dadosClientes.setCodigo(txtCampos[0].getText());
            dadosClientes.setNomeRepresentante(txtCampos[1].getText());
            dadosClientes.setTelefone(txtCampos[2].getText());
            ClientesDAO.excluiCliente(dadosClientes);
            carregaLista();
        
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null,erro.getMessage());
            }
        }
        if(evt.getSource() == btnSalvar){
         try{
             gravaCliente();
             ClientesDAO.cadastraCliente(dadosClientes);
             carregaLista();
             JOptionPane.showMessageDialog(null,"Cliente " + dadosClientes.getNomeRepresentante()+ " cadastrado");
             
         }catch(Exception erro){
             JOptionPane.showMessageDialog(null,erro.getMessage());
         }
        }
    }
    // nao usamos throw Exception pois eh ela mesma que mostra o Exception
    public static void carregaLista(){
        while(mdlClientes.getRowCount()>0){
            mdlClientes.removeRow(0); // limpa a lista
        }
        
        
        try{
            java.util.List<Clientes> vetorClientes = new ArrayList<Clientes>();
            vetorClientes = ClientesDAO.listaClientes();
            // para cada cliente existente no vetorCLientes faca
            for(Clientes clienteAtual: vetorClientes){
                String dados[] = new String[4];
                dados[0] = clienteAtual.getCodigo();
                dados[1] = clienteAtual.getNomeRepresentante();
                dados[2] = clienteAtual.getTelefone();
                mdlClientes.addRow(dados);
            }
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        
    }
    public void limpaCampos(){
        for(int i = 0; i< strCampos.length; i++){
            txtCampos[i].setText(null);
        }
    }
    public void gravaCliente(){
        dadosClientes.setCodigo(txtCampos[0].getText());
        dadosClientes.setNomeRepresentante(txtCampos[1].getText());
        dadosClientes.setTelefone(txtCampos[2].getText());
        
    }
    
}
