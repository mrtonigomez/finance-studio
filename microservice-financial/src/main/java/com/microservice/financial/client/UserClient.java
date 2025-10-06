package com.microservice.financial.client;

import com.microservice.financial.client.get.UserGetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "msvc-user", url = "localhost:9002/api/users")
public interface UserClient {

    @GetMapping("/{id}")
    ResponseEntity<UserGetDto> getUserById(@PathVariable Long id);
}
