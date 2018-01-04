public class SistemaControl {

    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            ConexaoDAO.abreConexao();
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            System.out.println(erro.getMessage());
        }
        
        //LoginView osesd = new LoginView();
        SistemaView objSistema = new SistemaView();
        
    }
    
}
