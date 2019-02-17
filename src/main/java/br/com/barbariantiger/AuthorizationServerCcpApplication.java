package br.com.barbariantiger;

import java.security.KeyPair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthorizationServerCcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerCcpApplication.class, args);
	}
	
	@Bean
	public KeyPair keyPair() {
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwks.jks"), "jwtoken".toCharArray());
		return new KeyPair(keyStoreKeyFactory.getKeyPair("jwtoken").getPublic(), keyStoreKeyFactory.getKeyPair("jwtoken").getPrivate());
	}

}
