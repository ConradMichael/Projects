package garage;

public class Car extends Vehicle{
	
	protected String Model;
	protected float Engine;
	protected int Wheels;
	protected int Seats;
	
	public Car(String vMake, String vModel) {
		super(vMake);
		this.Model = vModel;
	}

	public Car(String vMake, String vModel, float vEngine, int vWeight, int vWheels, int vSeats) {
		super(vMake, vWeight);
		this.Engine = vEngine;
		this.Model = vModel;
		this.Wheels = vWheels;
		this.Seats = vSeats;
	}
	
	public String toString() {
		
		return ("This car is a: " + this.getMake() + " " + Model + " it has a: " + Engine + "L Engine, It weighs: " + this.getWeight() +
				", it has: " + Seats + " Seats, and " + Wheels + " Wheels.");
		
	}
	
	public double getServiceAmount() {
		double amount = this.Wheels * 25;
		amount += this.Engine * 40;
		return amount;
	}
	

}
