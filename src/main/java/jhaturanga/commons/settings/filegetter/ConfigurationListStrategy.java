package jhaturanga.commons.settings.filegetter;

import java.nio.file.Path;
import java.util.List;

import jhaturanga.commons.settings.config.ConfigurationObjectStrategy;

public interface ConfigurationListStrategy {

    /**
     * 
     * @return a list of path of all file and directory.
     * @throws URISyntaxException
     */
    List<Path> getAllPath();

    /**
     * 
     * @return a list of name of all file and directory.
     * @throws URISyntaxException
     */
    List<? extends ConfigurationObjectStrategy> getAll();

    /**
     * 
     * @return selected folder path
     */
    Path getFolderPath();
}