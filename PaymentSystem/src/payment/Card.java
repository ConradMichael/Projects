package payment;

public abstract class Card implements PaymentMethod{

	private String name;
	private String cardNumber;
	private String CVV;
	private String expiryDate;
	
	public Card(String vName, String vCardNumber, String vCVV, String vExpiryDate) {
		
		this.name = vName;
		this.cardNumber = vCardNumber;
		this.CVV = vCVV;
		this.expiryDate = vExpiryDate;
		
	}
	
	@Override
	public String toString() {
		return (getType() + " card." + "Name: " + name + ", Card Number: " + cardNumber + ", CVV: " + CVV + ", Expiry Date: " + expiryDate);
	}
	
	@Override
	public void pay(double amount) {
		System.out.println("Paid: £" + amount + " by Card.");
	}
	
	protected abstract String getType();
	protected abstract void executePayment(double amount);
	
}

