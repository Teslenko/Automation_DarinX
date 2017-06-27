import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.*;
import org.junit.*;
import org.junit.rules.TestRule;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;


public class Newjava {
    final String browserName = "chrome";
    final int timeOut = 5000;
    final String browserPropertyName = "webdriver.chrome.driver";
    final String browserPropertyPath = "./src/main/resources/chromedriver";

    @Test
    public void setup () throws InterruptedException{
        open("http://darinx.biz/");         Thread.sleep(1000);

            CompanyPage();

            Services();

            PortfolioBlog();
    }

    @Test
        public void CompanyPage() throws InterruptedException {
            $(by("type", "video/mp4")).
                    shouldNotHave(attribute("src", "/wp-content/uploads/2016/08/darin_x.mp4"));                 //Verification video Link
            $("li#menu-item-84").find(String.valueOf($$("ul.sub-menu").
                    get(1))).findAll("a.menu-item-link.js-smooth-scroll");                                      //Verification all link
            $("li#menu-item-84").hover();                                                                       //Hover on "Compani" button
            $(by("href", "http://darinx.biz/company/about-us/")).click();   Thread.sleep(2000);                 //Click on "About as" button
            $(by("href", "http://darinx.biz")).click();                     Thread.sleep(2000);                 //Go to Home Page
            $("li#menu-item-84").hover();                                                                       //Hover on "Compani" button
            $(byText("Our approach")).shouldBe(attribute("href", "http://darinx.biz/company/our-approach/"));   //Verification Link button
            $(byText("Our mission")).shouldBe(attribute("href", "http://darinx.biz/company/our-mission/"));     //Verification Link button
            $(byText("Our team")).shouldBe(attribute("href", "http://darinx.biz/company/our-team/"));           //Verification Link button
        }

    @Test
        public void Services() throws InterruptedException {
            $("li#menu-item-626").hover().$(by("href","http://darinx.biz/services/mobile-app-development/")).hover().//Hover on "Services" button
                    find(String.valueOf($$("ul.sub-menu").get(2))).findAll("a.menu-item-link.js-smooth-scroll");     //Verification "Mobile App Development" link
            $("li#menu-item-626").hover().$("li#menu-item-53").hover().find(String.valueOf($$("ul.sub-menu").get(3))).
                    findAll("a.menu-item-link.js-smooth-scroll");                                                    //Verification "Web Development" link
            $("li#menu-item-626").hover().$("li#menu-item-52").hover().find(String.valueOf($$("ul.sub-menu").get(4))).
                    findAll("a.menu-item-link.js-smooth-scroll");                                                    //Verification "UI/UX Design" link
            $("li#menu-item-626").hover().$("li#menu-item-51").hover().find(String.valueOf($$("ul.sub-menu").get(5))).
                    findAll("a.menu-item-link.js-smooth-scroll");                                                    //Verification "Quality Assurance" link
            $("li#menu-item-626").hover().$("li#menu-item-50").hover().find(String.valueOf($$("ul.sub-menu").get(6))).
                    findAll("a.menu-item-link.js-smooth-scroll");
    }

    @Test
        public void PortfolioBlog() throws InterruptedException {
            $(by("href", "http://darinx.biz/portfolio-darinx/")).click();                   //Click on Portfolio Button
            $(by("src","http://darinx.biz/wp-content/uploads/2016/08/logo.png")).
                    doubleClick(); Thread.sleep(3000);                                      //Click on DarinX Button
            navigator.back();                                       Thread.sleep(1500);     //Go to Home Page
            $(byText("Blog")).click();                              Thread.sleep(2000);     //Click on Blog Button
            $(".mk-pagination-inner").scrollTo();                   Thread.sleep(1500);     //Observe page and scroll down
            navigator.back();                                       Thread.sleep(1500);     //Go to Home Page
            actions().sendKeys(Keys.CONTROL,"t").build().perform(); Thread.sleep(1500);     //Open "ContactUs" in New Tab
            $(byText("Contact Us")).click();                        Thread.sleep(2000);     //Click to "Contact Us" button
            switchTo().window(1);                                   Thread.sleep(2500);     //Go over to second Tab
            switchTo().window(1).close();                           Thread.sleep(1000);     //Close second Tab
            switchTo().window(0);                                   Thread.sleep(1000);     //Go over to first Tab
            $(byText("Request a Callback")).click();                Thread.sleep(1500);     //Click on "Request a Callback" button
            $("div.fc_modal-dialog.fc_modal-dialog-2").shouldBe(visible);
    }

    @Rule
    public TestRule report = new com.codeborne.selenide.junit.TextReport(). //Test Logo in the form of a table
            onFailedTest(true);

    @Before
        public void before(){
            Configuration.browser = browserName;
            Configuration.timeout = timeOut;
            System.setProperty(browserPropertyName, browserPropertyPath);
    }
}