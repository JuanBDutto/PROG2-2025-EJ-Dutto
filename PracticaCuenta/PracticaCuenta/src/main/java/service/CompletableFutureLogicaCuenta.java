package service;

import dto.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class CompletableFutureLogicaCuenta {
    private List<Cuenta> cuentas;
    private LogicaCuenta logica;

    public CompletableFutureLogicaCuenta(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
        this.logica = LogicaCuenta.getInstancia();

        // Registrar las cuentas en LogicaCuenta
        for (Cuenta cuenta : cuentas) {
            logica.agregarCuenta(cuenta);
        }
    }

    public void ejecutarOperacionesConcurrencia() throws InterruptedException, ExecutionException {
        System.out.println("Ejecutando operaciones concurrentes...");

        long inicio = System.currentTimeMillis();

        List<CompletableFuture<Void>> listaFutures = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    Random rand = ThreadLocalRandom.current();
                    int cuentaIndex = rand.nextInt(cuentas.size());
                    double monto = 100 + rand.nextDouble() * 900; // entre 100 y 1000
                    boolean esAgregar = rand.nextBoolean();

                    if (esAgregar) {
                        logica.agregarSaldo(cuentaIndex, monto);
                    } else {
                        logica.quitarSaldo(cuentaIndex, monto);
                    }
                }
            });

            listaFutures.add(future);
        }

        // Esperar que terminen todas las operaciones
        CompletableFuture<Void> todas = CompletableFuture.allOf(
                listaFutures.toArray(new CompletableFuture[0])
        );
        todas.join();

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total (ms): " + (fin - inicio));

        // Mostrar saldo final por cuenta
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println("Cuenta " + i + " - Saldo final: " + logica.consultarSaldo(i));
        }
    }

}
