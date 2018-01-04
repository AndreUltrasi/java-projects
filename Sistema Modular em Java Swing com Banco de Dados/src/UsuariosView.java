
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class UsuariosView extends JInternalFrame implements ActionListener{
    public static Container ctnLogin;
    public static JLabel lblUsuario, lblSenha;
    public static JTextField txtUsuario;
    public static JPasswordField pwdSenha;
    public static JButton btnLogin;
    
    public static JDesktopPane dskJanelas;
    
    public UsuariosView(){
        super("Login View");
        ctnLogin = new Container();
        ctnLogin.setLayout(null);
        this.add(ctnLogin);
                
        lblSenha = new JLabel();
        pwdSenha = new JPasswordField();
       
        lblUsuario = new JLabel("Usuários");
        lblUsuario.setBounds(20, 10,300,100);
        ctnLogin.add(lblUsuario);
        
        lblSenha = new JLabel("Senha");
        lblSenha.setBounds(20,50,300,100);
        ctnLogin.add(lblSenha);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(80,45,180,30);
        ctnLogin.add(txtUsuario);
        
        pwdSenha = new JPasswordField();
        pwdSenha.setBounds(80,85,180,30);
        ctnLogin.add(pwdSenha);
        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(80,140,150,40);
        btnLogin.addActionListener(this);
        ctnLogin.add(btnLogin);
        

        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setSize(300, 240);
        this.show();
        this.setResizable(false);
        this.setLocation(550, 150);
    }  
    
    
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnLogin){
            try{
                 
                Usuarios tmpUsuario = UsuariosDAO.validaUsuario(txtUsuario.getText(), pwdSenha.getText());
                if(tmpUsuario == null){
                    JOptionPane.showMessageDialog(null, "Dados inválidos");
                    txtUsuario.grabFocus();
                }else{
                    if(tmpUsuario.getPermissao() == 1){
                        //adm
                        SistemaView.desbloqueiaMenu(true);
                    }else if (tmpUsuario.getPermissao() == 2){
                       //normal
                       SistemaView.desbloqueiaMenu(true);
                       SistemaView.btnMenu[1].setEnabled(false);
                       SistemaView.btnMenu[6].setEnabled(false);
                    }
                    this.dispose();
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        //this.show();
        //ClientesView telaClientes = new ClientesView();
        //dskJanelas.add(ctnLogin);
    }
}
