package AOP_demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class AOP_Expressions {
	@Pointcut("execution(* AOP_demo.DAO.*.*(..))")
	public void forDaoPackage() {}
		
	@Pointcut("execution(* AOP_demo.DAO.*.get*(..))")
	public void getter() {}
			
	@Pointcut("execution(* AOP_demo.DAO.*.set*(..))")
	public void setter() {}
		
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
}
