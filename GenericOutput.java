package test;

public class GenericOutput {
	
	public static void emitUndispensable()
	{
	   System.out.println("Requested amount is not dispensable");

	}
	
	public static void  emitZeroDeposit()
	{
		 System.out.println("Deposit amount cannot be zero");
	}
	
	public static void emitIncorrectDeposit()
	{
   	 System.out.println("Incorrect deposit amount");   		
	}
	
	public static void emitMessage(String s)
	{
		System.out.println(s);
	}
	
	public static void emitInsufficientFunds()
	{
		 System.out.println("Incorrect or Insufficient Funds");
	}
	
	public static void emitIncorrectValues()
	{
		System.out.println("Incorrect values entered");
	}
	
	public static void emitMismatch()
	{
		System.out.println("Mismatch in denomination and values");
	}
	
	public static void emitIncorrectInput()
	{
		System.out.println("The deposit input is incorrect");
		
		
	}

}
