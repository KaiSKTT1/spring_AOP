package AOP_demo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import AOP_demo.DAO.AccountDAO;

public class AfterReturnningDemo {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);
		
		//call methods to find accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();
		
		//display the accounts
		System.out.println("\n\n\tMain program: AfterReturning");
		System.out.println("-----------------------------------");
		System.out.println(theAccounts);
		
		context.close();
	}
	
}
