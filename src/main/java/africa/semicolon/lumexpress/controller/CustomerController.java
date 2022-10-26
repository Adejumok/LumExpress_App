package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.service.CustomerService;
import africa.semicolon.lumexpress.exception.LumExpressException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;


@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerRegistrationRequest customerRegistrationRequest) throws FileNotFoundException, LumExpressException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.register(customerRegistrationRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
        var users= customerService.getAllCustomers();
        log.info("cust-->{}", users);
        return ResponseEntity.ok(users);
    }



}
