package com.nkovacevic.filesorter.core.local;

import com.nkovacevic.filesorter.core.File;
import com.nkovacevic.filesorter.core.Source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LocalDirectorySource implements Source {

    private final Path sourcePath;

    public LocalDirectorySource(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public Stream<File> getFilesFromSource() throws IOException {
        return Files.walk(sourcePath).filter(Files::isRegularFile).map(LocalFile::new);
    }

}
