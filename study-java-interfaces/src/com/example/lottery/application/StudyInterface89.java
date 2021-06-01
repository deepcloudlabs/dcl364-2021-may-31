package com.example.lottery.application;

import java.util.List;

// Java SE 8
// 1. Functional Interface
// 2. default method
// 3. static method

// Java SE 9
// 1. private method
// 2. static private method

public class StudyInterface89 {

	public static void main(String[] args) {
		var numbers = List.of(4,8,15,16,23,42);
		var sumOfEvenNumbers = numbers.stream()
				                      .filter( Arithmetic::even )
				                      .map( Arithmetic::square )
				                      .reduce( 0 , Integer::sum );
		System.err.println(sumOfEvenNumbers);
	}

}
interface Arithmetic {
	static boolean even(int value) {
		return value%2 == 0;
	}
	static int square(int value) {
		return value * value;
	}
}
// Functional Interface
@FunctionalInterface
abstract interface Fun {
	public static final int x = 42;
	public abstract int fun(); // SAM

	default int gun() { // default implementation
		System.err.println("Default gun!");
		return 42;
	}
	public static int sun() { // functional programming utility method
		return 3615 + run();
	}
	private static int run() {
		return 549;
	}
}

class A implements Fun {

	@Override
	public int fun() {
		return 0;
	}

	@Override
	public int gun() {
		System.err.println("A::gun!");
		return 108;
	}
	
}