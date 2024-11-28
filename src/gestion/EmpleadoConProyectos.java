package gestion;

import gestion.GestionProyectos;
import interfaces.IEmpleado;
import interfaces.IGestionProyectos;

public class EmpleadoConProyectos {
    private final IEmpleado empleado;
    private final IGestionProyectos gestionProyectos;

    public EmpleadoConProyectos(IEmpleado empleado) {
        this.empleado = empleado;
        this.gestionProyectos = new GestionProyectos();
    }

    public IEmpleado getEmpleado() {
        return empleado;
    }

    public IGestionProyectos getGestionProyectos() {
        return gestionProyectos;
    }
}
