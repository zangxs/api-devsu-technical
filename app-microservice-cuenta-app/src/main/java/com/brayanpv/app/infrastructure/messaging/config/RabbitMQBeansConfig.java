package com.brayanpv.app.infrastructure.messaging.config;

import com.brayanpv.app.infrastructure.messaging.constants.RabbitMQConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBeansConfig {


    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(RabbitMQConstants.CLIENTE_EXCHANGE, true, false);
        // durable = true: el exchange sobrevive a un reinicio del broker
        // autoDelete = false: no se elimina aunque no tenga colas enlazadas
    }

    @Bean
    public Queue cuentasClienteEventsQueue() {
        return QueueBuilder.durable(RabbitMQConstants.QUEUE_CUENTAS_CLIENTE_EVENTS)
                .withArgument("x-dead-letter-exchange", RabbitMQConstants.DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitMQConstants.DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding bindingClienteCreado(Queue cuentasClienteEventsQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(cuentasClienteEventsQueue)
                .to(clienteExchange)
                .with(RabbitMQConstants.ROUTING_KEY_CLIENTE_CREADO);
    }

    @Bean
    public Binding bindingClienteActualizado(Queue cuentasClienteEventsQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(cuentasClienteEventsQueue)
                .to(clienteExchange)
                .with(RabbitMQConstants.ROUTING_KEY_CLIENTE_ACTUALIZADO);
    }


    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(RabbitMQConstants.DLX_EXCHANGE, true, false);
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(RabbitMQConstants.DLQ_QUEUE).build();
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(RabbitMQConstants.DLQ_ROUTING_KEY);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }
}
