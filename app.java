import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.File;
import Byte.converter;

public class app {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the file you want to process...");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            String green = "\u001B[32m";
            String red = "\u001B[31m";
            String reset = "\u001B[0m";

            System.out.println(green + "File founded " + reset + "> " + file.getAbsolutePath() + reset);
            String outputName = file.getName().replaceAll("[^a-zA-Z0-9]", "_");
            boolean success = converter.convertToHpp(file, outputName);
            if (success) {
                System.out.println(green + "File has successfully converted " + reset + "> " + outputName + ".hpp" + reset);
            } else {
                System.out.println(red + "Failed to convert the file." + reset);
            }
        } else {
            String yellow = "\u001B[33m";
            String reset = "\u001B[0m";
            System.out.println(yellow + "No file selected." + reset);
        }
    }
}