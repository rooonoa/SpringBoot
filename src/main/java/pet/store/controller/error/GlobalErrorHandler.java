package pet.store.controller.error;

import java.util.HashMap;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j

public class GlobalErrorHandler {

	/*
	 * We added a method handleNoSuchElementException that specify a response status of 
	 * 404(Not Found). It takes in NoSuchElementException as a parameter and return a map 
	 * with a single entry with the Key being "message"
	 */
	
	@ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("Error: {}", ex.getMessage());
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.toString());
        return errorResponse;
    }
	
}
