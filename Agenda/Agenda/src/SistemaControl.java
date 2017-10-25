

/**
 *
 * @author 28253995
 */
public class SistemaControl {

   
    public static void main(String[] args) {
        try {
            ConexaoDao.abreConexao();
            ConexaoDao.fechaConexao();
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
        SistemaView objSistema = new SistemaView();
    }
    
}
