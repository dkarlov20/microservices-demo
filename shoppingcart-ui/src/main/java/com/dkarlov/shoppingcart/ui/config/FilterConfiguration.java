package com.dkarlov.shoppingcart.ui.config;

import com.dkarlov.shoppingcart.ui.filter.AuthHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    AuthHeaderFilter authHeaderFilter() {
        return new AuthHeaderFilter();
    }
}
