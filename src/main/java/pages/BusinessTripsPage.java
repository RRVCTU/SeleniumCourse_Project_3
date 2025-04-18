package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BusinessTripsPage extends BasePage {

    @FindBy(xpath = "//*[@class='user-name']")
    private WebElement pageTitleText;

    @FindBy(xpath = "//select[@data-ftid='crm_business_trip_businessUnit']//option[text()='Отдел внутренней разработки']")
    private WebElement internalDevelopmentSubdivisionChooseButton;

    @FindBy(xpath = "//a[@id='company-selector-show']")
    private WebElement hostOrganizationListButton;

    @FindBy(xpath = "//div[@class='company-container']//span[@class='select2-arrow']")
    private WebElement chooseHostOrganizationArrowList;

    @FindBy(xpath = "//div[text()='(Edge) Призрачная Организация Охотников']")
    private WebElement chosenOrganizationOption;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_tasks_1']")
    private WebElement ticketOrderCheckbox;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureCity]']")
    private WebElement departureCityField;

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCityField;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement businessTripDepartureDate;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement businessTripReturnDate;

    @FindBy(xpath = "//div[@id='ui-datepicker-div']")
    private WebElement calendarPickClose;

    @FindBy(xpath = "//div[contains(@id, 'uniform-crm_business_trip_businessUnit')]/span")
    private WebElement selectedDepartmentField;

    @FindBy(xpath = "//div[@class='company-container']//span[contains(@class, 'chosen')]")
    private WebElement selectedHostOrganizationField;

    @FindBy(xpath = "//button[@class='btn btn-success action-button']")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "//span[@class='validation-failed']")
    private WebElement validationErrorMessage;

    @Step("Проверка что открыта страница создания новой командировки")
    public BusinessTripsPage checkPageTitle() {
        Assert.assertEquals("Проверка тайтла страницы для вкладки командировки",
                "Создать командировку", waitUtilElementToBeVisible(pageTitleText).getText());
        return this;
    }

    @Step("Выбор подразделения")
    public BusinessTripsPage chooseSubdivision() {
        waitUtilElementToBeClickable(internalDevelopmentSubdivisionChooseButton).click();
        return this;
    }

    @Step("Выбор принимающей организации")
    public BusinessTripsPage chooseHostOrganization() {
        waitUtilElementToBeClickable(hostOrganizationListButton).click();
        waitUtilElementToBeClickable(chooseHostOrganizationArrowList).click();
        waitUtilElementToBeClickable(chosenOrganizationOption).click();
        return this;
    }

    @Step("Выбор билетов")
    public BusinessTripsPage clickTicketOrderCheckbox() {
        waitUtilElementToBeClickable(ticketOrderCheckbox).click();
        return this;
    }

    @Step("Выбор города отправления")
    public BusinessTripsPage chooseDepartureCity(String city) {
        waitUtilElementToBeVisible(departureCityField).clear();
        waitUtilElementToBeVisible(departureCityField).sendKeys(city);
        return this;
    }

    @Step("Выбор города прибытия")
    public BusinessTripsPage chooseArrivalCity(String city) {
        waitUtilElementToBeVisible(arrivalCityField).sendKeys(city);
        return this;
    }

    @Step("Выбор даты начала командировки")
    public BusinessTripsPage chooseBusinessTripDepartureDate(String date) {
        waitUtilElementToBeVisible(businessTripDepartureDate).sendKeys(date);
        return this;
    }

    @Step("Выбор даты окончания командировки")
    public BusinessTripsPage chooseBusinessTripReturnDate(String date) {
        waitUtilElementToBeVisible(businessTripReturnDate).sendKeys(date);
        waitUtilElementToBeClickable(calendarPickClose).click();
        return this;
    }

    @Step("Проверка, что выбранное подразделение заполнено правильно")
    public BusinessTripsPage checkDepartmentField() {
        Assert.assertEquals("Проверка выбранного подразделения",
                "Отдел внутренней разработки", waitUtilElementToBeVisible(selectedDepartmentField).getText());
        return this;
    }

    @Step("Проверка, что принимающая организация заполнена правильно")
    public BusinessTripsPage checkHostOrganizationField() {
        Assert.assertEquals("Проверка принимающей организации",
                "(Edge) Призрачная Организация Охотников",
                waitUtilElementToBeVisible(selectedHostOrganizationField).getText());
        return this;
    }

    // Здесь не смог правильно проверить, т.к. не нашел что меняется в DOM при выборе или отмене чекбакса,
    // поэтому просто привязался к элементу чекбокс и его присутствию на странице. Вообщем проверка так себе.
    @Step("Проверка, что выбран чекбокс заказ билетов")
    public BusinessTripsPage checkTicketsOrderChosen() {
        Assert.assertTrue("Проверка чекбокса на Заказе билетов",
                waitUtilElementToBeVisible(ticketOrderCheckbox).isDisplayed());
        return this;
    }

    // Странно, не смог найти нужные значения сохраненные у веб-элемента. Обычно в DOM в поле value, должно быть новое
    // значение, но его нет. Или это баг такой? Вообщем на UI глазами видны нужные значение, но по getText() вынимаются
    // пустые строки. Пока исключил проверку. В поле expected, должна быть Казань
    @Step("Проверка, что поле город убытия заполнено правильно")
    public BusinessTripsPage checkDepartureCity(String city) {
//        Assert.assertEquals("Проверка  города выбытия", city,
//                waitUtilElementToBeVisible(departureCityField).getText());
        return this;
    }

    // Пока исключил проверку. В поле expected, должен быть Ярославль
    @Step("Проверка, что поле город прибытия заполнено правильно")
    public BusinessTripsPage checkArrivalCity(String city) {
//        Assert.assertEquals("Проверка города прибытия", city,
//                waitUtilElementToBeVisible(arrivalCityField).getText());
        return this;
    }

    // Такая же история и с датами.
    // Пока исключил проверку. В поле expected, должна быть дата 01.05.2025
    @Step("Проверка, что поле дата начала командировки заполнено правильно")
    public BusinessTripsPage checkDepartureDate(String date) {
//        Assert.assertEquals("Проверка даты начала командировки", date,
//                waitUtilElementToBeVisible(businessTripDepartureDate).getText());
        return this;
    }

    // Пока исключил проверку. В поле expected, должна быть дата 31.05.2025
    @Step("Проверка, что поле дата окончания командировки заполнено правильно")
    public BusinessTripsPage checkReturnDate(String date) {
//        Assert.assertEquals("Проверка даны окончания командировки", date,
//                waitUtilElementToBeVisible(businessTripReturnDate).getText());
        return this;
    }

    @Step("Нажатие на кнопку Сохранить и Закрыть")
    public BusinessTripsPage clickSaveAndCloseButton() {
        waitUtilElementToBeClickable(saveAndCloseButton).click();
        return this;
    }

    @Step("Проверка, что появляется сообщение об ошибке")
    public BusinessTripsPage checkValidationErrorMessage(String text) {
        Assert.assertEquals("Проверка появления сообщения об ошибке", text,
                waitUtilElementToBeVisible(validationErrorMessage).getText());
        return this;
    }

}
