
import org.hibernate.Version;

public class HibernateCheck {
    public static void main(String[] args) {
        System.out.println(
                "Hibernate Version: " + Version.getVersionString()
        );
    }
}
