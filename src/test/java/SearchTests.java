import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @BeforeAll
    static void configureTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void successfulSearchTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("[id=firstName]").setValue("Rishad");
        $("[id=lastName]").setValue("Geyushov");
        $("[id=userEmail]").setValue("test@email.com");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("7941643176");
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__year-select").selectOption("1999"); // Выбрать год
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__day--003").click();
        $(".subjects-auto-complete__value-container input").setValue("English").pressEnter();
        $(".subjects-auto-complete__value-container input").setValue("Computer Science").pressEnter();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFromClasspath("images.jpeg");
        $("#currentAddress").setValue("г. Барнаул, ул. Гоголя, д. 38");
        $("#state").click();  // Открываем выпадающее меню
        $("#state").$(byText("Haryana")).click();
        $("#city").click();  // Открываем выпадающее меню
        $("#city").$(byText("Karnal")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Rishad Geyushov"));
        $(".table-responsive").shouldHave(text("test@email.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("7941643176"));
        $(".table-responsive").shouldHave(text("03 October,1999"));
        $(".table-responsive").shouldHave(text("English, Computer Science"));
        $(".table-responsive").shouldHave(text("Reading, Music"));
        $(".table-responsive").shouldHave(text("images.jpeg"));
        $(".table-responsive").shouldHave(text("г. Барнаул, ул. Гоголя, д. 38"));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));
        $("#closeLargeModal").click();


    }

    @AfterAll
    static void sleepForView() {
        sleep(5000); // чтобы увидеть результат
    }
}
