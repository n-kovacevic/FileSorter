package com.nkovacevic.filesorter.core;

import java.io.IOException;
import java.nio.file.Path;

public interface File {

    String getFileName();

    String getBaseName();

    String getExtension();

    Path download(Path destination) throws IOException;

}
