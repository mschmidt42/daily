package com.github.mschmidt42.daily;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestUtils {
    public static Component getChildNamed(Component parent, String name) {
        if (parent != null && name.equals(parent.getName())) {
            return parent;
        }
        if (parent instanceof Container) {
            Component[] children = ((Container) parent).getComponents();
            for (Component child : children) {
                Component found = getChildNamed(child, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public static void clickComponent(Component component, int button) {
        try {
            Point p = getLocationOnScreenCentered(component);
            Robot robot;
            
                robot = new Robot();
            robot.mouseMove(p.x, p.y);
            robot.mousePress(button);
            Thread.sleep(50);
            robot.mouseRelease(button);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static Point getLocationOnScreenCentered(Component component) {
        Point center = getLocationOnScreen(component);
        center.x += component.getWidth() / 2;
        center.y += component.getHeight() / 2;
        return center;
    }

    private static Point getLocationOnScreen(Component component) {
        Point location = component.getLocationOnScreen();
        Insets insets = getScreenInsets(component);
        location.x += insets.left;
        location.y += insets.top;
        return location;
    }

    private static Insets getScreenInsets(Component component) {
        Rectangle bounds = component.getGraphicsConfiguration().getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(component.getGraphicsConfiguration());
        insets.left = bounds.x + insets.left;
        insets.right = bounds.x + bounds.width - insets.right;
        insets.top = bounds.y + insets.top;
        insets.bottom = bounds.y + bounds.height - insets.bottom;
        return insets;
    }
}
