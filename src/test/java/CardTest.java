import com.codeborne.selenide.conditions.CssClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @BeforeEach
    void shouldOpenBroswer() {
        open("http://localhost:9999/");
    }


    @Test
    public void shouldSetForm() {
        $("[type=text]").setValue("Никита-Александрович");
        $("[type=tel]").setValue("+79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }


    @Test
    public void shouldValidationOfTheNameField() {
        $("[type=text]").setValue("Nikita");
        $("[type=tel]").setValue("+79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    public void shouldValidationOfThePhoneField() {
        $("[type=text]").setValue("Никита");
        $("[type=tel]").setValue("79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldEmptyName() {
        $("[type=tel]").setValue("+79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldEmptyPhone() {

        $("[type=text]").setValue("Никита");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    public void shouldDontClickOnTheCheckbox() {
        $("[type=text]").setValue("Никита");
        $("[type=tel]").setValue("+79012345678");
        $(".button__content").click();
        $("[data-test-id=agreement]").shouldHave(new CssClass("input_invalid"));
    }
}

