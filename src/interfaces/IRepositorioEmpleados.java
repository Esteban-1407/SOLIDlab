package interfaces;

import enums.TipoEmpleado;
import interfaces.IEmpleado;

import java.util.List;
import java.util.Optional;

public interface IRepositorioEmpleados {
    void agregar(IEmpleado empleado);
    void eliminar(String id);
    List<IEmpleado> obtenerTodos();
    List<IEmpleado> obtenerPorTipo(TipoEmpleado tipo);
    Optional<IEmpleado> buscarPorId(String id);

}
