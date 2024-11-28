package empleados;

import enums.TipoEmpleado;
import interfaces.IEmpleado;

public class Empleado implements IEmpleado {
    private final String nombre;
    private final String id;
    private final String tipo;

    public Empleado(String nombre, String id, String tipo) {
        this.nombre = nombre;
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public TipoEmpleado getTipo() {
        return TipoEmpleado.valueOf(tipo);
    }
}
