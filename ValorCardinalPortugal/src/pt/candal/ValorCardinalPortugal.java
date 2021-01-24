package pt.candal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ValorCardinalPortugal {

	// campos em ordinal
	
	// xxx
	private static final String[] CARDINAL_UNIDADES = { "", "um", "dois", "três", "quatro", "cinco", "seis", "sete",
			"oito", "nove" };
	private static final String[] CARDINAL_DEZENAS = { "", "dez", "vinte", "trinta", "quarenta", "cinquenta",
			"sessenta", "setenta", "oitenta", "noventa" };
	private static final String[] CARDINAL_DEZENAS_DEZ = { "dez", "onze", "doze", "treze", "catorze", "quinze",
			"desasseis", "desassete", "dezoito", "dezanove" };
	private static final String[] CARDINAL_CENTENAS = { "", "cento", "duzentos", "trezentos", "quatrocentos",
			"quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos" };
	private static final String CARDINAL_ZERO = "zero";
	private static final String CARDINAL_UM = "um";
	private static final String CARDINAL_UMA = "uma";
	private static final String CARDINAL_CEM = "cem";

	private static final String[] CARDINAL_GRUPOS_PLURAL = { "", "mil", "milhões", "milhares de milhão", "biliões",
			"dezenas de bilião", "centenas de bilião", "milhares de bilião", "dezenas de milhar de bilião",
			"centenas de milhar de bilião", "triliões" };

	private static final String[] CARDINAL_GRUPOS_SINGULAR = { "", "mil", "milhão", "milhar de milhão", "bilião",
			"dezena de bilião", "centena de bilião", "milhar de bilião", "dezena de milhar de bilião",
			"centena de milhar de bilião", "trilião" };

	private static final boolean[] CARDINAL_GRUPOS_MASCULINO = { true, true, true, true, true, false, false, true,
			false, false, true };

	// xxx.000
	private static final String CARDINAL_MILHARES = "mil";

	// xxx.000.000
	private static final String CARDINAL_MILHOES_P = "milhões";
	private static final String CARDINAL_MILHOES_1 = "milhão";

	// xxx.000.000.000
	private static final String CARDINAL_MILHAR_MILHOES_P = "milhares de milhão";
	private static final String CARDINAL_MILHAR_MILHOES_1 = "milhar de milhão";

	// xxx.000.000.000.000
	private static final String CARDINAL_BILIOES_P = "biliões";
	private static final String CARDINAL_BILIOES_1 = "bilião";
	private static final String CARDINAL_DEZENAS_BILIOES_P = "dezenas de bilião";
	private static final String CARDINAL_DEZENAS_BILIOES_1 = "dezena de bilião";
	private static final String CARDINAL_CENTENA_BILIOES_P = "centenas de bilião";
	private static final String CARDINAL_CENTENA_BILIOES_1 = "centena de bilião";

	// xxx.000.000.000.000.000
	private static final String milhar_bilioes_P = "milhares de bilião";
	private static final String milhar_bilioes_1 = "milhar de bilião";

	// confirmar
	private static final String dezenas_milhar_bilioes_P = "dezenas de milhar de bilião";
	private static final String dezenas_milhar_bilioes_1 = "dezena de milhar de bilião";
	// confirmar
	private static final String centena_milhar_bilioes_P = "centenas de milhar de bilião";
	private static final String centena_milhar_bilioes_1 = "centena de milhar de bilião";

	// xxx.000.000.000.000.000.000
	private static final String CARDINAL_TRILIOES_P = "triliões";
	private static final String CARDINAL_TRILIOES_1 = "trilião";

	// milhar = (1 + 3 zeros)
	// milhões = (1 + 6 zeros)
	// mil milhões= (1 + 9 zeros)
	// bilião = um milhão de milhões (1 + 12 zeros)
	// trilião = um milhão de biliões (1 + 18 zeros)
	// quatrilião = um milhão de triliões (1 + 24 zeros);
	// quintilião = um milhão de quatriliões (1 + 30 zeros);
	// sextilião = um milhão de quintiliões (1 + 36 zeros);
	// septilião = um milhão de sextiliões (1 + 42 zeros);
	// octilião = um milhão de septiliões (1 + 48 zeros);
	// nonilião = um milhão de octiliões (1 + 54 zeros).

	private static final String FRASE_SUFIXO_AO = "ão";
	private static final String FRASE_SUFIXO_OES = "ões";

	private static final String FRASE_E = " e ";
	private static final String FRASE_VIRGULA = ", ";
	private static final String FRASE_DE = " de";
	private static final String FRASE_NOME_INTEIROS_PLURAL = "euros";
	private static final String FRASE_NOME_INTEIROS_SINGULAR = "euro";
	private static final String FRASE_NOME_DECIMAIS_PLURAL = "centimos";
	private static final String FRASE_NOME_DECIMAIS_SINGULAR = "centimo";

	public String Converte(BigDecimal valor) {

		return Converte(valor, false, false);
	}

	public String Converte(BigDecimal valor, boolean vazioSeZeroParteinteira) {

		return Converte(valor, vazioSeZeroParteinteira, false);
	}

	public String Converte(BigDecimal valor, boolean vazioSeZeroParteinteira, boolean vazioSeZeroParteDecimail) {

		BigDecimal bigDecimal = valor.setScale(2, RoundingMode.FLOOR);
		String temp = bigDecimal.toString();

		return Converte(temp, vazioSeZeroParteinteira, vazioSeZeroParteDecimail);
	}

	public String Converte(String valor) {

		return Converte(valor, false, false);
	}

	public String Converte(String valor, boolean vazioSeZeroParteinteira) {

		return Converte(valor, vazioSeZeroParteinteira, false);
	}

	public String Converte(String valor, boolean vazioSeZeroParteinteira, boolean vazioSeZeroParteDecimail) {

		String[] partes = DivideEmPartesInteiraDecimal(valor);

		// separa por grupos de mil "???"
		String[] gruposInteiros = DivideEmGruposDeMil(partes[0]);
		String[] gruposDecimais = DivideEmGruposDeMil(partes[1]);

		// descodifica os grupos inteiros
		String[] gruposCardinaisInteiros = new String[gruposInteiros.length];
		for (int x = 0; x < gruposInteiros.length; x++)
			gruposCardinaisInteiros[x] = DescodificaCardinal(gruposInteiros[x], (gruposInteiros.length - x - 1));

		// descodifica os groupos decimais
		String[] gruposCardinaisDecimais = new String[gruposDecimais.length];
		for (int x = 0; x < gruposDecimais.length; x++)
			gruposCardinaisDecimais[x] = DescodificaCardinal(gruposDecimais[x], (gruposDecimais.length - x - 1));

		// junta todos os grupos
		String finalInteiros = JuntaTodosGruposDeMil(gruposCardinaisInteiros, vazioSeZeroParteinteira);
		String finalDecimais = JuntaTodosGruposDeMil(gruposCardinaisDecimais, vazioSeZeroParteDecimail);

		// caso: se valor = 0.0 mostra sempre "zero"
		if ((finalInteiros.length() == 0) && (finalDecimais.length() == 0))
			finalInteiros = CARDINAL_ZERO + " " + FRASE_NOME_INTEIROS_PLURAL;

		// caso: analiza se coloca "de" ou "e" antes do qualificador
		if (finalInteiros.length() > 2) {
			// C# String temp = finalInteiros.SubString((finalInteiros.length() - 3), 3);
			String temp = finalInteiros.substring((finalInteiros.length() - 3), finalInteiros.length());
			if (temp.equals(FRASE_SUFIXO_OES))
				finalInteiros += FRASE_DE;
			else {
				// C# temp = finalInteiros.SubString((finalInteiros.length() - 2), 2);
				temp = finalInteiros.substring((finalInteiros.length() - 2), finalInteiros.length());
				if (temp.equals(FRASE_SUFIXO_AO))
					finalInteiros += FRASE_DE;
			}
		}

		// obtem qualificadores
		String qualificadorInteiros = ObtemQualificadorParteInteira(partes[0], vazioSeZeroParteinteira);
		String qualificadoreDecimais = ObtemQualificadorParteBigDecimal(partes[1], vazioSeZeroParteDecimail);

		// caso: adiciona qualificador inteiros
		if (finalInteiros.length() > 0)
			finalInteiros += " " + qualificadorInteiros;

		// caso: adiciona qualificador decimais
		if ((finalDecimais.length() > 0) && (finalInteiros.length() > 0))
			finalDecimais += " ";
		
		finalDecimais += qualificadoreDecimais;

		// case: adiciona " e " entre a frase inteiros & frase decimais
		String dual = "";
		if ((finalInteiros.length() > 0) && (finalDecimais.length() > 0))
			dual = FRASE_E;

		return finalInteiros + dual + finalDecimais;
	}

	private String[] DivideEmPartesInteiraDecimal(String valor) {

		if (valor.indexOf(".") == -1)
			valor += ".00";

		String[] partes = valor.split("\\.");

		if (partes[0].length() == 0)
			partes[0] = "0";

		if (partes[1].length() == 0)
			partes[1] = "00";
		else if (partes[1].length() == 1)
			partes[1] += "0";

		return partes;
	}

	private String[] DivideEmGruposDeMil(String valor) {

		///String temp = valor;

		// extrai
		List<String> list = new ArrayList<String>();
		while (valor.length() > 3) {
			// C# String str3 = temp.SubString(temp.length() - 3);
			String str3 = valor.substring(valor.length() - 3);
			list.add(str3);
			// C# temp = temp.SubString(0, temp.length() - 3);
			valor = valor.substring(0, valor.length() - 3);
		}
		
		list.add(String.format("%1$3s", valor).replace(' ', '0')); // garante comprimento = 3
		// temp.PadLeft(3, '0')

		// reverte array
		int count = list.size();
		String[] groupos = new String[count];
		for (int x = 0; x < count; x++)
			groupos[count - 1 - x] = list.get(x);

		return groupos;
	}

	private String JuntaTodosGruposDeMil(String[] grouposEmCardinal, boolean vazioSeZero) {

		String resultado = "";

		for (int x = 0; x < grouposEmCardinal.length; x++) {

			if (grouposEmCardinal[x].length() == 0)
				continue;

			// no ultimo elemento analisa se coloca  " e " no fim
			if ((x == (grouposEmCardinal.length - 1)) && (resultado.length() > 1)) {
				int pos = grouposEmCardinal[x].indexOf(FRASE_E);
				if (pos == -1) {
					resultado = RemoveUltimasVirgulasEmExcesso(resultado);
					// result = result.SubString(0, result.Length() - 2);
					resultado += FRASE_E;
				}
			}

			resultado += grouposEmCardinal[x];
			resultado += FRASE_VIRGULA;
		}

        if ((resultado.length() == 0) && (!vazioSeZero))
            resultado = CARDINAL_ZERO;
//		if (resultado.length() == 0) {
//			if (vazioSeZero)
//				return "";
//			else
//				return CARDINAL_ZERO;
//		}

		resultado = RemoveUltimasVirgulasEmExcesso(resultado);

		return resultado;
	}

	private String RemoveUltimasVirgulasEmExcesso(String valor) {
		
        if (valor.length() < 2)
            return valor;
		
        String resultado = valor;

		// C# while (resultado.SubString(resultado.length() - 2, 2) == FRASE_VIRGULA)
		while (resultado.substring(resultado.length() - 2, resultado.length()).equals(FRASE_VIRGULA))
			// C# resultado = resultado.SubString(0, resultado.length() - 2);
			resultado = resultado.substring(0, resultado.length() - 2);

		return resultado;
	}

	private String DescodificaCardinal(String valor, int nivel) {

		if (valor.equals("000"))
			return "";

		String[] cardinalArray = new String[3];
		byte[] digitArray = new byte[3];

		for (byte x = 0; x < 3; x++)
			// C# digitArray[x] = Byte.parseByte(valor.SubString(x, 1));
			digitArray[x] = Byte.parseByte(valor.substring(x, (x + 1)));

		cardinalArray[0] = ObtemCentenas(digitArray[0], digitArray[1], digitArray[2]);
		cardinalArray[1] = ObtemDezenas(digitArray[1], digitArray[2]);
		cardinalArray[2] = ObtemUnidades(digitArray[2], digitArray[1]);

		String resultado = JuntaCentenasDezenasUnidades(cardinalArray[0], cardinalArray[1], cardinalArray[2]);

		resultado = AdicionaSufixoDeGrupoMil(resultado, nivel);

		return resultado;
	}

	private String JuntaCentenasDezenasUnidades(String centena, String dezena, String unidade) {

		String resultado = centena;
		if ((centena.length() > 0) && ((dezena.length() > 0) || (unidade.length() > 0)))
			resultado += FRASE_E;

		resultado += dezena;
		if ((dezena.length() > 0) && (unidade.length() > 0))
			resultado += FRASE_E;

		resultado += unidade;

		return resultado;
	}

	private String ObtemUnidades(byte digito, byte dezena) {

		if (dezena == 1)
			return "";

		return CARDINAL_UNIDADES[digito];
	}

	private String ObtemDezenas(byte digito, byte unidade) {

		if (digito == 1)
			return CARDINAL_DEZENAS_DEZ[unidade];

		return CARDINAL_DEZENAS[digito];
	}

	private String ObtemCentenas(byte digito, byte dezena, byte unidade) {

		if ((digito == 1) && (dezena == 0) && (unidade == 0))
			return CARDINAL_CEM; // Caso : Cem

		return CARDINAL_CENTENAS[digito];
	}

	private String ObtemQualificadorParteBigDecimal(String valor, boolean vazioSeZero) {

		byte valTemp = Byte.parseByte(valor);

		if (valTemp > 1)
			return FRASE_NOME_DECIMAIS_PLURAL;

		if (valTemp == 1)
			return FRASE_NOME_DECIMAIS_SINGULAR;

		if ((valTemp == 0) && (!vazioSeZero))
			return FRASE_NOME_DECIMAIS_PLURAL;

		return "";
	}

	private String ObtemQualificadorParteInteira(String valor, boolean vazioSeZero) {

		double valTemp = Double.parseDouble(valor);

		if (valTemp > 1)
			return FRASE_NOME_INTEIROS_PLURAL;

		if (valTemp == 1)
			return FRASE_NOME_INTEIROS_SINGULAR;

		if ((valTemp == 0) && (!vazioSeZero))
			return FRASE_NOME_INTEIROS_PLURAL;

		return "";
	}

	private String AdicionaSufixoDeGrupoMil(String valor, int nivel) {
		
		String resultado = "";

		switch (nivel) {

		case 0: // xxx -unidades, dezenas, centenas
			resultado = valor;
			break;

		case 1: // xxx.000 - milhares
			if (valor.equals(CARDINAL_UM))
				resultado = CARDINAL_MILHARES; // special : remove palavra "um" (um mil)
			else
				resultado += valor + " " + CARDINAL_MILHARES;
			break;

//		case 2: // milhoes xxx.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UM + " " + CARDINAL_MILHOES_1;
//			else
//				resultado += valor + " " + CARDINAL_MILHOES_P;
//			break;
//
//		case 3: // milhar de milhoes xxx.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UM + " " + CARDINAL_MILHAR_MILHOES_1;
//			else
//				resultado += valor + " " + CARDINAL_MILHAR_MILHOES_P;
//			break;
//
//		case 4: // bilioes xxx.000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UM + " " + CARDINAL_BILIOES_1;
//			else
//				resultado += valor + " " + CARDINAL_BILIOES_P;
//			break;
//
//		case 5: // dezena de bilioes xxx-000.000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UMA + " " + CARDINAL_DEZENAS_BILIOES_1;
//			else
//				resultado += valor + " " + CARDINAL_DEZENAS_BILIOES_P;
//			break;
//
//		case 6: // centena de bilioes xxx.000.000.000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UMA + " " + CARDINAL_CENTENA_BILIOES_1;
//			else
//				resultado += valor + " " + CARDINAL_CENTENA_BILIOES_P;
//			break;
//
//		case 7: // milhar de bilião xxx.000.000.000,000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UM + " " + milhar_bilioes_1;
//			else
//				resultado += valor + " " + milhar_bilioes_P;
//			break;
//
//		case 8: // dezena de milhar de bilião xxx.000.000.000,000.000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UMA + " " + dezenas_milhar_bilioes_1;
//			else
//				resultado += valor + " " + dezenas_milhar_bilioes_P;
//			break;
//
//		case 9: // centena de milhar de bilião xxx.000.000.000,000.000.000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UMA + " " + centena_milhar_bilioes_1;
//			else
//				resultado += valor + " " + centena_milhar_bilioes_P;
//			break;
//
//		case 10: // trilião xxx.000.000.000,000.000.000.000.000.000.000
//			if (valor.equals(CARDINAL_UM))
//				resultado = CARDINAL_UM + " " + CARDINAL_TRILIOES_1;
//			else
//				resultado += valor + " " + CARDINAL_TRILIOES_P;
//			break;

		default:

			if (valor.equals(CARDINAL_UM)) {
				if (CARDINAL_GRUPOS_MASCULINO[nivel])
					resultado = CARDINAL_UM;
				else
					resultado = CARDINAL_UMA;

				resultado += " " + CARDINAL_GRUPOS_SINGULAR[nivel];
			} else
				resultado = valor + " " + CARDINAL_GRUPOS_PLURAL[nivel];
			break;
		}

		return resultado;
	}

}
