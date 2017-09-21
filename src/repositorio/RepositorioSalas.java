package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Sala;

public class RepositorioSalas {

    private List<Sala> salas;

    public RepositorioSalas() {
        salas = new ArrayList<Sala>();
    }

    public boolean addSala(Sala sala) {
        return (salas.add(sala));
    }

    public List<Sala> getListaSala() {
        return salas;
    }

    public boolean salaExiste(int numeroSala) {
        for (Sala sala : salas) {
            if (sala.getNumeroSala() == numeroSala) {
                return true;
            }
        }
        return false;
    }
    
    public Sala buscarSala(int numeroSala){
        for (Sala sala : salas) {
            if (sala.getNumeroSala() == numeroSala) {
                return sala;
            }
        }
        return null;
    }
}