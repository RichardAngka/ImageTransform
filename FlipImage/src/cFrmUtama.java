import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.Math;

public class cFrmUtama extends JFrame{
    BufferedImage image, original;
    File f;
    int x, y, z, color;
    private JPanel FormTyped;

    private void formKeyTyped(java.awt.event.KeyEvent e){
        if (e.getKeyChar() == 27) this.dispose();
    }

    private void restoreOriginal(){
        image = new BufferedImage(1920, 1080,BufferedImage.TYPE_INT_RGB);
        for (y = 0; y < original.getHeight(); y++){
            for (x = 0; x < original.getWidth()-1; x++){
                color = original.getRGB(x, y);
                image.setRGB(x, y, color);
            }
        }
    }
    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        switch(evt.getButton()){
            case MouseEvent.BUTTON1:
                for (int y = 0; y < original.getHeight(); y++){
                    z = original.getWidth() - 1;
                    for (int x = 0; x < original.getWidth(); x++){
                        color = original.getRGB(x,y);
                        image.setRGB(z, y, color);
                        z--;
                    }
                }
                repaint();
                break;
            case MouseEvent.BUTTON3:
                for (int x = 0; x < original.getWidth(); x++){
                    z = original.getHeight() - 1;
                    for (int y = 0; y < original.getHeight(); y++){
                        color = original.getRGB(x,y);
                        image.setRGB(x, z, color);
                        z--;
                    }
                }

                repaint();
                break;
            case MouseEvent.BUTTON2:
                restoreOriginal();
                repaint();
                break;
        }
    }
    public cFrmUtama(){
        FormTyped.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        this.setSize(1920, 1080);
        addListener();
        original = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        f = new File("D:\\Semester5\\Grafika_Komputer\\Tugas8\\bliss.bmp");
        try {
            original = ImageIO.read(f);
        } catch (IOException ex) {
        }
        restoreOriginal();
    }

    private void addListener() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
    }
    public void paint(Graphics g){
        super.repaint();
        g.drawImage(image, 0, 0, this);
        this.setTitle("Easy Rotate");
    }

}
