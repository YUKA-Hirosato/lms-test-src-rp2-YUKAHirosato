package jp.co.sss.lms.ct.f01_login1;

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
import org.springframework.boot.test.web.server.LocalServerPort;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	@LocalServerPort
	private int port;

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
		WebDriverUtils webDriver = new WebDriverUtils();

		// 指定のURLの画面を開く
		//再利用性
		//WebDriverUtils.goTo("http://localhost:" + port + "/lms");
		webDriver.goTo("http://localhost:8080/lms/");

		//Titleの取得とアサーション
		assertEquals("ログイン | LMS", WebDriverUtils.webDriver.getTitle());

		// 開いたページのキャプチャを取得する
		WebDriverUtils.getEvidence(new Object() {
		});

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// TODO ここに追加
		//DBに登録されていないユーザーを入力
		webDriver.findElement(By.id("loginId")).sendKeys("Student999");
		webDriver.findElement(By.id("password")).sendKeys("Student999");
		//送信ボタン押して

		WebElement classElement = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertEquals("*&nbsp;ログインに失敗しました。", classElement.getText());

		// 開いたページのキャプチャを取得する
		WebDriverUtils.getEvidence(new Object() {
		});

	}

}
