import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SistemaView extends JFrame implements ActionListener{
    //declaração dos objetos
    public static Container CntPrincipal, CtnTopo, CtnMenu;
    public static JLabel LblBanner;
    public static JButton btnMenu[];
    public static String strModulos[]={"Clientes", "Funcionarios","Fornecedores", "Produtos", "Vendas", "Caixa", "Usuários", "Sair"};
    public static JDesktopPane dskJanelas;
    public static boolean a = true;
    public SistemaView(){
        super("Sistema Modular");
        //Construção e configuração
        CntPrincipal= new Container();//Criando Container
        CntPrincipal.setLayout(new BorderLayout());
        CtnTopo=new Container();
        CtnTopo.setLayout(new GridLayout(2,1));
        CtnMenu=new Container();
        CtnMenu.setLayout(new GridLayout(2,4));
        LblBanner = new JLabel(new ImageIcon(""));
        btnMenu=new JButton[8];//define quantidade de botões
        
        
        btnMenu[0]=new JButton(new ImageIcon(""));
        btnMenu[0].setBackground(Color.WHITE);
        btnMenu[0].setToolTipText(strModulos[0]);
        btnMenu[0].addActionListener(this);
        CtnMenu.add(btnMenu[0]);//Adicionando botões no ctnMenu
        
        dskJanelas = new JDesktopPane();
        

        //adicionando elementos no Container
        this.add(CntPrincipal);//adicionando Container principal na janela
        CntPrincipal.add(CtnTopo,BorderLayout.NORTH);//adicionando layout no norte do principal
        CtnTopo.add(LblBanner);//adicionando banner na linha do topo
        CtnTopo.add(CtnMenu);
        CntPrincipal.add(dskJanelas, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(montarTela());
        this.show();
        this.setResizable(false);
        
        a = false;
        if (a == false){
            ClientesView telaClientesView = new ClientesView();
            SistemaView.btnMenu[0].setEnabled(false);
            dskJanelas.add(telaClientesView);
        }
        
    }
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==btnMenu[0]);
        //Abrir tela de Clientes
        ClientesView telaClientesView = new ClientesView();
        SistemaView.btnMenu[0].setEnabled(false);
        dskJanelas.add(telaClientesView);
    }
    public static Dimension montarTela() {

        Toolkit info = Toolkit.getDefaultToolkit();//Criando objeto para acessar informações do sitema
        Dimension res = info.getScreenSize();//pegando resolução da tela e guardando na variavel 'res'
        return res;//retornando a resolução
    }//fechando montarTela()
}
