package AOP_demo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import AOP_demo.Account;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
		
	@AfterThrowing(
		pointcut = "execution(* AOP_demo.DAO.AccountDAO.findAccounts(..))",
		throwing = "theExc"
	)
	public void afterThrowingFindAccountsAdvice(
			JoinPoint joinPoint, Throwable theExc) {
		//print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n\t=====> Executing @AfterThrowing on method: " + method);
		
		//log the exception
		System.out.println("\n\t=====> The Exception is: " + theExc);
	}
	
	
	// add a new advice for @AfterReturnning on the findAccounts() method
	@AfterReturning(
		pointcut = "execution(* AOP_demo.DAO.AccountDAO.findAccounts(..))",
		returning = "result"
	)
	public void afterReturnningFindAccountsAdvice(
			JoinPoint joinPoint, List<Account> result) {
		//print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n\t=====> Executing @AfterReturning on method: " + method);
		
		//print out the results of the method call
		System.out.println("\n\t=====> Result is: " + result);
	}
	
	
	@Before("AOP_demo.aspect.AOP_Expressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {		
		System.out.println("\n=====>>> Executing @Before advice on method");		
		
		//display the method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		//display method arguments
		System.out.println("\t\tMethod : " + methodSig);
	
		//get args
		Object[] args = joinPoint.getArgs();
		
		//loop thru args
		for (Object tempArg : args) {
			System.out.println("\t" + tempArg);
			if(tempArg instanceof Account) {
				
				//download and print Account specific stuff
				Account account = (Account)tempArg;
				System.out.println("\tAccount name: " + account.getName());
				System.out.println("\tLevel name  : " + account.getLevel());
			}
		}
			
	}
	
}
