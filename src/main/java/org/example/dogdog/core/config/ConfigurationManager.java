package org.example.dogdog.core.config;

import lombok.extern.slf4j.Slf4j;
import org.example.dogdog.core.config.resource.ResourceLoader;
import org.example.dogdog.core.config.resource.property.PropertiesResourceLoader;
import org.example.dogdog.core.config.resource.yaml.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Slf4j
public class ConfigurationManager implements Configuration {

    private static final String PROPERTIES_FILE_EXTENSION = ".properties";

    private static final String YAML_FILE_EXTENSION = ".yaml";

    private static final String YML_FILE_EXTENSION = ".yml";

    private final Configuration configuration;

    public ConfigurationManager(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public int getInt(String id) {
        return configuration.getInt(id);
    }

    @Override
    public String getString(String id) {
        return configuration.getString(id);
    }

    @Override
    public Boolean getBoolean(String id) {
        return configuration.getBoolean(id);
    }

    @Override
    public void loadResources(List<Path> resourcePaths) {
        try {
            for(Path resourcePath : resourcePaths) {
                String fileName = resourcePath.getFileName().toString();
                if(fileName.endsWith(PROPERTIES_FILE_EXTENSION)){
                    ResourceLoader resourceLoader = new PropertiesResourceLoader();
                    Map<String, String> configurationMap = resourceLoader.loadResource(resourcePath);
                    configuration.putAll(configurationMap);
                } else if(fileName.endsWith(YML_FILE_EXTENSION) || fileName.endsWith(YAML_FILE_EXTENSION)){
                    ResourceLoader resourceLoader = new YamlConfiguration();
                    Map<String, String> configurationMap = resourceLoader.loadResource(resourcePath);
                    configuration.putAll(configurationMap);
                }
            }
        } catch (IOException e) {
            log.error("not load resources.", e);
            System.exit(-1);
        }
    }
}
