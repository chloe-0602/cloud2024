package com.atguigu.cloud.predicate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * ClassName: MyRoutePredicateFactory
 * Package: com.atguigu.cloud.predicate
 * Description:
 *    定义会员等级，金、银、铜
 * @Author Xu, Luqin
 * @Create 2024/11/10 21:05
 * @Version 1.0
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    public static final String USER_TYPE_KEY = "userType";
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {

        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst(USER_TYPE_KEY);
                if (null == userType){
                    return false;
                }

                if (userType.equals(config.getUserType())){
                    return true;
                }
                return false;
            }
        };
    }
    @Validated
    public static class Config {
        @Getter
        @Setter
        @NotNull
        private String userType;
    }
}
