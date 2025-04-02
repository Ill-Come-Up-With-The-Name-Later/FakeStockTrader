package utilities;

/**
 * Represents a day in time such as January 1, 1970.
 */
public class Date implements Comparable<Date> {

    public enum Month {

        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }

    private Month month;
    private int day;
    private int year;

    public Date(Month month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * The month of this date
     *
     * @return The month of this date
     */
    public Month getMonth() {
        return month;
    }

    /**
     * The day of the month of this date
     *
     * @return The day of the month of this date
     */
    public int getDay() {
        return day;
    }

    /**
     * The date's year
     *
     * @return The date's year
     */
    public int getYear() {
        return year;
    }

    /**
     * More recent dates appear first.
     *
     * @param o the object to be compared.
     * @return 0 if the dates are the same, 1 if the date is further forward in time,
     * and -1 if the date is further in the past in time.
     */
    @Override
    public int compareTo(Date o) {
        if(o.equals(this)) {
            return 0;
        }

        // Comparing year, month, and day took some figuring out
        if(this.year < o.year) {
            return -1;
        }

        if(this.month.ordinal() < o.month.ordinal() && (o.year == this.year)) {
            return -1;
        }

        if(this.day < o.day && (o.month.ordinal() == this.month.ordinal()) && (o.year == this.year)) {
            return -1;
        }

        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Date date)) return false;

        return day == date.day && year == date.year && month == date.month;
    }

    @Override
    public String toString() {
        return this.month.ordinal() + 1 + "/" + this.day + "/" + this.year;
    }
}
