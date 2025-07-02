package AOP_demo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;

import AOP_demo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	//add a new method: findAccount()
	
	public java.util.List<Account> findAccounts(boolean flag) {
		
		if(flag) {
			throw new RuntimeException("$$$ Lỗi Exception $$$");
		}
		
		List<Account> myAccounts = new ArrayList<Account>();
		
		//create sample accounts
		Account tempAccount1 = new Account("name1","level1");
		Account tempAccount2 = new Account("name2","level2");
		Account tempAccount3 = new Account("name3","level3");
		
		//add them to our accounts list
		myAccounts.add(tempAccount1);
		myAccounts.add(tempAccount2);
		myAccounts.add(tempAccount3);
		
		return myAccounts;
	}
	
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
