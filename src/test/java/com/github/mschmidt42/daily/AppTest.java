package com.github.mschmidt42.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Frame;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;
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
        assertTrue(frame.isVisible());

        // Check that the frame's title is "Daily"
        assertEquals("Daily 2.0", frame.getTitle());

        // Check that the label contains the expected text
        JLabel label = (JLabel) frame.getContentPane().getComponent(0);
        assertTrue(label.getText().contains("Was habe ich gestern erreicht?"));
        assertTrue(label.getText().contains("Was habe ich heute geplant?"));
        assertTrue(label.getText().contains("Was hält mich auf?"));

        Image icon = frame.getIconImage();
        assertNotNull(icon);

        // Check that the frame size and location are correct
        assertEquals(500, frame.getWidth());
        assertEquals(200, frame.getHeight());
        assertEquals(700, frame.getX());
        assertEquals(0, frame.getY());

        // Check that the default close operation is EXIT_ON_CLOSE
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
    }
}
