package servlets;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

/**
 * Test class AllRequestsServlet
 *
 * @author Vladimir Shkerin
 * @since 08.02.2017
 */
public class AllRequestsServletTest {

    private HttpServletRequest getMockRequest() throws IOException {
        return mock(HttpServletRequest.class);
    }

    private HttpServletResponse getMockResponse(StringWriter stringWriter) throws IOException {
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        return response;
    }

    @Test
    public void testDoGet() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        final HttpServletRequest request = getMockRequest();
        final HttpServletResponse response = getMockResponse(stringWriter);
        when(request.getParameter("key")).thenReturn("answer");

        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        allRequestsServlet.doGet(request, response);

        Assert.assertEquals("answer", stringWriter.toString());

        verify(response).setContentType("text/html;charset=utf-8");
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void testDoPost() throws Exception {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        allRequestsServlet.doPost(request, response);
    }

}