package model;

/**
 *
 * @author Roger
 */
public class Ingresso {
    private int id;
    private Sessao sessao;
    
    /**
     *
     * @param sessao Object Sessao
     */
    public Ingresso(Sessao sessao){
        this.sessao = sessao;
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

    @Override
    public String toString() {
        return "Ingresso{" + "id=" + id + ", sessao=" + sessao + '}';
    }
}
