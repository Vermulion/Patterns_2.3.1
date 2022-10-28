package ru.netology.delivery.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeliveryInfo {
    private final String city;
    private final String date;
    private final String name;
    private final String phoneNumber;
}
