import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class MyNotepad extends JFrame {
    public MyNotepad() {
        super("My Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

//        Tao thanh menu voi nut open, save, exit
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);


        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);


//        Xu ly su kien nut open, mo file txt bat ky
        openMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
//                1. No thread
//                loadingWithoutThread(textArea, fileName);

//                2. With thread
                LoadingTask loadingTask = new LoadingTask(textArea, fileName);
                Thread thread = new Thread(loadingTask);
                thread.start();
            }
        });
    }

    public void loadingWithoutThread(JTextArea textArea, String fileName) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            textArea.setText("");
            String line = null;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                textArea.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyNotepad());
    }
}

class LoadingTask implements Runnable {
    private String fileName;
    private JTextArea textArea;

    public LoadingTask(JTextArea textArea, String fileName) {
        this.textArea = textArea;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            textArea.setText("");
            String line = null;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                textArea.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}