package com.shop.shop_inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaveandReadTaskRepo {
	@Autowired
	Repository repository;

	@Test
	public void FindAndReadTask() {
		//Given
		Item item1 = new Item("Banana",new Date(18745123),20,320);
		Item item2 = new Item("Mandarin",new Date(19745123),10,424);
		Item item3 = new Item("Apple",new Date(20745123),5,801);
		//When
		repository.save(item1);
		repository.save(item2);
		repository.save(item3);
		//Then
		assertEquals(3, repository.count());
		assertEquals("Mandarin",repository.getReferenceById(2).getName());
		assertEquals(19745123,repository.getReferenceById(2).getExpiry().getTime());
	}
}
