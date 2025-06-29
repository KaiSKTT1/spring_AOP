package AOP_demo.DAO;

import org.springframework.stereotype.Component;

import AOP_demo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass()  + " in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass()  + " in setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return false;
	}
	
	

	
}
