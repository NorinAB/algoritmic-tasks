package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/design-underground-system/
// An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.
//
// Implement the UndergroundSystem class:
//
// void checkIn(int id, string stationName, int t)
// A customer with a card ID equal to id, checks in at the station stationName at time t.
// A customer can only be checked into one place at a time.
// void checkOut(int id, string stationName, int t)
// A customer with a card ID equal to id, checks out from the station stationName at time t.
// double getAverageTime(string startStation, string endStation)
// Returns the average time it takes to travel from startStation to endStation.
// The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
// The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
// There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
// You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
class UndergroundSystemTests {

    class UndergroundSystem {
        HashMap<String, List<Integer>> routsWithDurations = new HashMap<>();
        HashMap<Integer, CheckInInfo> usersCheckIns = new HashMap<>();

        class CheckInInfo {
            String station;
            int time;

            public CheckInInfo(String station, int time) {
                this.station = station;
                this.time = time;
            }
        }

        public UndergroundSystem() {
        }

        public void checkIn(int id, String stationName, int t) {
            usersCheckIns.put(id, new CheckInInfo(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            CheckInInfo checkIn = usersCheckIns.remove(id);
            addRoutDuration(getRoutKey(checkIn.station, stationName), t - checkIn.time);
        }

        public double getAverageTime(String startStation, String endStation) {
            String routKey = getRoutKey(startStation, endStation);
            return routsWithDurations.get(routKey).stream().mapToInt(i -> i).average().orElse(0);
        }

        private void addRoutDuration(String routKey, int duration) {
            if (!routsWithDurations.containsKey(routKey)) {
                routsWithDurations.put(routKey, new LinkedList<>());
            }
            routsWithDurations.get(routKey).add(duration);
        }

        private String getRoutKey(String from, String to) {
            return "rout from: " + from + " to:" + to;
        }
    }

    @Test
    void testCase1() {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        assertEquals(14.0, undergroundSystem.getAverageTime("Paradise", "Cambridge")); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        assertEquals(11.0, undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        assertEquals(11.0, undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        assertEquals(12.0, undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
    }

    @Test
    void testCase2() {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(10, "Leyton", 3);
        undergroundSystem.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> "Paradise" in 8-3 = 5
        assertEquals(5.0, undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 5.00000, (5) / 1 = 5
        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> "Paradise" in 16-10 = 6
        assertEquals(5.5, undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 5.50000, (5 + 6) / 2 = 5.5
        undergroundSystem.checkIn(2, "Leyton", 21);
        undergroundSystem.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> "Paradise" in 30-21 = 9
        assertEquals((5 + 6 + 9.0) / 3, undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 6.66667, (5 + 6 + 9) / 3 = 6.66667
    }
}
