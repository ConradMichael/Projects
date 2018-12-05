package payment;

public class PaymentApp {

	private static String PaymentType = "Cash";
	
	public static void main(String[] args) {
		
		Basket basket = new Basket();

		basket.addItem(new Item("Item 1", 19.99));
		basket.addItem(new Item("Item 2", 49.99));

		basket.pay(PaymentMethodFactory.getPaymentMethod(PaymentType));
		
	}
	
}

