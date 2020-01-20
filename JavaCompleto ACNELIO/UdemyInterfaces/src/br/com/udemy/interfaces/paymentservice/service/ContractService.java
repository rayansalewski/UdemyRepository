package br.com.udemy.interfaces.paymentservice.service;

import java.util.Calendar;
import java.util.Date;

import br.com.udemy.interfaces.paymentservice.entities.Contract;
import br.com.udemy.interfaces.paymentservice.entities.Installments;
import lombok.Data;

@Data
public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		
		double basicQuota = (contract.getTotalValue() / months);
		
		for (int i = 1; i <= months; i++) {
			
			Date date = addMonths(contract.getDate(), i);
			
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			
			contract.addInstallment(new Installments(date, fullQuota));
		}
	}
	
	private Date addMonths(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		
		return calendar.getTime();
	}
}
