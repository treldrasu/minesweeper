import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mines extends JFrame {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 257;
    private final int HEIGHT = 300;

    private JLabel statusbar;

    public Mines() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Minesweeper");

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar), BorderLayout.CENTER);

        setResizable(false);
        pack();
        //für Test deaktiviert
        setVisible(true);

    }

    public static void main(String[] args) {
        new Mines();
    }
}
