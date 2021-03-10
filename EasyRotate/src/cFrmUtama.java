import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.Math;

public class cFrmUtama extends JFrame {
    BufferedImage image, original;
    File f;
    int x, y, color;

    private JPanel KeyForm;

    private void formKeyTyped(java.awt.event.KeyEvent e){
        switch ((int)e.getKeyChar()){
            case 27: this.dispose(); break;
            case 49:
                image = new BufferedImage(original.getHeight(), original.getWidth(), BufferedImage.TYPE_INT_RGB);
                for (int y0 = 0; y0 < original.getHeight(); y0++){
                    x = original.getHeight()-1-y0;
                    for (int x0 =0; x0 < original.getWidth(); x0++){
                        y = x0;
                        color = original.getRGB(x0, y0);
                        image.setRGB(x, y, color);
                    }
                }
                repaint();
                break;
        }
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
    public cFrmUtama(){
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
    private void addListener(){
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                formKeyTyped(e);
            }
        });
    }

    public void paint(Graphics g){
        super.repaint();
        g.drawImage(image, 0, 0, this);
        this.setTitle("Easy Rotate");
    }
}
