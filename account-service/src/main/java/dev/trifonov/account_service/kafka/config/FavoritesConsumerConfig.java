package dev.trifonov.account_service.kafka.config;

import dev.trifonov.account_service.kafka.message.NewFavoriteProductMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class FavoritesConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.application.kafka.consumer.groups.favorites}")
    private String favoritesConsumerGroup;

    /*@Value("${spring.application.kafka.consumer.json-deserializer.trusted-packages.catalog-service}")
    private String jsonDeserializerTrustedPackages;*/

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NewFavoriteProductMessage> favoritesKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NewFavoriteProductMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        JsonDeserializer<NewFavoriteProductMessage> jsonDeserializer = new JsonDeserializer<>(NewFavoriteProductMessage.class);
        jsonDeserializer.setUseTypeHeaders(false);
        factory.setConsumerFactory(
                new DefaultKafkaConsumerFactory<>(
                        getConsumerFactoryProps(),
                        new StringDeserializer(),
                        jsonDeserializer
                ));
        return factory;
    }

    private Map<String, Object> getConsumerFactoryProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, favoritesConsumerGroup);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        return props;
    }
}
