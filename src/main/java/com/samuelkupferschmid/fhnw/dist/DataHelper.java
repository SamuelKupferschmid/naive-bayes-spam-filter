package com.samuelkupferschmid.fhnw.dist;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by samue on 22.10.2016.
 */
public final class DataHelper {

    public static String[] getFileContents(String path) throws IOException, URISyntaxException {
        URI uri = Thread.currentThread().getContextClassLoader().getResource(path).toURI();
        File[] files = new File(uri).listFiles();

        String[] contents = new String[files.length];

        for(int i = 0;i< files.length;i++) {
            byte[] data = Files.readAllBytes(Paths.get(files[i].toURI()));
            contents[i] = new String(data, Charset.defaultCharset());
        }

        return contents;
    }
}
