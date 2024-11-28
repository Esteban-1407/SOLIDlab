package empleados;

import enums.TipoEmpleado;

public class EmpleadoTiempoParcial extends Empleado {
    public EmpleadoTiempoParcial(String nombre, String id, String tipo) {
        super(nombre, id,tipo);
    }

    @Override
    public TipoEmpleado getTipo() {
        return TipoEmpleado.TIEMPO_PARCIAL;
    }
}
