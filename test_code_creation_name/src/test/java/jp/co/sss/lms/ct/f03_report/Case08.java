package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {
	private static String reportDate;

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
		//ログイン情報を入力し、送信
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StlmsAA01");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		utils.visibilityTimeout(By.cssSelector(".navbar-brand"), 10);

		assertEquals("コース詳細 | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		List<WebElement> rows = webDriver.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			if (row.getText().contains("提出済み")) {
				reportDate = row.findElement(By.tagName("td")).getText();

				WebElement detailButton = row.findElement(By.cssSelector(".btn.btn-default"));

				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].scrollIntoView({block:'center'});",
						detailButton);

				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].click();",
						detailButton);

				visibilityTimeout(By.tagName("h2"), 10);

				List<WebElement> weeklyReports = webDriver.findElements(By.cssSelector("input[value*='提出済み週報']"));

				if (!weeklyReports.isEmpty()) {

					reportDate = webDriver.findElement(By.cssSelector("#sectionDetail h2 small")).getText().trim();

					assertEquals("セクション詳細 | LMS", webDriver.getTitle());

					getEvidence(new Object() {
					});

					break;
				}
				webDriver.navigate().back();
				visibilityTimeout(By.tagName("h2"), 10);

				rows = webDriver.findElements(By.tagName("tr"));

			}
		}
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		//確認するボタンを押下する
		webDriver.findElement(By.cssSelector("form input[type='submit']")).click();

		//画面確認
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
	}

}
