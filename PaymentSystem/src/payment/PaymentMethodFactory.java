package payment;

public class PaymentMethodFactory {

	public static PaymentMethod getPaymentMethod(String type) {
		
		switch(type) {
		
			case "Credit":
				return createCreditCard();
				
			case "Debit":
				return createDebitCard();
				
			case "Cash":
				return useCash();
		}
		throw new IllegalArgumentException();
	}
		
	
	public static CreditCard createCreditCard() {
		return new CreditCard("Tom Smith", "7456 1326 8644 1543", "734", "02/20");
		}	
	
	public static DebitCard createDebitCard() {
		return new DebitCard("Tom Smith", "6541 4366 1367 2145", "629", "08/19");
	}	
	
	public static Cash useCash() {
		return new Cash();
	}	
}
