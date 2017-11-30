/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikeias.iolistener.resources.impressao.padrao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class JavaFX extends JApplet {

    private static int JFXPANEL_WIDTH_INT = 2480;
    private static int JFXPANEL_HEIGHT_INT = 3508;
    private static JFXPanel fxContainer;
    private static String content;
    private static boolean terminado = false;
    private static BufferedImage imagem;

    public static boolean isTerminado() {
        return terminado;
    }

    public static BufferedImage getImagem() {
        return imagem;
    }

    public void inicializar(String content, int width, int height) {
        JFXPANEL_WIDTH_INT = width;
        JFXPANEL_HEIGHT_INT = height;
        this.content = content;
        terminado = false;
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setUndecorated(true);

                JApplet applet = new JavaFX();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);

                applet.start();
            }
        });

    }

    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    int value = 1;

    private void createScene() {
        value = 1;
        WebView webview = new WebView();
        final WebEngine webEngine = webview.getEngine();
        if (content.startsWith("http")) {
            webEngine.load(content);
        } else {
            webEngine.loadContent(content);
        }

        webEngine.getLoadWorker().stateProperty().addListener(new javafx.beans.value.ChangeListener<Worker.State>() {
            @Override
            public void changed(
                    ObservableValue<? extends Worker.State> observable,
                    Worker.State oldValue, Worker.State newValue) {

                if (value == 1) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int old = value;
                            do {
                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex) {
                                }
                            } while (old != value);
                            saveImage();
                        }
                    }).start();
                }
                System.out.println("atualizando view " + ++value + " " + newValue + "...");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(webview);
        fxContainer.setScene(new Scene(root));
    }

    void saveImage() {
        imagem = new BufferedImage(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagem.createGraphics();

        fxContainer.validate();
        fxContainer.repaint();
        fxContainer.printAll(g2d);

        g2d.dispose();
        terminado = true;
    }

}
