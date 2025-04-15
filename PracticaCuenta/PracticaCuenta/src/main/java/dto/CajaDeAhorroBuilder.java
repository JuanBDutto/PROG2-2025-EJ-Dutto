package dto;

public class CajaDeAhorroBuilder {
    private double saldoInicial = 0;

    public CajaDeAhorroBuilder setSaldoInicial(double saldo) {
        this.saldoInicial = saldo;
        return this;
    }

    public CajaDeAhorro build() {
        return new CajaDeAhorro(saldoInicial);
    }
}