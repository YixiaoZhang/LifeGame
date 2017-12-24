

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class LifeGame extends JFrame implements MouseMotionListener{
	private final World world;
	//JButton button = new JButton ("button");
	static JMenu location=new JMenu();
	public LifeGame(int rows,int columns)
	{
		world=new World(rows, columns);
		world.setBackground(Color.LIGHT_GRAY);
		new Thread(world).start();
		add(world);
	}
	public static void main(String[]args)
	{
		LifeGame frame=new LifeGame(40, 50);
		
		frame.addMouseMotionListener(frame);
		JMenuBar menu=new JMenuBar();
		frame.setJMenuBar(menu);
		
		
		JMenu options =new JMenu("Options");
		menu.add(options);
		JMenu changeSpeed=new JMenu("ChangeSpeed");
		menu.add(changeSpeed);
		JMenu other = new JMenu("Other");
		menu.add(other);
		
//		JMenuItem arrow=options.add("Arrow");
//		arrow.addActionListener(frame.new ArrowActionListener());
//		JMenuItem square=options.add("Square");
//		square.addActionListener(frame.new SquareActionListener());
		JMenuItem start=options.add("Start");
		start.addActionListener(frame.new StartActionListener());
		JMenuItem random=options.add("Random");
		random.addActionListener(frame.new RandomActionListener());
		
		JMenuItem stop=options.add("Stop");
		stop.addActionListener(frame.new StopActionListener());
		JMenuItem pause=options.add("Pause");
		pause.addActionListener(frame.new PauseActionListener());
		JMenuItem doityourself=options.add("Add");
		doityourself.addActionListener(frame.new DIYActionListener());
		JMenuItem clean=options.add("Kill");
		clean.addActionListener(frame.new CleanActionListener());
		
		JMenuItem slow=changeSpeed.add("Slow");
		slow.addActionListener(frame.new SlowActionListener());
		JMenuItem fast=changeSpeed.add("Fast");
		fast.addActionListener(frame.new FastActionListener());
		JMenuItem hyper=changeSpeed.add("Hyper");
		hyper.addActionListener(frame.new HyperActionListener());
		
		JMenuItem help=other.add("Help");
		help.addActionListener(frame.new HelpActionListener());
		
		JMenuItem about=other.add("About");
		about.addActionListener(frame.new AboutActionListener());
		//about.addActionListener(frame.new AboutActionListener());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1007,859);
		//frame.setSize(1207,859);
		//JButton button = new JButton ("button");
		//frame.button.setBounds(108,100,100,100);
		//frame.add(frame.button,BorderLayout.EAST);
		frame.setTitle("Game of Life");
		frame.setVisible(true);
		frame.setResizable(false);
	}
//	class ArrowActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			world.diy=false;
//			world.setArrow();
//		}
//	}
//	class SquareActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e) 
//		{
//			world.diy=false;
//			world.setSquare();
//		}
//	}
	class RandomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.diy=false;
			world.clean=false;
			world.setBackground(Color.LIGHT_GRAY);
			//world.setStop();
			world.setRandom();
		}
	}
	class StartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.begintime=System.currentTimeMillis();
			world.setBackground(Color.LIGHT_GRAY);
			world.diy=false;
			world.clean=false;
			world.setShape();
		}
	}
	class StopActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.time=0;
			world.setBackground(Color.LIGHT_GRAY);
			world.diy=false;
			world.clean=false;
			world.setStop();
		}
	}
	class PauseActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.LIGHT_GRAY);
			world.diy=false;
			world.clean=false;
			world.setPause();
		}
	}
	class SlowActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedSlow();
		}
	}
	class FastActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedFast();
		}
	}
	class HyperActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedHyper();
		}
	}
	class HelpActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "����������Ϸ!!!\n������Ϸ��Ӣ����ѧ��Լ�����ζ١�������1970�귢����ϸ���Զ���\n "+"1�����һ��ϸ����Χ��3��ϸ��Ϊ�������ϸ��Ϊ��;\n"
												+"2�� ���һ��ϸ����Χ��2��ϸ��Ϊ�������ϸ��������״̬���ֲ���;\n"
												+"3�� ����������£���ϸ��Ϊ����");
		}
	}
	class AboutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "��Ϸ���ߣ����㡢Ī���ѡ�֣����");
		}
	}
	class CleanActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.clean=true;
			world.diy=false;
			world.setBackground(Color.orange);
		}
	}
	class DIYActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.diy=true;
			world.clean=false;
			world.setBackground(Color.cyan);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.diy){
		int x=e.getX();
		int y=e.getY();
		//button.setText("x:"+x+"y:"+y);
		World.pauseshape[(y-50)/20][x/20]=1;
		world.setDiy();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.clean){
		int x=e.getX();
		int y=e.getY();
		//button.setText("x:"+x+"y:"+y);
		World.pauseshape[(y-50)/20][x/20]=0;
		world.setDiy();
		}
	}
}

