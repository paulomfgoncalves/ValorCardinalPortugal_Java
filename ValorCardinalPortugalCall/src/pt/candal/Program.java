package pt.candal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {

	public static void main(String[] args) throws IOException {

		System.out.println("Programa. Traduz valor para cardinal.\n\n");

		System.out.println("Introduza valor '#0.00':");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String valor = reader.readLine();

		System.out.println("\nProcessa :[" + valor + "]\n");

		// executa
		ValorCardinalPortugal objIns = new ValorCardinalPortugal();
		String resultado = objIns.converte(valor);

		System.out.println("Resultado:[" + resultado + "]\n");

		int enter = reader.read();
	}
}
