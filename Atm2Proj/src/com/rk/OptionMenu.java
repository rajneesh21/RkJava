package com.rk;

import java.util.*;

public class OptionMenu {

	public void menu() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Rk_Atm");
		System.out.println("Enter Customer No.");
		int custNo = sc.nextInt();
		System.out.println("Enter Pin");
		int pin = sc.nextInt();

		Dao d = new Dao();
		d.dbase(custNo, pin);

	}

	public int transaction() {
		Dao d = new Dao();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Enter the option number \n 1. for Balance Check \n 2. for Deposit \n 3. for Withdraw \n 4. for exit");
		int n = sc.nextInt();
		return n;

	}
}
