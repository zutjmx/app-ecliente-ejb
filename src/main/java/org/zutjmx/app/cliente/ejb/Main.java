package org.zutjmx.app.cliente.ejb;

import org.zutjmx.webapp.ejb.remote.models.Producto;
import org.zutjmx.webapp.ejb.remote.service.ServiceEjbRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        ServiceEjbRemote serviceEjbRemoteUno = null;
        ServiceEjbRemote serviceEjbRemoteDos = null;

        /*final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        env.put("jboss.naming.client.ejb.context", true);*/

        try {
            /*InitialContext remoteContext = new InitialContext(env);*/
            InitialContext remoteContext = new InitialContext();
            serviceEjbRemoteUno = (ServiceEjbRemote) remoteContext.lookup("ejb:/app-ejb-remote/ServiceEjb!org.zutjmx.webapp.ejb.remote.service.ServiceEjbRemote?stateful");
            serviceEjbRemoteDos = (ServiceEjbRemote) remoteContext.lookup("ejb:/app-ejb-remote/ServiceEjb!org.zutjmx.webapp.ejb.remote.service.ServiceEjbRemote?stateful");

            String saludoUno = serviceEjbRemoteUno.saludar("Jesús Zúñiga Trejo");
            String saludoDos = serviceEjbRemoteDos.saludar("Ana María Alejandre Arroyo");

            System.out.println(saludoUno);
            System.out.println(saludoDos);

            Producto producto = new Producto("Cerveza oscura Victoria mega 1.2L");
            System.out.println(":: Nuevo producto: " + producto + " ::");

            serviceEjbRemoteUno.listar().forEach(System.out::println);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
