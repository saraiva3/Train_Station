package com.csc.TrainStation;

import java.awt.Color;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Train implements Runnable{
	
	  private final Vector sharedQueue,sharedQueue2,sharedQueue3;	
	
	  private static volatile int startStation1,startStation2,startStation3;
	  private volatile int localStation;
	
	  private volatile int emptyStation1,emptyStation2,emptyStation3;
	  private final int SIZE;
	  private SaveOutput save;
	  private Frame frame;
	
	  public Train(Vector sharedQueue,Vector sharedQueue2,Vector sharedQueue3 ,int size, Frame frame,SaveOutput save){
		  
	      this.sharedQueue = sharedQueue;
	      this.SIZE = size;
	      this.sharedQueue3 = sharedQueue3;
	      this.sharedQueue2 = sharedQueue2;
	      this.localStation = 1;
	      this.frame = frame;	
	      startStation1 = 0;
	      startStation2 = 0;
	      this.save = save;
	      startStation3 = 0;     
	      
	  }

	  @Override
	  public void run() {
	      while (true) {
	          try {
	        	  //sleep just to simulate an amount of time that it takes to board people, and also to allow the animation to make sense
	        	  Thread.sleep(5000);
	        	  consume();
	                 
	          } catch (InterruptedException ex) {
	              Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
	          }

	     }
	  }
	  
	  private int consume() throws InterruptedException {
		  //There is three main if statements, each one is associated to one station
		  //Both trains make the path: 1 -> 2 -> 3 -> 1 -> 2 ......
		  if(localStation == 1){		    
			  //if the queue is Empty it is necessary to verify also the other stations, here what I am doing is setting a flag to say that this station will not generate people
			  //Go to the other stations and verify if they still have people. If the train make the round trip and does not find anyone, everything stops.
		      while (sharedQueue.isEmpty()) {
		        	synchronized (sharedQueue) {
		        		
		        		frame.station1Status.setForeground(Color.GREEN);  
				        frame.station1Status.setText("Empty");
				        frame.station1People.setForeground(Color.GREEN);  
					    frame.station1People.setText("0");
		        		if(emptyStation1 == 0){
		        			emptyStation1=1;
		        			localStation = 2;
		        			break;
		        		}	        		        		
		        		if(emptyStation1 ==1 && emptyStation2 == 1 && emptyStation3 == 1){		        		
		        			
		        			if(Thread.currentThread().getName().equals("Train 1")){
			        			frame.train1People.setText(""+0);
			        		}
			        		else{
			        			frame.train2People.setText(""+0);
			        		}	
		        			sharedQueue.wait();
		        		}		               
		            }
		        }	
		      //Here is where I remove people from the train
		      synchronized (sharedQueue) {
		    	  
		          sharedQueue.notifyAll();		          
		          if(!sharedQueue.isEmpty()){	
		        	 
		        	  if(Thread.currentThread().getName().equals("Train 1")){
		        		  frame.train1Location.setText("Station 1");		        		 
		        	  }
		        	  else{
		        		  frame.train2Location.setText("Station 1");
		        	  }
		        	  
		        	  frame.station1People.setForeground(Color.YELLOW);  
		        	  frame.station1People.setText(""+sharedQueue.size());
		        	  frame.station1Status.setForeground(Color.YELLOW);
		              frame.station1Status.setText("Ok");
		             //If there is enough people to fill the entire train, take them all
		        	  if(sharedQueue.size() >= this.SIZE){
		        		 
			        	  for(int i=0;i<this.SIZE;i++){
			        		  save.setConsumed(1);
			        		  frame.outStation1.setText(""+save.getConsumed());			        		 
					          
					          frame.station1Status.setText("Ok");
			        		  sharedQueue.remove(0);
			        		  frame.station1People.setText(""+sharedQueue.size());
			        		  Thread.currentThread().sleep(2000);
			        		
			        		  if(Thread.currentThread().getName().equals("Train 1")){
			        			  frame.train1People.setText(""+(i+1));
			        		  }
			        		  else{
			        			  frame.train2People.setText(""+(i+1));
			        		  }			        		 
			        	  }			        	
			        	  localStation = 2;
			        	 
		        	  }
		        	  else{
		        		  //if there is not enough people to fill the entire train, take everyone in the station
		        		  startStation1=0;
		        		  for(int i=0;i<sharedQueue.size();i++){
		        			  sharedQueue.remove(0);
		        			  save.setConsumed(1);
		        			  frame.outStation1.setText(""+save.getConsumed());
		        			  if(Thread.currentThread().getName().equals("Train 1")){				        		 
				        		  frame.train1People.setText(""+(i+1));
				        	  }
		        			  else{
		        				  frame.train2People.setText(""+(i+1)); 
		        			  }
		        		  }		        		
		        		  frame.btnStartStation.setEnabled(true);
		        		  //After boarding everyone, "CLOSE THE DOORS" (while(true)) and wait until someone press the start button
		        		  while(true){		  
		        				
		        				//The start button is only avaliable in this situation, once you have pressed it, it will be disabled again
		        			  if(startStation1==1){
		        				  frame.station1People.setForeground(Color.GREEN);  
		    		        	  frame.station1People.setText("0");
		    		        	  frame.station1Status.setForeground(Color.GREEN);
		    		              frame.station1Status.setText("Empty");	    		            
		    		          	  frame.btnStartStation.setEnabled(false);
		        				  localStation = 2;
		        				  break;
		        			  }
		        		  }		        		  
		        	  }		        	 
		        	  frame.station1People.setForeground(Color.YELLOW);  
		        	  frame.station1People.setText(""+sharedQueue.size());
		        	  frame.station1Status.setForeground(Color.YELLOW);
		              frame.station1Status.setText("Ok");	
		              //After all the interactions, verify if the station is empty jus to change labels and make clear what is happening
		              if(sharedQueue2.isEmpty()){
				        	frame.station2People.setForeground(Color.GREEN);  
					        frame.station2People.setText("0");
					        frame.station2Status.setForeground(Color.GREEN);
					        frame.station2Status.setText("Empty");				         
				        	return -1;
				      }
		              //here I make the Thread regarding the train to move, I have two images that looks the same
		              //each one move when it is done realizing all the computations in the station
		              
		              if(Thread.currentThread().getName().equals("Train 1")){
		            	  TrainCanvas.directions(430,15, 0, 7,430,15,0,0,1);
			              
		              }
		              else{
		            	  TrainCanvas.directions(0,0, 0, 0,430,15,0,7,5);
			              
		              }
		             // TrainCanvas.directions(430,15,0,7);
		        	  return 1;	            	
		          }
		          //If it returns 1 means that the train removed people from the station
		          //if returns -1 probably there is nobody in the station
		          return -1;
		      }
	    }
		  //Code for station 2 and 3 have the same configuration as station 1.
	    else if (localStation == 2){
	    	
	    	
	        while (sharedQueue2.isEmpty()) {
	            synchronized (sharedQueue2) {
	            	frame.station2Status.setForeground(Color.GREEN);  
			        frame.station2Status.setText("Empty");
			        frame.station2People.setForeground(Color.GREEN);  
				    frame.station2People.setText("0");
	            	if(emptyStation2==0){	            		
	        			emptyStation2= 1;
	        			localStation = 3;
	        			break;
	        		}	        		
	        		else if(emptyStation1 ==1 && emptyStation2 == 1 && emptyStation3 == 1){	        			
	        			
	        			frame.station1Status.setForeground(Color.GREEN);  
				        frame.station1Status.setText("Empty");
				        frame.station2Status.setForeground(Color.GREEN);  
				        frame.station2Status.setText("Empty");
				        
	        			if(Thread.currentThread().getName().equals("Train 1")){
		        			frame.train1People.setText(""+0);
		        		}
		        		else{
		        			frame.train2People.setText(""+0);
		        		}	
	        			sharedQueue2.wait();
	        		}                
	            }
	        }	 
	        synchronized (sharedQueue2) {
	        		
	        	sharedQueue2.notifyAll();	           
	        	if(!sharedQueue2.isEmpty()){
	        		
	        		if(Thread.currentThread().getName().equals("Train 1")){
			        	frame.train1Location.setText("Station 2");		        		 
			        }
			        else{
			        	frame.train2Location.setText("Station 2");
			        }
	        		        	 
	        		if(sharedQueue2.size() >= this.SIZE){
	        			for(int i=0;i<this.SIZE;i++){
	        			
	        				save.setConsumed2(1);
				        	frame.outStation2.setText(""+save.getConsumed2());			        	
				        	Thread.currentThread().sleep(2000);
				        	frame.station2People.setText(""+sharedQueue2.size());
				        	frame.station2Status.setText("OK");
	        				sharedQueue2.remove(0);
	        				
					        if(Thread.currentThread().getName().equals("Train 1")){
			        			frame.train1People.setText(""+(i+1));
			        		}
			        		else{
			        			frame.train2People.setText(""+(i+1));
			        		}			  			        		  
				        }				        	 
				        localStation = 3;
				       
			        }
			        else{
			        startStation2 = 0;	
			      	
			        	for(int i=0;i<sharedQueue2.size();i++){
			        		
			        		sharedQueue2.remove(0);
			        		save.setConsumed2(1);
			        		frame.outStation2.setText(""+save.getConsumed2());
			        		if(Thread.currentThread().getName().equals("Train 1")){				        		 
			        			frame.train1People.setText(""+(i+1));					        		  
					        }
			        		else{
			        			frame.train2People.setText(""+(i+1)); 
			        		}
			        	}
			        	frame.btnStartStation_1.setEnabled(true);
			        	while(true){	
			        		 
			        		if(startStation2 ==1){
			        			localStation = 3;
			        			frame.station2People.setForeground(Color.GREEN);  
		    		        	frame.station2People.setText("0");
		    		        	frame.station2Status.setForeground(Color.GREEN);
		    		            frame.station2Status.setText("Empty");	
			        			frame.btnStartStation_1.setEnabled(false);
			        			
			        			break;
			        		}
			        	}		        		  
			        }			        
			        frame.station2People.setForeground(Color.YELLOW);  
			        frame.station2People.setText(""+sharedQueue2.size());
			        frame.station2Status.setForeground(Color.YELLOW);
			        frame.station2Status.setText("Ok");
			        	  
			        if(sharedQueue2.isEmpty()){
			        	frame.station2People.setForeground(Color.GREEN);  
				        frame.station2People.setText("0");
				        frame.station2Status.setForeground(Color.GREEN);
				        frame.station2Status.setText("Empty");				         
			        	return 0;
			        }
			        if(Thread.currentThread().getName().equals("Train 1")){
		            	  TrainCanvas.directions(430,617, -7, 0,0,0,0,0,1);
			              
		              }
		              else{
		            	  TrainCanvas.directions(0,0, 0, 0,430,617,-7,0,5);
			              
		              }
			     //   TrainCanvas.directions(430,617,-7,0);
			        return 1;	    	 	
			            	
	        	}
	            return -1;
	        }
	     
	    }
	    else if (localStation == 3){
	    	
	        while (sharedQueue3.isEmpty()) {
	            synchronized (sharedQueue3) {
	            	frame.station3Status.setForeground(Color.GREEN);  
			        frame.station3Status.setText("Empty");
			        frame.station3People.setForeground(Color.GREEN);  
				    frame.station3People.setText("0");
	            	if(emptyStation3==0){
	            		
	        			emptyStation3= 1;
	        			localStation = 1;
	        			break;
	        		}	        		   		
	        		else if(emptyStation1 ==1 && emptyStation2 == 1 && emptyStation3 == 1){	    
	        			if(Thread.currentThread().getName().equals("Train 1")){
		        			frame.train1People.setText(""+0);
		        		}
		        		else{
		        			frame.train2People.setText(""+0);
		        		}	
	        		
	        			sharedQueue3.wait();
	        		}                
	            }
	        }	 
	        synchronized (sharedQueue3) {
	        	sharedQueue3.notifyAll();	           
	        	if(!sharedQueue3.isEmpty()){
	        	
	        		if(Thread.currentThread().getName().equals("Train 1")){
			        	frame.train1Location.setText("Station 3");		        		 
			        }
			        else{
			        	frame.train2Location.setText("Station 3");
			        }	        				        	 
	        		if(sharedQueue3.size() >= this.SIZE){
	        			for(int i=0;i<this.SIZE;i++){
	        				sharedQueue3.remove(0);
	        				
	        				save.setConsumed3(1);
				        	frame.outStation3.setText(""+save.getConsumed3());			        	
				        	Thread.currentThread().sleep(2000);
				        	frame.station3People.setText(""+sharedQueue3.size());
				        	frame.station3Status.setText("OK");
					        if(Thread.currentThread().getName().equals("Train 1")){
			        			frame.train1People.setText(""+(i+1));
			        		}
			        		else{
			        			frame.train2People.setText(""+(i+1));
			        		}			  			        		  
				        }				        	 
				        localStation = 1;
			        }
			        else{
			        startStation3 = 0;	
			       
			        	for(int i=0;i<sharedQueue3.size();i++){
			        		sharedQueue3.remove(0);
			        		save.setConsumed3(1);
			        		frame.outStation3.setText(""+save.getConsumed3());
			        		if(Thread.currentThread().getName().equals("Train 1")){				        		 
			        			frame.train1People.setText(""+(i+1));					        		  
					        }
			        		else{
			        			frame.train2People.setText(""+(i+1)); 
			        		}
			        	}
			        	frame.btnStartStation_2.setEnabled(true);
			        	while(true){	
			        	
			        		if(startStation3 ==1){
			        			localStation = 1;
			        			frame.btnStartStation_2.setEnabled(false);
			        			frame.station3People.setForeground(Color.GREEN);  
		    		        	frame.station3People.setText("0");
		    		        	frame.station3Status.setForeground(Color.GREEN);
		    		            frame.station3Status.setText("Empty");	
		    		            frame.btnStartStation_2.setEnabled(false);
			        			
			        			break;
			        		}
			        	}		        		  
			        }			        
			        frame.station3People.setForeground(Color.YELLOW);  
			        frame.station3People.setText(""+sharedQueue2.size());
			        frame.station3Status.setForeground(Color.YELLOW);
			        frame.station3Status.setText("Ok");
			        	  
			        if(sharedQueue3.isEmpty()){
			        	frame.station3People.setForeground(Color.GREEN);  
				        frame.station3People.setText("0");
				        frame.station3Status.setForeground(Color.GREEN);
				        frame.station3Status.setText("Empty");				         
			        	return 0;
			        }
			        if(Thread.currentThread().getName().equals("Train 1")){
		            	  TrainCanvas.directions(120,638, 7,-13,0,0,0,0,1);			              
		              }
		              else{
		            	  TrainCanvas.directions(0,0, 0, 0,120,638,7,-13,5);			              
		              }
			       // TrainCanvas.directions(120,638,7,-13);
			        return 1;   	 	
			            	
	        	}
	            return -1;
	        }     
	    }
		return 0;
	  }
	  //Variables to make the start
	  public static void startStation1(){
		  startStation1 = 1;
	  }
	  public static void startStation2(){
		  startStation2 = 1;	  
	  }
	  public static void startStation3(){
		  startStation3 = 1;
	  }
}
