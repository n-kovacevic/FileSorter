package com.nkovacevic.filesorter.core;

import java.io.IOException;

public interface Destination {

    void upload(File file, String location) throws IOException;

}
