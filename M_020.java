package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

public class M_020 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	// boolean setupSuccess = true;
	private String ID_1 = null;
	private String PW_1 = null;
	private String M_URL = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-06-19
	 * Subject : CJ Mall 운영  
	 * Name : M_020
	 * Scenario :  마이존 버튼 선택
	 * Assertion :  마이존 Text 체크
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
		M_URL = sp.getProperty("M_URL");
		
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps = DesiredCapabilities.android();
			// Devices Name 은 아무거나 입력해도 상관없음
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "1a66dc8f");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
			System.out.println("Start driver.");
			driver = new AndroidDriver<WebElement>(appiumUrl, caps);

		} catch (Exception e) {
			System.out.println("Session Creation failed.");
			e.printStackTrace();
			assertTrue(true);
			// setupSuccess = false;
			return;
		}

	}

	@Test
	public void m_020() throws Exception {
		
		driver.get(M_URL);
		
		boolean isExist = false;
		//팝업닫기
		isExist = existElement(driver, By.xpath("//*[@id='notToday']"), "오늘 하루 보지 않기");
		if (isExist) {
			driver.findElement(By.xpath("//*[@id='popup_spot']/div/div/div/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/a[1]")).click();
		//좌측카테고리 버튼 클릭
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='gnb']/div[1]/a")).click();
		Thread.sleep(3000);
		System.out.println("로그인 텍스트 클릭");
		//로그인 클릭
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[2]/fieldset/div[2]")).click();
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		Thread.sleep(5000);
		if (isExist) {
			driver.findElement(By.xpath("//*[@id='popup_spot']/div/div/div/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		driver.findElement(By.xpath("//*[@id='go_myzone']")).click();
		System.out.println("마이존 이동");
		//마이존 이동
		
		Thread.sleep(7000);
		
		System.out.println(driver.findElement(By.xpath("//*[@id='header']/div/h1")).getText());
		//alert check
		// text check
        if ("마이존".equals(driver.findElement(By.xpath("//*[@id='header']/div/h1")).getText())) {
           System.out.println("TC_20 PASS");
           Thread.sleep(3000);
           assertTrue(true);
           return;
        } else {
           System.out.println("TC_20 FAIL");
           assertTrue(false);
			}
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		// wait.ignoring(NoSuchElementException.class);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}

}
