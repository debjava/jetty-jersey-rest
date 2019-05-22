package com.test.app;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

@Path("/api")
public class Service {

  @GET
  @Path("test/")
  public Response test() {
    System.out.println("test invoked");
    return Response.ok().build();
  }

  @POST
  @Path("employee/")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response getEmployee(Employee employee) {
    employee.setEmployeeName(employee.getEmployeeName() + " Welcome");
    return Response.status(Status.OK).entity(employee).build();
  }

  public static void main(String[] args) throws Exception {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    int port = 46100;
    Server jettyServer = new Server(port);
    jettyServer.setHandler(context);
    // org.eclipse.jetty.util.log.Log.getRootLogger().setDebugEnabled(true);

    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    // Tells the Jersey Servlet which REST service/class to load.
    jerseyServlet.setInitParameter(
        "jersey.config.server.provider.classnames", Service.class.getCanonicalName());

    try {
      jettyServer.start();
      jettyServer.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jettyServer.destroy();
    }
  }
}
