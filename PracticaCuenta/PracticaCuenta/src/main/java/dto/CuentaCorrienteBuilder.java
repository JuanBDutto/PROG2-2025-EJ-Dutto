package dto;

public class CuentaCorrienteBuilder {
    private double saldoInicial = 0;
    private double giroDescubierto;


    public CuentaCorrienteBuilder setSaldoInicial(double saldo) {
        this.saldoInicial = saldo;
        return this;
    }

    public CuentaCorrienteBuilder setGiroDescubierto(double giroDescubierto) {
        this.giroDescubierto = giroDescubierto;
        return this;
    }

    public CuentaCorriente build() {
        return new CuentaCorriente(saldoInicial, giroDescubierto);
    }
}
