package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

public class M_051 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	// boolean setupSuccess = true;
	private String ID_1 = null;
	private String ID_2 = null;
	private String ID_3 = null;
	private String ID_4 = null;
	private String ID_5 = null;
	private String PW_1 = null;
	private String PW_2 = null;
	private String PW_3 = null;
	private String PW_4 = null;
	private String PW_5 = null;
	private String P_URL = null;
	private String M_URL = null;
	private String NAME = null;
	private String BIRTH = null;
	private long waitTime = 50;
	private String PRODUCT = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-06-19
	 * Subject : CJ Mall 운영  
	 * Name : M_051
	 * Scenario :  고객센터 > 이벤트 참여 현황 버튼 선택
	 * Assertion :  로그인 버튼 Text 체크
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		ID_2 = sp.getProperty("ID_2");
		ID_3 = sp.getProperty("ID_3");
		ID_4 = sp.getProperty("ID_4");
		ID_5 = sp.getProperty("ID_5");
		PW_1 = sp.getProperty("PW_1");
		PW_2 = sp.getProperty("PW_2");
		PW_3 = sp.getProperty("PW_3");
		PW_4 = sp.getProperty("PW_4");
		PW_5 = sp.getProperty("PW_5");
		P_URL = sp.getProperty("P_URL");
		M_URL = sp.getProperty("M_URL");
		NAME = sp.getProperty("NAME");
		BIRTH = sp.getProperty("BIRTH");
		PRODUCT = sp.getProperty("PRODUCT");
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
	public void M_051() throws Exception {

		driver.get(M_URL);

		boolean isExist = false;
		// 팝업닫기
		isExist = existElement(driver, By.xpath("//*[@id='notToday']"), "오늘 하루 보지 않기");
		if (isExist) {
			driver.findElement(By.xpath("//*[@id='popup_spot']/div/div/div/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		// 스크롤내리기
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id='footer']/div[1]/div[3]/ul/li[1]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		Thread.sleep(3000);
		System.out.println("스크롤내리기");
		// 고객센터 클릭
		driver.findElement(By.xpath("//*[@id='footer']/div[1]/div[2]/ul/li[1]")).click();
		Thread.sleep(3000);
		// 이벤트페이지 클릭
		driver.findElement(By.xpath("//*[@id='content']/div/div[2]/ul/li[4]/a")).click();
		Thread.sleep(3000);
		
		if ("로그인".equals(driver.findElement(By.xpath("//*[@id='header']/div/h1")).getText())) {
			Thread.sleep(3000);
			System.out.println("TC_51 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_51 FAIL");
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
