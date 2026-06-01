package war_monitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration     // 이 클래스를 설정 클래스라고 지정
@EnableWebSocketMessageBroker // WebSocket 메시지 브로커 기능 켜주는거
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // "/topic" 으로 시작하는 경로로 메시지 전달

        config.setApplicationDestinationPrefixes("/app");
        // 클라이언트가 서버로 보낼떄 "/app prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .withSockJS();
        // 브라우저가 "/ws"로 WebSocket 연결
    }
}
