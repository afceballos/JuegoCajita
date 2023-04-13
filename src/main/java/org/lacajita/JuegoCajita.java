package org.lacajita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JuegoCajita extends JFrame implements KeyListener {

    private JPanel panel;
    private JLabel caja;
    private boolean tocaBorde;

    public JuegoCajita() {
        setTitle("Juego de la cajita");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        caja = new JLabel();
        caja.setOpaque(true);
        caja.setBackground(Color.BLUE);
        caja.setBounds(0,0,50,50);
        panel.add(caja);

        add(panel);
        panel.addKeyListener(this);
        panel.setFocusable(true);

        setVisible(true);
    }

    public void moverCaja(int dx, int dy) {
        int x = caja.getX() + dx;
        int y = caja.getY() + dy;

        // verificar que la caja no salga de los límites del panel
        if (x < 0) {
            x = 0;
        } else if (x + caja.getWidth() > panel.getWidth()) {
            x = panel.getWidth() - caja.getWidth();
        }
        if (y < 0) {
            y = 0;
        } else if (y + caja.getHeight() > panel.getHeight()) {
            y = panel.getHeight() - caja.getHeight();
        }

        // cambiar la posición de la caja y actualizar su estado
        caja.setLocation(x, y);
        tocaBorde = (x == 0 || x + caja.getWidth() == panel.getWidth() ||
                y == 0 || y + caja.getHeight() == panel.getHeight());
//        System.out.println(tocaBorde);
        if (tocaBorde) {
            int newSize = (int) (Math.random() * 10) + 1; //
            int r = (int) (Math.random() * 256); //generar valor aleatorio para el componente rojo
            int g = (int) (Math.random() * 256); //generar valor aleatorio para el componente verde
            int b = (int) (Math.random() * 256); //generar valor aleatorio para el componente azul
            Color colorAleatorio = new Color(r, g, b); //crear color aleatorio a partir de los valores RGB
            if (caja.getWidth() <= 300){ // no permite que la caja sea mas grande que 300px
                caja.setSize(caja.getWidth() + newSize, caja.getHeight() + newSize); //cambiar tamaño de la caja
            }
            caja.setBackground(colorAleatorio); //cambiar color de la caja
        } else {
//            int newSize = (int) (Math.random() * 10) - 10; //
//            System.out.println(newSize);
            if (caja.getWidth() > 20){// no permite que la caja sea mas pequeña que 200px
                caja.setSize(caja.getWidth() - 10, caja.getHeight() - 10); //cambiar tamaño de la caja
            }
        }

        //hacer una pausa en el hilo de unos 10 mm
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moverCaja(-5, 0);
                break;
            case KeyEvent.VK_RIGHT:
                moverCaja(5, 0);
                break;
            case KeyEvent.VK_UP:
                moverCaja(0, -5);
                break;
            case KeyEvent.VK_DOWN:
                moverCaja(0, 5);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new JuegoCajita();
    }
}
