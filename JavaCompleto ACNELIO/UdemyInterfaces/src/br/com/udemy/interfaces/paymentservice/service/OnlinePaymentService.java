package br.com.udemy.interfaces.paymentservice.service;

public interface OnlinePaymentService {

	Double paymentFee(double amount);
	Double interest(double amount, int months);
}
