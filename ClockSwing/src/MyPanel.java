import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Calendar;
import javax.swing.JPanel;
public class MyPanel extends JPanel implements Runnable{
	
	
	private static final long serialVersionUID = 1L;
	Thread t = new Thread(this);
	
	
	
	MyPanel(){
		setVisible(true);
		setSize(200, 200);
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.WHITE);
		t.start();
	}//constructor
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString(Calendar.getInstance().get(Calendar.SECOND) + "", 100, 100);
	}//paint
	
	

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