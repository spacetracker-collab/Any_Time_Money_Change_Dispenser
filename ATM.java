package test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import test.CurrencyUtils;
import test.DenomStore;
import test.GenericOutput;

public class ATM {
	
	  final static String deposit = "D";
	  final static String  withdrawal = "W";
	  final static String quit = "Q";
	  
	  private static ATM atmInstance = null;
	  
	
	public static ATM getInstance()
	{
		if (atmInstance != null)
		{
			return atmInstance;
			
		}
		else
		{
			atmInstance = new ATM();
			return atmInstance;
		}
	}
	
	  public  int getBalance() {
			return balance;
		}

		public void setBalance(int mBalance) {
			 balance = mBalance;
		}
		
	  private   int balance=0;
	  
	  public static int init = 0;
	 
	  
	  
	   public static void main (String [] args)
	   {
		   ATM atm = ATM.getInstance();
		   Scanner sc = new Scanner(System.in);		   

		   while(true)
		   {
			   System.out.println("Press D for Deposit, W for Withdraw, Q for Quit");
		       
			   String depositOrWithdraw = sc.nextLine();
			   
			   
		   
		       if(depositOrWithdraw.equals(deposit))
		       {
		    	  if( sc.hasNextLine())
		          atm.deposit(sc.nextLine());
		       }
		       if(depositOrWithdraw.equals(withdrawal))		       {
		    	   
			       if( sc.hasNextLine())
		    	   atm.withdraw(sc.nextLine());
		       }
		       if(depositOrWithdraw.equals(quit))
		       {
		    	  
		    	   GenericOutput.emitMessage("Thanks. Your Updated Balance is " + atm.getBalance());
		    	   sc.close();
		       } 
		   }
		  
	   }

	   private  void deposit(String depositCurrency)
	   {
		 if ( (depositCurrency == null) || depositCurrency.isEmpty()) 
		 {
			 GenericOutput.emitIncorrectValues();
			 return;
		 }
		 depositCurrency = depositCurrency.replace(" ", "");
         ArrayList denominations = new ArrayList();
         ArrayList values = new ArrayList();
         ArrayList finalDenominations = new ArrayList();
   	     ArrayList finalValues = new ArrayList();  
         StringBuilder sb = new StringBuilder();
         
         try
         {
            String [] currencyTokens = depositCurrency.split(",");
	         for(int i = 0 ; i < currencyTokens.length; i ++)
	         {
	        	 String [] denominationValues = currencyTokens[i].split(":");
	        	 denominations.add( denominationValues[0]);
	             values.add(denominationValues[1]);
	        	 
	         }
         }
         catch(Exception e)
         {
        	 GenericOutput.emitIncorrectInput();
        	 return;
         }
	     
	     
	 

	     Iterator it1 = denominations.iterator();
	     while (it1.hasNext()){
	    	 
	    	 String denomination = (String) it1.next();

	    	 if (CurrencyUtils.checkZero(denomination)== true)
	    	 {
	    		 GenericOutput.emitZeroDeposit();
	    		 return;
	    	 }
	    	 if(CurrencyUtils.checkNegative(denomination) == true)
	    	 {
		    	 GenericOutput.emitIncorrectDeposit();
		    	 return;
	    	 }	
	    	 
	    	 finalDenominations.add(denomination);
	     }
	     
         Iterator it2 = values.iterator();
         while (it2.hasNext()){
    	 
    	 String value = (String) it2.next();

    	 if(CurrencyUtils.checkNegative(value) == true)
    	 {
    		 GenericOutput.emitIncorrectDeposit();
	    	 return;
    	 }
    	 
    	 if (CurrencyUtils.checkZero(value)== true)
    	 {
    		 GenericOutput.emitZeroDeposit();
    		 return;
    	 }
          finalValues.add(value);
         }
	     
	     if(finalDenominations.size() != finalValues.size())	 
	     {
	    	 GenericOutput.emitMismatch();
	     }
	     
	     
	     
	     int depositedMoney = getDepositedMoney(denominations, values);
	     
	     ATM.getInstance().setBalance(depositedMoney);
	    
	     
	    
	    	
	    	
	     
    	
    	 sb.append("Balance: ");
    	 
    	 sb.append("20s="+DenomStore.getDenom20()+","); 
    	 sb.append("10s="+DenomStore.getDenom10()+","); 
    	 sb.append("5s="+DenomStore.getDenom5()+","); 
    	 sb.append("1s="+DenomStore.getDenom1()+","); 
    	 sb.append("Total="+depositedMoney); 
    	 GenericOutput.emitMessage(sb.toString());
    	 return;
    	 }
    	 
	   private int getDepositedMoney(ArrayList denominations, ArrayList values)
	   {
		   
		   Iterator denomination = denominations.iterator();
		   Iterator value = values.iterator();
		   
		   int depositedMoney = 0;
		   
		   while (denomination.hasNext())
		   {
			   String denominationString = (String) denomination.next();
			   denominationString = denominationString.replace("s","");
			   int denom = Integer.parseInt(denominationString);
			   int val = Integer.parseInt((String) value.next());
			   if(denom==20)
			   {
				   DenomStore.setDenom20(DenomStore.getDenom20()+val);
			   }
			   if(denom==10)
			   {
				   
				   DenomStore.setDenom10(DenomStore.getDenom10()+val);
			   }
			   if(denom==5)
			   {
				   DenomStore.setDenom5(DenomStore.getDenom5()+val);
				 
			   }
			   if(denom==1)
			   {
				   DenomStore.setDenom1(DenomStore.getDenom1()+val);
			   }
			   
			   int fiveTotal = DenomStore.getDenom5()*5;
			   int twentyTotal = DenomStore.getDenom20()*20;
			   int tenTotal = DenomStore.getDenom10()*10;
			   int oneTotal = DenomStore.getDenom1()* 1;
			   

			  
			   
			   depositedMoney =   
					             twentyTotal+
					             fiveTotal+
					             tenTotal+
					             oneTotal;
			   
			   
			   }
			   
			   
		   		   
		return depositedMoney;
	   }

	   
	   
	   
	   
	   
	   
	  
	   public  void withdraw(String withdrawalCurrency)
	   {
		   if((withdrawalCurrency == null) || withdrawalCurrency.isEmpty())
		   {
			   GenericOutput.emitIncorrectValues();
			   return;
		   }
		   
		   withdrawalCurrency = withdrawalCurrency.replace(" ", "");
		   int withdrawal = Integer.parseInt(withdrawalCurrency);
		   
		   
		   if((withdrawal <= 0 )|| (withdrawal > balance) )
		   {
			   GenericOutput.emitInsufficientFunds();
			   return;
		   }
		   
		   int withdrawal20 = withdrawal/20;
		   int remWithdrawal20 = withdrawal - withdrawal20*20 ;
		   
		   int withdrawal10 = remWithdrawal20/10;
		   int remWithdrawal10 = remWithdrawal20 - withdrawal10*10;
		   
		   int withdrawal5 = remWithdrawal10/5;
		   int remWithdrawal5 = remWithdrawal10 - withdrawal5*5;
		   
		   int withdrawal1 = remWithdrawal5/1;
		   
		  int  finalwithdrawal20 = 0;
		  int finalwithdrawal10 = 0;
		  int finalwithdrawal5 = 0;
		  int  finalwithdrawal1 = 0;
		  
		   if( (withdrawal20 > DenomStore.getDenom20()) )
		   {
			   if(withdrawal10 * 2 <= DenomStore.getDenom10())
			   {
				   withdrawal10 = withdrawal10*2;
				   DenomStore.setDenom10(DenomStore.getDenom10() - withdrawal10);
				   finalwithdrawal10 = withdrawal10;
			   }
			   else
			   {
				   if(withdrawal5*4 <= DenomStore.getDenom5())
				   {
					   withdrawal5 = withdrawal5*4;
					   DenomStore.setDenom5(DenomStore.getDenom5() - withdrawal5);
	
					   finalwithdrawal5 = withdrawal5;
				   }
				   else
				   if(withdrawal1*20 <= DenomStore.getDenom1())
				   {
					   withdrawal1 = withdrawal1*20;
					   finalwithdrawal1 = withdrawal1;
				   }
				   else
				   {
					   GenericOutput.emitUndispensable();
					   return;
				   }
			   }
		   }
		   else
		   {
			   DenomStore.setDenom20(DenomStore.getDenom20() - withdrawal20);

			   
		   }
		   
		   if(withdrawal10 > DenomStore.getDenom10())
		   {
			   if(withdrawal5*2 <= DenomStore.getDenom5())
			   {
				   DenomStore.setDenom5(DenomStore.getDenom5() - withdrawal5*2);
				  
				   finalwithdrawal5 = withdrawal5*2;
			   }
			   else
				   if(withdrawal5*10 <= DenomStore.getDenom1())
				   {
					   DenomStore.setDenom1(DenomStore.getDenom1() - withdrawal5*10);
					  
				   }
				   else
				   {
					   GenericOutput.emitUndispensable();
					   return;
				   }
			   
		   }
		   else
		   {
			   DenomStore.setDenom10(DenomStore.getDenom10() - withdrawal10);
			   finalwithdrawal10 = withdrawal10;
		   }
				   
		   if(withdrawal5 > DenomStore.getDenom5())
		   {
			   if(withdrawal5*5 <= DenomStore.getDenom1())
			   {
				   DenomStore.setDenom1(DenomStore.getDenom1() - withdrawal*5);
				   finalwithdrawal5 = withdrawal*5;
			   }
			   else
			   {
				   GenericOutput.emitUndispensable();
				   return;
			   }
		   }
		   else
		   {
			      DenomStore.setDenom5(DenomStore.getDenom5() - withdrawal5);
				  finalwithdrawal5 = withdrawal5;
			}
		   
	   
	   if(withdrawal1 > DenomStore.getDenom1())
	   {
           GenericOutput.emitUndispensable();
		   return;

	   }
	   else
	   {
		   DenomStore.setDenom1(DenomStore.getDenom1() - withdrawal1);
		   finalwithdrawal1 = withdrawal1;
	   }
	   
	   
				   
	   GenericOutput.emitMessage("Dispensed:" + "20s:=" + finalwithdrawal20
		    		              + " 10s:="+ finalwithdrawal10+
				                   " 5s:="+ finalwithdrawal5+
				                   " 1s:="+ finalwithdrawal1);
		       
	   GenericOutput.emitMessage(new Integer(ATM.getInstance().getBalance()).toString());
	   ATM.getInstance().setBalance(ATM.getInstance().getBalance() - withdrawal);
	   
	   
		   
	   GenericOutput.emitMessage("Balance:"+"20s:="+ DenomStore.getDenom20() +
		    	       " 10s:="+ DenomStore.getDenom10()+
		    	       " 5s:="+ DenomStore.getDenom5()+
		    	       " 1s:="+ DenomStore.getDenom1()+
		    		   " Total="+ ATM.getInstance().getBalance());
		      
		return;
	   }}


	
		   
		   

