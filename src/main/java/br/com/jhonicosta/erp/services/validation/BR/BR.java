package br.com.jhonicosta.erp.services.validation.BR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Fonte: https://gist.github.com/adrianoluis/5043397d378ae506d87366abb0ab4e30
public class BR {
	// CPF
	private static final int[] weightSsn = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	// CNPJ
	private static final int[] weightTin = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calculate(final String str, final int[] weight) {
		int sum = 0;
		for (int i = str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length() + i];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}

	/**
	 * Valida CPF
	 *
	 * @param ssn
	 * @return
	 */
	public static boolean isValidCPF(final String ssn) {
		if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}"))
			return false;
		final Integer digit1 = calculate(ssn.substring(0, 9), weightSsn);
		final Integer digit2 = calculate(ssn.substring(0, 9) + digit1, weightSsn);
		return ssn.equals(ssn.substring(0, 9) + digit1.toString() + digit2.toString());
	}

	/**
	 * Valida CNPJ
	 *
	 * @param tin
	 * @return
	 */
	public static boolean isValidCNPJ(final String tin) {
		if ((tin == null) || (tin.length() != 14) || tin.matches(tin.charAt(0) + "{14}"))
			return false;
		final Integer digit1 = calculate(tin.substring(0, 12), weightTin);
		final Integer digit2 = calculate(tin.substring(0, 12) + digit1, weightTin);
		return tin.equals(tin.substring(0, 12) + digit1.toString() + digit2.toString());
	}

	public static String cpfGerenarion() {
		List<Integer> cpf = new ArrayList<>();
		Integer validador = 0;

		Random random = new Random();

		for (int x = 10; x > 1; x--) {
			int numero = random.nextInt(10);
			cpf.add(numero);
			validador += (x * numero);
		}

		validador = validador % 11;

		if (validador < 2) {
			cpf.add(0);
		} else {
			cpf.add(11 - validador);
		}

		int y = 0;
		validador = 0;
		for (int x = 11; x > 1; x--) {
			validador += (x * cpf.get(y));
			y++;
		}

		validador = validador % 11;
		if (validador < 2) {
			cpf.add(0);
		} else {
			cpf.add(11 - validador);
		}
		String cpfValido = "";
		for (Integer integer : cpf) {
			cpfValido += String.valueOf(integer);
		}
		return cpfValido;
	}

	public static String cnpjGerenarion() {
		List<Integer> cnpj = new ArrayList<>();
		List<Integer> validador = Arrays.asList(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);

		Random random = new Random();

		for (int x = 9; x > 1; x--) {
			int numero = random.nextInt(9);
			cnpj.add(numero);
		}
		cnpj.addAll(Arrays.asList(0, 0, 0, 1));

		int numero = 0;
		for (int i = 0; i < cnpj.size(); i++) {
			numero += cnpj.get(i) * validador.get(i + 1);
		}
		numero = numero % 11;
		if (numero < 2) {
			cnpj.add(0);
		} else {
			cnpj.add(11 - numero);
		}
		numero = 0;
		for (int i = 0; i < cnpj.size(); i++) {
			numero += cnpj.get(i) * validador.get(i);
		}
		numero = numero % 11;
		if (numero < 2) {
			cnpj.add(0);
		} else {
			cnpj.add(11 - numero);
		}
		String cnpjString = "";
		for (Integer i : cnpj) {
			cnpjString += String.valueOf(i);
		}
		return (cnpjString);
	}
}