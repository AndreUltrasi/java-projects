public class Clientes {

    public String codigo, nomeEmpresa, nomeRepresentante, cargo, endereco, cidade,
            estado, pais, cep, telefone, email;
    public int status; 

    public Clientes(){
        
    }
    
    public Clientes(String tmpCodigo, String tmpNomeEmpresa, String tmpNomeRepresentante, String tmpCargo, String tmpEndereco, String tmpCidade, String tmpEstado, String tmpPais, String tmpCep, String tmpTelefone, String tmpEmail, int tmpStatus) {
        this.setStatus(tmpStatus);
        this.setCodigo(tmpCodigo);
        this.setNomeEmpresa(tmpNomeEmpresa);
        this.setNomeRepresentante(tmpNomeRepresentante);
        this.setCargo(tmpCargo);
        this.setEndereco(tmpEndereco);
        this.setCidade(tmpCidade);
        this.setEstado(tmpEstado);
        this.setPais(tmpPais);
        this.setCep(tmpCep);
        this.setTelefone(tmpTelefone);
        this.setEmail(tmpEmail);
        
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public void setNomeRepresentante(String nomeRepresentante) {
        this.nomeRepresentante = nomeRepresentante;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setStatus(int tmpStatus){
        status=tmpStatus;
    }
    
    public int getStatus(){
        return status;
    }
            
 
}
