
package InheritanceDemo;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FULL_TIME")
public class FullTimeEmployee extends Employee {

    private double monthlySalary;

    public FullTimeEmployee() {}

    public FullTimeEmployee(
            String name,
            String email,
            double monthlySalary) {

        super(name, email);
        this.monthlySalary = monthlySalary;
    }
}
