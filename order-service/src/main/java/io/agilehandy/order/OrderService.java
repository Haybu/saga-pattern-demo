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

import java.util.Date;
import java.util.UUID;

import io.agilehandy.commons.commands.CreateOrder;

import org.springframework.stereotype.Service;

/**
 * @author Haytham Mohamed
 **/

@Service
public class OrderService {

	private final OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public UUID create(CreateOrder createOrder) {
		UUID orderId = UUID.randomUUID();
		Order order = new Order();
		order.setId(orderId);
		order.setCost(createOrder.getCost());
		order.setCreateDate(new Date());
		order.setDestination(createOrder.getDestination());
		order.setQuantity(createOrder.getQuantity());
		repository.save(order);
		//send a message to a broker to start a transaction to fulfill
		fulfill(orderId);
		return orderId;
	}

	// todo:
	private void fulfill(UUID orderId) {

	}
}
