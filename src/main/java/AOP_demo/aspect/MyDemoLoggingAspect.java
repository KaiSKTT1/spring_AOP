package AOP_demo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* AOP_demo.service.TrafficFortuneService.getFortune(..))")
	public Object aroundGetFortune2(ProceedingJoinPoint proceedingJoinPoint) {
	    Object res = null;
	    try {
	        res = proceedingJoinPoint.proceed();
	    } catch (Throwable e) { // THAY ĐỔI QUAN TRỌNG: Exception -> Throwable: Thực tế sử dụng
	        myLogger.warning(e.getMessage());
	    }
	    // ...
	    return res;
	}
	
	@Around("execution(* AOP_demo.service.TrafficFortuneService.getFortune(..))")
	public Object aroungGetFortune(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		//print out method we are advising on
		String method = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n\t=====> Executing @Around on method: " + method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//now, let's execute the method
		Object res = null;
		
		try {
			res = proceedingJoinPoint.proceed();
			
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
		
			//throw e; -> Sẽ bị lỗi nó ném reThrow 
			//cho method khác gọi nó bắt buộc phải throw hoặc là try-catch
		}
		
		//get end timestamp
		long end = System.currentTimeMillis();
		
		//compute duration and display it
		long duration = end - begin;
		myLogger.info("\n\t=====> Duration: " + duration/1000.0 + " seconds");
		
		return res;
	}
	
	
	@After("execution(* AOP_demo.DAO.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		
		//print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n\t=====> Executing @After (finally) on method: " + method);
	}
	
	@AfterThrowing(
		pointcut = "execution(* AOP_demo.DAO.AccountDAO.findAccounts(..))",
		throwing = "theExc"
	)
	public void afterThrowingFindAccountsAdvice(
			JoinPoint joinPoint, Throwable theExc) {
		//print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n\t=====> Executing @AfterThrowing on method: " + method);
		
		//log the exception
		myLogger.info("\n\t=====> The Exception is: " + theExc);
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
		myLogger.info("\n\t=====> Executing @AfterReturning on method: " + method);
		
		//print out the results of the method call
		myLogger.info("\n\t=====> Result is: " + result);
	}
	
	
	@Before("AOP_demo.aspect.AOP_Expressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {		
		myLogger.info("\n=====>>> Executing @Before advice on method");		
		
		//display the method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		//display method arguments
		myLogger.info("\t\tMethod : " + methodSig);
	
		//get args
		Object[] args = joinPoint.getArgs();
		
		//loop thru args
		for (Object tempArg : args) {
			myLogger.info("\t" + tempArg);
			if(tempArg instanceof Account) {
				
				//download and print Account specific stuff
				Account account = (Account)tempArg;
				myLogger.info("\tAccount name: " + account.getName());
				myLogger.info("\tLevel name  : " + account.getLevel());
			}
		}
			
	}
	
}
