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
	public static String reportDate;
	WebDriverUtils utils = new WebDriverUtils();

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
		goTo("http://localhost:8080/lms/");

		assertEquals("ログイン | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		//ログイン情報を入力し、送信
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StlmsAA01");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		utils.visibilityTimeout(By.cssSelector(".navbar-brand"), 10);

		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		//一覧の行を取得
		List<WebElement> rows = webDriver.findElements(By.tagName("tr"));

		for (WebElement row : rows) {
			//上から見て「提出済み」がある行を探す
			if (row.getText().contains("提出済み")) {
				//日付を保存
				reportDate = row.findElement(By.tagName("td")).getText();

				WebElement detailButton = row.findElement(By.cssSelector(".btn.btn-default"));
				//ボタンが画面中央に来るようにスクロール
				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].scrollIntoView({block:'center'});",
						detailButton);
				//詳細ボタン押下
				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].click();",
						detailButton);

				visibilityTimeout(By.tagName("h2"), 10);
				//セクション詳細画面に週報があるか確認
				List<WebElement> weeklyReports = webDriver.findElements(By.cssSelector("input[value*='提出済み週報']"));

				if (!weeklyReports.isEmpty()) {
					//日付を保存
					reportDate = webDriver.findElement(By.cssSelector("#sectionDetail h2 small")).getText().trim();

					assertEquals("セクション詳細 | LMS", webDriver.getTitle());

					getEvidence(new Object() {
					});

					break;
				}
				//週報が無ければコース詳細画面に戻る
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
		//WebElement weeklyReportButton =	
		utils.scrollBy("100");
		webDriver.findElement(By.cssSelector("input[value*='提出済み週報']")).click();

		visibilityTimeout(By.tagName("h2"), 3);
		//画面確認
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
		//報告内容を修正
		final WebElement reportText = webDriver.findElement(By.name("contentArray[2]"));
		reportText.clear();
		reportText.sendKeys("ケース08 受講生 レポート修正(週報) 正常系");

		//「提出する」ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();

		//画面確認
		visibilityTimeout(By.tagName("h2"), 3);

		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		webDriver.findElement(By.partialLinkText("ようこそ")).click();
		//画面確認
		visibilityTimeout(By.tagName("h2"), 3);

		assertEquals("ユーザー詳細", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// 1. 日付から曜日部分「(日)」などを除去して「2022年10月2日」の形にする
		String targetDate = reportDate.replaceAll("\\(.+\\)", "");

		// 2. 「指定の日付」と「週報【デモ】」の両方を文字に含む行（tr）の中にある、
		//    value属性に「詳細」が含まれるinput要素（ボタン）をXPathでピンポイントに特定
		String xpathSelector = String.format(
				"//h3[text()='レポート']/following-sibling::table//tr[td[contains(text(), '%s')] and td[contains(text(), '週報【デモ】')]]//input[@type='submit' and contains(@value, '詳細')]",
				targetDate);

		// 詳細ボタンを取得
		WebElement detailButton = webDriver.findElement(By.xpath(xpathSelector));

		// 3. 画面の真ん中までスクロール
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				detailButton);

		// 4. JavaScriptで確実にクリックを実行
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				detailButton);

		// --- ここから下は元の検証処理を維持 ---
		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		// レポート内容を確認
		WebElement reportDetailTable = webDriver
				.findElement(By.xpath("//h3[text()='報告レポート']/following-sibling::table"));

		String actualText = reportDetailTable.getText();

		assertTrue(actualText.contains("ケース08 受講生 レポート修正(週報) 正常系"));

		getEvidence(new Object() {
		});
	}
}