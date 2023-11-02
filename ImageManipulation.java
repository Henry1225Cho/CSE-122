import java.util.*;
import java.awt.*;

// Yunho Cho
// TA: Ben Wang
// CSE 122 AF
// July 20 2023
// ImageManipulation
// This class is image manipulation. Where we can edit specific pixel's color and reflect them
// across y-axis. it also can create rectangle and inverse the color inside of the rectangle.
public class ImageManipulation {
    public static void main(String[] args) {
        Picture pic = new Picture("suzzallo.jpg");
        Color[][] pixels = pic.getPixels();

        // TODO: Apply filter from Task 1
        increaseColor(pixels);
        pic.setPixels(pixels);
        pic.save("creative1.jpg");

        // TODO: Apply filter from Task 2
        negative(pixels);
        pic.setPixels(pixels);
        pic.save("creative2.jpg");

        // TODO: Apply filter from Task 3
        negative(pixels);
        negativeRectangle(pixels, 150, 1050, 650, 1150);
        negativeRectangle(pixels, 150, 850, 650, 950);
        negativeRectangle(pixels, 150, 650, 650, 750);
        pic.setPixels(pixels);
        pic.save("creative3.jpg");

        // TODO: Apply filter from Task 4
        mirrorRight(pixels);

        // save completed image with all filters applied.
        pic.setPixels(pixels);
        pic.save("creative4.jpg");
    }

    // Increases each pixel's color of the whole image and turn the color's tone to purple.
    // no exception
    // no return
    // Parameter:
    //  - pixels: Color of red, green, and blue information of each pixel.
    public static void increaseColor(Color[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color originalColor = pixels[i][j];
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();
                
                Color newColor = new Color(Math.min(red + 63, 255), Math.min(green + 13, 255),
                                                                    Math.min(blue + 94, 255));
                pixels[i][j] = newColor;
            }
        }
    }

    // Invert each pixel's color of the whole image from originalcolor.
    // no exception
    // no return 
    // Parameter:
    //  - pixels: Color of red, green, and blue information of each pixel.
    public static void negative(Color[][] pixels) { 
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color originalColor = pixels[i][j];
                pixels[i][j] = fillPixel(originalColor);
            }
        }
    }

    // Creates shape of rectangle in given coordinates bound and inside of the 
    // rectangle it inverse each pixel's color by given coordinate bounds.
    // no exception
    // no return
    // Parameter:
    //  - pixels: Color of red, green, and blue information of each pixel.
    //    - row1: Given top coordinated of pixel
    //    - col1: Given left side coordinated of pixel
    //    - row2: Given bottom coordinated of pixel
    //    - col2: Given right side coordinated of pixel
    public static void negativeRectangle(Color[][] pixels, int row1, int col1, int row2, int col2) {
        for(int r = row1; r < row2; r++) {
            for(int c = col1; c < col2; c++) {
                Color originalColor = pixels[r][c];
                pixels[r][c] = fillPixel(originalColor);
            }
        }
    }

    // To inverse specific coordinates of the pixel's color
    // no exception
    //  Return:
    // - Color: inverse color of the speicific pixel's color location 
    //       Parameter:
    // - originalColor: Original color of the specific pixel's location
    public static Color fillPixel(Color originalColor) {
        int red = originalColor.getRed();
        int green = originalColor.getGreen();
        int blue = originalColor.getBlue();
        int r = 255 - red;
        int g = 255 - green;
        int b = 255 - blue;
        Color negativeColor = new Color(r, g, b);
        return negativeColor;
    }

    // Mirror reflects the right image across y-axis onto left side
    // no exception
    // no return 
    // Parameter:
    //  - pixels: Color of red, green, and blue information of each pixel.
    public static void mirrorRight(Color[][] pixels) {
        for(int r = 0; r < pixels.length; r++) {
            for(int c = 0; c < pixels[0].length/2; c++) {
                pixels[r][c] = pixels[r][pixels[0].length - 1 - c];
            }
        }
    }
}