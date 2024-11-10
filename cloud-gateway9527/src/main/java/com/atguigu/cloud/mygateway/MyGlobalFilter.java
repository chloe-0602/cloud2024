package com.atguigu.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: MyGatewayFilterFactory
 * Package: com.atguigu.cloud.mygateway
 * Description:
 *  自定义全局Filter，实现无感日志记录
 *  来源： https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway/global-filters.html
 * @Author Xu, Luqin
 * @Create 2024/11/11 7:23
 * @Version 1.0
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    private final static String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            long begin_visit_time = exchange.getAttribute(BEGIN_VISIT_TIME);
            log.info("访问接口主机: {}", exchange.getRequest().getURI().getHost());
            log.info("访问接口端口: {}", exchange.getRequest().getURI().getPort());
            log.info("访问接口URL: {}", exchange.getRequest().getURI().getPath());
            log.info("访问接口参数: {}", exchange.getRequest().getURI().getRawQuery());
            log.info("访问接口时长: {}毫秒", (System.currentTimeMillis() - begin_visit_time));
            log.info("========================================");
            System.out.println();
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
