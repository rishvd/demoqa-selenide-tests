package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderMale = $("[for=gender-radio-1]"),
            phoneInput = $("#userNumber"),
            birthInput = $("#dateOfBirthInput"),
            yearSelect = $(".react-datepicker__year-select"),
            monthSelect = $(".react-datepicker__month-select"),
            subjectInput = $(".subjects-auto-complete__value-container input"),
            hobbyReading = $("[for=hobbies-checkbox-2]"),
            hobbyMusic = $("[for=hobbies-checkbox-3]"),
            uploadInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            resultsModal = $("#example-modal-sizes-title-lg"),
            resultsTable = $(".table-responsive"),
            closeModal = $("#closeLargeModal");

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String name) {
        firstNameInput.setValue(name);
        return this;
    }

    public PracticeFormPage setLastName(String name) {
        lastNameInput.setValue(name);
        return this;
    }

    public PracticeFormPage setEmail(String name) {
        emailInput.setValue(name);
        return this;
    }

    public PracticeFormPage selectGender() {
        genderMale.click();
        return this;
    }

    public PracticeFormPage setPhone(String name) {
        phoneInput.setValue(name);
        return this;
    }

    public PracticeFormPage setBirthDate(String year, String month, String day) {
        birthInput.click();
        birthInput.clear();
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);
        $$(".react-datepicker__day--0" + day).find(Condition.visible).click();
        return this;
    }


    public PracticeFormPage setSubjects(String... subjects) {
        for (String subject : subjects) {
            subjectInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public PracticeFormPage selectHobbies() {
        hobbyReading.click();
        hobbyMusic.click();
        return this;
    }

    public PracticeFormPage uploadPicture(String filename) {
        uploadInput.uploadFromClasspath(filename);
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public PracticeFormPage selectStateAndCity(String state, String city) {
        stateInput.click();
        stateInput.$(byText(state)).click();
        cityInput.click();
        cityInput.$(byText(city)).click();
        return this;
    }

    public PracticeFormPage submit() {
        submitButton.click();
        return this;
    }

    public PracticeFormPage checkResult(String... expectedTexts) {
        resultsModal.shouldBe(Condition.visible).shouldHave(Condition.text("Thanks for submitting the form"));
        for (String text : expectedTexts) {
            resultsTable.shouldHave(Condition.text(text));
        }
        return this;
    }

        //public PracticeFormPage checkResult() {}
    public PracticeFormPage closeModal() {
        closeModal.click();
        return this;
    }

}