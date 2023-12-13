package com.example.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.util.StringUtils;

@Configuration
public class OktaTokenResolver implements BearerTokenResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OktaTokenResolver.class);
    @Override
    public String resolve(HttpServletRequest request) {
        String authorization = request.getHeader("authorization");
        if (StringUtils.hasText(authorization)) {
            int tokenStartIndex = authorization.toLowerCase().startsWith("bearer ") ? "bearer ".length() : 0;
            LOGGER.info("Token found in header!");
            return authorization.substring(tokenStartIndex);
        }
        return null;
    }
}
