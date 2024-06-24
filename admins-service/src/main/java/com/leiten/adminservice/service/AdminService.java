package com.leiten.adminservice.service;

import java.util.List;

import com.leiten.adminservice.dto.request.AdminRequestDTO;
import com.leiten.adminservice.dto.response.AdminResponseDTO;
import com.leiten.adminservice.dto.response.ResponseDTO;
import com.leiten.adminservice.entities.Admin;

/**
 * @author smriti
 */
public interface AdminService {

    void saveAdmin(AdminRequestDTO requestDTO);

    AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO);

    Admin updateAdmin(AdminRequestDTO requestDTO);

    Admin fetchAdminByUsername(String username);




    ResponseDTO adminsToSendEmails();


    List<Admin> fetchAllAdmins();
}
