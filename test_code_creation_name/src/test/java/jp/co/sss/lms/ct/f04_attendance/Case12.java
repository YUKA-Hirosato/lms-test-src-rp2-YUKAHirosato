package jp.co.sss.lms.ct.f04_attendance;

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
 * 結合テスト 勤怠管理機能
 * ケース12
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース12 受講生 勤怠直接編集 入力チェック")
public class Case12 {
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
		WebDriverUtils.goTo("http://localhost:8080/lms/");

		assertEquals("ログイン | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StlmsAA01");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		utils.visibilityTimeout(By.cssSelector(".navbar-brand"), 10);

		assertEquals("コース詳細 | LMS", WebDriverUtils.webDriver.getTitle());

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() {
		// TODO ここに追加
		webDriver.findElement(By.linkText("勤怠")).click();
		webDriver.switchTo().alert().accept();

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「勤怠情報を直接編集する」リンクから勤怠情報直接変更画面に遷移")
	void test04() {
		// TODO ここに追加
		//リンクを押下する
		webDriver.findElement(By.linkText("勤怠情報を直接編集する")).click();
		//画面確認
		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 不適切な内容で修正してエラー表示：出退勤の（時）と（分）のいずれかが空白")
	void test05() {
		// TODO ここに追加

		//出勤
		WebElement startHour = webDriver.findElement(By.id("startHour0"));
		Select startHourDropdown = new Select(startHour);
		startHourDropdown.selectByValue("");

		WebElement startMinute = webDriver.findElement(By.id("startMinute0"));
		Select startMinuteDropdown = new Select(startMinute);
		startMinuteDropdown.selectByValue("0");

		//退勤
		WebElement endHour = webDriver.findElement(By.id("endHour0"));
		Select endHourDropdown = new Select(endHour);
		endHourDropdown.selectByValue("");

		WebElement endMinute = webDriver.findElement(By.id("endMinute0"));
		Select endMinuteDropdown = new Select(endMinute);
		endMinuteDropdown.selectByValue("0");

		//「更新」ボタンを画面中央までスクロールし押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".btn.btn-info.update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		//「OK」を押下
		webDriver.switchTo().alert().accept();
		//ちょっと待つ
		visibilityTimeout(By.cssSelector(".help-inline.error"), 3);

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正してエラー表示：出勤が空白で退勤に入力あり")
	void test06() {
		// TODO ここに追加
		//出勤
		WebElement startHour = webDriver.findElement(By.id("startHour0"));
		Select startHourDropdown = new Select(startHour);
		startHourDropdown.selectByValue("");

		WebElement startMinute = webDriver.findElement(By.id("startMinute0"));
		Select startMinuteDropdown = new Select(startMinute);
		startMinuteDropdown.selectByValue("");

		//退勤
		WebElement endHour = webDriver.findElement(By.id("endHour0"));
		Select endHourDropdown = new Select(endHour);
		endHourDropdown.selectByValue("18");

		WebElement endMinute = webDriver.findElement(By.id("endMinute0"));
		Select endMinuteDropdown = new Select(endMinute);
		endMinuteDropdown.selectByValue("0");

		//「更新」ボタンを画面中央までスクロールし押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".btn.btn-info.update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		//「OK」を押下
		webDriver.switchTo().alert().accept();
		//ちょっと待つ
		visibilityTimeout(By.cssSelector(".help-inline.error"), 3);

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正してエラー表示：出勤が退勤よりも遅い時間")
	void test07() {
		// TODO ここに追加
		//出勤
		WebElement startHour = webDriver.findElement(By.id("startHour0"));
		Select startHourDropdown = new Select(startHour);
		startHourDropdown.selectByValue("21");

		WebElement startMinute = webDriver.findElement(By.id("startMinute0"));
		Select startMinuteDropdown = new Select(startMinute);
		startMinuteDropdown.selectByValue("0");

		//退勤
		WebElement endHour = webDriver.findElement(By.id("endHour0"));
		Select endHourDropdown = new Select(endHour);
		endHourDropdown.selectByValue("18");

		WebElement endMinute = webDriver.findElement(By.id("endMinute0"));
		Select endMinuteDropdown = new Select(endMinute);
		endMinuteDropdown.selectByValue("0");

		//「更新」ボタンを画面中央までスクロールし押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".btn.btn-info.update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		//「OK」を押下
		webDriver.switchTo().alert().accept();
		//ちょっと待つ
		visibilityTimeout(By.cssSelector(".help-inline.error"), 3);

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正してエラー表示：出退勤時間を超える中抜け時間")
	void test08() {
		// TODO ここに追加
		//出勤
		WebElement startHour = webDriver.findElement(By.id("startHour0"));
		Select startHourDropdown = new Select(startHour);
		startHourDropdown.selectByValue("14");

		WebElement startMinute = webDriver.findElement(By.id("startMinute0"));
		Select startMinuteDropdown = new Select(startMinute);
		startMinuteDropdown.selectByValue("0");

		//退勤
		WebElement endHour = webDriver.findElement(By.id("endHour0"));
		Select endHourDropdown = new Select(endHour);
		endHourDropdown.selectByValue("18");

		WebElement endMinute = webDriver.findElement(By.id("endMinute0"));
		Select endMinuteDropdown = new Select(endMinute);
		endMinuteDropdown.selectByValue("0");

		//中抜け時間
		WebElement blankTime = webDriver.findElement(
				By.cssSelector("select[name='attendanceList\\[0\\]\\.blankTime']"));
		Select blankTimeDropdown = new Select(blankTime);
		blankTimeDropdown.selectByValue("300");

		//「更新」ボタンを画面中央までスクロールし押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".btn.btn-info.update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		//「OK」を押下
		webDriver.switchTo().alert().accept();
		//ちょっと待つ
		visibilityTimeout(By.cssSelector(".help-inline.error"), 3);

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正してエラー表示：備考が100文字超")
	void test09() {
		// TODO ここに追加
		//備考欄に100文字以上を入力
		webDriver.findElement(By.cssSelector("input[type='text']")).sendKeys("あ".repeat(101));
		//「更新」ボタンを画面中央までスクロールし押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".btn.btn-info.update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		//「OK」を押下
		webDriver.switchTo().alert().accept();
		//ちょっと待つ
		visibilityTimeout(By.cssSelector(".help-inline.error"), 3);

		//エラー表示確認
		WebElement errorMsg = webDriver.findElement(By.cssSelector(".help-inline.error"));
		assertTrue(errorMsg.isDisplayed());

		//スクショ
		getEvidence(new Object() {
		});

	}

}
