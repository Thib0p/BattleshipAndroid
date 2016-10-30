package com.excilys.formation.battleships;
public class FunctionHelpers {
public static class ShipException extends RuntimeException{
	public ShipException(){
		System.out.println("Cannot create a ship there");
	}
}
public static class ShipAlreadyStruck extends Exception{
	public ShipAlreadyStruck(){
		System.out.println("This ship has already been struck here..");
	}
}
public static void clearConsole() {
	final String ANSI_CLS = "\u001b[2J";
	final String ANSI_HOME = "\u001b[H";
	System.out.print(ANSI_CLS + ANSI_HOME);
	System.out.flush();
}
}
