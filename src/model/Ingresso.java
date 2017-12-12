package model;

import util.DateUtil;

/**
 *
 * @author Roger
 */
public class Ingresso {
    private int id;
    private int qtdIngresso;
    private Sessao sessao;
    private String nomeSala;
    private String nomeFilme;
    private String sessaoHora;

    
    /**
     *
     * @param sessao Object Sessao
     */
    public Ingresso(Sessao sessao){
        this.sessao = sessao;
    }

    public Ingresso(Sessao sessao, int qtdIngresso){
        this.sessao = sessao;
        this.qtdIngresso = qtdIngresso;
    }

    public Ingresso(int id, int qtdIngresso, Sessao sessao){
        this.id = id;
        this.sessao = sessao;
        this.qtdIngresso = qtdIngresso;
    }
    /**
     *
     * @return Object Sessao
     */
    public Sessao getSessao() {
        return this.sessao;
    }

    /**
     *
     * @param sessao Object Sessao
     */
    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
    /**
     *
     * @return Identificador ingresso
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id Identificador Ingresso
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeSala() {
        return this.sessao.getNomeSala();
    }

    public String getNomeFilme() {
        return this.sessao.getNomeFilme();
    }

    public String getSessaoHora() {
        return DateUtil.timeToString(this.sessao.getHora());
    }

    public void setQtdIngresso(int qtdIngresso) {
        this.qtdIngresso = qtdIngresso;
    }

    public int getQtdIngresso() {
        return qtdIngresso;
    }

    
    @Override
    public String toString() {
        return "Ingresso{" + "id=" + id + ", sessao=" + sessao + '}';
    }
}
