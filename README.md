# Any_Time_Money_Change_Dispenser
This is a project  I did for an  interview

The following was the problem

**Java Coding Requirement Sheet: 


Develop a program called ATM. It allows customers to deposit and withdraw in these denominations: 20, 10, 5, and 1 dollar bills.

Deposit: Customer inputs the number of currency notes in each denomination

D.1) If any input values are negative, print "Incorrect deposit amount".
D.2) If all the input values are zero, print "Deposit amount cannot be zero".
D.3) If the input values are valid, increment the balances of corresponding dollar bills and print the available new balances in each denomination and the total balance.

Withdraw: Customer input the amount to withdraw. ATM dispenses the 20, 10, 5, and 1 dollar bills as needed. 

W.1) If the input amount is zero, negative, or over the current balance, print "Incorrect or insufficient funds".
W.2) If the input amount is in valid range, print the number of current notes dispensed in each denomination. Use the available higher denomination first. Also, print the available new balances in each denomination and the total balance.
W.3) If any denomination is not available to cover the withdrawal amount, do not reduce the balances. Instead, print "Requested withdraw amount is not dispensable". See Withdraw 3 scenario below.


For example, 

Deposit 1: 10s: 8, 5s: 20
---------------------------------

Balance: 20s=0, 10s=8, 5s=20, 1s=0, Total=180

Deposit 2: 20s: 3, 5s: 18, 1s: 4
-----------------------------------------

Balance: 20s=3, 10s=8, 5s=38, 1s=4, Total=334

Withdraw 1: 75
---------------------

Dispensed: 20s=3, 10s=1, 5s=1
Balance: 20s=0, 10s=7, 5s=37, 1s=4, Total=259

Withdraw 2: 122
----------------------

Dispensed: 10s=7, 5s=10, 1s=2
Balance: 20s=0, 10s=0, 5s=27, 1s=2, Total=137

Withdraw 3: 63
----------------------

Output: "Requested withdraw amount is not dispensable"

Note: At this stage, the ATM has only two 1 dollar bills. So, the withdrawal amount cannot be dispensed.

Withdraw 3: 253
----------------------

Output: "Incorrect or insufficient funds"

Withdraw 4: 0
-------------------

Output: "Incorrect or insufficient funds"

Withdraw 5: -25
----------------------

Output: "Incorrect or insufficient funds"


Tips: This program should be expandable to support 50s and 100s in future. Please allow the program to support any denominations with little or no code change.

public class ATM {
   public void deposit(...) {
   }

   public void withdraw(...) {
   }
}



**Solution**

The code completely follows the happy path as described in the attachment.

To keep it simple : 

a) The program is completely console driven
b) All the Java files are POJOs
c) The ATM class is singleton
d) String based utilities are in a separate class
e) All output is moved to GenericOutput so as to be extensible and configurable
f) There is a class called DenomStore which can be extended with further denominations of 50 and 100
c) Suitable null checks and try catch exceptions are provided

Testing 

The program works exactly on the values and formats as shown in the example sheet.  In addition to the example sheet the following were also tested

a) Withdrawal with zero amount
b) Depositing 3 10s
c) Withdrawing  a 1 after the prior deposit
d) Entering 0 as the deposit

The program gave the correct inputs.

The program was written in Java 1.8 in approximately 6 hours with internet access to access certain Java APIs (String Split as StringTokenizer is legacy) on Eclipse Kepler.
