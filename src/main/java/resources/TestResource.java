package resources;

/**
 * Test class for resource
 *
 * @author Vladimir Shkerin
 * @since 12.02.2017
 */
public class TestResource {

    private String name;
    private int age;

    public TestResource(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TestResource() {
        this.name = "";
        this.age = 0;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

}
