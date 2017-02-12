package accountServer;

import base.ResourceServer;
import context.Context;
import resources.ResourceServerImpl;

/**
 * Controller for resource server
 *
 * @author Vladimir Shkerin
 * @since 12.02.2017
 */
public class ResourceServerController implements ResourceServerControllerMBean {

    private ResourceServer resourceServer;

    public ResourceServerController(Context context) {
        this.resourceServer = (ResourceServerImpl) context.get(ResourceServerImpl.class);
    }

    @Override
    public String getName() {
        return resourceServer.getName();
    }

    @Override
    public int getAge() {
        return resourceServer.getAge();
    }

}
