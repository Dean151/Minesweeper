package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Sprite {
    public static final int SQUARE_SIZE = GUI.SQUARE_SIZE;

    public static final ImageIcon unrevealed = new ImageIcon(new ImageIcon("sprites/unrevealed.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));

    private final ImageIcon revealed = new ImageIcon(new ImageIcon("sprites/revealed.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon exploded = new ImageIcon(new ImageIcon("sprites/exploded.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon validated = new ImageIcon(new ImageIcon("sprites/validated.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));

    private final ImageIcon mine = new ImageIcon(new ImageIcon("sprites/mine.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon flag = new ImageIcon(new ImageIcon("sprites/flag.png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH));

    private ImageIcon[] numbers = new ImageIcon[9];
    private ImageIcon[] mines = new ImageIcon[3];
    private ImageIcon[] flags = new ImageIcon[3];

    private ImageIcon[] counter = new ImageIcon[10];

    public Sprite() {
        // Creating all the sprites once for all

        // Creating revealed numbers
        numbers[0] = revealed;
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = merge(new ArrayList<ImageIcon>(Arrays.asList(revealed, new ImageIcon(new ImageIcon("sprites/"+String.valueOf(i)+".png").getImage().getScaledInstance(SQUARE_SIZE,SQUARE_SIZE, Image.SCALE_SMOOTH)))));
        }

        // Creating mines and flags icons
        ImageIcon[] backgrounds = {unrevealed, validated, exploded};
        for (int i = 0; i < backgrounds.length; i++) {
            ImageIcon back = backgrounds[i];
            mines[i] = merge(new ArrayList<ImageIcon>(Arrays.asList(back, mine)));
            flags[i] = merge(new ArrayList<ImageIcon>(Arrays.asList(back, flag)));
        }

        // Creating counter images
        for (int i = 0; i < counter.length; i++) {
            counter[i] = new ImageIcon("sprites/counter/"+String.valueOf(i)+".png");
        }
    }

    public ImageIcon getNumber(int number) {
        return numbers[number];
    }

    public ImageIcon getMine() {
        return mines[0];
    }

    public ImageIcon getMine(boolean revealed) {
        if (revealed) return mines[2]; // Red feedback
        else return getMine();
    }

    public ImageIcon getFlag() {
        return flags[0];
    }

    public ImageIcon getFlag(boolean wasMine) {
        if (wasMine) return flags[1]; // Green feedback
        else return flags[2]; // Red feedback
    }

    public ImageIcon getCounter(int number) {
        return counter[number];
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
