import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class MyPanel extends JPanel implements Runnable{
	
	
	private static final long serialVersionUID = 1L;
	Thread t = new Thread(this);
	public static final int[] centerpoint = {400,400};
	BufferedImage clockbg;
	
	
	
	MyPanel(){
		setVisible(true);
		setPreferredSize(new Dimension(800, 800));
		setBackground(Color.WHITE);
		try {
			clockbg = ImageIO.read(new File("res/clock.png"));
		} catch (Exception e) {
			
		}
		t.start();
		
	}//constructor
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		double[] secXY = getSecXY();
		double[] minXY = getMinXY();
		double[] hourXY = getHourXY();
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform origXForm = g2d.getTransform();
		AffineTransform newXForm = (AffineTransform)(origXForm.clone());
		int xRot = this.getWidth() / 2;
	    int yRot = this.getHeight() / 2;
	    newXForm.rotate(Calendar.getInstance().get(Calendar.SECOND), xRot, yRot);
	    g2d.setTransform(newXForm);
	    int x = (getWidth() - clockbg.getWidth(this)) / 2;
	    int y = (getHeight() - clockbg.getHeight(this)) / 2;
	    g2d.drawImage(clockbg, x, y, this);
	    g2d.setTransform(origXForm);
	    
		g.drawLine(centerpoint[0], centerpoint[1], (int)secXY[0], (int)secXY[1]);
		g.drawLine(centerpoint[0], centerpoint[1], (int)minXY[0], (int)minXY[1]);
		g.drawLine(centerpoint[0], centerpoint[1], (int)hourXY[0], (int)hourXY[1]);
		System.out.println(Calendar.getInstance().getTime());
	}//paint
	
	
	
	private double[] getSecXY() {
		double[] secXY = new double[2];
		double cal = (double)Calendar.getInstance().get(Calendar.SECOND);
		double rad = (cal-15)/60*Math.PI*2;
		secXY[0] = Math.cos(rad)*350+400;
		secXY[1] = Math.sin(rad)*350+400;
		return secXY;
	}
	
	private double[] getMinXY() {
		double[] minXY = new double[2];
		double cal = (double)Calendar.getInstance().get(Calendar.MINUTE);
		double rad = (cal-15)/60*Math.PI*2;
		minXY[0] = Math.cos(rad)*400+400;
		minXY[1] = Math.sin(rad)*400+400;
		return minXY;
	}
	
	
	
	private double[] getHourXY() {
		double[] hourXY = new double[2];
		double calm = (double)Calendar.getInstance().get(Calendar.MINUTE);
		double calh = (double)Calendar.getInstance().get(Calendar.HOUR);
		double rad = ((calh*60+calm)-180)/720*Math.PI*2;
		hourXY[0] = Math.cos(rad)*200+400;
		hourXY[1] = Math.sin(rad)*200+400;
		return hourXY;
	}
	
	

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(500);
			}//try
			catch (InterruptedException e) {
				e.printStackTrace();
			}//catch
		}//while
	}//run
	
	
	
}//class MyPanel