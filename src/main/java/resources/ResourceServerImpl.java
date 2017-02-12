package resources;

import base.ResourceServer;

/**
 * Server for resource
 *
 * @author Vladimir Shkerin
 * @since 12.02.2017
 */
public class ResourceServerImpl implements ResourceServer {

    private TestResource testResource;

    public ResourceServerImpl(TestResource testResource) {
        this.testResource = testResource;
    }


    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }

}
