package AOP_demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import AOP_demo.DAO.AccountDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		//read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);
		//call the business method
		accountDAO.addAccount();
		
		//do it again
		System.out.println("\nLet's call it again!");
		accountDAO.addAccount();
		
		//close the context
		context.close();
	}
	
}
