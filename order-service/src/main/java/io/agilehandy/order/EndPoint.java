/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.agilehandy.order;

import java.util.UUID;

import io.agilehandy.commons.commands.CreateOrder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haytham Mohamed
 **/

@RestController
public class EndPoint {

	private final OrderService orderService;

	public EndPoint(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/orders/create")
	public String create(@RequestBody CreateOrder createOrder) {
		UUID orderId = orderService.create(createOrder);
		return "A new order (number: " + orderId.toString() + ") is created.";
	}
}
