package com.example.transfer.apis;

import com.example.transfer.dto.ErrorResponse;
import com.example.transfer.dto.MoneyTransferRequestDto;
import com.example.transfer.exceptions.InvalidCurrencyException;
import com.example.transfer.model.Transaction;
import com.example.transfer.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MoneyTransferRestController {

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferRestController.class);

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "", description = "", tags = {"/transferMoney"}) //for swagger documentation
    @PostMapping("/transferMoney")
    @ResponseBody
    public ResponseEntity<?> transferMoney(@Valid @RequestBody MoneyTransferRequestDto moneyTransferRequestDto) {
        try {
            logger.info("get in /transfer");
            Transaction transaction = transactionService.transfer(moneyTransferRequestDto);

            // Return success response with transaction details
            return new ResponseEntity<>(transaction, HttpStatus.OK);

            //handle some exceptions
        } catch (NumberFormatException e) {
            logger.error(e.toString());
            ErrorResponse errorResponse = new ErrorResponse("Ids must be a number value, " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (InvalidCurrencyException e) {
            logger.error(e.toString());
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
