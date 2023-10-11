import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WidgetMetric {
    private JLabel cpuLabel;
    private JLabel usedRAMLabel;
    private JLabel maxRamLabel;
    private JLabel freeRamLabel;
    private Point initialClick;

    public WidgetMetric() {
        cpuLabel = new JLabel("CPU - %");
        usedRAMLabel = new JLabel("RAM - GB");
        maxRamLabel = new JLabel("Max RAM - GB");
        freeRamLabel = new JLabel("Free RAM - GB");
    }

    public JLabel getCpuLabel() {
        return cpuLabel;
    }

    public void setCpuLabel(String value) {
        String appendText = "CPU " + value + " %";
        cpuLabel.setText(appendText);
    }

    public JLabel getUsedRAMLabel() {
        return usedRAMLabel;
    }

    public void setUsedRAMLabel(String value) {
        String appendText = "RAM " + value + " GB";
        usedRAMLabel.setText(appendText);
    }

    public JLabel getMaxRamLabel() {
        return maxRamLabel;
    }

    public void setMaxRamLabel(String value) {
        String appendText = "Max RAM " + value + " GB";
        maxRamLabel.setText(appendText);
    }

    public JLabel getFreeRamLabel() {
        return freeRamLabel;
    }

    public void setFreeRamLabel(String value) {
        String appendText = "Free RAM " + value + " GB";
        freeRamLabel.setText(appendText);
    }

    public void startWidget() {
        // Crear una ventana JFrame
        JFrame frame = new JFrame("PC_metrics");
        // Establecer el tamaño de la ventana
        frame.setSize(150, 150);
        frame.setUndecorated(true);

        // Hacer que la ventana se cierre correctamente al hacer clic en la "X"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false); // Hacer el panel transparente

        Font font = new Font("Arial", Font.PLAIN, 16);
        cpuLabel.setForeground(Color.black); // Establecer el color de las etiquetas
        cpuLabel.setFont(font);

        usedRAMLabel.setForeground(Color.black);
        usedRAMLabel.setFont(font);

        maxRamLabel.setForeground(Color.black);
        maxRamLabel.setFont(font);

        freeRamLabel.setForeground(Color.black);
        freeRamLabel.setFont(font);


        // Agregar un panel superior para el botón de cierre
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("X");
        closeButton.setOpaque(true);
        closeButton.setBackground(new Color(134, 0, 0, 255));
        topPanel.add(closeButton);
        panel.add(topPanel, BorderLayout.NORTH);

        JPanel metricsPanel = new JPanel();
        metricsPanel.setOpaque(true);
        metricsPanel.setLayout(new BoxLayout(metricsPanel, BoxLayout.Y_AXIS));

        metricsPanel.add(cpuLabel);
        metricsPanel.add(Box.createVerticalStrut(10)); // Agregar espacio fijo
        metricsPanel.add(usedRAMLabel);
        metricsPanel.add(Box.createVerticalStrut(10)); // Agregar espacio fijo
        metricsPanel.add(maxRamLabel);
        metricsPanel.add(Box.createVerticalStrut(10)); // Agregar espacio fijo
        metricsPanel.add(freeRamLabel);

        panel.add(metricsPanel, BorderLayout.CENTER);

        // Añadir el componente al contenido de la ventana
        frame.getContentPane().add(panel);

        // Hacer que la ventana sea transparente
        setWindowTransparent(frame);

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

        // Agregar el listener al botón de cierre
        closeButton.addActionListener(e -> System.exit(0));

        // Hacer que la ventana sea visible
        frame.setVisible(true);
    }

    // Método privado para hacer transparente la ventana
    private void setWindowTransparent(JFrame frame) {
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.getRootPane().setOpaque(false);
        frame.setOpacity(0.85f);
    }
}
