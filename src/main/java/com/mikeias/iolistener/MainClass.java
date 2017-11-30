/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener;

import com.mikeias.iolistener.resources.impressao.ImpressoraResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author mfernandes
 */
public class MainClass {

    public static void main(String[] args) throws Exception {

//        Server server = new Server(8080);
        // Setup JMX
//        MBeanContainer mbContainer = new MBeanContainer(
//                ManagementFactory.getPlatformMBeanServer());
//        server.addBean(mbContainer);
//        WebAppContext webapp = new WebAppContext();
//        webapp.setContextPath("/");
//        File warFile = new File(
//                "/home/mfernandes/NetBeansProjects/WebConnector/dist/WebConnector.war");
//        webapp.setWar(warFile.getAbsolutePath());
//
//        server.setHandler(webapp);
//
//        server.start();
//
//        server.dumpStdErr();
        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
//        server.join();
        ResourceConfig config = new ResourceConfig();
//        config.register(ImpressoraResource.class);
        config.packages("com.mikeias.iolistener.resources");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(9090);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }

    }

}
