package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Item;
import com.example.demo.repository.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@GetMapping()
	public List<Item> getItems() {
		return itemRepository.findAll();
	}

	@GetMapping("/{id}")
	public Item getItem(@PathVariable String id) {
		return itemRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}

	@PutMapping()
	public void editItem(@RequestBody Item item) {
		itemRepository.findById(item.getId()).orElseThrow(() -> new RuntimeException());
		itemRepository.save(item);
	}

	@PostMapping()
	public void addItem(@RequestBody Item item) {
		itemRepository.save(item);
	}

	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable String id) {
		itemRepository.findById(id).orElseThrow(() -> new RuntimeException());
		itemRepository.deleteById(id);
	}

}
