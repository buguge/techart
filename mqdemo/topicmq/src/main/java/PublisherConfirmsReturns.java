import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class PublisherConfirmsReturns implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
