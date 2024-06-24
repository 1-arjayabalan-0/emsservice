package com.leiten.emsapigateway.feignInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leiten.emsapigateway.responseDTO.AdminResponseDTO;

import static com.leiten.emsapigateway.constants.MicroServiceConstants.*;

import java.util.Optional;

@FeignClient(name = ADMIN_MICROSERVICE)
@Service
@RequestMapping(value = BASE_API)
public interface AdminInterface {

    @RequestMapping(value = AdminMicroServiceConstants.FETCH_ADMIN_BY_USERNAME)
    Optional<AdminResponseDTO> fetchAdminByUsername(@PathVariable("username") String username);
}
