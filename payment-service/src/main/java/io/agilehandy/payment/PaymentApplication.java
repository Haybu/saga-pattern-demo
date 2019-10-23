package io.agilehandy.payment;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServiceBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBApplication.class, args);
	}

}

@RestController
class Endpoint {

	@Value("#{maxSecondsToDelay}")
	int maxSecondsToDelay;

	@Value("#{lowerBound}")
	int lowerBound;

	@Value("#{higherBound}")
	int higherBound;

	@GetMapping("/pay")
	public Status doSomething() {
		int randomInteger = new Random().nextInt(10);

		if (randomInteger > lowerBound && randomInteger < higherBound) {
			delay(maxSecondsToDelay);
			return Status.FAILURE;
		}

		delay(maxSecondsToDelay);
		return Status.SUCCESS;
	}

	private void delay(int maxSecondsToDelay) {
		try {
			int secondsToSleep = new Random().nextInt(maxSecondsToDelay);
			Thread.sleep(secondsToSleep * 1000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}

}

enum Status {
	SUCCESS,
	FAILURE
}
