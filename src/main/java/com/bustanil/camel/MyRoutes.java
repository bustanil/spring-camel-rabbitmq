package com.bustanil.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoutes extends RouteBuilder {

    @Override public void configure() throws Exception {
        from("netty4-http:http://127.0.0.1:9999")
            .to("rabbitmq://127.0.0.1/message")
        ;

        from("rabbitmq://127.0.0.1/message")
                .process(exchange -> {
                    System.out.println("The message is : " + exchange.getIn().getBody(String.class));
                })
        .to("log:com.bustanil.camel");
    }

}
