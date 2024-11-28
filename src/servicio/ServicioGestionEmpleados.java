package servicio;

import gestion.EmpleadoConProyectos;
import enums.TipoEmpleado;
import interfaces.IEmpleado;
import interfaces.IRepositorioEmpleados;

import java.util.List;

public class ServicioGestionEmpleados {
    private final IRepositorioEmpleados repositorio;

    public ServicioGestionEmpleados(IRepositorioEmpleados repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarEmpleado(IEmpleado empleado) {
        repositorio.agregar(empleado);
    }

    public List<IEmpleado> obtenerEmpleados() {
        return repositorio.obtenerTodos();
    }

    public List<IEmpleado> obtenerEmpleadosPorTipo(TipoEmpleado tipo) {
        return repositorio.obtenerPorTipo(tipo);
    }

    public EmpleadoConProyectos convertirAGestorProyectos(String id) {
        return repositorio.buscarPorId(id)
                .map(EmpleadoConProyectos::new)
                .orElseThrow(() -> new IllegalArgumentException("empleados.Empleado no encontrado"));
    }
}
