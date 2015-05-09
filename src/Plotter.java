import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Plotter extends JFrame {

	private static final long serialVersionUID = 1L;

	double	x1=-1.5,y1=-1.5, x2=1.5, y2=1.5;
	
	
	Plotter(int w, int h, final Complex a, final double x1, final double y1, final double x2, final double y2) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(false);
		setLocationByPlatform(true);
		setSize(w, h);
		setResizable(false);
		setTitle(a.re() +"+"+ a.im()+"i ("+x1+","+y1+")("+x2+","+y2+") Julia Set");

		
		
		
		BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = bufferedImage.createGraphics();
	    
		
		for (int x = 0; x < getWidth(); x++)
			for (int y = 0; y < getHeight(); y++) {
				Complex c = new Complex((x * (x2 - x1)) / ((double) getWidth()) + x1, (y * (y2 - y1)) / ((double) getHeight()) + y1);
				
				int d = convergence(c, a, 255);

				g2d.setColor(new Color(255-d, 255-d, 255-d));
				g2d.drawRect(x, y, 1, 1);

			}
		
		try {
			File f = new File(a.re()+"+"+a.im()+"i ("+x1+","+y1+")("+x2+","+y2+").png");
			ImageIO.write(bufferedImage, "png", f);
			System.out.println(f.getName()+"written");
		} catch (IOException e) {
			System.out.println("Error writing image.");
		}
		
		
		/*getContentPane().add(new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				
				BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			    Graphics2D g2d = bufferedImage.createGraphics();
			    
				
				((Graphics2D) g).setColor(Color.BLUE);
				for (int x = 0; x < getWidth(); x++)
					for (int y = 0; y < getHeight(); y++) {
						Complex c = new Complex((x * (x2 - x1)) / ((double) getWidth()) + x1, (y * (y2 - y1)) / ((double) getHeight()) + y1);
						
						int d = convergence(c, a, 255);

						((Graphics2D) g).setColor(new Color(255-d, 255-d, 255-d));
						((Graphics2D) g).drawRect(x, y, 1, 1);
						
						g2d.setColor(new Color(255-d, 255-d, 255-d));
						g2d.drawRect(x, y, 1, 1);

					}
			}
			
			
		});*/
		
		
		
		/*JFrame settings = new JFrame();
		settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settings.setAlwaysOnTop(false);
		settings.setLocationByPlatform(true);
		settings.setSize(240, 200);
		settings.setLayout(null);
		
		JButton bUp = new JButton("up");
		bUp.setBounds(70, 10, 100, bUp.getPreferredSize().height);
		bUp.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				y1-=0.1*(y2-y1);
				y2-=0.1*(y2-y1);
				repaint();
			}
		
		});
		settings.getContentPane().add(bUp);
		
		JButton bDown = new JButton("down");
		bDown.setBounds(70, 70, 100, bUp.getPreferredSize().height);
		bDown.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				y1+=0.1*(y2-y1);
				y2+=0.1*(y2-y1);
				repaint();
			}
		
		});
		settings.getContentPane().add(bDown);
		

		JButton bLeft = new JButton("left");
		bLeft.setBounds(10, 40, 100, bLeft.getPreferredSize().height);
		bLeft.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				x1-=0.1*(x2-x1);
				x2-=0.1*(x2-x1);
				repaint();
			}
		
		});
		settings.getContentPane().add(bLeft);
		
		
		JButton bRight = new JButton("right");
		bRight.setBounds(130, 40, 100, bRight.getPreferredSize().height);
		bRight.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				x1+=0.1*(x2-x1);
				x2+=0.1*(x2-x1);
				repaint();
			}
		
		});
		settings.getContentPane().add(bRight);
		

		JButton bPlus = new JButton("+");
		bPlus.setBounds(20, 100, 100, bPlus.getPreferredSize().height);
		bPlus.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				x1*=0.5;
				x2*=0.5;
				y1*=0.5;
				y2*=0.5;
				repaint();
			}
		
		});
		settings.getContentPane().add(bPlus);
		

		JButton bMinus = new JButton("-");
		bMinus.setBounds(120, 100, 100, bMinus.getPreferredSize().height);
		bMinus.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				x1/=0.5;
				x2/=0.5;
				y1/=0.5;
				y2/=0.5;
				repaint();
			}
		
		});
		settings.getContentPane().add(bMinus);
		settings.setVisible(true);
		
		*/
		//setVisible(true);
	}

	
	int convergence(Complex x, Complex a, int d) {
		for (int i = 0; i < d; i++) {
			x = x.times(x).plus(a);
			if (x.abs() > 1000)
				return i;
		}
		return d;
	}
	
	void setScope(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		repaint();
	}
	
	public static void main(String args[]) throws IOException{
		
		if(args.length==6)
			new Plotter(500, 500,
	    	  		new Complex(new Double(args[0]), new Double(args[1])),
	    	  		new Double(args[2]),new Double(args[3]),new Double(args[4]),new Double(args[5]));
		else
			System.out.println("Usage: a b x1 y1 x2 y2");
		//new Plotter(500,500);
	}
	
}
