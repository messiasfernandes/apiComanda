package com.comanda.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class GeradordeCodigo {
	 private static Set<String> ean13sGerados = new HashSet<>();

	    public static String CriarEAN13() {
	        String ean13;
	        do {
	            ean13 = gerarEAN13();
	        } while (!ean13sGerados.add(ean13)); // Adiciona ao conjunto, se já existe, gera novamente

	        return ean13;
	    }

	    private static String gerarEAN13() {
	        LocalDateTime datahora = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	        String formattedDateTime = datahora.format(formatter);
	        String stringConcatenada = "789" + formattedDateTime.substring(5, 14);

	        String ean13 = CalcularDigitoEan.calcularEAN13(stringConcatenada);
	        CodigoBarraEAN codigoBarra = new CodigoBarraEAN(ean13);

	        System.out.println("Codigo de barra: " + codigoBarra.validar(codigoBarra));
	        System.out.println("Numero do codigo de barras: " + codigoBarra.getCodigoBarra());

	        return codigoBarra.getCodigoBarra();
	    }


	public static String GerarCodigoFabricante() {
		LocalDateTime datahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String dataFormatada = datahora.format(formatter);
		String codigofabricante = dataFormatada.substring(8, 14);
		System.out.println(codigofabricante);
		return codigofabricante;
	}
}
