class Convert {
    private static int[] intervals = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static String[] numeral = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};


    /**
     * Dichotomic search in sorted array of intervals
     *
     * @param number
     * @return floor index closest to number
     */
    private final static int findFloor(final int number, final int firstIndex, final int lastIndex) {
        if (firstIndex == lastIndex)
            return firstIndex;
        if (intervals[firstIndex] == number)
            return firstIndex;
        if (intervals[lastIndex] == number)
            return lastIndex;
        final int median = (lastIndex + firstIndex) / 2;
        if (median == firstIndex)
            return firstIndex;
        if (number == intervals[median])
            return median;
        if (number > intervals[median])
            return findFloor(number, median, lastIndex);
        else
            return findFloor(number, firstIndex, median);

    }

    static String toRoman(final int number) {
        int floorIndex = findFloor(number, 0, intervals.length - 1);
        if (number == intervals[floorIndex])
            return numeral[floorIndex];
        return numeral[floorIndex] + toRoman(number - intervals[floorIndex]);
    }

    static int toArabic(String roman) {
        int result = 0;
        for (int i = intervals.length - 1; i >= 0; i--) {
            while (roman.indexOf(numeral[i]) == 0 && numeral[i].length() > 0) {
                result += intervals[i];
                roman = roman.substring(numeral[i].length());
            }
        }
        return result;
    }
}
