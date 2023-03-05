package com.github.mschmidt42.daily;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.*;
import java.awt.event.*;

public class DailyMainTest {
    private DailyMain messageLabel;

    
    @BeforeEach
    public void setUp() {
        messageLabel = new DailyMain();
    }

    @AfterEach
    public void tearDown() {
        messageLabel.dispose();
    }

    @Test
    public void testHelloLabel() {
        JLabel helloLabel = (JLabel) TestUtils.getChildNamed(messageLabel, "messageLabel");
        assertNotNull(helloLabel);
        assertTrue(helloLabel.getText().contains("Was habe ich gestern erreicht?"));
        
        assertEquals(SwingConstants.LEFT, helloLabel.getHorizontalAlignment());
    }

    @Test
    public void testStopwatchLabel() {
        JLabel stopwatchLabel = (JLabel) TestUtils.getChildNamed(messageLabel, "stopwatchLabel");
        assertNotNull(stopwatchLabel);
        assertEquals("00:00  ", stopwatchLabel.getText());
        assertEquals(SwingConstants.RIGHT, stopwatchLabel.getHorizontalAlignment());
    }

    @Test
    public void testStopwatch() throws InterruptedException {
        JLabel stopwatchLabel = (JLabel) TestUtils.getChildNamed(messageLabel, "stopwatchLabel");

        // left-click to start stopwatch
        TestUtils.clickComponent(stopwatchLabel, MouseEvent.BUTTON1);
        Thread.sleep(2000);
        String elapsedTime1 = stopwatchLabel.getText();
        assertTrue(elapsedTime1.startsWith("00:"));

        // left-click to stop stopwatch
        TestUtils.clickComponent(stopwatchLabel, MouseEvent.BUTTON1);
        Thread.sleep(1000);
        String elapsedTime2 = stopwatchLabel.getText();
        assertEquals(elapsedTime1, elapsedTime2);

        // right-click to reset stopwatch
        TestUtils.clickComponent(stopwatchLabel, MouseEvent.BUTTON3);
        assertEquals("00:00  ", stopwatchLabel.getText());
    }

}
