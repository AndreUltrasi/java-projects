
public class Clientes {
    public String codigo, nomeRepresentante, telefone;

    public Clientes() {
    }

    public Clientes(String tmpcodigo, String tmpnomeRepresentante, String tmptelefone) {
        this.setCodigo(tmpcodigo);
        this.setNomeRepresentante(tmpnomeRepresentante);
        this.setTelefone(tmptelefone);
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String tmpcodigo) {
        codigo = tmpcodigo;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public void setNomeRepresentante(String tmpnomeRepresentante) {
        nomeRepresentante = tmpnomeRepresentante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String tmptelefone) {
        telefone = tmptelefone;
    }
    
}