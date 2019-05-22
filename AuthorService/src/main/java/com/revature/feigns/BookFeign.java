package com.revature.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.dtos.BookDTO;

@FeignClient("book-service")
public interface BookFeign {

	// localhost:8080/books?authorId=1
	@GetMapping("")
	public List<BookDTO> getBooksByAuthorId(@RequestParam int authorId);
}
