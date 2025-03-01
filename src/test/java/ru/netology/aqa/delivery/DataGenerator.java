package ru.netology.aqa.delivery;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    protected static Faker faker = new Faker(new Locale("ru"));
    protected static String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Новосибирск", "Нижний Новгород"};

    public static String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateCity() {
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateFullName() {
        return faker.name().fullName();
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }
}