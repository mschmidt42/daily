package com.github.mschmidt42.daily;

import javax.swing.*;
import java.net.URL;

public class App 
{
    public static void main( String[] args ) {
        new App().appMain(args);
    }

    public void appMain(String[] args) {
        // Create a new JFrame window
        JFrame frame = new JFrame("Daily");

        // Create a new JLabel with the three bullet points
        JLabel label = new JLabel("<html>" + 
            "<style>li { margin-bottom: 0px; }</style>" +
            "<ul style='font-size: 20pt;'>"  +
            "<li>Was habe ich gestern erreicht?</li>" +
            "<li>Was habe ich heute geplant?</li>" +
            "<li>Was hält mich auf?</li>" +
            "</ul></html>");


        // Add the label to the frame's content pane
        frame.getContentPane().add(label);

        // Set the frame to always be on top
        frame.setAlwaysOnTop(true);

        // Set icon from resources
        URL imgUrl = getClass().getClassLoader().getResource("icon.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        frame.setIconImage(icon.getImage());
        
        // Set the size of the frame
        frame.setSize(500, 200);

        frame.setLocation(700, 0);

        // Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        frame.setVisible(true);
    }
}
