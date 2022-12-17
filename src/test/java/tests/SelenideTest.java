package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SelenideTest extends BaseTest {

    @Test
    @DisplayName("Hover test")
    public void hoverTest() {

        SelenideElement solutionsButton = $$("[aria-label = 'Global']").findBy(text("Solutions"));

        step("Открыть главную страницу Github", () -> {
            open("https://github.com/");
        });

        step("В верхнем меню навести курсор на Solutions", () -> {
            solutionsButton.hover();
        });

        step("Кликнуть по разделу Enterprise из открывшегося списка", () -> {
            solutionsButton.parent().$(byText("Enterprise")).click();
        });

        step("На открытой странице должен быть заголовок с текстом Build like the best", () -> {
            $(byTagAndText("h1", "Build like the best")).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Drag-n-drop test using Selenide")
    public void dragAndDropSimpleTest() {

        SelenideElement rectA = $("#column-a"),
                rectB = $("#column-b");

        step("Открыть страницу drag-n-drop", () -> {
            open("https://the-internet.herokuapp.com/drag_and_drop");
        });

        step("Перенести прямоугольник А на место В", () -> {
            rectA.dragAndDropTo(rectB);
        });

        step("Текст первого прямоугольника должен быть В", () -> {
            $("#columns div").shouldHave(text("B"));
        });
    }

    @Test
    @DisplayName("Drag-n-drop test using Actions")
    public void dragAndDropActionsTest() {

        SelenideElement rectA = $("#column-a"),
                rectB = $("#column-b");

        step("Открыть страницу drag-n-drop", () -> {
            open("https://the-internet.herokuapp.com/drag_and_drop");
        });

        step("Перенести прямоугольник А на место В", () -> {
            actions().dragAndDrop(rectA, rectB).perform();
        });

        step("Текст первого прямоугольника должен быть В", () -> {
            $("#columns div").shouldHave(text("B"));
        });
    }

    @Test
    @DisplayName("Drag-n-drop test using element offset")
    public void dragAndDropByOffsetTest() {

        SelenideElement rectA = $("#column-a"),
                rectB = $("#column-b");

        step("Открыть страницу drag-n-drop", () -> {
            open("https://the-internet.herokuapp.com/drag_and_drop");
        });

        step("Перенести прямоугольник А на место В", () -> {
            actions().dragAndDropBy(rectA, rectB.getRect().getX() - rectA.getRect().getX(), 0);
        });

        step("Текст первого прямоугольника должен быть В", () -> {
            $("#columns div").shouldHave(text("B"));
        });
    }
}
