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
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {
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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		//詳細ボタンを押下
		webDriver.findElement(By.cssSelector("td form input:nth-child(3)")).click();

		//ちょっと待つ
		utils.visibilityTimeout(By.cssSelector(".nav.navbar-nav.navbar-right"), 3);

		//セクション画面の確認
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		//レポート提出ボタンを押下
		webDriver.findElement(By.cssSelector("td form .btn.btn-default")).click();

		//ちょっと待つ
		utils.visibilityTimeout(By.cssSelector(".well.bs-component"), 3);

		//レポート登録画面の確認
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// TODO ここに追加
		//報告内容を入力
		webDriver.findElement(By.cssSelector("textarea")).sendKeys("ケース07実行");

		//「提出する」ボタンを押下
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		//確認ボタン名が変更されているか確認
		WebElement btnName = webDriver.findElement(By.cssSelector("form input[type='submit']"));
		assertEquals("提出済み日報【デモ】を確認する", btnName.getAttribute("value"));

		//スクショ
		getEvidence(new Object() {
		});

	}

}
