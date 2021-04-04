package jhaturanga.commons.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public final class CommandLine {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void clearConsole() {

        final String os = System.getProperty("os.name");

        if (!os.contains("Windows")) {
            System.out.print("\033\143");
        }

    }

    /**
     * Prompt a message and read a line.
     * 
     * @param format - the message to prompt
     * @param args   - the args for the formatted message
     * @return the line
     * @throws IOException
     */
    public String readLine(final String format, final Object... args) {

        if (System.console() != null) {
            return System.console().readLine(format, args);
        }

        this.print(String.format(format, args));

        try {
            return reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Read a line from console.
     * 
     * @return the line
     * @throws IOException
     */
    public String readLine() {

        if (System.console() != null) {
            return System.console().readLine();
        }

        try {
            return this.reader.readLine();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String readPassword(final String format, final Object... args) {
        if (System.console() != null) {
            return String.valueOf(System.console().readPassword(format, args));
        }
        return String.valueOf(readLine(format, args).toCharArray());
    }

    public String readPassword() {
        if (System.console() != null) {
            return Arrays.toString(System.console().readPassword());
        }
        final String line = this.readLine();
        return String.valueOf(line.toCharArray());
    }

    public void println(final String format) {
        System.out.println(format);
    }

    public void print(final String format) {
        System.out.print(format);
    }
}
