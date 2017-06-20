package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

public class M_033 {
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
	 * Name : M_033
	 * Scenario :  임의의 상품 > 장바구니 버튼 선택
	 * Assertion :  장바구니 Text 체크
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
	public void M_033() throws Exception {
		
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
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		Thread.sleep(5000);
		if (isExist) {
			driver.findElement(By.xpath("//*[@id='popup_spot']/div/div/div/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		driver.findElement(By.xpath(".//*[@id='header']/div[3]/a[1]")).click();
		//검색창 클릭
		driver.findElement(By.xpath("//*[@id='input_srh']")).clear();
		driver.findElement(By.xpath("//*[@id='input_srh']")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button.btn_search")).click();
		System.out.println("상품검색");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='prd_lst']/li[1]/a/div[1]/img")).click();
		// 상품진입
		Thread.sleep(5000);
		System.out.println("임의상품 진입");
		Thread.sleep(3000);
		
		//장바구니 담기
		driver.findElement(By.xpath("//*[@id='orderArea']/div[1]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='orderArea']/div[2]/div[2]/div[2]/a[1]")).click();
		
		System.out.println("장바구니 담기");
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("//*[@id='header']/div/a[3]/div/strong")).getText());
		// text check
		if ("1".equals(
				driver.findElement(By.xpath("//*[@id='header']/div/a[3]/div/strong")).getText())) {
			
			driver.findElement(By.xpath("//*[@id='header']/div/a[3]/div")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='_fullBasket']/div[2]/div/div[1]/div[1]/button")).click();
			Thread.sleep(3000);
			System.out.println("TC_33 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_33 FAIL");
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
