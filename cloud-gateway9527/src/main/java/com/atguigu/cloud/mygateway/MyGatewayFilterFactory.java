package com.atguigu.cloud.mygateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.SetStatusGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: MyGatewayFilterFactory
 * Package: com.atguigu.cloud.mygateway
 * Description:
 *   自定义条件Filter，实现判断接口参数中是否包含chloe，比如？chloe=123
 *   参考的类： SetStatusGatewayFilterFactory
 * @Author Xu, Luqin
 * @Create 2024/11/11 7:47
 * @Version 1.0
 */
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config>{
    public static final String STATUS_KEY = "status";
    public MyGatewayFilterFactory() {
        super(MyGatewayFilterFactory.Config.class);
    }
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(STATUS_KEY);
    }
    @Override
    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("进入自定义网关过滤器MyGatewayFilterFactory，status===="+config.getStatus());

                ServerHttpRequest request = exchange.getRequest();
                if (request.getQueryParams().containsKey("chloe")){
                    return chain.filter(exchange);
                }else {
                    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                    return exchange.getResponse().setComplete();
                }
            }
        };
    }

    public static class Config {
        @Getter@Setter
        private String status;
    }
}
