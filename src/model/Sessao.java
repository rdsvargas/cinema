package model;

import java.time.LocalTime;

/**
 *
 * @author Roger
 */
public class Sessao {

    private int id;
    private LocalTime hora;
    private Sala sala;
    private Filme filme;
    private int qtdAssentos;
    private int ingressos_vendidos;

    public Sessao(LocalTime hora, Sala sala, Filme filme) {
        this.hora = hora;
        this.sala = sala;
        this.filme = filme;
        this.qtdAssentos = sala.getQtdAssentos();
    }

    public Sessao(int id, LocalTime hora, Sala sala, Filme filme, int ingressos_vendidos) {
        this.id = id;
        this.hora = hora;
        this.sala = sala;
        this.filme = filme;
        this.qtdAssentos = sala.getQtdAssentos();
        this.ingressos_vendidos = ingressos_vendidos;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Filme getFilme() {
        return this.filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getIngressos_vendidos() {
        return this.ingressos_vendidos;
    }

    public void setIngressos_vendidos(int ingressos_vendidos) {
        this.ingressos_vendidos = ingressos_vendidos;
    }

    @Override
    public String toString() {
        return "Sessao{" + "id=" + id + ", hora=" + hora + ", sala=" + sala + ", filme=" + filme + ", qtdAssentos=" + qtdAssentos + '}';
    }
    
}
