package com.bu.roomy.domain;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class UpgradableBookings implements Bookings {

    private final int roomsAmount;

    private final Queue<Customer> assignedCustomers;

    public UpgradableBookings(int roomsAmount, List<Customer> assignedCustomers) {
        this.roomsAmount = roomsAmount;
        this.assignedCustomers = sortDesc(assignedCustomers);
    }

    @Setter
    private UpgradableBookings higherGradeBookings;

    @Override
    public int getBookedRoomsAmount() {
        return Math.min(roomsAmount, assignedCustomers.size());
    }

    @Override
    public double getTotalValue() {
        int bookedRoomsAmount = Math.min(roomsAmount, assignedCustomers.size());
        List<Customer> bookedCustomers = assignedCustomers.stream()
            .limit(bookedRoomsAmount)
            .toList();
        return bookedCustomers.stream()
            .map(Customer::preferencedPrice)
            .map(Price::getPrice)
            .reduce(0d, Double::sum);
    }

    int findThisAndHigherGradeFreeRooms() {
        if (higherGradeBookings == null)
            return freeRooms();
        return freeRooms() + higherGradeBookings.findThisAndHigherGradeFreeRooms();
    }

    int higherGradeFreeRooms() {
        return findThisAndHigherGradeFreeRooms() - freeRooms();
    }

    void upgradeThisAndHigherGrade() {
        List<Customer> customersForUpgrade = new ArrayList<>();
        int higherGradeFreeRooms = higherGradeFreeRooms();
        int overbookedCustomers = overbookedCustomers();

        if (higherGradeFreeRooms > 0 && overbookedCustomers > 0)
            for (int i = 0; i < Integer.min(higherGradeFreeRooms, overbookedCustomers); i++)
                customersForUpgrade.add(assignedCustomers.poll());

        if (customersForUpgrade.size() > 0) {
            higherGradeBookings.bookAdditional(customersForUpgrade);
            higherGradeBookings.upgradeThisAndHigherGrade();
        }
    }

    void bookAdditional(List<Customer> upgraded) {
        assignedCustomers.addAll(upgraded);
    }

    private Queue<Customer> sortDesc(List<Customer> customers) {
        return customers.stream()
            .sorted(Comparator.comparingDouble(Customer::getPreferredPriceValue).reversed())
            .collect(Collectors.toCollection(LinkedList::new));
    }

    private int freeRooms() {
        return roomsAmount - assignedCustomers.size();
    }

    private int overbookedCustomers() {
        return assignedCustomers.size() - roomsAmount;
    }
}
