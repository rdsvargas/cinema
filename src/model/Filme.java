package model;

/**
 *
 * @author Roger
 */
public class Filme {

    private int id;
    private String nome;
    private String genero;
    private String sinopsia;

    /**
     *
     * @param id Identificador do filme (int))
     * @param nome  Nome do filme (Character (30))
     * @param genero Genero do filme (Character (20))
     * @param sinopsia Sinopsia da filme (Character (50))
     */
    public Filme(int id, String nome, String genero, String sinopsia) { // throws Exception {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }

    /**
     *
     * @param nome Nome do filme (Character (30))
     * @param genero Genero do filme (Character (20))
     * @param sinopsia Sinopsia do filme (Character (50))
     */
    public Filme(String nome, String genero, String sinopsia) {

        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }
    
    /**
     *
     * @return Identificador do filme
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id Identificador do filme
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return Nome do filme
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome Nome do filme
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return Genero do filme
     */
    public String getGenero() {
        return genero;
    }

    /**
     *
     * @param genero Genero do filme
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     *
     * @return Sinopsia do filme
     */
    public String getSinopsia() {
        return sinopsia;
    }

    /**
     *
     * @param sinopsia Sinopsia do filme
     */
    public void setSinopsia(String sinopsia) {
        this.sinopsia = sinopsia;
    }

    @Override
    public String toString() {
        return "Filme{" + "codigo=" + this.id + ", nome=" + this.nome + ", genero=" + this.genero + ", sinopsia=" + this.sinopsia + '}';
    }
}
