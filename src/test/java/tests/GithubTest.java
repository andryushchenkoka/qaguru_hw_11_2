package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GithubTest extends BaseTest {

    @Test
    public void hoverTest() {
        SelenideElement solutionsButton = $$("[aria-label = 'Global']").findBy(text("Solutions"));

        step("Открыть главную страницу Github", () -> {
            open("");
        });

        step("В верхнем меню навести курсор на Solutions", () -> {
            solutionsButton.hover();
        });

        step("Кликнуть по разделу Enterprise из открывшегося списка", () -> {
            solutionsButton.$(byText("Enterprise")).click();
        });

        step("На открытой странице должен быть заголовок с текстом Build like the best", () -> {
            $(byTagAndText("h1", "Build like the best")).shouldBe(visible);
        });
    }
}
