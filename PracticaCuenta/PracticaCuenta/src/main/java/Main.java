import dto.*;
import service.CompletableFutureLogicaCuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Cuenta> cuentas = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            double saldo = 50000 + (random.nextDouble() * (100000 - 50000));
            CajaDeAhorro cuentaAhorro = new CajaDeAhorroBuilder()
                    .setSaldoInicial(saldo)
                    .build();
            cuentas.add(cuentaAhorro);
        }

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            double saldo = 50000 + (random.nextDouble() * (100000 - 50000));
            double giroD = 100000 + (random.nextDouble() * (200000 - 100000));
            CuentaCorriente cuentaCorriente = new CuentaCorrienteBuilder()
                    .setSaldoInicial(saldo)
                    .setGiroDescubierto(giroD)
                    .build();
            cuentas.add(cuentaCorriente);

            CompletableFutureLogicaCuenta completableFuture = new CompletableFutureLogicaCuenta(cuentas);
            completableFuture.ejecutarOperacionesConcurrencia();

        }



    }
}
