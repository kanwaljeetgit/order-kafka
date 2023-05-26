package com.sapient.orderkafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class OrderKafkaApplicationTests {

	@Test
	void contextLoads() {
		ObjectNode jsonNodes = new ObjectMapper().createObjectNode();
		jsonNodes.put("z",1);

		jsonNodes.put("b",22);

		jsonNodes.put("y",22);

		jsonNodes.put("a",1);
		System.out.println(jsonNodes);
	}

}
