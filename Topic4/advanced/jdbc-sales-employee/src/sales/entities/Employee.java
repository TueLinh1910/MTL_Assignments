package sales.entities;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String birthdate;
    private Integer supervisor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d, LastName: %s, FirstName: %s, BirthDate: %s, Supervisor: %s",
            id, lastName, firstName, birthdate, supervisor
        );
    }
}