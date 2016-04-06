package com.idea.main;

import com.idea.adapters.weather.producers.weather.ForecastIOProducer;
import com.idea.kafka.mqtt.bridge.KafkaConsumer;
import com.idea.kafka.mqtt.bridge.MqttConsumerToKafkaProducer;

public class Idea {
	public static void main(String[] args){
		//Start the mqtt listener
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					MqttConsumerToKafkaProducer.start();
				} catch (Exception e) {
					System.out.println("Error in MQttConsumerToKafkaProducer : " + e.getMessage());
				}
			}
		});
		//start the kafka collector
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					KafkaConsumer.start();
				} catch (Exception e) {
					System.out.println("Error in KafkaConsumer : " + e.getMessage());
				}
			}
		});
		
		//start the active weather data collector
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					ForecastIOProducer.start();
				} catch (Exception e) {
					System.out.println("Error in ForecastIOProducer : " + e.getMessage());
				}
			}
		});
		
		t1.start();
		//t2.start();
		//t3.start();
		
	}
}
