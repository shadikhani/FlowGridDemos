package temp;

import org.jfairy.Fairy;
import org.jfairy.producer.person.Person;

public class TestJFairy {
	public static void main(String[] args) {


		for (int i = 100; i < 400; i++) {
			Fairy fairy = Fairy.create();
			Person person = fairy.person();
			System.out.println("new Person(" + i + ", \"" + person.firstName() + "\", \"" + person.lastName() + "\", " + person.age() + ", " + "new Address(\"" + person.getAddress().getPostalCode() + "\",\"" + person.getAddress().getCity() +  "\"),\"" +  person.telephoneNumber() + "\"),");
		}
	}
}