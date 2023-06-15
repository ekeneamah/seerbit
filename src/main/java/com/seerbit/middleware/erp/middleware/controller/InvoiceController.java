package com.seerbit.middleware.erp.middleware.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.middleware.erp.middleware.model.Invoice;



@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

    @PostMapping("/invoices")
    public ResponseEntity<String>  createInvoice(@RequestBody Invoice invoice, @RequestHeader("Authorization") String authorizationHeader) {
        // Perform any necessary business logic here
        String bearerToken = authorizationHeader.substring("Bearer ".length());
        // Prepare the JSON payload
        String jsonPayload = convertToJson(invoice);
         // Define the command for curl
         String curlCommand = "curl -X POST -H \"Content-Type: application/json\" -H \"Authorization: Bearer " + bearerToken + "\" -d '" + jsonPayload + "'https://seerbitapi.com/invoice/create";

         try {
            // Execute the curl command
            Process process = Runtime.getRuntime().exec(curlCommand);
            // Read the JSON response
            InputStream is = process.getInputStream();
            String jsonResponse = readResponse(is);
            // Parse the JSON response
            
            // Wait for the curl command to complete
            int exitCode = process.waitFor();

            // Check the exit code and handle accordingly
            return ResponseEntity.ok(jsonResponse);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Create the ApiResponse object for failure response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create invoice");
        }
    }
    @PostMapping("/orders")
    public String createOrder(@RequestBody Invoice orderRequest, @RequestHeader("Authorization") String authorizationHeader) {
        // Process the order request and perform necessary actions
        System.out.println("Received order: " + orderRequest);
        StringBuilder responseBuilder = new StringBuilder();
        try {
            // Define the URL of the target REST API server
            String apiUrl = "https://seerbitapi.com/invoice/create"; // Replace with the actual API URL

            // Create the URL object
            URL url = new URL(apiUrl);

            // Open the connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            // Set the bearer token in the Authorization header
            String bearerToken = authorizationHeader.substring("Bearer ".length());
            conn.setRequestProperty("Authorization", "Bearer " + bearerToken);



            // Write the JSON data to the request body
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(orderRequest.toString().getBytes());
            outputStream.flush();

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // Check the response status code and handle accordingly
            int responseCode = conn.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                return "Order created successfully "+responseBuilder;
            } else {
                return "Failed to create order "+responseBuilder;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to create order "+responseBuilder;
        }
    }
    private String convertToJson(Invoice orderRequest) {
        // Implement the logic to convert OrderRequest object to JSON string
        // You can use a library like Jackson or Gson to serialize the object
        // Here's an example using Jackson:
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(orderRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String readResponse(InputStream inputStream) throws IOException {
        // Read the JSON response from the InputStream
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
}







