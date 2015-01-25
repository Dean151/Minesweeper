package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
    /**
     * Merge the images listed in ArrayList, with the corresponding alpha transparency
     * @param images list of ImageIcons to merge
     * @param transparency list of alpha transparency
     * @return ImageIcon result of the merge
     */
    public static ImageIcon merge(ArrayList<ImageIcon> images, ArrayList<Float> transparency)
    {
        BufferedImage dest = null;
        Graphics2D destG = null;
        int rule; // This is SRC for the top image, and DST_OVER for the other ones
        float alpha;

        for (int i = 0, size = images.size(); i < size; i++)
        {
            Image image = images.get(i).getImage();

            rule = AlphaComposite.SRC_OVER; // Default value
            alpha = transparency.get(i);

            if (i == 0)
            {
                dest = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                destG = dest.createGraphics();

                rule = AlphaComposite.SRC; // Rule for 1st image
            }
            destG.setComposite(AlphaComposite.getInstance(rule, alpha));
            destG.drawImage(image, 0, 0, null);
        }

        return new ImageIcon(dest);
    }
}
