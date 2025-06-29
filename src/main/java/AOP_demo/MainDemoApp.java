package AOP_demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import AOP_demo.DAO.AccountDAO;
import AOP_demo.DAO.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		//read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);
		
		// get membership bean from spring container
		MembershipDAO theMembershipDAO = 
				context.getBean("membershipDAO", MembershipDAO.class);

		
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();

		
		//close the context
		context.close();
	}
	
}
