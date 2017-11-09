package model;


public class Ingresso {
    private int id;
    private Sessao sessao;
    
    public Ingresso(Sessao sessao){
        this.sessao = sessao;
    }

    public Sessao getSessao() {
        return this.sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ingresso{" + "id=" + id + ", sessao=" + sessao + '}';
    }
}
