package tech.arao.minibank;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import tech.arao.minibank.data.domain.Account;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MiniBankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountRESTControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllAccounts() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/bank/accounts/all", HttpMethod.GET, entity,
				String.class);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetAccountById() {
		Account account = restTemplate.getForObject(getRootUrl() + "/bank/accounts/1", Account.class);
		Assert.assertNotNull(account);
	}

	@Test
	public void testCreateAccount() {
		Account account = new Account(1L, new BigDecimal(500));
		ResponseEntity<Account> postResponse = restTemplate.postForEntity(getRootUrl() + "/bank/accounts/create", account, Account.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateAmount() {
		int id = 1;
		restTemplate.put(getRootUrl() + "/accounts/" + id, new Double(100));
		Account updatedAccount = restTemplate.getForObject(getRootUrl() + "/bank/accounts/" + id, Account.class);
		Assert.assertNotNull(updatedAccount);
	}
}
