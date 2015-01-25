package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Sprite {
    public static final int SQUARE_SIZE = GUI.SQUARE_SIZE;

    public static final ImageIcon unrevealed = new ImageIcon(new ImageIcon("sprites/unrevealed.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon revealed = new ImageIcon(new ImageIcon("sprites/revealed.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon exploded = new ImageIcon(new ImageIcon("sprites/exploded.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));

    private static final ImageIcon mine = new ImageIcon(new ImageIcon("sprites/mine.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon flag = new ImageIcon(new ImageIcon("sprites/flag.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));

    private static final ImageIcon nb1 = new ImageIcon(new ImageIcon("sprites/1.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb2 = new ImageIcon(new ImageIcon("sprites/2.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb3 = new ImageIcon(new ImageIcon("sprites/3.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb4 = new ImageIcon(new ImageIcon("sprites/4.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb5 = new ImageIcon(new ImageIcon("sprites/5.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb6 = new ImageIcon(new ImageIcon("sprites/6.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb7 = new ImageIcon(new ImageIcon("sprites/7.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private static final ImageIcon nb8 = new ImageIcon(new ImageIcon("sprites/8.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));

    public static ImageIcon getMine(boolean revealed) {
        if (revealed) return merge(new ArrayList<ImageIcon>(Arrays.asList(exploded, mine)));
        else return merge(new ArrayList<ImageIcon>(Arrays.asList(unrevealed, mine)));
    }

    public static ImageIcon getFlag() {
        return merge(new ArrayList<ImageIcon>(Arrays.asList(unrevealed, flag)));
    }

    public static ImageIcon getNumber(int number) {
        ImageIcon nb = null;
        switch (number) {
            case 1:
                nb = nb1;
                break;
            case 2:
                nb = nb2;
                break;
            case 3:
                nb = nb3;
                break;
            case 4:
                nb = nb4;
                break;
            case 5:
                nb = nb5;
                break;
            case 6:
                nb = nb6;
                break;
            case 7:
                nb = nb7;
                break;
            case 8:
                nb = nb8;
                break;
        }
        if (nb instanceof ImageIcon) return merge(new ArrayList<ImageIcon>(Arrays.asList(revealed, nb)));
        else return null;
    }

    /**
     * Merge the images listed in ArrayList, all with a 1.0 alpha transparency
     * @param images list of ImageIcons to merge
     * @return ImageIcon result of the merge
     */
    public static ImageIcon merge(ArrayList<ImageIcon> images) {
        ArrayList<Float> transparency = new ArrayList<Float>();

        for (ImageIcon i : images) {
            transparency.add(new Float(1.0));
        }

        return merge(images, transparency);
    }

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
