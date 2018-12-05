package payment;

public class Cash implements PaymentMethod{

	@Override
	public void pay(double amount) {
		
		System.out.println("Paid: £" + amount + " in Cash.");
		
	}

}
