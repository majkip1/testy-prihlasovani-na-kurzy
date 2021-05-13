package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void prihlaseniRodice() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        WebElement inputEmail = prohlizec.findElement(By.id("email"));
        inputEmail.sendKeys("super@mario.com");
        WebElement inputPassword = prohlizec.findElement(By.id("password"));
        inputPassword.sendKeys("superLuigi1");
        WebElement buttonLogin = prohlizec.findElement(By.xpath("//button[@type = 'submit']"));
        buttonLogin.click();

        String jmenoVHtml = prohlizec.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/div/a/strong")).getText();
        Assertions.assertEquals("Super Mario",jmenoVHtml);

    }

    @Test
    public void vyberKurzAPrihlasRodice() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/1-digitalni-akademie-testovani");
        WebElement buttonVytvoritPrihlasku = prohlizec.findElement(By.linkText("Vytvořit přihlášku"));
        buttonVytvoritPrihlasku.click();
        WebElement poleEmail = prohlizec.findElement(By.id("email"));
        poleEmail.sendKeys("super@mario.com");
        WebElement polePassword = prohlizec.findElement(By.id("password"));
        polePassword.sendKeys("superLuigi1");
        WebElement buttonLogin = prohlizec.findElement(By.xpath("//button[@type = 'submit']"));
        buttonLogin.click();
        WebElement vyberTerminPrihlaseni = prohlizec.findElement(By.xpath("//div[@class='filter-option-inner-inner']"));
        vyberTerminPrihlaseni.click();
        WebElement vyberTerminKurzu= prohlizec.findElement(By.xpath("//*[@id=\"bs-select-1-0\"]"));
        vyberTerminKurzu.click();
        WebElement poleJmenoZaka = prohlizec.findElement(By.id("forename"));
        poleJmenoZaka.sendKeys("Game");
        WebElement polePrijmeniZaka = prohlizec.findElement(By.id("surname"));
        polePrijmeniZaka.sendKeys("Super");
        WebElement narozeniZaka = prohlizec.findElement(By.id("birthday"));
        narozeniZaka.sendKeys("1.1.2010");
        WebElement platbaCash = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[8]/td[2]/span[4]/label"));
        platbaCash.click();
        WebElement chackboxPodminky = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[11]/td[2]/span/label"));
        chackboxPodminky.click();
        WebElement vytvoritPrihlaskuButton = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[11]/td[2]/input"));
        vytvoritPrihlaskuButton.click();
        WebElement zalozkaPrihlaska = prohlizec.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[1]/a[2]"));
        zalozkaPrihlaska.click();

    }

    @Test
    public void prihlaseniRodiceAPakKurz() {
        prihlaseniRodice();
        WebElement vytvoritNovouPrihlasku = prohlizec.findElement(By.xpath("//a[text() = 'Vytvořit novou přihlášku']"));
        vytvoritNovouPrihlasku.click();
        List<WebElement> seznamTlacitekViceInformaciVsechKurzu =
                prohlizec.findElements(By.xpath(
                        "//div[@class = 'card']//a[text() = 'Více informací']"
                ));
        WebElement tretiTlacitkoViceInformaci = seznamTlacitekViceInformaciVsechKurzu.get(1);
        tretiTlacitkoViceInformaci.click();
        WebElement vytvoritPrihlaskuWeboveKurzy = prohlizec.findElement(By.xpath("//a[@href = 'https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1']"));
        vytvoritPrihlaskuWeboveKurzy.click();
        WebElement buttonVyberDatum = prohlizec.findElement(By.xpath("//div[text() = 'Vyberte termín...']"));
        buttonVyberDatum.click();
        WebElement vyberDatum = prohlizec.findElement(By.xpath("//span[@class = 'text']"));
        vyberDatum.click();
        WebElement krestniJmenoZaka = prohlizec.findElement(By.id("forename"));
        krestniJmenoZaka.sendKeys("Luigi");
        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.sendKeys("Mario");
        WebElement datumNarozeni = prohlizec.findElement(By.id("birthday"));
        datumNarozeni.sendKeys("1.1.2010");
        WebElement zpusobUhrady =
                prohlizec.findElements(By.cssSelector(".custom-control.custom-radio .custom-control-label")).get(3);
        zpusobUhrady.click();
        WebElement poleCashbox = prohlizec.findElement(By.xpath("//label[@class = 'custom-control-label']"));
        poleCashbox.click();
        WebElement buttonVytvoritPrihlasku = prohlizec.findElement(By.xpath("//input[@type = 'submit']"));
        buttonVytvoritPrihlasku.click();

        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci");
        WebElement zalozkaTabulka = prohlizec.findElement(By.linkText("\n" +
                "                        Přihlášky                    "));
        zalozkaTabulka.click();


       // List<WebElement> seznamPrihlasek = prohlizec.findElements(By.xpath("//table/tbody/tr/td[17]"));
        //Assertions.assertEquals(1, skutecnyPocetPrihlasek);
    }

    @Test
    public void vlastniPrihlaska() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");

        List<WebElement> seznamTlacitekViceInformaciVsechKurzu =
            prohlizec.findElements(By.xpath(
                    "//div[@class = 'card']//a[text() = 'Více informací']"
            ));
        WebElement prvniTlacitkoViceInformaci = seznamTlacitekViceInformaciVsechKurzu.get(0);
        prvniTlacitkoViceInformaci.click();

        List<WebElement> seznamTlacitekTrimesicniKomplexniKurzy =
             prohlizec.findElements(By.xpath(
                     "//div[@class = 'card']//a[text() = 'Vytvořit přihlášku']"
             ));
        WebElement druheTlacitkoVytvoritPrihlasku = seznamTlacitekTrimesicniKomplexniKurzy.get(1);
        druheTlacitkoVytvoritPrihlasku.click();

        WebElement poleEmail = prohlizec.findElement(By.id("email"));
        poleEmail.sendKeys("super@mario.com");
        WebElement polePassword = prohlizec.findElement(By.id("password"));
        polePassword.sendKeys("superLuigi1");
        WebElement buttonLogin = prohlizec.findElement(By.xpath("//button[@type = 'submit']"));
        buttonLogin.click();

        WebElement vyberteTermin = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[2]/td[2]/div/button"));
        vyberteTermin.click();
        vyberteTermin.sendKeys(Keys.DOWN);
        vyberteTermin.sendKeys("\n");

        WebElement jmenoZaka = prohlizec.findElement(By.id("forename"));
        jmenoZaka.sendKeys("Alex");
        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.sendKeys("Mario");
        WebElement datumNarozeni = prohlizec.findElement(By.id("birthday"));
        datumNarozeni.sendKeys("1.1.2020");

        WebElement zpusobUhrady =
                prohlizec.findElements(By.cssSelector(".custom-control.custom-radio .custom-control-label")).get(3);
        zpusobUhrady.click();
        WebElement poleCashbox = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[11]/td[2]/span"));
        poleCashbox.click();
        WebElement buttonVytvoritPrihlasku = prohlizec.findElement(By.xpath("//input[@type = 'submit']"));
        buttonVytvoritPrihlasku.click();

    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
