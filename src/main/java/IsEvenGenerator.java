import java.io.*;
import java.nio.charset.StandardCharsets;

public class IsEvenGenerator {

    private static final int BUFFER_SIZE = 1024 * 1024 * 512;

    public void generateIsEven(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = new BufferedWriter(osw, BUFFER_SIZE)) {
            generateIsEven(bufferedWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawProgressBar(float progress) {
        System.out.print("\r[");

        int maxBars = 50;
        int bars    = (int)(progress * maxBars);

        for (int i = 0; i < maxBars; ++i) {
            if (i < bars) {
                System.out.print("=");
            } else {
                System.out.print("-");
            }
        }

        System.out.print("] " + (int)(progress * 100) + "%      ");
    }

    private void generateIsEven(Writer writer) throws IOException {
        writer.write("private boolean isEven(int number) {\n");
        for (int i = 0; i >= 0; ++i) {
            String ifStartString;
            switch (i) {
                case 0:
                    ifStartString = "if";
                    break;
                case Integer.MAX_VALUE:
                    ifStartString = "else";
                    break;
                default:
                    ifStartString = "else if";
                    break;
            }
            writer.write("\t" + ifStartString + " (number == " + i + ") return " +
                  (i % 2 == 0 ? "true" : "false") + ";\n");

            if ((i & 0xffff) == 0 || i == Integer.MAX_VALUE) {
                drawProgressBar((float)i / (float)Integer.MAX_VALUE);
            }
        }
        System.out.println();
        writer.write("}");
    }
}
