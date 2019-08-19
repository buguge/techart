import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMsgProducer {

    private static final Logger log = LogManager.getLogger(OrderMsgProducer.class.getSimpleName());

    @Autowired
    private RabbitTemplate orderMQTemplate;

    public void send(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);

        String routingKey = "approval.order.c";
        if ("notify".equals(jsonObject.getString("type"))) {
            routingKey = "notify.order";
        }
        log.info("代理商服务费入队,routingKey={}, 消息={}", routingKey, json);
        orderMQTemplate.convertAndSend(routingKey, json);
        log.info("代理商服务费入队完成,routingKey={}, 消息={}", routingKey, json);
    }
}
