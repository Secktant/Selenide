
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

//    private WebDriver driver;


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


//    @Test
//    public void validationOfThePhoneField() {
//        open("http://localhost:9999/");
//        $("[type=text]").setValue("Никита");
//        $("[type=tel]").setValue("79012345678");
//        $(".checkbox__box").click();
//        $(".button__content").click();
//        $(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
//
//    }

}
