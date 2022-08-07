package com.example.restservice;

import java.rmi.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import com.example.rmitest.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class GreetingController {

	private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/xor")
	@CrossOrigin(origins = "*")
	public String greeting(@RequestParam(value = "q") String q) throws Exception {

		log.info("q = " + q);

		Search access = (Search)Naming.lookup("rmi://localhost:1900/xor");
		String answer = access.query(q);

		log.info("answer = " + answer);

		return answer; 
	}
        @GetMapping("/greeting2")
        public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {

                String answer = name;
                return new Greeting(counter.incrementAndGet(), String.format(template, answer));
        }

}
