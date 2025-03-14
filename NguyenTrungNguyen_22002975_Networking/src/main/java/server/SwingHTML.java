package server;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SwingHTML extends JFrame {
    private JTextArea htmlInput;
    private JEditorPane htmlDisplay;
    private JScrollPane inputScrollPane;
    private JScrollPane displayScrollPane;

    public SwingHTML() {
        setTitle("HTML Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2, 10, 10));

        // Create HTML input area
        htmlInput = new JTextArea();
        htmlInput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputScrollPane = new JScrollPane(htmlInput);
        inputScrollPane.setBorder(BorderFactory.createTitledBorder("HTML Input"));

        // Create HTML display area
        htmlDisplay = new JEditorPane();
        htmlDisplay.setContentType("text/html");
        htmlDisplay.setEditable(false);
        displayScrollPane = new JScrollPane(htmlDisplay);
        displayScrollPane.setBorder(BorderFactory.createTitledBorder("HTML Preview"));

        // Add document listener to update the preview in real time
        htmlInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateHtmlPreview();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateHtmlPreview();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateHtmlPreview();
            }
        });

        // Add components to the frame
        add(inputScrollPane);
        add(displayScrollPane);

        // Set default HTML content
        htmlInput.setText("<html>\n<body>\n<h1>Hello World</h1>\n<p>This is a HTML preview.</p>\n</body>\n</html>");
        updateHtmlPreview();
    }

    private void updateHtmlPreview() {
        htmlDisplay.setText(htmlInput.getText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingHTML app = new SwingHTML();
            app.setVisible(true);
        });
    }
}
