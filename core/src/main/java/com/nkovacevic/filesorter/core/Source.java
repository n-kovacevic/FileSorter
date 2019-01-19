package com.nkovacevic.filesorter.core;

import java.io.IOException;
import java.util.stream.Stream;

public interface Source {

    Stream<File> getFilesFromSource() throws IOException;

}
