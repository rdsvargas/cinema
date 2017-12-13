package model;

/**
 *
 * @author Roger
 */
public class Sala {

    private int id;
    private String codigo;
    private int qtdAssentos;

    /**
     *
     * @param id Identificador da sala
     * @param codigo Identificação da sala
     * @param qtdAssentos Quantidade de assentos da sala
     */
    public Sala(int id, String codigo, int qtdAssentos){
        this.id = id;
        this.codigo = codigo;
        this.qtdAssentos = qtdAssentos;
    }

    /**
     *
     * @param codigo Identificação da sala
     * @param qtdAssentos Quantidade de assentos da sala
     */
    public Sala(String codigo, int qtdAssentos){
        this.codigo = codigo;
        this.qtdAssentos = qtdAssentos;
    }
            
    /**
     *
     * @return Identificador da sala
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id Idenficador da sala
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return Identificação da sala
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo Identificação da sala
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return Quantidade de assentos da sala
     */
    public int getQtdAssentos() {
        return qtdAssentos;
    }

    /**
     *
     * @param qtdAssentos Quantidade de assentos da sala
     */
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
    
    @Override
    public String toString() {
        //return "Sala{Id=" + this.id + ", Código Sala=" + this.codigo + ", Qtd Assentos=" + this.qtdAssentos + '}';
        return this.getCodigo();
    }
}
