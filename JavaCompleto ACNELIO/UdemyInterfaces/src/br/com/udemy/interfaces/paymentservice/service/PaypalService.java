package br.com.udemy.interfaces.paymentservice.service;

public class PaypalService implements OnlinePaymentService {

	public static final double FEE_PERCENTAGE = 0.02;
	public static final double MONTHLY_PERCENTAGE = 0.01;
	
	@Override
	public Double paymentFee(double amount) {
		return amount * FEE_PERCENTAGE;
	}

	@Override
	public Double interest(double amount, int months) {
		return amount * MONTHLY_PERCENTAGE * months;
	}

	
}
