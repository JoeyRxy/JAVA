package mine.learn.basic.serialize;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Industry implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6528308957474461459L;
    private String name;
    private Set<Person> employee;

    public Industry() {
        employee = new HashSet<>();
    }

    public Industry(String name, Collection<Person> employee) {
        this();
        this.name = name;
        if (employee != null)
            this.employee.addAll(employee);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getEmployee() {
        return employee;
    }

    public void setEmployee(Collection<Person> employee) {
        if (employee != null)
            this.employee.addAll(employee);
    }

    public void addEmployee(Person employee) {
        this.employee.add(employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Industry other = (Industry) obj;
        return Objects.equals(employee, other.employee) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Industry [employee=" + employee + ", name=" + name + "]";
    }

}