package com.csc.TrainStation;

import java.util.Vector;


public class Main {

    public static void main(String args[]) {
        Vector sharedQueue = new Vector();
        Vector sharedQueue2 = new Vector();
        Vector sharedQueue3 = new Vector();
        int consumed1, consumed2, consumed3;
        Frame frame = new Frame();
        frame.start();
        //5 Threads are created plus 1 thread to the animation plus 1 thread to the interface
        
        SaveOutput save = new SaveOutput(frame);
        //Test case , station with size 2
        //Test case, station with size 10
        
        //station(Line containing people, Size of your station, interface, name)
        Thread prodThread = new Thread(new Station(sharedQueue, 2,frame), "Station 1");
        Thread prodThread2 = new Thread(new Station(sharedQueue2,10,frame), "Station 2");
        Thread prodThread3 = new Thread(new Station(sharedQueue3, 10,frame), "Station 3");
        //train(Line containing people station1, station2, station3, Size of your train, interface class to save output, name)
        Thread consThread = new Thread(new Train(sharedQueue, sharedQueue2,sharedQueue3, 4,frame,save), "Train 1");
        Thread consThread2 = new Thread(new Train(sharedQueue,sharedQueue2,sharedQueue3, 4,frame,save), "Train 2");
        
       
        prodThread2.start();
        prodThread.start();
        prodThread3.start();
        consThread.start();
        consThread2.start();
      
    }
}


