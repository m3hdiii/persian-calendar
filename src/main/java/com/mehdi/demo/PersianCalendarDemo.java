package com.mehdi.demo;

import com.mehdi.gui.calendar.JPanelCalendar;

import javax.swing.*;

public class PersianCalendarDemo extends JFrame {
    public PersianCalendarDemo() {
        super("Persian Calendar Demo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel jPanel = new JPanelCalendar();
        add(jPanel);
        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        new PersianCalendarDemo();
    }
}
