package garage;

public class Motorbike extends Vehicle {
	
	private String model;
	private int CC;
	private boolean hasStorage;
	
	public Motorbike(String vMake, String vModel) {
		super(vMake);
		this.model = vModel;
	}
	
	public Motorbike(String vMake, String vModel, int vCC, boolean vhasStorage) {
		super(vMake);
		this.model = vModel;
		this.CC = vCC;
		this.hasStorage = vhasStorage;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public int getCC() {
		return this.CC;
	}
	
	public String toString() {
		if(hasStorage) {

		return("This bike is a: " + this.getMake() + " " + model + " it has a " + CC + " CC Engine and it has Storage");
		} else {
		
		return("This bike is a: " + this.getMake() + " " + model + " it has a " + CC + " CC Engine");
		}
	}
}
