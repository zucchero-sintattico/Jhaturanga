package jhaturanga.commons.settings.filegetter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import jhaturanga.commons.configurations.DirectoryConfigurations;

public abstract class PathFromDirectory implements ConfigurationListStrategy {
    private static final int BUFFER = 1024;

    /**
     * 
     * @param folderPath
     * @return folder content.
     * @throws URISyntaxException
     */
    public List<String> getDirectotyContent(final String folderPath) {
        try {
            DirectoryConfigurations.validateResourcesDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileValidator("css");
        this.fileValidator("piece");

        final File folder = new File(folderPath);
        return folder == null ? List.of() : Arrays.asList(folder.list());

    }

    private void fileValidator(final String folderPath) {
        final Reflections reflections = new Reflections(folderPath, new ResourcesScanner());
        final Set<String> resourceList = reflections.getResources(x -> true);
        resourceList.forEach(elem -> {

            try {
                if (!Files.exists(Path.of(Path.of(DirectoryConfigurations.RESOURCES_DIRECTORY_PATH + elem).toUri()))) {

                    copy(ClassLoader.getSystemResourceAsStream(elem),
                            new FileOutputStream(DirectoryConfigurations.RESOURCES_DIRECTORY_PATH + elem));

                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        });
    }

    private static void copy(final InputStream instream, final FileOutputStream outstream) {

        try {

            final byte[] buffer = new byte[BUFFER];

            int length;
            /*
             * copying the contents from input stream to output stream using read and write
             * methods
             */
            for (length = instream.read(buffer); length > 0; length = instream.read(buffer)) {
                outstream.write(buffer, 0, length);
            }

            // Closing the input/output file streams
            instream.close();
            outstream.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
