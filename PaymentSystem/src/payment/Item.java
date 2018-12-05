package payment;

public class Item {

	private String description;
	private double cost;
	
	public Item(String vDescription, double vCost) {
		this.setDescription(vDescription);
		this.cost = vCost;
	}
	
	public double getCost() {
		
		return cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}




