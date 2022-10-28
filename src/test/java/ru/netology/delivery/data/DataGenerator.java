package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {

        public static DeliveryInfo generateInfo(int days) {

            Faker faker = new Faker(new Locale("ru"));
            return new DeliveryInfo(
                    generateCity(),
                    LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }

    String generateCity() {
        String[] cities = new String[]{"Пермь", "Владимир", "Вологда", "Нижний Новгород", "Краснодар", "Санкт-Петербург"};
        int itemIndex = (int) (Math.random() * cities.length);
        return cities[itemIndex];
    }
}