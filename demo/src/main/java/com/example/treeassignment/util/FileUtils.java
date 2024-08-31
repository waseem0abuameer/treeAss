package com.example.treeassignment.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * file utilities.
 *
 * @author Waseem abuameer
 */
public final class FileUtils {

    /**
     * constructor.
     */
    private FileUtils() {
    }

    /**
     * read a file content under project resource folder.
     *
     * @param resourceFileName resource file name.
     * @return file content as string.
     * @throws IOException if an I/O error occurs while reading from the stream.
     */
    public static String readResourceFile(final String resourceFileName) throws IOException {

        ClassPathResource cpr = new ClassPathResource(resourceFileName);
        byte[] data = FileCopyUtils.copyToByteArray(cpr.getInputStream());

        return new String(data, StandardCharsets.UTF_8);
    }
}