package baker6romeo.udacity.jwdnd.c1.review;

import baker6romeo.udacity.jwdnd.c1.review.model.ChatMessage;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {
	@LocalServerPort
	private Integer port;

	public String baseURL;

	private static WebDriver driver;

	private ChatPage chatPage;
	private LoginPage loginPage;
	private SignupPage signupPage;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

//	@AfterAll
//	public static void afterAll() {
//		driver.quit();
//	}

//	@BeforeEach
//	public void beforeEach() {
//
//	}

	@Test
	public void signupLogin() {

		//  These processes of signing up, logging in, sending message could have been in a single method in their page
		//  object that we then call from here
		String firstname = "Roger";
		String lastname = "Moore";
		String username = "007";
		String password = "MoneyPenny";
		String message = "This is a message!";

		driver.get("http://localhost:" + port + "/login");

		loginPage = new LoginPage(driver);
		loginPage.signup();
		signupPage = new SignupPage(driver);
		signupPage.enterFirstname(firstname);
		signupPage.enterLastname(lastname);
		signupPage.enterUsername(username);
		signupPage.enterPassword(password);
		signupPage.clickSignup();
		signupPage.clickLogin();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		chatPage = new ChatPage(driver);
		chatPage.enterMessageText(message);
		chatPage.clickSubmit();

		ChatMessage sentMessage	= chatPage.getFirstMessage();

		assertEquals(username, sentMessage.getUsername());
		assertEquals(message, sentMessage.getMessageText());

	}

	@Test
	void contextLoads() {
	}

}
