package com.mehdi.demo;

import com.mehdi.gui.misc.JPanelPersianGregorianDateConverter;

import javax.swing.*;

public class PersianGregorianDateConverter extends JFrame {
    public PersianGregorianDateConverter() {
        super("Persian-Gregorian Date Converter");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().add(new JPanelPersianGregorianDateConverter());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new PersianGregorianDateConverter();
    }
}
