package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
* This project is a count down timer that can be used
* to count down multiple things at once as well as add time and
* take time away. It has many functions such as adding timers
* together and comparing them.
*
* @author  Dylan Strohschein
* @version CIS 613
* @date 1/31/2018
*/

public class CountDownTimer {

	//Instance Variables
	int hours, minutes, seconds;
	static boolean isSuspended = false;
	
	   /**
	   * This is the default constructor
	   * it initializes all the time values to zero.
	   */
	public CountDownTimer() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	
	   /**
	   * This is used to initialize a timer with a specified number
	   * of hours, minutes and seconds.
	   * @param hours This is the number of hours the timer
	   * will have
	   * @param minutes  This is the number of minutes the timer
	   * will have
	   * @param seconds This is the number of seconds the timer
	   * will have
	   */
	public CountDownTimer(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		
		isValidTime();
		
	}
	
	   /**
	   * This is used to initialize a timer with a specified number
	   * of minutes and seconds.
	   * @param minutes  This is the number of minutes the timer
	   * will have
	   * @param seconds This is the number of seconds the timer
	   * will have
	   */
	public CountDownTimer(int minutes, int seconds) {
		this.hours = 0;
		this.minutes = minutes;
		this.seconds = seconds;
		
		isValidTime();
		
	}
	
	   /**
	   * This is used to initialize a timer with the same values
	   * as another timer.
	   * @param other This is another count down timer that you
	   * would like to make a copy of.
	   */
	public CountDownTimer(CountDownTimer other) {
		this.hours = other.getHours();
		this.minutes = other.getMinutes();
		this.seconds = other.getSeconds();
		
		isValidTime();
		
	}
	
	   /**
	   * This converts a string in the format "H:M:S" to a timer
	   * @param startTime This is the string that specifies the
	   * time you wish to set the timer equal to.
	   */
	public CountDownTimer(String startTime) {
		
		if (startTime.contains(":")) {
			
			String[] splitData = startTime.split(":");
			
			for (String s : splitData) {
				if (s.equals("")) {
					s = "0";
				}
			}
			
			if (splitData.length > 2) {
				this.hours = Integer.parseInt(splitData[0]);
				this.minutes = Integer.parseInt(splitData[1]);
				this.seconds = Integer.parseInt(splitData[2]);
			}else {
				this.hours = 0;
				this.minutes = Integer.parseInt(splitData[0]);
				this.seconds = Integer.parseInt(splitData[1]);
			}
			
		}else {
			
			this.hours = 0;
			this.minutes = 0;
			this.seconds = Integer.parseInt(startTime);
			
		}
		
		isValidTime();

	}
	
	   /**
	   * This is used to check if two timers are equal to
	   * each other
	   * @param other This is the timer that you want to compare
	   * the current timer equal to.
	   */
	public boolean equals(Object other) {
		
		CountDownTimer compare = (CountDownTimer)other;
		
		if (this.hours == compare.getHours() && this.minutes == compare.getMinutes() && this.seconds == compare.getSeconds()) {
			return true;
		}
		
		return false;
	}
	
	   /**
	   * This compares 2 timers to see if they are equal.
	   * @param t1 This is one count down timer that you want
	   * to compare
	   * @param t2 This is the other count down timer that you want
	   * to compare
	   */
	public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
		
		if (t1.hours == t2.getHours() && t1.minutes == t2.getMinutes() && t1.seconds == t2.getSeconds()) {
			return true;
		}
		
		return false;
		
	}
	
	   /**
	   * This is used to compare if two timers
	   * @param other This is the timer that you want to compare
	   * the current timer to.
	   */
	public int compareTo (CountDownTimer other) {
		
		int myValue = getValue(this);
		int otherValue = getValue(other);
		
		if (myValue > otherValue) {
			return 1;
		}else if (myValue < otherValue) {
			return -1;
		}
		
		return 0;
	
	}
	
	   /**
	   * This compares 2 timers.
	   * @param t1 This is one count down timer that you want
	   * to compare
	   * @param t2 This is the other count down timer that you want
	   * to compare
	   */
	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
		
		int value1 = getValue(t1);
		int value2 = getValue(t2);
		
		if (value1 > value2) {
			return 1;
		}else if (value1 < value2) {
			return -1;
		}
		
		return 0;
		
	}
	
	public void sub (int seconds) {
		
		if (isSuspended == false) {
			int value = getValue(this);
			value -= seconds;
			secondsToTime(value, this);
		}
		
		isValidTime();
		
	}
	
	public void dec() {
		
		if (isSuspended == false) {
			
			int value = getValue(this);
			value -= 1;
			
			if (value <= 0) {
				value = 0;
			}
				
			secondsToTime(value, this);
		}

		isValidTime();
		
	}
	
	public void add(int seconds) {
		
		if (isSuspended == false) {
			int value = getValue(this);
			value += seconds;
			secondsToTime(value, this);	
		}
		
	}
	
	public void add (CountDownTimer other) {
		
		if (isSuspended == false) {
			int otherValue = getValue(other);
			this.add(otherValue);	
		}
		
	}
	
	public void inc() {
		
		if (isSuspended == false) {
			int value = getValue(this);
			value += 1;
			secondsToTime(value, this);
		}

	}
	
	public void isValidTime() {
		
		if (this.minutes >= 60 || this.minutes < 0) {
			throw new IllegalArgumentException();
		}
		
		if (this.seconds >= 60 || this.seconds < 0) {
			throw new IllegalArgumentException();
		}
		
	}
	
	public String toString() {
		
		String minString = "";
		
		if (this.minutes > 9) {
			minString = this.minutes + "";
		}else {
			minString = "0" + this.minutes;
		}
		
		String secString = "";
		
		if (this.seconds > 9) {
			secString = this.seconds + "";
		}else {
			secString = "0" + this.seconds;
		}
		
		return this.hours + ":" + minString + ":" + secString;
		
	}
	
	public static int getValue(CountDownTimer timer) {
		
		return timer.getHours() * 3600 + timer.getMinutes() * 60 + timer.getSeconds();	
	
	}
	
	public static void secondsToTime(int seconds, CountDownTimer timer) {
		
		timer.setHours(seconds / 3600);
		seconds = seconds % 3600;
		
		timer.setMinutes(seconds / 60);
		seconds = seconds % 60;
		
		timer.setSeconds(seconds);
		
	}
	
	public void save(String fileName) {
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IllegalArgumentException();
		}
		
		out.println(this.hours);
		out.println(this.minutes);
		out.println(this.seconds);
		out.close();
	}
	
	public void load(String fileName) {
		int data;
		
		try {
			
			Scanner fileReader = new Scanner(new File(fileName));
			
			data = fileReader.nextInt();
			this.hours = data;
			data = fileReader.nextInt();
			this.minutes = data;
			data = fileReader.nextInt();
			this.seconds = data;
			
			fileReader.close();
			
		}catch (Exception error) {
			
			throw new IllegalArgumentException();
			
		}
		
	}
	
	public static void Suspend(Boolean flag) {
		isSuspended = flag;
	}
	
	public static boolean getSuspend() {
		return isSuspended;
	}
	
	//Get methods
	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	//Set methods
	public void setHours(int hours) {	
		this.hours = hours;
	}
	
	public void setMinutes(int minutes) {	
		this.minutes = minutes;
	}
	
	public void setSeconds(int seconds) {	
		this.seconds = seconds;
	}
	
}
