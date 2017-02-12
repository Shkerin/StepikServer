package servlets;

import accountServer.ResourceServerController;
import accountServer.ResourceServerControllerMBean;
import base.ResourceServer;
import context.Context;
import org.testng.util.Strings;
import resources.ResourceServerImpl;
import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Servlet for resource server
 *
 * @author Vladimir Shkerin
 * @since 12.02.2017
 */
public class ResourceServlet extends HttpServlet {

    public static final String PAGE_URL = "/resources";
    private ResourceServer resourceServer;
    private Context context;

    public ResourceServlet(Context context) {
        this.resourceServer = (ResourceServer) context.get(ResourceServerImpl.class);
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathResource = req.getParameter("path");
        if (Strings.isNotNullAndNotEmpty(pathResource)) {
            TestResource testResource = (TestResource) ReadXMLFileSAX.readXML(pathResource);
            resourceServer = new ResourceServerImpl(testResource);

            context.remove(ResourceServerImpl.class);
            context.add(ResourceServerImpl.class, resourceServer);

            try {
                ResourceServerControllerMBean serverStatistics = new ResourceServerController(context);
                MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
                ObjectName name = new ObjectName("Admin:type=ResourceServerController");
                if (mbs.isRegistered(name)) {
                    mbs.unregisterMBean(name);
                }
                mbs.registerMBean(serverStatistics, name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void readResourceFromPathNIO(String pathResource) {
        try (RandomAccessFile aFile = new RandomAccessFile(pathResource, "r")) {

            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(128);

            int byteRead = inChannel.read(buf);
            while (byteRead != -1) {

                System.out.println("Read " + byteRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.println((char) buf.get());
                }
                System.out.println("\n");

                buf.clear();
                byteRead = inChannel.read(buf);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
