package AOP_demo.service;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

    // Sử dụng Logger của SLF4J 👇
    private static final Logger logger = LoggerFactory.getLogger(TrafficFortuneService.class);
    
    public String getFortune() {
        logger.debug("Bắt đầu xử lý getFortune...");
        try {
            logger.debug("Bắt đầu sleep 5 giây...");
            TimeUnit.SECONDS.sleep(5);
            logger.debug("Sleep hoàn thành");
        } catch (InterruptedException e) {
            logger.error("Lỗi khi sleep", e);
        }
        logger.debug("Chuẩn bị return kết quả...");
        
        return "Expect heavy traffic this morning";
    }

    public String getFortune(boolean flag) {
    	if(flag)
    		throw new RuntimeException("\n\t**Exception method getFortune(boolean)!!");
    	return "Expect heavy traffic this morning";
    }
    
}
