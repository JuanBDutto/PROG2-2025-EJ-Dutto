package dto;

public class CajaDeAhorro extends Cuenta implements IGestionSaldo {

    public CajaDeAhorro(double saldoInicial) {
        this.saldo = saldoInicial;
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
        if (monto > 0 && saldo >= monto) {
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

}