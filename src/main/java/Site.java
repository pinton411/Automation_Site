import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Scanner;

public class Site {

    public Site() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
    }

    public Scanner userInput = new Scanner(System.in);

    public void login() {

        System.out.println("Enter user name:");
        String userName = userInput.nextLine();
        System.out.println("Enter password:");
        String userPassword = userInput.nextLine();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        driver.manage().window().maximize();

        List<WebElement> elementList = driver.findElements(By.className("top-header-menu"));
        WebElement menu = elementList.get(0);

        List<WebElement> chunks = menu.findElements(By.tagName("li"));
        WebElement info = chunks.get(20);
        info.click();

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement userNameInput = driver.findElement(By.id("Ecom_User_ID"));
        WebElement userPasswordInput = driver.findElement(By.id("Ecom_Password"));
        if (userNameInput != null && userPasswordInput != null) {
            userNameInput.sendKeys(userName);
            userPasswordInput.sendKeys(userPassword);
        }

        List<WebElement> login = driver.findElements(By.className("submit"));
        WebElement button = login.get(0);
        button.click();

        List<WebElement> subMenu = driver.findElements(By.className("col-sm-6"));
        WebElement moodle = subMenu.get(6);
        moodle.click();

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        courseSelection(driver);
        signOut(driver);

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();
    }

    public void courseSelection(WebDriver driver) {

        List<WebElement> CoursesId = driver.findElements(By.className("multiline"));

        for (int i = 0; i < CoursesId.size(); i++) {
            System.out.println(i + ". " + CoursesId.get(i).getText());
        }

        try {
            System.out.println("Enter course number: ");
            WebElement courseSelection = CoursesId.get(userInput.nextInt());
            courseSelection.click();
        }catch (Exception e){
            System.out.println("ERROR something went wrong try again...");
            userInput.nextLine();
        }

    }

    public void signOut(WebDriver driver) {

        List<WebElement> profile = driver.findElements(By.className("action-menu-trigger"));
        WebElement profileSelection = profile.get(0);
        profileSelection.click();

        List<WebElement> chunkMenu = driver.findElements(By.className("dropdown-item"));
        WebElement exit = chunkMenu.get(5);

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        exit.click();
    }

}