package model;

public class Sala {

    private int id;
    private String codigo;
    private int qtdAssentos;

    public Sala(int id, String codigo, int qtdAssentos){
        this.id = id;
        this.codigo = codigo;
        this.qtdAssentos = qtdAssentos;
    }

    public Sala(String codigo, int qtdAssentos){
        this.codigo = codigo;
        this.qtdAssentos = qtdAssentos;
    }
            
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCode(String codigo) {
        this.codigo = codigo;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
    
    @Override
    public String toString() {
        return "Sala{Id=" + this.id + ", Código Sala=" + this.codigo + ", Qtd Assentos=" + this.qtdAssentos + '}';
    }
}
