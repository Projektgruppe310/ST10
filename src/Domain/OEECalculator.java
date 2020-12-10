package Domain;

import java.util.HashMap;

public class OEECalculator {

    /* OEE Formula: Availability x Performance x Quality
        Availability = Run Time / Planned Production Time
            Run Time = Planned Production Time - Stop Time
        Performance = (Ideal Cycle Time x Total Count) / Run Time
        or
        Performance = (Total Count / Run Time) / Ideal Run Rate
        Quality = Good Count / Total Count

        So,
        OEE = (Good Count x Ideal Cycle Time) / Planned Production Time

        Good Count should only include parts that are defect-free the first time through the process.
            Good Count = Total Count - Rejected Count

        Ideal Cycle Time is the theoretical minimum time to produce one piece.

        Planned Production Time is the total time that the manufacturing process is scheduled for production.
            Planned Production Time = Shift Length - Breaks
     */


        // 0 Pilsner, 1 Wheat, 2 IPA, 3 Stout, 4 Ale, 5 Alcohol Free
        private int beerType;

        private int totalCount;
        private int rejectedCount;
        private int goodCount;

        private double idealCycleTimeSeconds;

        private double shiftLengthMinutes;
        private double breaksMinutes;
        private double plannedProductionTimeMinutes;

        private double oee;

        // Is it better if shift length and breaks are hardcoded?
        /*
        public OEECalculator(int beerType, int totalCount, int rejectedCount, double shiftLengthMinutes, double breaksMinutes) {
            this.beerType = beerType;
            this.totalCount = totalCount;
            this.rejectedCount = rejectedCount;
            this.shiftLengthMinutes = shiftLengthMinutes;
            this.breaksMinutes = breaksMinutes;
        }*/

        public OEECalculator(int beerType, int totalCount, int rejectedCount) {
            this.beerType = beerType;
            this.totalCount = totalCount;
            this.rejectedCount = rejectedCount;
            this.shiftLengthMinutes = 480;
            this.breaksMinutes = 30;
        }


        private double idealCycleTime(int beerType) {
            HashMap<Integer, Integer> maximumSpeed = new HashMap<>();

            // 0 Pilsner, 1 Wheat, 2 IPA, 3 Stout, 4 Ale, 5 Alcohol Free
            maximumSpeed.put(0, 600);
            maximumSpeed.put(1, 300);
            maximumSpeed.put(2, 150);
            maximumSpeed.put(3, 200);
            maximumSpeed.put(4, 100);
            maximumSpeed.put(5, 125);

            int beersPerMinute = maximumSpeed.get(beerType);
            this.idealCycleTimeSeconds = (double)60 / beersPerMinute;
            return this.idealCycleTimeSeconds;
        }

        protected double calculateOEE (){
            this.goodCount = this.totalCount - this.rejectedCount;

            this.plannedProductionTimeMinutes = this.shiftLengthMinutes - this.breaksMinutes;
            this.oee = ((double)this.goodCount * idealCycleTime(this.beerType)) / this.plannedProductionTimeMinutes;
            return this.oee;
        }
    }

