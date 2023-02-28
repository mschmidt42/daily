package com.github.mschmidt42.daily;

import java.awt.Font;
import javax.swing.*;

public class App 
{
    public static void main( String[] args )
    {
        // Create a new JFrame window
        JFrame frame = new JFrame("Daily");

        // Create a new JLabel with the three bullet points
        JLabel label = new JLabel("<html><ul><li>Was habe ich gestern erreicht?</li><li>Was habe ich heute geplant?</li><li>Wobei habe ich Probleme?</li></ul></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        // Add the label to the frame's content pane
        frame.getContentPane().add(label);

        // Set the frame to always be on top
        frame.setAlwaysOnTop(true);

        
        // Set the size of the frame
        frame.setSize(500, 200);

        // Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        frame.setVisible(true);
    }
}
