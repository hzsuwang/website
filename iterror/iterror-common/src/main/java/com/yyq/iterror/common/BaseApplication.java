package com.yyq.iterror.common;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class BaseApplication  extends SpringBootServletInitializer {
    public BaseApplication() {
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(new Class[]{this.getClass()});
        return super.configure(builder);
    }
}
