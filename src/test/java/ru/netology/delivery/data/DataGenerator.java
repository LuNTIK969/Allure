package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        String[] listOfCities = {
                "Санкт-Петербург",
                "Москва",
                "Горно-Алтайск",
                "Уфа",
                "Улан-Удэ",
                "Махачкала",
                "Магас",
                "Нальчик",
                "Элиста",
                "Черкесск",
                "Петрозаводск",
                "Сыктывкар",
                "Йошкар-Ола",
                "Саранск",
                "Якутск",
                "Владикавказ",
                "Казань",
                "Кызыл",
                "Ижевск",
                "Абакан",
                "Грозный",
                "Чебоксары",
                "Барнаул",
                "Чита",
                "Петропавловск-Камчатский",
                "Краснодар",
                "Красноярск",
                "Пермь",
                "Владивосток",
                "Хабаровск",
                "Благовещенск",
                "Архангельск",
                "Астрахань",
                "Белгород",
                "Брянск",
                "Владимир",
                "Волгоград",
                "Вологда",
                "Воронеж",
                "Иваново",
                "Иркутск",
                "Калининград",
                "Калуга",
                "Кемерово",
                "Киров",
                "Кострома",
                "Курган",
                "Курск",
                "Гатчина",
                "Липецк",
                "Магадан",
                "Красногорск",
                "Мурманск",
                "Нижний Новгород",
                "Великий Новгород",
                "Новосибирск",
                "Омск",
                "Оренбург",
                "Орёл",
                "Пенза",
                "Псков",
                "Ростов-на-Дону",
                "Рязань",
                "Самара",
                "Саратов",
                "Южно-Сахалинск",
                "Екатеринбург",
                "Смоленск",
                "Тамбов",
                "Тверь",
                "Томск",
                "Тула",
                "Тюмень",
                "Ульяновск",
                "Челябинск",
                "Ярославль",
                "Биробиджан",
                "Нарьян-Мар",
                "Ханты-Мансийск",
                "Анадырь",
                "Салехард"
        };
        Random auto = new Random();
        int city = auto.nextInt(listOfCities.length);
        return listOfCities[city];
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+7##########");
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            return new UserInfo(generateCity(locale), generateName(locale),generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}