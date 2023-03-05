package com.github.mschmidt42.daily;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class DailyMain extends JFrame {
    private int fontSize = 18;
    private int lineSpacing = 0;
    private long interval = 2 * 60;
    private String title = "Daily";
    private int stopwatchFontSize = 12;
    private boolean stopwatchFontBold = true;

    private JLabel messageLabel, stopwatchLabel;
    private Timer stopwatch;
    private long startTime = 0;
    private boolean isRunning = false;

    public DailyMain() {
        super("Daily");
        setName("DailyMain");

        readConfiguration("daily.properties");
        setTitle(title);

        // Set icon from resources
        URL imgUrl = getClass().getClassLoader().getResource("icon.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        setIconImage(icon.getImage());

        // create hello label
        messageLabel = new JLabel("<html>" + 
            "<style>li { margin-bottom: " + lineSpacing + "px; }</style>" +
            "<ul style='font-size: " + fontSize + "pt;'>"  +
            "<li>Was habe ich gestern erreicht?</li>" +
            "<li>Was habe ich heute geplant?</li>" +
            "<li>Was hält mich auf?</li>" +
            "</ul><br></html>");
        messageLabel.setName("messageLabel");
        // messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        messageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(messageLabel, BorderLayout.CENTER);

        // create stopwatch label
        stopwatchLabel = new JLabel("00:00  ");        
        stopwatchLabel.setName("stopwatchLabel");
        stopwatchLabel.setFont(new Font("Verdana", stopwatchFontBold ? Font.BOLD : Font.PLAIN, stopwatchFontSize));
        stopwatchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(stopwatchLabel, BorderLayout.SOUTH);

        // create stopwatch timer
        stopwatch = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                long hours = elapsedTime / 3600;
                long minutes = (elapsedTime % 3600) / 60;
                long seconds = elapsedTime % 60;
                if(hours < 1){
                    stopwatchLabel.setText(String.format("%02d:%02d  ",  minutes, seconds));    
                } else {
                    stopwatchLabel.setText(String.format("%02d:%02d:%02d  ", hours, minutes, seconds));
                }
                // System.out.println(">>> "+ elapsedTime + "  "+ interval + String.format("  %02d:%02d:%02d  ", hours, minutes, seconds));
                if(elapsedTime > interval){
                    stopwatchLabel.setForeground(Color.RED);
                } 
            }
        });

        // add mouse listener to stopwatch label
        stopwatchLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (isRunning) {
                        stopwatch.stop();
                        isRunning = false;
                    } else {
                        startTime = System.currentTimeMillis();
                        stopwatch.start();
                        isRunning = true;
                        stopwatchLabel.setForeground(Color.BLACK);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    startTime = System.currentTimeMillis();
                    // stopwatch.stop();
                    // isRunning = false;
                    stopwatchLabel.setText("00:00  ");
                    stopwatchLabel.setForeground(Color.BLACK);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        // setLocationRelativeTo(null);
        setLocation(500, 0);
        setVisible(true);
    }

    private void readConfiguration(String filename){
        Path path = Path.of(filename);
        if(Files.exists(path)){
            try (InputStream input = new FileInputStream(filename)) {
                Properties prop = new Properties();
                prop.load(input);

                try {fontSize = Integer.parseInt(prop.getProperty("fontsize")) ;} catch(NumberFormatException e) {}
                try {lineSpacing = Integer.parseInt(prop.getProperty("linespacing"));} catch(NumberFormatException e) {}
                try {interval = Integer.parseInt(prop.getProperty("stopwatch.interval"));} catch(NumberFormatException e) {}
                try {stopwatchFontSize = Integer.parseInt(prop.getProperty("stopwatch.font.size"));} catch(NumberFormatException e) {}
                try {stopwatchFontBold = Boolean.parseBoolean(prop.getProperty("stopwatch.font.bold"));} catch(Exception e) {}
                title = prop.getProperty("title");
                

            } catch (IOException ex) {
                System.err.println("Error reading properties file. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

    }

}
