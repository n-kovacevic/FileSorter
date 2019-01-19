package com.nkovacevic.filesorter.core.local;

import com.nkovacevic.filesorter.core.File;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LocalFile implements File {

    private final Path path;

    LocalFile(Path path) {
        this.path = path;
    }

    @Override
    public String getFileName() {
        return path.getFileName().toString();
    }

    @Override
    public String getBaseName(){
        return FilenameUtils.getBaseName(getFileName());
    }

    @Override
    public String getExtension(){
        return FilenameUtils.getExtension(getFileName());
    }

    @Override
    public Path download(Path destination) throws IOException {
        Path out = Files.createTempFile(destination, getBaseName(), getExtension());
        Files.copy(this.path, out, StandardCopyOption.REPLACE_EXISTING);
        return out;
    }
}
