package com.farmacia.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import java.util.Comparator;


public class AlertaComparador implements Comparator<Producto> {
    @Override
    public int compare(Producto p1, Producto p2) {
        if (p1.getDiasParaVencimiento() != p2.getDiasParaVencimiento()) {
            return Integer.compare(p1.getDiasParaVencimiento(), p2.getDiasParaVencimiento());
        } else {
            return Integer.compare(p1.getStock(), p2.getStock());
        }
    }
}


