package interfaces;

import java.util.List;

public interface IGestionProyectos {
    void asignarProyecto(String proyecto);
    void finalizarProyecto(String proyecto);
    List<String> obtenerProyectos();
}
