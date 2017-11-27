/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources;

import java.awt.Robot;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mfernandes
 */
@Path("key")
public class KeyInputResource {

    static Thread main;
    static HashSet<String> registers = new HashSet();

    public KeyInputResource() {

        main = new Thread(new Runnable() {
            @Override
            public void run() {

                ///coletar dado
            }
        });
        main.start();

    }

    @GET
    @Path("register/{output}")
    @Produces(MediaType.APPLICATION_JSON)
    public void register(@PathParam("output") String output) {
        registers.add(output);
    }

    @GET
    @Path("remove/{output}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("output") String output) {
        registers.remove(output);
    }

}
