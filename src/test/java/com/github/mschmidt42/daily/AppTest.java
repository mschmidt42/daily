package com.github.mschmidt42.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testAppMain() {
        App app = new App();
        String[] args = new String[]{};
        app.appMain(args);

        // Check that the frame is visible
        JFrame frame = (JFrame) Frame.getFrames()[0];
        Assertions.assertTrue(frame.isVisible());

        // Check that the frame's title is "Daily"
        Assertions.assertEquals("Daily", frame.getTitle());

        // Check that the label contains the expected text
        JLabel label = (JLabel) frame.getContentPane().getComponent(0);
        Assertions.assertTrue(label.getText().contains("Was habe ich gestern erreicht?"));
        Assertions.assertTrue(label.getText().contains("Was habe ich heute geplant?"));
        Assertions.assertTrue(label.getText().contains("Was hält mich auf?"));
    }
}
