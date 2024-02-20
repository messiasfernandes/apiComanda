package com.comanda.utils;

import java.util.HashSet;
import java.util.Set;

public class GeradorSKU {
	private static Set<String> skusGerados = new HashSet<>();

    public static String gerarSKU() {
        String sku;
        do {
            sku = gerarCodigoSKU();
        } while (!skusGerados.add(sku)); // Adiciona ao conjunto, se já existe, gera novamente

        return sku;
    }

    private static String gerarCodigoSKU() {
        long timestamp = System.currentTimeMillis();
        int numeroAleatorio = (int) (Math.random() * 10000); // Ajuste o intervalo conforme necessário
        return "SKU_" + timestamp + "_" + numeroAleatorio;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String sku = gerarSKU();
            System.out.println("SKU gerado: " + sku);
        }
    }
}
