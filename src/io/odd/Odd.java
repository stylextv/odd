package io.odd;

import io.odd.random.Random;

public class Odd {
	
	public static void main(String[] args) {
		Random random = new Random();
		
		for(int i = 0; i < 10; i++) System.out.println(random.nextUUID());
	}
	
}
