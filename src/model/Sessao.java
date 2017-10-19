package model;

import java.time.LocalTime;

/**
 *
 * @author Roger
 */
public class Sessao {

    private LocalTime hora;
    private Sala sala;
    private Filme filme;
    private int qtdAssentos;

    public Sessao(LocalTime hora, Sala sala, Filme filme) {
        this.hora = hora;
        this.sala = sala;
        this.filme = filme;
        this.qtdAssentos = sala.getQtdAssentos();
    }

    public LocalTime getHora() {
        return hora;
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
        return filme;
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

}
