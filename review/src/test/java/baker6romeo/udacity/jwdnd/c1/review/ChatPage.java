package baker6romeo.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    Select select = new Select(messageType);

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterMessageText(String message) {
        messageText.sendKeys(message);
    }

    public void say() {
        select.selectByVisibleText("Say");
    }

    public void shout() {
        select.selectByVisibleText("Shout");
    }

    public void whisper() {
        select.selectByVisibleText("Whisper");
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
