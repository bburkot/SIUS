package pl.edu.agh.sius.client.http.viewmodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.Payment;
import pl.edu.agh.sius.Product;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.server.responses.Balance;
import pl.edu.agh.sius.server.responses.ResponseBalances;
import pl.edu.agh.sius.server.responses.ResponsePayments;
import pl.edu.agh.sius.server.responses.ResponseProducts;
import pl.edu.agh.sius.server.responses.ResponseSimple;

public class History {
	private Component historyWindow;
	private User loggedUser;
	private Window modalWindow;
	private ListModelList<Balance> balances;
	private ListModelList<Payment> acceptPayment;
	private ListModelList<Payment> userPay;
	private ListModelList<Payment> toUserPaid;
	private BigDecimal payToUserAmount;
	private Balance payToBalance;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		historyWindow = view;
	}
	
	@Init
	public void init(){
		loggedUser = (User) Sessions.getCurrent().getAttribute("user");
		if (loggedUser == null){
			Executions.sendRedirect("/");
			return;
		}		
		
		ResponseBalances response = ServiceUtils.getService().getUsersBalances(loggedUser);
		ResponsePayments response2 = ServiceUtils.getService().getUserPayments(loggedUser);
		
		if (response.getOperationStatus() != OperationStatus.SUCCEED
			|| response2.getOperationStatus() != OperationStatus.SUCCEED
		){		
			String msg = "Operacja nie powiodła sie";
			if (response.getMsg() != null)	msg += "\nPowód: " + response.getMsg();
			if (response2.getMsg() != null)	msg += "\nPowód: " + response2.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		balances = new ListModelList<>(response.getBalances());
		
		acceptPayment = new ListModelList<Payment>();
		userPay = new ListModelList<Payment>();
		toUserPaid = new ListModelList<Payment>();
		for (Payment p : response2.getPayments()){
			if (p.getFrom().getId().equals(loggedUser.getId())){
				userPay.add(p);
			} else if (p.getDate() == null){
				acceptPayment.add(p);
			} else
				toUserPaid.add(p);
		}
	}
	
	@Command
	public void showPay(

			@BindingParam("val") Balance value
	){
		if (modalWindow != null)
			modalWindow.detach();
		
		setPayToBalance(value);
		payToUserAmount = BigDecimal.ZERO;
		
		modalWindow = (Window)Executions.createComponents("/dialog-windows/payToUser.zul", historyWindow, null);
		modalWindow.doModal();
	}
		
	@Command
	public boolean isLessThanZero(BigDecimal value){
		return value.compareTo(BigDecimal.ZERO) < 0;
	}
	
	@Command
	public void pay(){
		ResponseSimple response = ServiceUtils.getService()
				.payMoney(payToBalance.getUser().getId(), 
						payToUserAmount.setScale(2, RoundingMode.HALF_UP), 
						loggedUser);
		
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			modalWindow.detach();
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	public void acceptPayment(@BindingParam("val") String paymentId){
		ResponseSimple response = ServiceUtils.getService().acceptPayment(paymentId, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			Executions.sendRedirect("/history.zul");
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
		
	}
	
	
	public ListModelList<Balance> getBalances() {
		return balances;
	}
	public ListModelList<Payment> getAcceptPayment() {
		return acceptPayment;
	}

	public BigDecimal getPayToUserAmount() {
		return payToUserAmount;
	}

	public void setPayToUserAmount(BigDecimal payToUserAmount) {
		this.payToUserAmount = payToUserAmount;
	}

	public Balance getPayToBalance() {
		return payToBalance;
	}

	public void setPayToBalance(Balance payToBalance) {
		this.payToBalance = payToBalance;
	}
}
