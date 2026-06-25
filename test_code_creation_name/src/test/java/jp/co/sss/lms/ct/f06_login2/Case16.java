package jp.co.sss.lms.ct.f06_login2;

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
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能②
 * ケース16
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース16 受講生 初回ログイン 変更パスワード未入力")
public class Case16 {
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
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA03");
		webDriver.findElement(By.id("password")).sendKeys("StudentAA03");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		//ちょっと待つ
		//	WebDriverUtils utils = new WebDriverUtils();
		//utils.visibilityTimeout(By.cssSelector(".nav.navbar-nav.navbar-right"), 1);

		//画面確認
		assertEquals("セキュリティ規約 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() {
		// TODO ここに追加
		//「同意します」を押下
		webDriver.findElement(By.cssSelector(".checkbox label")).click();
		//次へボタン押下
		webDriver.findElement(By.cssSelector("div .btn.btn-primary")).click();

		//画面確認
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() {
		// TODO ここに追加
		//現在のパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(1) input")).sendKeys("");
		//新しいパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(2) input")).sendKeys("");

		//確認パスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(3) input")).sendKeys("");

		//変更ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();

		//パスワード変更の確認の変更ボタンを押下

		utils.visibilityTimeout(By.id("upd-btn"), 3);

		webDriver.findElement(By.id("upd-btn")).click();

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() {
		// TODO ここに追加
		//現在のパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(1) input")).sendKeys("StudentAA03");
		//新しいパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(2) input"))
				.sendKeys("StudentAA033333333333333333333333333333");

		//確認パスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(3) input"))
				.sendKeys("StudentAA033333333333333333333333333333");

		utils.scrollBy("50");

		//変更ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();

		//パスワード変更の確認の変更ボタンを押下
		//	utils.scrollBy("50");

		utils.visibilityTimeout(By.id("upd-btn"), 3);

		webDriver.findElement(By.id("upd-btn")).click();

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() {
		// TODO ここに追加 
		//現在のパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(1) input")).sendKeys("StudentAA03");
		//新しいパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(2) input")).sendKeys("Student//03");

		//確認パスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(3) input")).sendKeys("Student//03");

		//変更ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();

		//パスワード変更の確認の変更ボタンを押下

		utils.visibilityTimeout(By.id("upd-btn"), 3);

		webDriver.findElement(By.id("upd-btn")).click();

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() {
		// TODO ここに追加
		//現在のパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(1) input")).sendKeys(" StudentAA03");
		//新しいパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(2) input")).sendKeys("StlmsAA03");

		//確認パスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(3) input")).sendKeys("StudentAA03");

		//変更ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();

		//パスワード変更の確認の変更ボタンを押下

		WebDriverUtils utils = new WebDriverUtils();
		utils.visibilityTimeout(By.id("upd-btn"), 3);

		webDriver.findElement(By.id("upd-btn")).click();

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

}
