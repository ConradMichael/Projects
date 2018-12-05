package payment;

public class CreditCard extends Card{

	public CreditCard(String vName, String vCardNumber, String vCVV, String vExpiryDate) {
		super(vName, vCardNumber, vCVV, vExpiryDate);
	}

	@Override
	protected String getType() {
		return "Credit";
	}

	@Override
	protected void executePayment(double amount) {
		
	}
	
	@Override
	public void pay(double amount) {
		System.out.println("Paid: £" + amount + " by Credit Card.");
	}

}

