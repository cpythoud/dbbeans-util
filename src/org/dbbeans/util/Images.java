package org.dbbeans.util;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

/**
 * Contains some image manipulation functions.
 */
public class Images {

    private static final List<String> ALLOWED_IMAGE_FORMAT_EXTENSIONS = Arrays.asList("jpg", "png", "gif", "jpeg");

    /**
     * Reduces the width and height of an image. The function will do its best to preserve the aspect ratio
     * of the image given the height and width you set, which means the specified width and height might be altered.
     * @param input file containing the original image.
     * @param output file in which the resized image will be stored.
     * @param width, target width or 0 if height is set and you want to preserve the image aspect ratio.
     * @param height, target height or 0 if width is set and you want to preserve the image aspect ratio.
     * @throws IOException, if any file system operation fails.
     * @throws IllegalArgumentException if input or output File is null, if width or height is smaller than 0
     * or bigger than the original image width or height,
     * if the input file does not exist or cannot be read, if the input filename doesn't have a proper extension
     * for an image file (jpg, png, gif, jpeg).
     * @see Images#downsize(File, File, int, int, boolean)
     * @see Images#downsize(File, File, int, int, boolean, boolean)
     */
    public static void downsize(final File input, final File output, final int width, final int height) throws IOException {
        downsize(input, output, width, height, true, false);
    }

    /**
     * Reduces the width and height of an image.
     * @param input file containing the original image.
     * @param output file in which the resized image will be stored.
     * @param width, target width or 0 if height is set and you want to preserve the image aspect ratio.
     * @param height, target height or 0 if width is set and you want to preserve the image aspect ratio.
     * @param preserveAspectRatio, set this to true and the function will do its best to preserve the aspect ratio
     *                             of the image given the height and width you set, which means the specified
     *                             width and height might be altered.
     * @throws IOException, if any file system operation fails.
     * @throws IllegalArgumentException if input or output File is null, if width or height is smaller than 0
     * or bigger than the original image width or height,
     * if the input file does not exist or cannot be read, if the input filename doesn't have a proper extension
     * for an image file (jpg, png, gif, jpeg).
     * @see Images#downsize(File, File, int, int, boolean, boolean)
     */
    public static void downsize(final File input, final File output, final int width, final int height, final boolean preserveAspectRatio) throws IOException {
        downsize(input, output, width, height, preserveAspectRatio, false);
    }

    /**
     * Reduces the width and height of an image.
     * @param input file containing the original image.
     * @param output file in which the resized image will be stored.
     * @param width, target width or 0 if height is set and you want to preserve the image aspect ratio.
     * @param height, target height or 0 if width is set and you want to preserve the image aspect ratio.
     * @param preserveAspectRatio, set this to true and the function will do its best to preserve the aspect ratio
     *                             of the image given the height and width you set, which means the specified
     *                             width and height might be altered.
     * @param lenient, set this to true if you want the function to ignore attempts to make the image bigger than
     *                 the original and return the original image without modification, otherwise an
     *                 IllegalArgumentException will be thrown.
     * @throws IOException, if any file system operation fails.
     * @throws IllegalArgumentException if input or output File is null, if width or height is smaller than 0,
     * if the input file does not exist or cannot be read, if the input filename doesn't have a proper extension
     * for an image file (jpg, png, gif, jpeg).
     */
    public static void downsize(final File input, final File output, final int width, final int height, final boolean preserveAspectRatio, final boolean lenient) throws IOException {
        if (input == null)
            throw new NullPointerException("Input file cannot be null.");
        if (output == null)
            throw new NullPointerException("Output file cannot be null.");
        if (width < 0)
            throw new IllegalArgumentException("Width must be positive or 0.");
        if (height < 0)
            throw new IllegalArgumentException("Height must be positive or 0.");
        if (width == 0 && height == 0)
            throw new IllegalArgumentException("Width and Height cannot both be equal to zero.");

        if (!input.exists())
            throw new IllegalArgumentException("File " + input.getCanonicalPath() + " does not exist.");
        if (!input.canRead())
            throw new IllegalArgumentException("File " + input.getCanonicalPath() + " cannot be read.");

        if (!Files.isFileExtensionOK(input, ALLOWED_IMAGE_FORMAT_EXTENSIONS))
            throw new IllegalArgumentException("File " + input.getCanonicalPath() + " is not in a supported format.");
        final String extension = Files.getExtension(input);

        final BufferedImage original = ImageIO.read(input);

        int w = width;
        int h = height;

        if (w == 0) {
            final double ratio = (double) height / (double) original.getHeight();
            w = (int) (original.getWidth() * ratio);
        }

        if (h == 0) {
            final double ratio = (double) width / (double) original.getWidth();
            h = (int) (original.getHeight() * ratio);
        }

        if (preserveAspectRatio) {
            final double originalAspectRatio = (double) original.getWidth() / (double) original.getHeight();
            final double targetAspectRatio = (double) w / (double) h;

            if (targetAspectRatio < originalAspectRatio)
                h = (int) (w / originalAspectRatio);
            else
                w = (int) (h * originalAspectRatio);
        }

        if (original.getWidth() < w || original.getHeight() < h) {
            if (lenient) {
                w = original.getWidth();
                h = original.getHeight();
            } else {
                throw new IllegalArgumentException("Images.downsize() cannot be used to enlarge an image.");
            }
        }

        final BufferedImage destination = getScaledInstance(original, w, h);
        ImageIO.write(destination, extension.toLowerCase(), output);
    }

    /**
     * Potentially reduce the size of an image file by reading it into memory and rewriting it to disk without
     * alterations.
     * @param input, file containing the original image.
     * @param output, file in which the optimised image will be stored.
     * @throws IOException, if any file system operation fails.
     */
    public static void optimize(final File input, final File output) throws IOException {
        if (input == null)
            throw new NullPointerException("Input file cannot be null.");
        if (output == null)
            throw new NullPointerException("Output file cannot be null.");

        if (!input.exists())
            throw new IllegalArgumentException("File " + input.getCanonicalPath() + " does not exist.");
        if (!input.canRead())
            throw new IllegalArgumentException("File " + input.getCanonicalPath() + " cannot be read.");

        if (!Files.isFileExtensionOK(input, ALLOWED_IMAGE_FORMAT_EXTENSIONS))
            throw new IllegalArgumentException("File " + input.getCanonicalPath() + " is not in a supported format.");
        final String extension = Files.getExtension(input);

        BufferedImage img = ImageIO.read(input);
        ImageIO.write(img, extension.toLowerCase(), output);
    }

    /**
     * Get the height of an image contained in a file.
     * @param image, file containing the image.
     * @return height of the image.
     */
    public static int getHeight(final File image) {
        if (image == null)
            throw new NullPointerException("Image file cannot be null.");

        BufferedImage img;
        try {
            if (!image.exists())
                throw new IllegalArgumentException("File " + image.getCanonicalPath() + " does not exist.");
            if (!image.canRead())
                throw new IllegalArgumentException("File " + image.getCanonicalPath() + " cannot be read.");

            if (!Files.isFileExtensionOK(image, ALLOWED_IMAGE_FORMAT_EXTENSIONS))
                throw new IllegalArgumentException("File " + image.getCanonicalPath() + " is not in a supported format.");

            img = ImageIO.read(image);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }

        return img.getHeight();
    }

    /**
     * Get the width of an image contained in a file.
     * @param image, file containing the image.
     * @return width of the image.
     */
    public static int getWidth(final File image) {
        if (image == null)
            throw new NullPointerException("Image file cannot be null.");

        BufferedImage img;
        try {
            if (!image.exists())
                throw new IllegalArgumentException("File " + image.getCanonicalPath() + " does not exist.");
            if (!image.canRead())
                throw new IllegalArgumentException("File " + image.getCanonicalPath() + " cannot be read.");

            if (!Files.isFileExtensionOK(image, ALLOWED_IMAGE_FORMAT_EXTENSIONS))
                throw new IllegalArgumentException("File " + image.getCanonicalPath() + " is not in a supported format.");

            img = ImageIO.read(image);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }

        return img.getWidth();
    }

    /**
     * Check if the extension of an image file is allowed. Allowed extensions are: jpg, png, gif, jpeg.
     * @param image, file which extension needs to be checked.
     * @return true if the file is accessible and its extension is one of the allowed extension, false otherwise.
     */
    public static boolean isImageFileExtensionOK(final File image) {
        if (image.exists()) {
            if (image.canRead()) {
                if (Files.isFileExtensionOK(image, ALLOWED_IMAGE_FORMAT_EXTENSIONS)) {
                    return true;
                }
            }
        }

        return false;
    }


    // I got some of the code below from the internet long ago and tweaked some of it.
    // I cannot find the source anymore. If anyone can enlighten me so that I can give proper attribution to
    // its original author, it would be great.
    private static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight) {
        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = img;
        int w = img.getWidth();
        int h = img.getHeight();

        do {
            if (w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }
}
