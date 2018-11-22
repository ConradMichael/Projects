package garage;
//on overload you can change return type to anything you want using any method.
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		ArrayList<Motorbike> bikes = new ArrayList<Motorbike>();

		Car Car1 = new Car("Vauxhaul", "Frontera", 2.8f, 2800, 4, 5);
		Car Car2 = new Car("Honda", "CR-Z", 1.5f, 1200, 4, 4);

		Motorbike Bike1 = new Motorbike("Bike1Make", "Bike1Model", 125, false);
		Motorbike Bike2 = new Motorbike("Bike2Make", "Bike1Mode2", 200, true);
		
		cars.add(Car1);
		cars.add(Car2);
		
		bikes.add(Bike1);
		bikes.add(Bike2);
		
		for(Car i : cars) {
			System.out.println(i);
			double serviceAmount = i.getServiceAmount();
			System.out.println("Cost To Service: " + serviceAmount);
		}
		
		System.out.println("");
		
		for(Motorbike i : bikes) {
			System.out.println(i);
		}

	}

}
