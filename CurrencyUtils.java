package test;

public class CurrencyUtils {
	
	 public  static boolean checkZero(String s)
	   {
		   if(s.equals("0s") || s.equals("0"))
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	   }
	 
	 public  static boolean checkNegative(String s)
	 {
		   if(s.contains("-"))
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	  }

}
