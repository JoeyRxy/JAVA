package mine.learn.annotation4j.sqlutils.bean;

import mine.learn.annotation4j.sqlutils.Constraints;
import mine.learn.annotation4j.sqlutils.DBTable;
import mine.learn.annotation4j.sqlutils.SqlInteger;
import mine.learn.annotation4j.sqlutils.SqlString;

/**
 * Member
 */
@DBTable(name = "MEMBER")
public class Member {

    @SqlString(30)
    String firstName;
    @SqlString(50)
    String lastName;
    @SqlInteger
    Integer age;
    @SqlString(value = 30, constraints = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member [handle=" + handle + "]";
    }

}