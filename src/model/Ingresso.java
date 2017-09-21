package model;


public class Ingresso {
    private int ingressosVendidos;
    private Sessao sessao;
    
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
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
    public void incrementaIngresso(){
        this.ingressosVendidos++;
    }
}
