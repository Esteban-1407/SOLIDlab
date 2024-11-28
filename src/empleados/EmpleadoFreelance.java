package empleados;

import enums.TipoEmpleado;

public class EmpleadoFreelance extends Empleado {
    public EmpleadoFreelance(String nombre, String id, String tipo) {
        super(nombre, id,tipo);
    }

    @Override
    public TipoEmpleado getTipo() {
        return TipoEmpleado.FREELANCE;
    }
}
