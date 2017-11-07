package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Filme;

public class RepositorioFilmes {

    private List<Filme> filmes;

    public RepositorioFilmes() {
        filmes = new ArrayList<Filme>();
    }

    public boolean addFilme(Filme filme) {
        return (filmes.add(filme));
    }

    public List<Filme> getListaFilmes() {
        return filmes;
    }

    public boolean filmeExiste(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().toUpperCase().equals(nome.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public int proximoCodigo() {
        return filmes.size() + 1;
    }

    public Filme buscarFilme(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equals(nome)) {
                return filme;
            }
        }
        return null;
    }

    public Filme buscarFilmePorCodigo(int codigo) {
        for (Filme filme : filmes) {
            if (filme.getId() == codigo) {
                return filme;
            }
        }
        return null;
    }
}
