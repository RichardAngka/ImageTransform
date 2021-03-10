import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.lang.Math;

public class cFrmUtama extends JFrame{
    BufferedImage image;
    Point TriangleSrc[] = new Point[3];
    Point TriangleDesc[] = new Point[3];
    int xAxis = 300, yAxis = 300;
    int dx, dy;
    private JPanel KeyForm;

    private void formKeyTyped(java.awt.event.KeyEvent e){
        if(e.getKeyChar() == 27) this.dispose();
    }

    private void Line(int x1, int x2, int y1, int y2, int color){
        int dx, dy, xLength, yLength, Count, i;
        double x, y, rx, ry;

        x=x1; y=y1;
        dx=x2-x1; dy=y2-y1;
        xLength = Math.abs(dx);
        yLength = Math.abs(dy);

        if (xLength > yLength){
            rx = 1;
            ry = Math.abs(yLength * 1.0/xLength);
            Count = xLength;
        }else{
            ry = 1;
            rx = Math.abs(xLength * 1.0/yLength);
            Count = yLength;
        }if (dx < 0){
            rx = -rx;
        }if (dy < 0){
            ry = -ry;
        }

        for (i = 0; i < Count; i++){
            image.setRGB((int)x, (int)y, color);
            x+=rx; y+=ry;
        }
    }
    public void drawPolygon(Point polygon[]){
        int i;
        for (i = 0; i < polygon.length-1; i++){
            Line(polygon[i].x, polygon[i+1].x, polygon[i].y, polygon[i+1].y, polygon[i].color);
        }
        Line(polygon[2].x, polygon[0].x, polygon[2].y, polygon[0].y, polygon[2].color);
    }

    public cFrmUtama() {
        int i;
        this.setSize(1920, 1080);
        addListener();
        image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        for (i = 0; i < TriangleSrc.length; i++) {
            TriangleSrc[i] = new Point();
            TriangleDesc[i] = new Point();
        }
        TriangleSrc[0].x = 500;
        TriangleSrc[0].y = 50;
        TriangleSrc[0].color = 0x00FFFF;
        TriangleSrc[1].x = 550;
        TriangleSrc[1].y = 100;
        TriangleSrc[1].color = 0x00FFFF;
        TriangleSrc[2].x = 450;
        TriangleSrc[2].y = 100;
        TriangleSrc[2].color = 0x00FFFF;
        drawPolygon(TriangleSrc);

        Line(xAxis, 0, xAxis, image.getHeight() - 1, 0x00FFFF);

        for (i = 0; i < TriangleSrc.length; i++) {
            dx = xAxis - TriangleSrc[i].x;
            TriangleDesc[i].x = xAxis + dx;
            TriangleDesc[i].y = TriangleSrc[i].y;
            TriangleDesc[i].color = 16777215 - TriangleSrc[i].color;
        }
        drawPolygon(TriangleDesc);

        Line(0,  image.getWidth()-i,yAxis, yAxis, 0x00FFFF);
        for (i = 0; i < TriangleSrc.length; i++){
            dy = yAxis - TriangleSrc[i].y;
            TriangleDesc[i].x = TriangleSrc[i].x;
            TriangleDesc[i].y = yAxis + dy;
            TriangleDesc[i].color= 16777215 - TriangleSrc[i].color;
        }
        drawPolygon(TriangleDesc);
    }
    private void addListener() {
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                formKeyTyped(e);
            }
        });
    }
    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 30, this);
        this.setTitle("Refleksi X dan Refleksi Y");
    }
}
