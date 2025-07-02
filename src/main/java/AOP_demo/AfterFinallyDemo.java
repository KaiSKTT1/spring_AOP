package AOP_demo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import AOP_demo.DAO.AccountDAO;

public class AfterFinallyDemo {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);
		
		//call methods to find accounts
		List<Account> theAccounts = null;
		try {
			//Giả sử hàm luôn cho ra exception
			boolean flag = true;
			theAccounts = theAccountDAO.findAccounts(flag);
			
		} catch (Exception e) {
			System.out.println("Main Program ... caught exception: " + e);
		}
		
		//display the accounts
		System.out.println("\n\n\tMain program: AfterReturning");
		System.out.println("-----------------------------------");
		System.out.println(theAccounts);
		
		context.close();
	}
	
}
