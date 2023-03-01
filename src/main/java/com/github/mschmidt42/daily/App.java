package com.github.mschmidt42.daily;

import javax.swing.*;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.net.URL;

public class App 
{
    @Option(name="-f", usage="set the font size in [px]")
    private int fontSize = 24;

    @Option(name="-s", usage="line spacing in [px]")
    private int lineSpacing = 0;

    @Option(name="-t", aliases="--title", usage="set the window title")
    private String title = "Daily 2.0";

    @Option(name="-w",  usage="window width")
    private int gemetryX = 500;

    @Option(name="-h",  usage="window height")
    private int gemetryY = 200;

    @Option(name="-x",  usage="window position X")
    private int posX = 700;

    @Option(name="-y",  usage="window position Y")
    private int posY = 000;

    public static void main( String[] args ) {
        new App().appMain(args);
    }

    public void appMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar daily.jar [options...] ");
            parser.printUsage(System.err);
            System.exit(1);
        }

        // Create a new JFrame window
        JFrame frame = new JFrame(title);

        // Create a new JLabel with the three bullet points
        JLabel label = new JLabel("<html>" + 
            "<style>li { margin-bottom: " + lineSpacing + "px; }</style>" +
            "<ul style='font-size: " + fontSize + "pt;'>"  +
            "<li>Was habe ich gestern erreicht?</li>" +
            "<li>Was habe ich heute geplant?</li>" +
            "<li>Was hält mich auf?</li>" +
            "</ul><br></html>");


        // Add the label to the frame's content pane
        frame.getContentPane().add(label);

        // Set the frame to always be on top
        frame.setAlwaysOnTop(true);

        // Set icon from resources
        URL imgUrl = getClass().getClassLoader().getResource("icon.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        frame.setIconImage(icon.getImage());

        
        // Set the size of the frame
        frame.setSize(gemetryX, gemetryY);
        frame.setLocation(posX, posY);

        // Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        frame.setVisible(true);
    }

}
