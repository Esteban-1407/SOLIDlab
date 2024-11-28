package repositorio;

import enums.TipoEmpleado;
import interfaces.IEmpleado;
import interfaces.IRepositorioEmpleados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioEmpleados implements IRepositorioEmpleados {
    private final List<IEmpleado> empleados;

    public RepositorioEmpleados() {
        this.empleados = new ArrayList<>();
    }

    @Override
    public void agregar(IEmpleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public void eliminar(String id) {
        empleados.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<IEmpleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    @Override
    public List<IEmpleado> obtenerPorTipo(TipoEmpleado tipo) {
        return empleados.stream()
                .filter(e -> e.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IEmpleado> buscarPorId(String id) {
        return empleados.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}
