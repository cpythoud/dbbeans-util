package org.dbbeans.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Contains some trivial static functions related to file and filename processing.
 */
public class Files {

    /**
     * Checks if a file as an extension, as evidenced by a dot character in filename that is neither the first
     * nor the last filename character.
     * @param filename to check.
     * @return true if filename has an extension, false otherwise.
     * @see Files#hasExtension(File)
     */
    public static boolean hasExtension(final String filename) {
        final int lastDotPosition = filename.indexOf(".");

        if (lastDotPosition < 1)
            return false;

        final String charBefore = filename.substring(lastDotPosition - 1, lastDotPosition);
        return !(charBefore.equals("/") || charBefore.equals("\\"));
    }

    /**
     * Checks if a file as an extension, as evidenced by a dot character in filename that is neither the first
     * nor the last filename character.
     * @param file to check.
     * @return true if the corresponding filename has an extension, false otherwise.
     * @see Files#hasExtension(String)
     */
    public static boolean hasExtension(final File file) {
        return hasExtension(file.getName());
    }

    /**
     * Returns the extension of a filename.
     * @param filename which extension we are interested in.
     * @return filename's extension.
     * @throws java.lang.IllegalArgumentException if the filename has no extension
     * @see Files#hasExtension(String)
     * @see Files#getExtension(File)
     */
    public static String getExtension(final String filename) {
        if (!hasExtension(filename))
            throw new IllegalArgumentException("File has no extension");

        return filename.substring(filename.lastIndexOf(".") + 1, filename.length());
    }

    /**
     * Returns the extension of a filename.
     * @param file which extension we are interested in.
     * @return file's extension.
     * @throws java.lang.IllegalArgumentException if the filename has no extension
     * @see Files#hasExtension(File)
     * @see Files#getExtension(String)
     */
    public static String getExtension(final File file) {
        return getExtension(file.getName());
    }

    /**
     * Checks a filename extension against a list of acceptable extensions.
     * @param filename to be checked.
     * @param extensions, list of acceptable extensions.
     * @return true if filename has an extension and this extension match at least one of the allowed extensions.
     * @see Files#isFileExtensionOK(File, List)
     */
    public static boolean isFileExtensionOK(final String filename, final List<String> extensions) {
        if (!hasExtension(filename))
            return false;

        final String extension = getExtension(filename).toLowerCase();

        return extensions.contains(extension);
    }

    /**
     * Checks a file's extension against a list of acceptable extensions.
     * @param file to be checked.
     * @param extensions, list of acceptable extensions.
     * @return true if the file has an extension and this extension match at least one of the allowed extensions.
     * @see Files#isFileExtensionOK(String, List)
     */
    public static boolean isFileExtensionOK(final File file, final List<String> extensions) {
        return isFileExtensionOK(file.getName(), extensions);
    }

    /**
     * This function take a filename and make sure the path information is not included. This function is used
     * to mitigate a bug/feature (?) of Windows shares where the whole path of the file is returned when File.getName()
     * is called.
     * @param filename to be sanitized.
     * @return the filename without any path information.
     */
    public static String sanitizeFilename(final String filename) {
        int index = filename.lastIndexOf("\\");

        if (index == -1)
            index = filename.lastIndexOf("/");

        if (index == -1)
            return filename;

        final int length = filename.length();
        if (index == length - 1)
            throw new IllegalArgumentException("Filename is a directory name");

        return filename.substring(index + 1, length);
    }

    /**
     * Read the content of a text file into a String
     * @param file to be read
     * @return text content of the file in a String
     */
    public static String read(File file) {
        final StringBuffer buffer = new StringBuffer();
        char[] buf = new char[1024];
        int numRead=0;

        try {
            final BufferedReader reader = new BufferedReader(new FileReader(file));

            while((numRead = reader.read(buf)) != -1){
                final String readData = String.valueOf(buf, 0, numRead);
                buffer.append(readData);
            }

            reader.close();
        } catch (final IOException ioex) {
            throw new RuntimeException(ioex);
        }

        return buffer.toString();
    }

    /**
     * Use a String to create a text file
     * @param s text to be written to file.
     * @param file into which the text is to be written
     */
    public static void write(final String s, final File file) {
        try {
            final PrintWriter out = new PrintWriter(file);
            out.println(s);
            out.close();
        } catch (final FileNotFoundException fnfex) {
            throw new RuntimeException(fnfex);
        }
    }
}
