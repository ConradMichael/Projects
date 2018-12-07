package dogcomp;

import java.util.ArrayList;
import java.util.Scanner;

public class DogComp {

	private static ArrayList<String> positions = new ArrayList<String>();
	private static int maxPos = 0;
	
	public static void main(String[] args) {
		System.out.println("How many positions are there?");
		Scanner s = new Scanner(System.in);
		maxPos = s.nextInt();
		
		improvedGenPositions();
		
		dogPosition();
		s.close();
	}
	
	private static void dogPosition() {
		Scanner s = new Scanner(System.in);

		System.out.println("Enter your dogs position: ");
		int dPos = s.nextInt();
		
		for(int i = 0; i < positions.size(); i++) {
			if((i + 1) != dPos) {
				System.out.println(positions.get(i));
			}
		}
		s.close();
	}
	
	private static void improvedGenPositions() {
		for(int i = 1; i < maxPos + 1; i++) {
			if(i % 10 == 1 && i % 100 != 11) {
				positions.add(i + "st");
			} else if (i % 10 == 2 && i % 100 != 12){
				positions.add(i + "nd");
			} else if (i % 10 == 3  && i % 100 != 13){
				positions.add(i + "rd");
			} else {
				positions.add(i + "th");
			}
		}
	}	
}
