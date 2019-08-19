package fanout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class PublisherConfirmsReturns implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {
    private final Logger log = LogManager.getLogger(this.getClass().getSimpleName());

    /**
     * 当消息发送到交换机（exchange）时，该方法被调用. ack=消息到达exchange?true:false
     * Confirmation callback.
     * @param correlationData correlation data for the callback.
     * @param ack true for ack, false for nack
     * @param cause An optional cause, for nack, when available, otherwise null.
     */
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("publisher confirm, ack:{}, correlationData:{}", ack, correlationData);
    }

    /*
publisher confirm, ack:true, correlationData:null
publisher confirm, ack:true, correlationData:null
     */

    /**
     * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
     * Returned message callback.
     * @param message the returned message.
     * @param replyCode the reply code.
     * @param replyText the reply text.
     * @param exchange the exchange.
     * @param routingKey the routing key.
     */
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息从交换机到队列失败！");
    }
    /*
manage_web_error_2019-07-05_1.log
Line 3: 2019-07-05 10:00:07,907 ERROR [AMQP Connection 192.168.40.20:5672] (PublisherConfirmsReturns.java:49) - publisher return, type:NO_ROUTE, exchange:huipiao.operation.remind.exchange, routing-key:operationRemindQueue_Key, message:{"mobilePhone":"18001150364","createTime":1562292000000,"merchantId":103201,"withdrawAmount":"6.000000"}----BOND_WITHDRAW_AUDIT_OK
Line 4: 2019-07-05 10:02:59,163 ERROR [AMQP Connection 192.168.40.20:5672] (PublisherConfirmsReturns.java:49) - publisher return, type:NO_ROUTE, exchange:huipiao.operation.remind.exchange, routing-key:operationRemindQueue_Key, message:{"orderNo":"20190601112346595574"}----RemindBuyerToPay
     */
}
