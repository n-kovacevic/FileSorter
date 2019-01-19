package com.nkovacevic.filesorter.core.local;

import com.nkovacevic.filesorter.core.Destination;
import com.nkovacevic.filesorter.core.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LocalDirectoryDestination implements Destination {

    private final Path path;

    public LocalDirectoryDestination(Path path) {
        this.path = path;
    }

    @Override
    public void upload(File file, String location) throws IOException {
        Path downloadLocation = path.resolve(location);
        Path tmp = file.download(downloadLocation);
        if (Files.exists(tmp))
            Files.copy(tmp, downloadLocation.resolve(file.getFileName()));
    }
}
