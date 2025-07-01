package AOP_demo.aspect;

import org.aspectj.lang.JoinPoint;
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
