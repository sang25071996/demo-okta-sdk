package com.example.config;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.client.ApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OktaConfig {

  @Bean
  public ApiClient apiClient() {
    ApiClient apiClient = Clients.builder()
        .setOrgUrl("https://dev-25520376.okta.com")
        .setClientCredentials(new TokenClientCredentials(System.getenv("OKTA_SECRET"))).build();
    return apiClient;
  }
}
