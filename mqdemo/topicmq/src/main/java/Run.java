import com.alibaba.fastjson.JSONObject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class Run {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext();
        context1.setConfigLocation("classpath:spring.xml");
        context1.refresh();
        context1.start();

        OrderMsgProducer orderMsgProducer = (OrderMsgProducer) context1.getBean("orderMsgProducer");
        String[] arr = {"approval", "notify"};

        for (int i = 0; i < 10; i++) {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", arr[new Random().nextInt(2)]);
            jsonObject.put("orderNo", String.valueOf(i) + currentTimeMillis);
            orderMsgProducer.send(jsonObject.toString());
        }
    }
}
