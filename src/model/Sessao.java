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
    private String nomeSala;
    private String nomeFilme;

    public Sessao(int id, LocalTime hora, Sala sala, Filme filme){
        this.id = id;
        this.hora = hora;
        this.sala = sala;
        this.filme = filme;
        this.qtdAssentos = sala.getQtdAssentos();
    }
    
    /**
     *
     * @param hora Horario da sessao
     * @param sala Object Sala
     * @param filme Object Filme
     */
    public Sessao(LocalTime hora, Sala sala, Filme filme) {
        this.hora = hora;
        this.sala = sala;
        this.filme = filme;
        this.qtdAssentos = sala.getQtdAssentos();
    }

    /**
     *
     * @param id Identificador da sessao
     * @param hora Horario da sessao
     * @param sala Object Sala
     * @param filme object Filme
     * @param ingressos_vendidos Quantidade de ingressos vendidos
     */
    public Sessao(int id, LocalTime hora, Sala sala, Filme filme, int ingressos_vendidos) {
        this.id = id;
        this.hora = hora;
        this.sala = sala;
        this.filme = filme;
        this.qtdAssentos = sala.getQtdAssentos();
        this.ingressos_vendidos = ingressos_vendidos;
    }

    /**
     *
     * @return Horario da sessao
     */
    public LocalTime getHora() {
        return this.hora;
    }

    /**
     *
     * @param hora Horario da sessao
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getNomeSala(){
        return sala.getCodigo();
    }
    
    /**
     *
     * @return Object Sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     *
     * @param sala Object Sala
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     *
     * @return Object Filme
     */
    public Filme getFilme() {
        return this.filme;
    }
    
    public String getNomeFilme(){
        return this.filme.getNome();
    }

    /**
     *
     * @param filme Object Filme
     */
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    /**
     *
     * @return Quantidade de assentos
     */
    public int getQtdAssentos() {
        return qtdAssentos;
    }

    /**
     *
     * @param qtdAssentos Quantidade de assentos
     */
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    /**
     *
     * @return Idenficador da sessao
     */
    public int getId(){
        return this.id;
    }
    
    /**
     *
     * @param id Identificados da sessao
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     *
     * @return Quantidade de ingressos vendidos
     */
    public int getIngressos_vendidos() {
        return this.ingressos_vendidos;
    }

    /**
     *
     * @param ingressos_vendidos Quantidade de ingressos vendidos
     */
    public void setIngressos_vendidos(int ingressos_vendidos) {
        this.ingressos_vendidos = ingressos_vendidos;
    }

    @Override
    public String toString() {
        return "Sessao{" + "id=" + id + ", hora=" + hora + ", sala=" + sala + ", filme=" + filme + ", qtdAssentos=" + qtdAssentos + '}';
    }
    
}
