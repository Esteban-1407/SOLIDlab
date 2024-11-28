package gestion;

import java.util.ArrayList;
import java.util.List;

public class GestorTiposEmpleado {
    private final List<String> tiposEmpleado = new ArrayList<>();

    public GestorTiposEmpleado() {
        // Inicializamos con los tipos base
        tiposEmpleado.add("Tiempo Completo");
        tiposEmpleado.add("Tiempo Parcial");
        tiposEmpleado.add("Freelance");
    }

    public void agregarNuevoTipo(String tipo) {
        if (!tiposEmpleado.contains(tipo)) {
            tiposEmpleado.add(tipo);
        }
    }

    public List<String> obtenerTipos() {
        return new ArrayList<>(tiposEmpleado);
    }

}