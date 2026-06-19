package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		WebDriverUtils.goTo("http://localhost:8080/lms/");

		assertEquals("ログイン | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		WebDriverUtils utils = new WebDriverUtils();

		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StlmsAA01");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		utils.visibilityTimeout(By.cssSelector(".navbar-brand"), 10);

		assertEquals("コース詳細 | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		//webDriver.findElement(By.cssSelector(".dropdown-menu")).click();
		//Select dropdown = new Select(webDriver.findElement(By.cssSelector("ul.dropdown-menu")).clock());
		//dropdown.selectByVisibleText("ヘルプ");
		//webDriver.findElement(By)
		webDriver.findElement(By.cssSelector(".dropdown-toggle")).click();
		webDriver.findElement(By.linkText("ヘルプ")).click();

		assertEquals("ヘルプ | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		WebDriverUtils utils = new WebDriverUtils();

		webDriver.findElement(By.linkText("よくある質問")).click();

		utils.visibilityTimeout(By.linkText("よくある質問"), 10);

		Set<String> windowHandles = webDriver.getWindowHandles();

		for (String handle : windowHandles) {
			webDriver.switchTo().window(handle);
		}

		//WebDriverWait wait = new WebDriverWait(WebDriverUtils.webDriver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.titleIs("よくある質問 | LMS"));

		assertEquals("よくある質問 | LMS", WebDriverUtils.webDriver.getTitle());

		// 開いたページのキャプチャを取得する
		WebDriverUtils.getEvidence(new Object() {
		});

	}

}
