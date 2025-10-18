package com.msgatewayserver.config;

import com.msgatewayserver.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;

    @Autowired
    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            if (path.contains("/auth")) {
                return chain.filter(exchange);
            }

            String tokenHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED, "Falta o es inválido el token de autorización");
            }

            String token = tokenHeader.substring(7);

            return webClient.build()
                    .post()
                    .uri("http://ms-auth/auth/validate?token=" + token)
                    .retrieve()
                    .bodyToMono(TokenDto.class)
                    .flatMap(response -> chain.filter(exchange))
                    .onErrorResume(e -> onError(exchange, HttpStatus.UNAUTHORIZED, "Token inválido o expirado"));
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        byte[] bytes = ("{\"error\":\"" + message + "\"}").getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    public static class Config {}
}

