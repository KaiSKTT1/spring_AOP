package AOP_demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	//Xử dụng @Pointcut để cho mã nguồn có thể tài sử dụng 
	@Pointcut("execution(* AOP_demo.DAO.*.*(..))")
	private void forDaoPackage() {
		
	}
	// create pointcut for getter methods
	@Pointcut("execution(* AOP_demo.DAO.*.get*(..))")
	private void getter() {}
		
	// create pointcut for setter methods
	@Pointcut("execution(* AOP_demo.DAO.*.set*(..))")
	private void setter() {}
		
	// create pointcut: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
		
	@Before("forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {		
		System.out.println("\n=====>>> Executing @Before advice on method");		
	}
		
	@Before("forDaoPackageNoGetterSetter()")
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");		
	}

}
