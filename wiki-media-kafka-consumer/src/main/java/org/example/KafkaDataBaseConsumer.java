package org.example;


import org.example.entity.WikiMediaData;
import org.example.repository.WikiMediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataBaseConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaDataBaseConsumer.class);
    private WikiMediaDataRepository dataRepository;

    public KafkaDataBaseConsumer(WikiMediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recentchange",
            groupId = "Wikimedia-consumer"
    )
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received ->%s",eventMessage ) );
        WikiMediaData wikiMediaData=new WikiMediaData();
        wikiMediaData.setWikiEventData(eventMessage);
        dataRepository.save(wikiMediaData);
    }
}
