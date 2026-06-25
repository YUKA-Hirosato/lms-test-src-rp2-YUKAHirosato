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

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能②
 * ケース17
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース17 受講生 初回ログイン 正常系")
public class Case17 {

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

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加

		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StudentAA01");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		//ちょっと待つ
		//	WebDriverUtils utils = new WebDriverUtils();
		//utils.visibilityTimeout(By.cssSelector(".nav.navbar-nav.navbar-right"), 1);

		//画面確認
		assertEquals("セキュリティ規約 | LMS", WebDriverUtils.webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() {
		// TODO ここに追加
		//「OK」を押下
		//webDriver.switchTo().alert().accept();

		//「同意します」を押下
		webDriver.findElement(By.cssSelector(".checkbox label")).click();
		//次へボタン押下
		webDriver.findElement(By.cssSelector("div .btn.btn-primary")).click();

		//画面確認
		assertEquals("パスワード変更 | LMS", WebDriverUtils.webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 変更パスワードを入力し「変更」ボタン押下")
	void test04() {
		// TODO ここに追加

		//現在のパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(1) input")).sendKeys("StudentAA01");
		//新しいパスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(2) input")).sendKeys("StlmsAA01");

		//確認パスワードを入力
		webDriver.findElement(By.cssSelector("form div:nth-of-type(3) input")).sendKeys("StlmsAA01");

		//変更ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();

		//パスワード変更の確認の変更ボタンを押下

		WebDriverUtils utils = new WebDriverUtils();
		utils.visibilityTimeout(By.id("upd-btn"), 3);

		webDriver.findElement(By.id("upd-btn")).click();

		//画面確認
		assertEquals("コース詳細 | LMS", WebDriverUtils.webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

}
