package dto;

public class CuentaCorriente extends Cuenta implements IGestionSaldo {

    private double giroDescubierto;

    public CuentaCorriente(double saldoInicial, double giroDescubierto) {
        this.saldo = saldoInicial;
        this.giroDescubierto = giroDescubierto;
        this.operaciones = 0;
    }

    @Override
    public synchronized boolean agregarSaldo(double monto) {
        if (monto > 0) {
            saldo += monto;
            operaciones++;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean quitarSaldo(double monto) {
        // Se permite el retiro si el saldo no excede el límite de descubierto
        if (monto > 0 && saldo - monto >= -giroDescubierto) {
            saldo -= monto;
            operaciones++;
            return true;
        }
        return false;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public int getOperaciones() {
        return operaciones;
    }

    public double getGiroDescubierto() {
        return giroDescubierto;
    }

    public void setGiroDescubierto(double giroDescubierto) {
        this.giroDescubierto = giroDescubierto;
    }
}