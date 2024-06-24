package com.leiten.loginservice.feignInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leiten.loginservice.constants.MicroServiceConstants;
import com.leiten.loginservice.constants.MicroServiceConstants.AdminMicroServiceConstants;
import com.leiten.loginservice.requestDTO.AdminRequestDTO;
import com.leiten.loginservice.responseDTO.AdminResponseDTO;

@FeignClient(name = AdminMicroServiceConstants.BASE)
@Service
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface AdminInterface {

    @RequestMapping(value = AdminMicroServiceConstants.SEARCH_ADMIN)
    AdminResponseDTO searchAdmin(@RequestBody AdminRequestDTO requestDTO);

    @RequestMapping(value = AdminMicroServiceConstants.UPDATE_ADMIN)
    void updateAdmin(@RequestBody AdminResponseDTO responseDTO);
}
