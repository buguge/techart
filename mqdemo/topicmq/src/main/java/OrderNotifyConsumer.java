import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class OrderNotifyConsumer implements MessageListener {
    private final Logger log = LogManager.getLogger(this.getClass().getSimpleName());

    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("操作提醒消息出队msg:{}", msg);
    }
}
