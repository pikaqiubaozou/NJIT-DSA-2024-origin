package oy.tol.tra;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName, "First name must not be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name must not be null");
    }

    public Person(Person person) {
        this(person.getFirstName(), person.getLastName());
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public int hashCode() {
        // 自己实现计算哈希值的方法
        int result = 17;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int compareTo(Person other) {
        int lastNameComparison = lastName.compareTo(other.lastName);
        return lastNameComparison != 0 ? lastNameComparison : firstName.compareTo(other.firstName);
    }
}