package model;

public class Filme {

    private int id;
    private String nome;
    private String genero;
    private String sinopsia;

    public Filme(int id, String nome, String genero, String sinopsia) { // throws Exception {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }

    public Filme(String nome, String genero, String sinopsia) {

        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Filme{" + "codigo=" + this.id + ", nome=" + this.nome + ", genero=" + this.genero + ", sinopsia=" + this.sinopsia + '}';
    }
}
