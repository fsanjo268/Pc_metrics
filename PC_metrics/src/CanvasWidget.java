import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasWidget {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panelRam;
    private JPanel panelFreeRAM;
    private JPanel panelCPU;

    private JLabel cpuLabel;
    private JLabel usedRAMLabel;
    private JLabel maxRamLabel;
    private JLabel freeRamLabel;
    private JButton xButton;
    private Point initialClick;

     public CanvasWidget(){

    }
    public JLabel getCpuLabel() {
        return cpuLabel;
    }

    public void setCpuLabel(String value) {
        String appendText = value + " %";
        cpuLabel.setText(appendText);
    }

    public JLabel getUsedRAMLabel() {
        return usedRAMLabel;
    }

    public void setUsedRAMLabel(String value) {
        String appendText = value + " GB";
        usedRAMLabel.setText(appendText);
    }

    public JLabel getMaxRamLabel() {
        return maxRamLabel;
    }

    public void setMaxRamLabel(String value) {
        String appendText = value + " GB";
        maxRamLabel.setText(appendText);
    }

    public JLabel getFreeRamLabel() {
        return freeRamLabel;
    }

    public void setFreeRamLabel(String value) {
        String appendText = value + " GB";
        freeRamLabel.setText(appendText);
    }

    public void startWidget() {
        JFrame frame = new JFrame("CanvasWidget");

        frame.setUndecorated(true);
        setWindowTransparent(frame);
        frame.setContentPane(panel1);

        frame.pack();
        Font font = new Font("Arial", Font.PLAIN, 13);
        cpuLabel.setForeground(Color.black); // Establecer el color de las etiquetas
        cpuLabel.setFont(font);

        usedRAMLabel.setForeground(Color.black);
        usedRAMLabel.setFont(font);

        maxRamLabel.setForeground(Color.black);
        maxRamLabel.setFont(font);

        freeRamLabel.setForeground(Color.black);
        freeRamLabel.setFont(font);
        xButton.setOpaque(true);
        xButton.setBackground(new Color(134, 0, 0, 255));
        // Hacer que la ventana se cierre correctamente al hacer clic en la "X"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar el mouse listener para arrastrar la ventana
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Obtener la posición actual de la ventana
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;

                // Determinar el desplazamiento del mouse
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Mover la ventana a la nueva posición
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });
        xButton.addActionListener(e -> System.exit(0));
        frame.setVisible(true);

    }

    // Método privado para hacer transparente la ventana
    private void setWindowTransparent(JFrame frame) {
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.getRootPane().setOpaque(false);
        frame.setOpacity(0.85f);
    }


}
