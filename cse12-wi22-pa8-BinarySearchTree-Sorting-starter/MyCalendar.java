import java.util.ArrayList;
/**
 * Implementation of a binary search tree.
 * Name: Akhil Pillai
 * PID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: MyTreeMap
 * 
 * Contains an implementation of a calendar using MyTreeMap,
 * graciously provided by Jianwei Gong. The calendar is capable
 * of adding bookings or returning itself.
 */

/**
 * A calendar that stores bookings using MyTreeMap.
 * Contains a smart booking method that prevents double bookings.
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * Constructs an empty calendar.
     */
    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }
    
    /**
     * Attempts to book an event. Returns true if booking was successful,
     * false if the booking conflicts with existing bookings.
     * @param start The start of the event.
     * @param end The end of the event.
     * @return whether the booking was successful
     * @throws IllegalStateException if start is negative
     * or after end
     */
    public boolean book(int start, int end) {
        //exception handling
        if (start < 0 || start >= end) throw new IllegalArgumentException();
        boolean insertable = true;
        //if the booking is within the closest booking to that booking
        if (calendar.floorKey(start) != null) {
            if (calendar.floorKey(start) <= start && start < calendar.get(calendar.floorKey(start)))
                //return false
                insertable = false;
        }
        //we check both start and end, for both floor and ceiling, if they exist
        if (calendar.ceilingKey(start) != null) {
            if (calendar.ceilingKey(start) <= start && start < calendar.get(calendar.ceilingKey(start)))
                insertable = false;
        }
        if (calendar.floorKey(end) != null) {
            if (calendar.floorKey(end) < end && end <= calendar.get(calendar.floorKey(end)))
                insertable = false;
        }
        if (calendar.ceilingKey(end) != null) {
            if (calendar.ceilingKey(end) < end && end <= calendar.get(calendar.ceilingKey(end)))
                insertable = false;
        }
        //book if possible
        if (insertable) calendar.put(start, end);
        //and return whether the booking was successful
        return insertable;
    }

    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}