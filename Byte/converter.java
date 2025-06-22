package Byte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class converter {
    public static boolean convertToHpp(File inputFile, String outputName) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(inputFile);
            byte[] data = new byte[(int) inputFile.length()];
            int bytesRead = 0;
            int offset = 0;
            while (offset < data.length && (bytesRead = fis.read(data, offset, data.length - offset)) >= 0) {
                offset += bytesRead;
            }
            if (offset < data.length) {
                String red = "\u001B[31m";
                String reset = "\u001B[0m";

                throw new IOException(red + "Failed to read the entire file " + reset + "> " + inputFile.getName());
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#pragma once\n\n");
            sb.append("// Generated from: ").append(inputFile.getName()).append("\n");
            sb.append("// Thank you for using ByteConvert. <3 \n\n");
            sb.append("unsigned char ").append(outputName).append("[] = {");

            for (int i = 0; i < data.length; i++) {
                if (i % 12 == 0)
                    sb.append("\n    ");
                sb.append(String.format("0x%02X", data[i]));
                if (i != data.length - 1)
                    sb.append(", ");
            }
            sb.append("\n};\n");
            sb.append("unsigned int ").append(outputName).append("_len = ").append(data.length).append(";\n");

            File outFile = new File(inputFile.getParent(), outputName + ".hpp");
            fos = new FileOutputStream(outFile);
            fos.write(sb.toString().getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
            }
        }
    }
}
