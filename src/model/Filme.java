package model;

public class Filme {

    private int codigo;
    private String nome;
    private String genero;
    private String sinopsia;

    public Filme(int codigo, String nome, String genero, String sinopsia) { // throws Exception {
        this.codigo = codigo;
        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }

    public Filme(String nome, String genero, String sinopsia) {

        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsia() {
        return sinopsia;
    }

    public void setSinopsia(String sinopsia) {
        this.sinopsia = sinopsia;
    }

    @Override
    public String toString() {
        return "Filme{" + "codigo=" + codigo + ", nome=" + nome + ", genero=" + genero + ", sinopsia=" + sinopsia + '}';
    }
}
