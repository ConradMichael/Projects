package payment;

import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Basket {

	private List<Item> basket = new ArrayList<>();
	
	public void addItem(Item vItem) {
		basket.add(vItem);
	}
	
	public void removeItem(Item vItem) {
		basket.remove(vItem);
	}
	
	public double getCost() {
		
		double d = basket.stream().mapToDouble(item -> item.getCost()).sum();

		DecimalFormat df = new DecimalFormat("#.##");
		String sCost = (df.format(d));
		d = new Double(sCost);
		
		return d;
		
	}
	
	public void pay(PaymentMethod method) {

		method.pay(getCost());
	}
	
}
