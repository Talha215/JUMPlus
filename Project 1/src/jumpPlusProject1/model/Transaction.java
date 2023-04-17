package jumpPlusProject1.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerId;
	private double amount;
	private LocalDateTime time;

	public enum TYPE {
		DEPOSIT, WITHDRAWAL, TRANSFER
	}

	private TYPE type;
	private String transferId;

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
		if (type == TYPE.TRANSFER && amount < 0) //you sent a transfer
			return "Transfer to [" + transferId + "]: " + NumberFormat.getCurrencyInstance().format(amount) + " on " + formatter.format(time);
		if (type == TYPE.TRANSFER && amount > 0) //you recieved a transfer
			return "Transfer from [" + transferId + "]: " + NumberFormat.getCurrencyInstance().format(amount) + " on " + formatter.format(time);
		if (type == TYPE.WITHDRAWAL)
			return "Withdrawal: " + NumberFormat.getCurrencyInstance().format(amount) + " on " + formatter.format(time);
		if (type == TYPE.DEPOSIT)
			return "Deposit: " + NumberFormat.getCurrencyInstance().format(amount) + " on " + formatter.format(time);

		return "Error";
	}

	public Transaction(String customerId, double amount, LocalDateTime time, TYPE type) {
		super();
		this.customerId = customerId;
		this.amount = amount;
		this.time = time;
		this.type = type;
		transferId = "";
	}

	public Transaction(String customerId, double amount, LocalDateTime time, TYPE type, String transferId) {
		super();
		this.customerId = customerId;
		this.amount = amount;
		this.time = time;
		this.type = type;
		this.transferId = transferId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Transaction() {

	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
}
