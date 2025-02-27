package ru.netology.aqa.delivery;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    protected static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateCity() {
        return faker.address().cityName();
    }

    public static String generateFullName() {
        return faker.name().fullName();
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }
}