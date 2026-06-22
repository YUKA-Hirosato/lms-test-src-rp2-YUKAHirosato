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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
		assertEquals("よくある質問 | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {

		// TODO ここに追加

		//カテゴリ検索をクリック
		webDriver.findElement(By.linkText("【研修関係】")).click();
		//表示された結果をテスト
		assertTrue(webDriver.findElement(By.cssSelector(".table.table-hover.sortabletable")).isDisplayed());

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// TODO ここに追加
		WebDriverUtils utils = new WebDriverUtils();

		//画面スクロール
		utils.scrollBy("100");
		//検索結果を押下

		WebElement hiddenElement = webDriver.findElement(By.cssSelector(".dn"));
		((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", hiddenElement);
		//内容が表示されるかのテスト

		assertTrue(webDriver.findElement(By.cssSelector(".text-warning.mr10")).isDisplayed());

		//キャプチャに保存
		WebDriverUtils.getEvidence(new Object() {
		});

	}

}
