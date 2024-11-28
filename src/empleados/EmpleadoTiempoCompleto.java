package empleados;

import enums.TipoEmpleado;

public class EmpleadoTiempoCompleto extends Empleado {
    public EmpleadoTiempoCompleto(String nombre, String id,String tipo) {
        super(nombre, id,tipo);
    }

    @Override
    public TipoEmpleado getTipo() {
        return TipoEmpleado.TIEMPO_COMPLETO;
    }
}
