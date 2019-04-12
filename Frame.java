package com.csc.TrainStation;



import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Frame implements ActionListener{

	public static JLabel station1Fig,station2Fig,station3Fig,station4Fig;
	public  JPanel panel;	
	public  JLabel lblOut,lblStation,lblStation_1,lblStation_2,lblStation_3,lblTrain,lblTrain_2,lblTrain_3,lblStatus,lblPeople,lblLocation;
	public  JLabel train1Location,train2Location,train3Location;
	public JLabel station1Status;
	public  JLabel station2Status;
	public  JLabel station3Status;
	public JLabel station4Status;
	public  JLabel train1Status;
	public  JLabel train2Status;
	public  JLabel train3Status;
	public  JLabel station1People,station2People,station3People,station4People,train1People,train2People,train3People;
	public  ImageIcon redIcon, greenIcon, yellowIcon;
	public  JLabel outStation1,outStation2,outStation3,outStation4;
	public JButton btnStartStation,btnStartStation_1,btnStartStation_2;
	
    public void start(){
        JFrame frame = new JFrame("Train Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1125, 760);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
       
        frame.add(new TrainCanvas());
  
        frame.setVisible(true);        
		BufferedImage img = null;
		try {
		    img =(BufferedImage) ImageIO.read(new File("C:\\Users\\Lucas\\Desktop\\Last Semester USA\\Parallel\\Projet1\\Project1\\ranger-station-icon-1005072354.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}		
		
		
	    station1Fig = new JLabel("1", SwingConstants.LEFT);
		station1Fig.setBounds(482, 11, 124, 60);
		Image dimg =  img.getScaledInstance(60, 69, BufferedImage.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		station1Fig.setIcon(imageIcon);				
		station2Fig = new JLabel("3", SwingConstants.LEFT);
		station2Fig.setBounds(10, 632, 192, 60);
		station2Fig.setIcon(imageIcon);
		station3Fig = new JLabel("2", SwingConstants.LEFT);
		station3Fig.setBounds(513, 632, 129, 60);
		station3Fig.setIcon(imageIcon);
		/*station4Fig = new JLabel("Station4", SwingConstants.LEFT);
		station4Fig.setBounds(10, 11, 124, 60);
		station4Fig.setIcon(imageIcon);*/
		 
		frame.add(station1Fig);
		frame.add(station2Fig);
		frame.add(station3Fig);
	//	frame.add(station4Fig);
			 
		panel = new JPanel();
		panel.setBounds(687, 0, 397, 710);
		frame.add(panel);
		panel.setLayout(null);			 
		lblStation = new JLabel("Station 1");
		lblStation.setBounds(650, 77, 66, 14);
		panel.add(lblStation);
		
		lblStation_1 = new JLabel("Station 2");
		lblStation_1.setBounds(650, 128, 66, 14);
		panel.add(lblStation_1);
			 
		lblStation_2 = new JLabel("Station 3");
		lblStation_2.setBounds(650, 186, 96, 14);
		panel.add(lblStation_2);
			 
		/*lblStation_3 = new JLabel("Station 4");
		lblStation_3.setBounds(650, 248, 96, 14);
	    panel.add(lblStation_3);*/
		 
	    lblTrain = new JLabel("Train 1");
     	lblTrain.setBounds(650, 322, 66, 14);
	  	panel.add(lblTrain);
			 
	  	lblTrain_2 = new JLabel("Train 2");
		lblTrain_2.setBounds(650, 383, 46, 14);
		panel.add(lblTrain_2);
			 
	/*	lblTrain_3 = new JLabel("Train 3");
		lblTrain_3.setBounds(650, 447, 46, 14);
		panel.add(lblTrain_3);*/
			 
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(720, 11, 46, 14);
		panel.add(lblStatus);
			 
		lblPeople = new JLabel("People");
		lblPeople.setBounds(820, 11, 46, 14);
	    panel.add(lblPeople);
			 
		lblLocation = new JLabel("Location");
		lblLocation.setBounds(920, 11, 66, 14);
		panel.add(lblLocation);
		
		lblOut = new JLabel("Out");
		lblOut.setBounds(1020, 11, 66, 14);
		panel.add(lblOut);
			 
		train1Location = new JLabel("");
		train1Location.setBounds(920, 322, 66, 14);
	    panel.add(train1Location);
			 
		train2Location = new JLabel("");
		train2Location.setBounds(920, 383, 66, 14);
		panel.add(train2Location);
			 
		train3Location = new JLabel("");
		train3Location.setBounds(920, 447, 66, 14);
		panel.add(train3Location);
			 
		
			 
		station1Status = new JLabel("");
		station1Status.setBounds(720, 77, 76, 14);
		
		panel.add(station1Status);
			 
		station2Status = new JLabel("");
		station2Status.setBounds(720, 128, 76, 14);
		panel.add(station2Status);
			 
		station3Status = new JLabel("");
		station3Status.setBounds(720, 186, 79, 14);
		panel.add(station3Status);
			 
	    station4Status = new JLabel("");
	    station4Status.setBounds(720, 248, 79, 14);
		panel.add(station4Status);
			 
		train1Status = new JLabel("");
		train1Status.setBounds(720, 322, 84, 14);
		panel.add(train1Status);
			 
		train2Status = new JLabel("");
		train2Status.setBounds(720, 383, 79, 14);
		panel.add(train2Status);
			 
		train3Status = new JLabel("");
		train3Status.setBounds(720, 447, 79, 14);
		panel.add(train3Status);
			 
		station1People = new JLabel("");
		station1People.setBounds(820, 77, 75, 14);
		panel.add(station1People);
			 
		station2People = new JLabel("");
		station2People.setBounds(820, 128, 75, 14);
		panel.add(station2People);
			 
		station3People = new JLabel("");
		station3People.setBounds(820, 186, 75, 14);
		panel.add(station3People);
			 
		station4People = new JLabel("");
		station4People.setBounds(820, 248, 75, 14);
		panel.add(station4People);
			 
		train1People = new JLabel("");
		train1People.setBounds(820, 322, 75, 14);
		panel.add(train1People);
			 
		train2People = new JLabel("");
		train2People.setBounds(820, 383, 75, 14);
		panel.add(train2People);
			 
		train3People = new JLabel("");
		train3People.setBounds(820, 447, 75, 14);
		panel.add(train3People);
		
		outStation1 = new JLabel("");
		outStation1.setBounds(1020, 77, 75, 14);
		panel.add(outStation1);
		
		outStation2 = new JLabel("");
		outStation2.setBounds(1020, 128, 75, 14);
		panel.add(outStation2);
		
		outStation3 = new JLabel("");
		outStation3.setBounds(1020, 186, 75, 14);
		panel.add(outStation3);
		
		outStation4 = new JLabel("");
		outStation4.setBounds(1020, 248, 75, 14);
		panel.add(outStation4);
		panel.setBackground(Color.WHITE);
		
		 btnStartStation = new JButton("Start Station 1");
		 btnStartStation.setBounds(720, 557, 134, 23);
		 panel.add(btnStartStation);
		 btnStartStation.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	              Train.startStation1();
	            }
	        });      
 
		 btnStartStation_1 = new JButton("Start Station2");
		 btnStartStation_1.setBounds(920, 557, 126, 23);
		 panel.add(btnStartStation_1);
		 btnStartStation_1.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	              Train.startStation2();
	            }
	        });   
		  btnStartStation_2 = new JButton("Start Station3");
		  btnStartStation_2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		 Train.startStation3();
		 	}
		 });
		  btnStartStation_2.setBounds(820, 611, 126, 23);
		 panel.add(btnStartStation_2);
		 btnStartStation_2.setEnabled(false);
		 btnStartStation_1.setEnabled(false);
		 btnStartStation.setEnabled(false);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}

class TrainCanvas extends JComponent {

	
    private static int lastX = 430;
    private static int lastY = 15;
    private static int trainSpeedX = 0;
    private static int trainSpeedY=0;
    private static int lastX2 = 430;
    private static int lastY2 = 15;
    private static int flag;
    private static int trainSpeedX2 = 0;
    private static int trainSpeedY2=0;
    private static int flag2=0;
    public TrainCanvas() {
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    try {Thread.sleep(20);} catch (Exception ex) {}
                }
            }
        });

        animationThread.start();

        
    }
    public static void directions(int x,int y,int speedX,int speedY,int x2, int y2, int speedX2, int speedY2,int flag2){
    	if(flag2 ==1){
    		flag = flag2;
    		lastX=x;
    		lastY=y;
    		trainSpeedX = speedX;
    		trainSpeedY = speedY;
    		
    	}else{
    	
		lastX2=x2;
		lastY2=y2;
		trainSpeedX2=speedX2;
		trainSpeedY2=speedY2;
    	}
		System.out.println(Thread.currentThread().isAlive());
		
	}
    public void paintComponent(Graphics g) {
    	
    	BufferedImage img = null;
    	BufferedImage img2 = null;
    	try
    	{
    	    img = ImageIO.read( new File("train-5-xxl.png" ));
    	    img2 = ImageIO.read(new File("train-5-xxl.png"));
    	}
    	catch ( IOException exc )
    	{
    	    //v
    	}
    
        Graphics2D gg = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        int trainW = 500;
        int trainH = 10;
       
        int x = lastX +trainSpeedX;
        int x2= lastX2 + trainSpeedX2;
        int y = lastY+trainSpeedY ;
        int y2 = lastY2+trainSpeedY2;
      
    
        
        if(y == 638){        
        	trainSpeedY=0;        
        }
        if(y2 == 638){
        	trainSpeedY2=0;
        }
        if(x == 115){        
        	trainSpeedX=0;        	
        }
        if(x2 == 115){
        	trainSpeedX2=0;
        }
      
        
        if(x== 449  || x== 442 ||  x== 437){        
        	trainSpeedX=0;        	
        	trainSpeedY=0;        	
        }
   
        
        if(x2==449 || x==442 || x == 437){
        	trainSpeedY2=0;
        	trainSpeedX2=0;
        }

        Image dimg =  img.getScaledInstance(60, 69, BufferedImage.SCALE_SMOOTH);
		Image dimg2 = img2.getScaledInstance(60, 69, BufferedImage.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
        Color preserveBackgroundColor = null;
		ImageObserver paintingChild = null;
		
		ImageObserver observer = null;
		
		gg.drawImage(dimg, x, y, observer);
		
		gg.drawImage(dimg2, x2, y2, observer);
		
		lastX2 = x2;
		lastY2 = y2;
        lastX = x;
        lastY=y;
    }

}