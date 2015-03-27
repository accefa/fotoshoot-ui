package sample;

import java.net.URI;

import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

@Path("helloworld")
public class RestServer {

	@GET
	@Produces("text/plain")
	public String getHello() {
		return "Hello World";
	}

	public static void main(String[] args) {
		ResourceConfig resourceConfig = new ResourceConfig().packages("sample");
		HttpServer server = JdkHttpServerFactory.createHttpServer(
				URI.create("http://localhost:8080/"), resourceConfig);
		JOptionPane.showMessageDialog(null, "Server beenden?");

		server.stop(0);
	}

}
