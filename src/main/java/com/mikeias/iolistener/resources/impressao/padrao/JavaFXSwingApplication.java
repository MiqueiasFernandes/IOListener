/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.padrao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author mfernandes
 */
public class JavaFXSwingApplication extends JApplet {

    private static final int JFXPANEL_WIDTH_INT = 2480 / 2;
    private static final int JFXPANEL_HEIGHT_INT = 3508 / 2;
    private static JFXPanel fxContainer;
    public static JFrame frame;
    static JApplet applet;

    final static int step = 5 * 1000;
    static int value = 1;
    public static String error = null;
    public static BufferedImage img;
    
    public static boolean inicializado = false;
    public static String content;

 

    public void main() throws HeadlessException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                frame = new JFrame("JavaFX 2 in Swing");
                frame.setUndecorated(true);
                JApplet applet = new JavaFXSwingApplication();
                applet.init();
                frame.setContentPane(applet.getContentPane());
                frame.pack();

                applet.start();
            }
        });
    }

    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        Platform.runLater(
                
//                new Thread(
                        
                        new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        })
//                .start()
                ;
    }
    
    public static WebView browser;

    private void createScene() {
        StackPane root = new StackPane();
        
        browser = new WebView();

        browser.getEngine().getLoadWorker().stateProperty().addListener(new javafx.beans.value.ChangeListener<Worker.State>() {
            @Override
            public void changed(
                    ObservableValue<? extends Worker.State> observable,
                    Worker.State oldValue, Worker.State newValue) {
                System.out.println("atualizando view " + ++value + " " + newValue + "...");
            }

        });
        
        inicializado = true;
        
        root.getChildren().add(browser);

        fxContainer.setScene(new Scene(root));
        
        
        new Thread(new Runnable() {
            @Override
            public void run() {
            
                while (true) {   
                    
                  String old = content; 
                  
                    
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                    error = ex.toString();
                    }
                    
                    System.out.println("cont " + old + " : " + content);
                    
                    if (old == null ? content != null : !old.equals(content)) {
                        handleContent();
                    }
                    
                    
                }
            
            
            
            }
        }).start();
        
        

    }
    
    
    private void handleContent() {
        
        
            value = 1;
        error = null;
        img = null;
        
        final WebEngine webEngine = browser.getEngine();
        
        System.out.println("importando conteudo...");
        if (content.startsWith("http")) {
            webEngine.load(content);
        } else {
            webEngine.loadContent(content);
        }
        
        

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (value > 0) {

                        int old = value;

                        Thread.sleep(step);

                        if (value == old) {

                            value = -100;

                            img = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
                            Graphics2D g2d = img.createGraphics();

                            fxContainer.validate();
                            fxContainer.repaint();
                            fxContainer.printAll(g2d);

                            g2d.dispose();
                            try {
                                ImageIO.write(img, "png", new File("/home/mfernandes/printing.png"));
                            } catch (IOException ex) {
                                error = ex.toString();
                            }
//                            frame.dispose();
//                            applet.destroy();
                        }
                    }
                } catch (InterruptedException ex) {
                    error = ex.toString();
                }
            }
        }).start();
    }
}
