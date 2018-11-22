package garage;

public class Vehicle extends Garage{
	
	protected int weight;
	protected String make;

	public Vehicle(String vMake) {
		this.make = vMake;
		this.weight = 0;
	}
	
	public Vehicle(String vMake, int vWeight) {
		this.make = vMake;
		this.weight = vWeight;
	}

	public void setMake(String vMake) {
		this.make = vMake;
	}
	
	public void setWeight(int vWeight) {
		this.weight = vWeight;
	}
	
	public String getMake() {
		return this.make;
	}
	
	public int getWeight() {
		return this.weight;
	}

}
