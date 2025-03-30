package gft.impact.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gft.impact.proposal.controllers.ProposalResponse;
import gft.impact.proposal.service.InvalidProductCodeException;
import gft.impact.proposal.service.InvalidProposalException;
import gft.impact.proposal.service.ProductNotFoundException;
import gft.impact.proposal.service.ProposalNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProposalException.class)
    public ResponseEntity<ProposalResponse> handleInvalidProposalException(InvalidProposalException ex) {
        ProposalResponse response = new ProposalResponse(1, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProposalNotFoundException.class)
    public ResponseEntity<ProposalResponse> handleProposalNotFoundException(ProposalNotFoundException ex) {
        ProposalResponse response = new ProposalResponse(2, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProductCodeException(InvalidProductCodeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("01", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("02", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
    	e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An error occurred: " + e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more specific exception handlers as needed
}
