package gestion;

import interfaces.IGestionProyectos;

import java.util.ArrayList;
import java.util.List;

public class GestionProyectos implements IGestionProyectos {
    private final List<String> proyectos;

    public GestionProyectos() {
        this.proyectos = new ArrayList<>();
    }

    @Override
    public void asignarProyecto(String proyecto) {
        proyectos.add(proyecto);
    }

    @Override
    public void finalizarProyecto(String proyecto) {
        proyectos.remove(proyecto);
    }

    @Override
    public List<String> obtenerProyectos() {
        return new ArrayList<>(proyectos);
    }
}
