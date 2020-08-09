import java.io.*;
import java.nio.charset.StandardCharsets;

public class IsEvenGenerator {

    private static final int BUFFER_SIZE = 1024 * 1024 * 512;

    private BufferedWriter bufferedWriter;

    public void generateIsEven(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(osw, BUFFER_SIZE);

            generateIsEven();

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateIsEven() throws IOException {
        write("private boolean isEven(int number) {\n");
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
            write("\t" + ifStartString + " (number == " + i + ") return " +
                  (i % 2 == 0 ? "true" : "false") + ";\n");
        }
        write("}");
    }

    private void write(String s) throws IOException {
        bufferedWriter.write(s);
    }
}