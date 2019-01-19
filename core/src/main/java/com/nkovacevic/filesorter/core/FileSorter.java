package com.nkovacevic.filesorter.core;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;


public class FileSorter {

    private final Source source;
    private final Destination destination;

    private final Map<String, Collection<String>> directoryToExtensionsMap;

    public FileSorter(Source source, Destination destination, Map<String, Collection<String>> directoryToExtensionsMap) {
        this.source = source;
        this.destination = destination;
        this.directoryToExtensionsMap = directoryToExtensionsMap;
    }


    public void sort(){

        try (Stream<File> files = source.getFilesFromSource()){
            files.forEach(file -> directoryToExtensionsMap.forEach( (dir, extensions) -> {
                if (FilenameUtils.isExtension(file.getFileName(), extensions)) {
                    try {
                        destination.upload(file, dir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
