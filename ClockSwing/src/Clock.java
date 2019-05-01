import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Clock extends JFrame{
	
	
	
	private static final long serialVersionUID = 1L;
	MyPanel myPanel = new MyPanel();
	
	
	
	Clock(){
		super("My Clock");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		add(myPanel, BorderLayout.NORTH);
	}//constructor

	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Clock();
			}//Method Run
		}//Runnable
		);//SwingUtilities.invokeLater
	}//Method Main
	
	
	
}//Class Clock