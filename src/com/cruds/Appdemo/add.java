package com.cruds.Appdemo;

import java.util.Scanner;

public class add {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		String choice;
		int a=0;
		int b=0;
		int c=0;
		int temp;


			System.out.println("enter the value of a");
			a=sc.nextInt();

			System.out.println("enter the value of b");
			b=sc.nextInt();
			do{
		
			System.out.println("\nplease enter the choice");

			System.out.println("1. add");
			System.out.println("2.sub");
			System.out.println("3 mul");
			System.out.println("4 div");
			System.out.println("5 even or odd");
			System.out.println("6 Swap");
			choice=sc.next();

			switch(choice) 
			{
			case "1":


				c=a+b;


				System.out.println("the value of c is"+c);

				break;

			case "2":


				c=a-b;


				System.out.println("the value of c is"+c);
				break;

			case"3":

				c=a*b;
				
				System.out.println("the value of c"+c);
				break;

			
			
			case "4" : 
				
				try {
					
					c=a/b;
					
				} catch (ArithmeticException e) {
					// TODO: handle exception
					System.out.println("Cannot divide by 0" );
				}
				
				System.out.println("the value of c is"+c);
				break;
				
				
			case "5" :
				
				if(c/2!=0 )
				{
					System.out.println(" " +c+ "its a even no");
				}
				else
				{
					System.out.println("its a odd no");
				}
				
				break;
				
			case "6" :
				
				System.out.println("the value of a before swap "+a);
				
				System.out.println("the value of b before swap "+b);
				
				 temp=a;
				   a=b;
				  b=temp;
				   
				   System.out.println("/n /nthe value of a after swap "+a);
				   System.out.println("the value of b after swap "+b);
				   
				   break;
			
			default :
				System.out.println("invalid choice");
				break;
			}
				
		}while(choice.equals(choice));

			sc.close();
	}
	

}