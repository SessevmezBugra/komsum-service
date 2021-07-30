package com.komsum.tag.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Profile("!test")
@Configuration
@SecurityScheme(name = "security_auth", 
				type = SecuritySchemeType.OAUTH2, 
				flows = @OAuthFlows(
							authorizationCode = @OAuthFlow(
												authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}", 
												tokenUrl = "${springdoc.oAuthFlow.tokenUrl}", scopes = @OAuthScope(
														name = "email")) 
						), scheme = "bearer", bearerFormat = "JWT"
				)
public class OpenAPIConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("**").pathsToMatch("/tag/**").build();
	}
	
}