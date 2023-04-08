package jumpPlusProject1.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import jumpPlusProject1.model.Transaction.TYPE;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phoneNumber;
	private String id;
	private String password;
	private double balance;
	private List<Transaction> transactions;

	public Customer(String name, String address, String phoneNumber, String id, String password, double balance) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.password = password;
		this.balance = balance;

		transactions = new ArrayList<>();
		transactions.add(new Transaction(id, balance, LocalDateTime.now(), TYPE.DEPOSIT));
	}

	public void addTransaction(double amount) {
		balance += amount;
		if(amount < 0)
			transactions.add(new Transaction(id, amount, LocalDateTime.now(), TYPE.WITHDRAWAL));
		else
			transactions.add(new Transaction(id, amount, LocalDateTime.now(), TYPE.DEPOSIT));
	}
	
	public void addTransaction(double amount, String transferId) {
		balance += amount;
		transactions.add(new Transaction(id, amount, LocalDateTime.now(), TYPE.TRANSFER, transferId));
	}

	public void printTransactions() {
		if (transactions.size() < 5)
			transactions.stream().forEach(System.out::println);
		else
			transactions.subList(transactions.size() - 5, transactions.size()).
				stream().
				collect(Collectors.toCollection(LinkedList::new)).
				descendingIterator().
				forEachRemaining(System.out::println);
	}

	@Override
	public String toString() {
		return "\nYou are logged in as: " + id + ".\nName: " + name + "\nAddress: " + address
				+ "\nPhone Number: " + phoneNumber + "\nBalance: " + NumberFormat.getCurrencyInstance().format(balance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
