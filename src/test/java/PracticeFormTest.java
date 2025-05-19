import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import static com.codeborne.selenide.Selenide.sleep;

public class PracticeFormTest {

    PracticeFormPage form = new PracticeFormPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        form.openPage()
                .setFirstName("Rishad")
                .setLastName("Geyushov")
                .setEmail("test@email.com")
                .selectGender()
                .setPhone("7941643176")
                .setBirthDate("1999", "October", "03")
                .setSubjects("English", "Computer Science")
                .selectHobbies()
                .uploadPicture("images.jpeg")
                .setAddress("г. Барнаул, ул. Гоголя, д. 38")
                .selectStateAndCity("Haryana", "Karnal")
                .submit()
                .checkResult(
                        "Rishad Geyushov",
                        "test@email.com",
                        "7941643176",
                        "Male",
                        "English, Computer Science",
                        "Reading, Music",
                        "images.jpeg",
                        "г. Барнаул, ул. Гоголя, д. 38",
                        "Haryana Karnal"
                )
                .closeModal();

    }
    @AfterAll
    static void sleepTime() {
        sleep(5000);
    }

}
