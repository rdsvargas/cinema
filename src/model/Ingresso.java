package model;


public class Ingresso {
    private int id;
    private int ingressosVendidos;
    private Sessao sessao;
    private Sala sala;
    
    public Ingresso(int ingressosVendidos, Sessao sessao){
        this.ingressosVendidos = ingressosVendidos;
        this.sessao = sessao;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public void setIngressosVendidos(int ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }

    public Sessao getSessao() {
        return this.sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Sala getSala() {
        return this.sala;
    }
    
    public void setSala(Sala sala){
        this.sala = sala;
    }
    
    public void incrementaIngresso(){
        this.ingressosVendidos++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
