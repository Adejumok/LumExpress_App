package africa.semicolon.lumexpress.data.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.exception.LumExpressException;
import africa.semicolon.lumexpress.exception.UserNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws FileNotFoundException, LumExpressException;

    String updateCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException;
    List<Customer> getAllCustomers();
}
