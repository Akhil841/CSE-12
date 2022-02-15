/**
 * My implementation of Student.
 * Meets the specifications outlined in PA5.
 */
import java.util.Objects;

/**
 * My implementation of Student.
 * Meets the specifications outlined in PA5.
 */
public class Student implements Comparable<Student> {
    //instance variables
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Constructs by assigning values to their respective
     * instance variables.
     * @param firstName The student's first name.
     * @param lastName The student's given name.
     * @param PID The student's PID.
     * @throws IllegalArgumentException if any of these
     * {@link String}s are null.
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID  == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * @return The student's given name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return The student's first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return The student's PID.
     */
    public String getPID() {
        return this.PID;
    }

    /**
     * Overrides Object.toEquals().
     * Returns whether this student is equal to another student.
     * @return True if the other object is a non-null {@link Student}
     * whose instance variables are all equal to this {@link Student}'s.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o != null && o instanceof Student)) return false;
        else {
            return this.getFirstName().equals(((Student) o).getFirstName()) && 
            this.getLastName().equals(((Student) o).getLastName()) &&
            this.getPID().equals(((Student) o).getPID());
        }
    }

    /**
     * Overrides Object.hashCode().
     * Returns a hash code based on the instance variables.
     * @return A hash code based on Objects.hash(firstName, lastName, PID).
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getFirstName(), this.getLastName(), this.getPID());
    }

    /**
     * Overrides Comparable<T>.compareTo(T).
     * Returns 1 if this student is lexicographically ahead of the other,
     * -1 vice versa, or 0 if the two students are equal
     * @return -1, 1, or 0 based on the positions of the last name, 
     * then the first name, and then the PID.
     */
    @Override
    public int compareTo(Student o) {
        int lastNameCompare = this.getLastName().compareTo(o.getLastName());
        if (lastNameCompare != 0) return lastNameCompare;
        else {
            int firstNameCompare = this.getFirstName().compareTo(o.getFirstName());
            if (firstNameCompare != 0) return firstNameCompare;
            else {
                int PIDCompare = this.getPID().compareTo(o.getPID());
                if (PIDCompare != 0) return PIDCompare;
                return 0;
            }
        }
    }
}
