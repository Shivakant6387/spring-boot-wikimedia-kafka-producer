package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataBaseConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaDataBaseConsumer.class);
    @KafkaListener(
            topics = "wikimedia_recentchange",
            groupId = "Wikimedia-consumer"
    )
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received ->%s",eventMessage ) );
    }
}
