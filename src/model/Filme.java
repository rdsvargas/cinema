package model;

public class Filme {

    private int codigo;
    private String nome;
    private String genero;
    private String sinopsia;

    public Filme(int codigo, String nome, String genero, String sinopsia) throws Exception {
        // validação dos dados informados
        this.validaData(codigo, "Código", 0);
        this.validaData(nome, "Filme", 30);
        this.validaData(genero, "Gênero", 20);
        this.validaData(sinopsia, "Sinópsia", 50);

        this.codigo = codigo;
        this.nome = nome;
        this.genero = genero;
        this.sinopsia = sinopsia;
    }

    private void validaData(Object data, String desc, int size) throws Exception {
        if (data instanceof String) {
            if (((String) data).length() > size) {
                throw new Exception(desc + " não pode ser maior que " + size +" caracteres.");
            } else if (((String) data).isEmpty()) {
                throw new Exception(desc + " inválido.");
            }
        } else {
            if ((int) data <= 0) {
                throw new Exception(desc + " inválido");
            }
        }
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
