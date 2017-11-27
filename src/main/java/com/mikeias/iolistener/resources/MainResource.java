/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mfernandes
 */
@Path("/")
public class MainResource {
    
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String home() {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Recurssos de hardware</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Dispositivos em localhost</h1>"
                + "<a href=\"impressora\">IMPRESSORAS</a> <br>"
                + "<a href=\"key\">TECLADOS</a> <br>"
                + "</body>\n"
                + "</html>";
    }

}
