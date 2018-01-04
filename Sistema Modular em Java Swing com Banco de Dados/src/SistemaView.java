import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SistemaView extends JFrame implements ActionListener{
    
    // 1º - Declaração dos objetos
    public static boolean jInternalFrameAttached = false;
    public static Container ctnPrincipal,ctnTopo,ctnMenu;
    public static JLabel lblBanner;
    public static JButton btnMenu[];
    public static String strModulos[] = {"Clientes", "Funcionários", 
        "Fornecedores", "Produtos", "Vendas", "Caixa", "Usuários", "Sair"};
    public static  JDesktopPane dskJanelas;
    
    public SistemaView(){
        // 2º - Construção dos objetos e configuração dos mesmos
        super("Sistema Modular - 4º Semestre - CIC");
        //o metodo super altera o atributo da classe superior
        //pode se usar para mais coisas
        
        ctnPrincipal = new Container();
        ctnPrincipal.setLayout(new BorderLayout());
        
        ctnTopo = new Container();
        ctnTopo.setLayout(new GridLayout(2,1));
        
        ctnMenu = new Container();
        ctnMenu.setLayout(new GridLayout(2,4));
        
        lblBanner = new JLabel(new ImageIcon(" "));
        
        btnMenu = new JButton[8];//definindo o número de botões
        
        for(int i=0; i<btnMenu.length; i++){
            btnMenu[i] = new JButton(new ImageIcon("img/" + i + ".png"));//configuração do botão strModulos[i]
            btnMenu[i].setBackground(Color.WHITE);
            lblBanner = new JLabel(new ImageIcon("img/banner.png"));
            btnMenu[i].setToolTipText(strModulos[i]);//configuração do botão
            btnMenu[i].addActionListener(this);//todos os botões ficam prontos para receber a programação
            ctnMenu.add(btnMenu[i]);//adicionando botões no Container menu
            
        }//fechando for
        
        dskJanelas = new JDesktopPane();//estanciando desktop
        
        //adicionando elementos do container
        this.add(ctnPrincipal);//adicionando container principal na janela
        ctnPrincipal.add(ctnTopo,BorderLayout.NORTH);//adicionando topo no norte do principal
        ctnTopo.add(lblBanner);//adiconando banner na primeira linha do topo
        ctnTopo.add(ctnMenu);//adicionando Menu na segunda linha do topo
        
        ctnPrincipal.add(dskJanelas, BorderLayout.CENTER);
        
        // construir login
        dskJanelas.add(new UsuariosView());
        desbloqueiaMenu(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(montarTela());
        this.show();
        this.setResizable(true);
        
}//fechamento Sistema View
  
    
    // 3° - Construindo o ActionPerformed 
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource( ) == btnMenu[0]){
            for (int i = 0; i<btnMenu.length; i++){
                btnMenu[i].setEnabled(true);
            }
            if(jInternalFrameAttached == true){
                dskJanelas.remove(0);
            }
            
            //abrir tela de Clientes
            btnMenu[0].setEnabled(false);
            ClientesView telaClientes = new ClientesView();
            
            jInternalFrameAttached = true;
            dskJanelas.add(telaClientes);
            
            
        }else if(evt.getSource() == btnMenu[1]){
            
            for (int i = 0; i<btnMenu.length; i++){
                btnMenu[i].setEnabled(true);
            }
            if(jInternalFrameAttached == true){
                dskJanelas.remove(0);
            }
            
            //abrir tela de Funcionarios
            btnMenu[1].setEnabled(false);
            FuncionariosView telaFuncionarios = new FuncionariosView();
            jInternalFrameAttached = true;
            dskJanelas.add(telaFuncionarios);
            
        }else if(evt.getSource() == btnMenu[3]){
            for (int i = 0; i<btnMenu.length; i++){
                btnMenu[i].setEnabled(true);
            }
            
            if(jInternalFrameAttached == true){
                dskJanelas.remove(0);
            }
            
            //abrir tela de Funcionarios
            btnMenu[3].setEnabled(false);
            ProdutosView telaProdutos = new ProdutosView();
            jInternalFrameAttached = true;
            dskJanelas.add(telaProdutos);
        }
        
}//fechamento void actionPerformed
    
   public static Dimension montarTela(){
       Dimension res;
       Toolkit info = Toolkit.getDefaultToolkit();
       res=info.getScreenSize();
       return res;
   }//fechando montarTela 
   public static void desbloqueiaMenu(boolean tmpStatus){
       for(int i = 0; i<btnMenu.length; i++){
           btnMenu[i].setEnabled(tmpStatus);
       }
   }
}//fechamento classe
