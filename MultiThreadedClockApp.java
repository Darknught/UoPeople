/*
* This program demonstrates the Java Thread model by implementing a simple clock application.
* The application uses multiple threads to concurrently update and display the current time and date.
*
* Key Features:
* - A Clock class maintains the current time and updates it continuously.
* - A dedicated thread updates the time in the background.
* - Another thread prints the current time and date to the console.
* - Thread priorities are used to give the display thread higher priority than the updating thread,
* ensuring smoother and more precise time display.
* - The output is displayed in the format "HH:mm:ss dd-MM-yyyy".
*
* This example highlights how Java multithreading and thread prioritization can be applied in real-life
* applications such as clocks, timers, or other concurrent systems.
*/


import java.text.SimpleDateFormat;
import java.util.Date;


class Clock {
private String currentTime;


public synchronized void updateTime() {
SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
this.currentTime = formatter.format(new Date());
}


public synchronized String getCurrentTime() {
return currentTime;
}
}


public class MultiThreadedClockApp {
public static void main(String[] args) {
Clock clock = new Clock();


// Thread to continuously update the time
Thread updaterThread = new Thread(() -> {
while (true) {
clock.updateTime();
try {
Thread.sleep(500); // update twice per second
} catch (InterruptedException e) {
e.printStackTrace();
}
}
});


// Thread to continuously display the time
Thread displayThread = new Thread(() -> {
while (true) {
System.out.println(clock.getCurrentTime());
try {
Thread.sleep(1000); // display every second
} catch (InterruptedException e) {
e.printStackTrace();
}
}
});


// Set priorities (display > updater)
updaterThread.setPriority(Thread.MIN_PRIORITY);
displayThread.setPriority(Thread.MAX_PRIORITY);


// Start threads
updaterThread.start();
displayThread.start();
}
}
