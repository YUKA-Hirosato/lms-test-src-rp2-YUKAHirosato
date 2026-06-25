package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

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
import org.openqa.selenium.support.ui.Select;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {
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
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
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
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加

		//「週報【デモ】」という文字を含む列（td）がある行（tr）の中から、「修正する」ボタンを探す
		String xpathSelector = "//h3[text()='レポート']/following-sibling::table"
				+ "//tr[td[contains(text(), '週報【デモ】')]]"
				+ "//input[@type='submit' and contains(@value, '修正する')]";

		// 修正するボタンを取得してクリック
		WebElement editButton = webDriver.findElement(By.xpath(xpathSelector));

		// 3. 画面の真ん中までスクロール
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				editButton);

		// 4. JavaScriptで確実にクリックを実行
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				editButton);

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("レポート登録 | LMS", webDriver.getTitle());

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		// TODO ここに追加

		//学習項目をクリア
		//学習項目
		WebElement intFiledNameText = webDriver.findElement(By.id("intFieldName_0"));

		// 3. 画面の真ん中までスクロール
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				intFiledNameText);

		intFiledNameText.clear();

		//「提出する」ボタンを押下
		//提出ボタン
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submit);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submit);

		//エラー表示確認
		WebElement error = webDriver.findElement(By.cssSelector(".form-control.errorInput"));
		assertTrue(error.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		// TODO ここに追加

		//学習項目を再入力
		//学習項目
		WebElement intFiledNameText = webDriver.findElement(By.id("intFieldName_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				intFiledNameText);

		intFiledNameText.sendKeys("ITリテラシー①");

		//理解度をクリア
		//理解度
		WebElement intFiledValueText = webDriver.findElement(By.id("intFieldValue_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				intFiledValueText);
		Select intFiledValueDropdown = new Select(intFiledValueText);

		intFiledValueDropdown.selectByValue("");

		//「提出する」ボタンを押下
		//提出ボタン
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submit);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submit);

		//エラー表示確認
		WebElement error = webDriver.findElement(By.cssSelector(".form-control.errorInput"));
		assertTrue(error.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		// TODO ここに追加
		//理解度再入力
		//理解度
		WebElement intFiledValueText = webDriver.findElement(By.id("intFieldValue_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				intFiledValueText);
		Select intFiledValueDropdown = new Select(intFiledValueText);
		intFiledValueDropdown.selectByValue("2");

		//目標の達成度が数値以外
		//目標の達成度
		WebElement contactText = webDriver.findElement(By.id("content_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				contactText);
		contactText.clear();
		contactText.sendKeys("達成");

		//「提出する」ボタンを押下
		//提出ボタン
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submit);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submit);

		//エラー表示確認
		WebElement error = webDriver.findElement(By.cssSelector(".form-control.errorInput"));
		assertTrue(error.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		// TODO ここに追加
		//目標の達成度をクリア

		//目標の達成度
		WebElement contactText = webDriver.findElement(By.id("content_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				contactText);
		contactText.clear();
		//目標の達成度を入力
		contactText.sendKeys("100");

		//「提出する」ボタンを押下
		//提出ボタン
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submit);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submit);

		//エラー表示確認
		WebElement error = webDriver.findElement(By.cssSelector(".form-control.errorInput"));
		assertTrue(error.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		// TODO ここに追加
		//目標の達成度をクリア
		//目標の達成度
		WebElement contactText = webDriver.findElement(By.id("content_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				contactText);
		contactText.clear();

		//所感をクリア
		//所感
		WebElement content1Text = webDriver.findElement(By.id("content_1"));
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				content1Text);
		content1Text.clear();

		//「提出する」ボタンを押下
		//提出ボタン
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submit);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submit);

		//エラー表示確認
		WebElement error = webDriver.findElement(By.cssSelector(".form-control.errorInput"));
		assertTrue(error.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		// TODO ここに追加
		//目的の達成度入力
		WebElement contactText = webDriver.findElement(By.id("content_0"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				contactText);
		contactText.sendKeys("3");

		//所感再入力（2000字越え）
		WebElement content1Text = webDriver.findElement(By.id("content_1"));
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				content1Text);
		content1Text.sendKeys("あ".repeat(2001));

		//一週間の振り返り入力（2000字越え）
		//一週間の振り返り
		WebElement content2Text = webDriver.findElement(By.id("content_2"));
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				content2Text);
		content2Text.clear();

		content2Text.sendKeys("あ".repeat(2001));

		//「提出する」ボタンを押下
		//提出ボタン
		WebElement submit = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submit);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submit);

		//エラー表示確認
		WebElement error = webDriver.findElement(By.cssSelector(".form-control.errorInput"));
		assertTrue(error.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

}
