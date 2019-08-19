package fanout;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class FanoutRun {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(FanoutRun.class.getSimpleName());

        //带资源的try语句（try-with-resource）,try块退出时，会自动调用res.close()方法，关闭资源。
        try (ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml")) {
            context1.registerShutdownHook();
            context1.start();

            OrderMsgProducer orderMsgProducer = (OrderMsgProducer) context1.getBean("orderMsgProducer");

            for (int i = 0; i < 10; i++) {
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderNo", String.valueOf(i) + currentTimeMillis);
                orderMsgProducer.send(jsonObject.toString());
            }
        } catch (BeansException e) {
            logger.error("spring 容器启动错误", e);
        }
    }
}
