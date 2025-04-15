package service;

import dto.Cuenta;
import dto.IGestionSaldo;

import java.util.ArrayList;
import java.util.List;

public class LogicaCuenta {

    private static class SingletonHelper {

        private static final LogicaCuenta INSTANCIA = new LogicaCuenta();
    }

    private List<Cuenta> cuentas;

    private LogicaCuenta() {
        cuentas = new ArrayList<>();
    }

    public static LogicaCuenta getInstancia() {
        return SingletonHelper.INSTANCIA;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public boolean agregarSaldo(int id, double monto) {
        if (id >= 0 && id < cuentas.size()) {
            Cuenta cuenta = cuentas.get(id);
            if (cuenta instanceof IGestionSaldo) {
                return ((IGestionSaldo) cuenta).agregarSaldo(monto);
            }
        }
        return false;
    }

    public boolean quitarSaldo(int id, double monto) {
        if (id >= 0 && id < cuentas.size()) {
            Cuenta cuenta = cuentas.get(id);
            if (cuenta instanceof IGestionSaldo) {
                return ((IGestionSaldo) cuenta).quitarSaldo(monto);
            }
        }
        return false;
    }

    public double consultarSaldo(int id) {
        if (id >= 0 && id < cuentas.size()) {
            Cuenta cuenta = cuentas.get(id);
            if (cuenta instanceof IGestionSaldo) {
                return ((IGestionSaldo) cuenta).getSaldo();
            }
        }
        return -1;
    }
}


