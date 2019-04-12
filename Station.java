package com.csc.TrainStation;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Station implements Runnable {
	    private final Vector sharedQueue;
	    private final int SIZE;
	    private Frame frame;
	    private volatile int arrivalRate;
	   
	    
	    public Station(Vector sharedQueue, int size, Frame frame) {
	        this.sharedQueue = sharedQueue;
	        this.SIZE = size;
	        this.frame = frame;
	    }
	   
	    @Override
	    public void run() {
	    	 
	    	Random r = new Random();
	    	int low = 2;
	    	int high = 4;
	    	arrivalRate = r.nextInt(high-low)+low;
	    	//Here you change the number of people that will be generate, using this actual configuration it can generate:
	    	// 20 - 30 - 40 people
	        for (int i = 0; i < this.SIZE; i++) {
	        	for(int j=0;j<arrivalRate;j++){
		          	           
		            try {
		                produce(j);
		            } catch (InterruptedException ex) {
		                Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
		            }	
		        }
	        }
	    }

	    private void produce(int i) throws InterruptedException {
	    	//Every time that the line is full, it stops and notify the other threads, people will leave the station only if there is a train at this station
	    	//Otherwise they have to wait
	        while (sharedQueue.size() == SIZE) {
	        	//Synchronized method restricts the threads of changing the sharedQueue at the very same time, if this happens, may cause lose of passengers
	        	//If two threads add one person at the very same time, the counter will increase just one, and you will lose the other one. We do not want that
	            synchronized (sharedQueue) {	            	
	              
	                if(Thread.currentThread().getName().equals("Station 1")){	                	
	                	frame.station1Status.setForeground(Color.RED);
		            	frame.station1Status.setText("Full");
		            	frame.station1People.setForeground(Color.RED);
		            	
	                }
	                else if(Thread.currentThread().getName().equals("Station 2")){	                	
	                	frame.station2Status.setForeground(Color.RED);
		            	frame.station2Status.setText("Full");
		            	frame.station2People.setForeground(Color.RED);
		            	
	                }
	                else{
	                	frame.station3Status.setForeground(Color.RED);
		            	frame.station3Status.setText("Full");
		            	frame.station3People.setForeground(Color.RED);
		            	
	                }
	                sharedQueue.wait();
	            }
	        }	       
	        synchronized (sharedQueue) {	
	        	//If the station is not full, generate people and add they to the line
	            sharedQueue.add(i);	  
	            if(Thread.currentThread().getName().equals("Station 1")){	                	
	            	frame.station1People.setForeground(Color.YELLOW);  
	  	        	frame.station1People.setText(""+sharedQueue.size());
	  	        	frame.station1Status.setForeground(Color.YELLOW);
	  	            frame.station1Status.setText("OK");
                }
                else if(Thread.currentThread().getName().equals("Station 2")){	                	
                	frame.station2People.setForeground(Color.YELLOW);  
      	        	frame.station2People.setText(""+sharedQueue.size());
      	        	frame.station2Status.setForeground(Color.YELLOW);
      	            frame.station2Status.setText("OK");
                }
                else{
                	frame.station3People.setForeground(Color.YELLOW);  
      	        	frame.station3People.setText(""+sharedQueue.size());
      	        	frame.station3Status.setForeground(Color.YELLOW);
      	            frame.station3Status.setText("OK");
                }
	          
	            sharedQueue.notifyAll();
	        }
	    }
}
