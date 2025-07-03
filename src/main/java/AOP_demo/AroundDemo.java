package AOP_demo;



import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import AOP_demo.service.TrafficFortuneService;


public class AroundDemo {

	private static Logger myLogger = Logger.getLogger(AroundDemo.class.getName());
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = 
				context.getBean("trafficFortuneService",TrafficFortuneService.class);
		
		myLogger.info("\n\tMain Program: AroundDemoApp");
		
		myLogger.info("\n\tCalling getFortune");
		
		boolean flag = true;
		String data = fortuneService.getFortune(flag);
		myLogger.info("\n\tMy fortune is: " + data);
		myLogger.info("\n\tFinished");
		
		context.close();
	}
	
}
