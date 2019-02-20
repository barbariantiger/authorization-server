package br.com.barbariantiger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nimbusds.jose.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AuthorizationServerCcpApplication.class})
public class AuthorizationServerCcpApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void givenClientCredentialsGrantWhenApplicationAuthorizedThen200() throws Exception {
		String base64 = Base64.encode("my_client:password").toString();
		this.mockMvc.perform(post("/oauth/token")
				.content("grant_type=client_credentials")
				.header("Authorization", "Basic " + base64)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
