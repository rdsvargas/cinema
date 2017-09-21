package model;

public class Sala {

    private int numeroSala;
    private int qtdAssentos;

    public Sala(int numeroSala, int qtdAssentos){
        this.numeroSala = numeroSala;
        this.qtdAssentos = qtdAssentos;
    }
    
    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    @Override
    public String toString() {
        return "Sala{" + "Nr. Sala=" + numeroSala + ", Qtd Assentos=" + qtdAssentos + '}';
    }
}
