package payment;

public class DebitCard extends Card{

	public DebitCard(String vName, String vCardNumber, String vCVV, String vExpiryDate) {
		super(vName, vCardNumber, vCVV, vExpiryDate);
	}

	@Override
	protected String getType() {
		return "Debit";
	}

	@Override
	protected void executePayment(double amount) {
		
	}
	
	@Override
	public void pay(double amount) {
		System.out.println("Paid: £" + amount + " by Debit Card.");
	}
}

