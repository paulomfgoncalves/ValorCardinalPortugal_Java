package pt.candal;


public class Program {

	public static void main(String[] args) {
		
//		 //double operation = 890.0 / 1440.0;
//		 //BigDecimal big = new BigDecimal(operation);
//		double ab = Double.parseDouble("1234567890123456789123456789012345678901234567890.99");  
//		System.out.println(String.format("double : %s", ab));
//		
//		 //BigDecimal big = new BigDecimal("99999999999999999999999999.99"); //max c#
//		 BigDecimal big = new BigDecimal("1234567890123456789123456789012345678901234567890.99");
//		                                  
//		 
//		 System.out.println(String.format("operation : %s", big));
//		 
//		 BigDecimal big3 = new BigDecimal(ab);      // double to dicimal  
//		 BigDecimal big2 = BigDecimal.valueOf(ab);  // double to dicimal      
//		 big2 = big2.setScale(2, RoundingMode.FLOOR);        
//		 System.out.println(String.format("operation : %s", big2));
//
//		 double d2 = big2.doubleValue(); // dicimal to double
//         
//         //System.out.println(String.format("operation : %s", operation));
//         System.out.println(String.format("scaled : %s", d2));
//		
//		//BigInteger cc = new BigInteger(0);
//
//		//decimal dec = 1.9;
//		
//		
//		BigDecimal aa = new BigDecimal(1.19);
//		BigInteger bb = aa.toBigInteger();
//		BigInteger cc = aa.toBigIntegerExact();
//
//		String xx= "1010110";
//		
//		//String yyy = String.format("%03d", xx); //String.format("%1$3s", xx);
//		//String yyy =   String.format("%05s", xx);
//		             
//		String yyy = String.format("%1$3s", xx).replace(' ', '0');
//		yyy=yyy;
//		
		

		ValorCardinalPortugal xpto = new ValorCardinalPortugal();
		String result = xpto.Converte("12345678901234567890.99");
		 System.out.println(result);
		
	}

}
