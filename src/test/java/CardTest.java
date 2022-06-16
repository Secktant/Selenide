import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {


    @Test
    public void shouldSetForm() {
        open("http://localhost:9999/");
        $("[type=text]").setValue("Никита-Александрович");
        $("[type=tel]").setValue("+79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }


    @Test
    public void validationOfTheNameField() {
        open("http://localhost:9999/");
        $("[type=text]").setValue("Nikita");
        $("[type=tel]").setValue("+79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    public void validationOfThePhoneField() {
        open("http://localhost:9999/");
        $("[type=text]").setValue("Никита");
        $("[type=tel]").setValue("79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void EmptyName() {
        open("http://localhost:9999/");
        $("[type=tel]").setValue("+79012345678");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void EmptyPhone() {
        open("http://localhost:9999/");
        $("[type=text]").setValue("Никита");
        $(".checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }


}

