package swingWorker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ExecutionException;
import java.util.List;

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

        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        add(progressBar, BorderLayout.SOUTH);


//        Xu ly su kien nut open, mo file txt bat ky
        openMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("/Downloads/"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files (*.txt)", "txt");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
//                1. No thread
//                loadingWithoutThread(textArea, fileName);

//                2. With thread
//                LoadingTask loadingTask = new LoadingTask(textArea, fileName);
//                Thread thread = new Thread(loadingTask);
//                thread.start();

//                3. With SwingWorker
                progressBar.setValue(0);
                LoadingTextSwingWorker worker = new LoadingTextSwingWorker(textArea, progressBar, fileName);
                worker.execute();
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

class LoadingTextSwingWorker extends SwingWorker<Long, Object> {
    private String fileName;
    private JTextArea textArea;
    private JProgressBar progressBar;

    public LoadingTextSwingWorker(JTextArea textArea, JProgressBar progressBar, String fileName) {
        this.textArea = textArea;
        this.fileName = fileName;
        this.progressBar = progressBar;
    }

    @Override
    protected Long doInBackground() throws Exception {
        long fileLines = 0;

        // Get file lines
        try (BufferedReader lineCounter = new BufferedReader(new FileReader(fileName))) {
            while (lineCounter.readLine() != null) {
                fileLines++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            publish(0, "");

            String line;
            int readLines = 0;
            int progress;

            while ((line = bufferedReader.readLine()) != null) {
                readLines++; // +1 for newline

                // Calculate progress as percentage
                progress = (int) ((readLines * 100) / fileLines);

                // Publish progress updates
                publish(progress, line + '\n');

                // Small delay to see progress (remove in production)
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLines;
    }

    @Override
    protected void process(List<Object> chunks) {
        // Process is called on EDT, safe to update UI
        for (int i = 0; i < chunks.size(); i += 2) {
            int progress = (Integer) chunks.get(i);
            String content = (String) chunks.get(i + 1);

            progressBar.setValue(progress);
            textArea.append(content);
        }
    }

    @Override
    protected void done() {
        progressBar.setValue(100);
        try {
            JOptionPane.showMessageDialog(null, "Loading completed total lines: " + get() );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
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