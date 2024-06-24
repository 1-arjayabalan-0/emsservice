package com.leiten.adminservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leiten.adminservice.dto.request.AdminRequestDTO;
import com.leiten.adminservice.dto.response.AdminResponseDTO;
import com.leiten.adminservice.dto.response.ResponseDTO;
import com.leiten.adminservice.entities.Admin;
import com.leiten.adminservice.exceptions.NoContentFoundException;
import com.leiten.adminservice.repository.AdminRepository;
import com.leiten.adminservice.service.AdminService;
import com.leiten.adminservice.utility.AdminUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.leiten.adminservice.query.AdminQuery.createQueryToFetchAdminDetails;
import static com.leiten.adminservice.query.AdminQuery.createQueryToFetchAdminsToSendEmail;
import static com.leiten.adminservice.utility.AdminUtils.convertToAdminResponse;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author smriti
 */

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public void saveAdmin(AdminRequestDTO requestDTO) {

        log.info(":::: SAVE ADMIN PROCESS STARTED::::");
        validateAdminRequestDTO.accept(requestDTO);

        System.out.println("VALIDATION DONE");
    }

    public Consumer<AdminRequestDTO> validateAdminRequestDTO = (requestDTO) -> {
//        adminRepository.fetchAdminByUsername(requestDTO.getUsername()).ifPresent(admin -> {
//            throw new DataDuplicationException(DUPLICATE_USERNAME_MESSAGE, DUPLICATE_USERNAME_DEVELOPER_MESSAGE);
//        });
//
//        adminRepository.fetchAdminByEmailAddress(requestDTO.getEmailAddress()).ifPresent(admin -> {
//            throw new DataDuplicationException(DUPLICATE_EMAILADDRESS_MESSAGE, DUPLICATE_EMAILADDRESS_DEVELOPER_MESSAGE);
//        });
    };


    //    @Override
//    public void saveAdmin(AdminRequestDTO requestDTO) {
////        Admin admin = MapperUtility.map(requestDTO, Admin.class);
////
////        admin.setPassword(BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt()));
////        admin.setLoginAttempt(0);
////
////        admin.setRoles(Arrays.asList("ROLE_USER"));
////        adminRepository.save(admin);
//    }

    /*SEARCH ADMIN FOR LOGIN VALIDATION*/
    @Override
    public AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO) throws NoContentFoundException {

        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchAdminDetails.apply(requestDTO)).getResultList();

//        if (ObjectUtils.isEmpty(results))
//            throw new NoContentFoundException(AdminNotFoundException.MESSAGE, AdminNotFoundException.DEVELOPER_MESSAGE);

        return convertToAdminResponse.apply(results);
    }
    
    @Override
    public Admin updateAdmin(AdminRequestDTO requestDTO) {
        return null;
    }
    
    @Override
    public Admin fetchAdminByUsername(String username) {
        return null;
    }
    
    
    /*FOR UPDATING LOGIN ATTEMPTS */
//    @Override
//    public Admin updateAdmin(AdminRequestDTO requestDTO) {
//
////        Admin admin = this.adminRepository.getAdminById(requestDTO.getId()).orElseThrow(() -> {
////            return new NoContentFoundException(AdminNotFoundException.MESSAGE, AdminNotFoundException.DEVELOPER_MESSAGE);
////        });
//
//        admin.setStatus(requestDTO.getStatus());
////        admin.setLoginAttempt(requestDTO.getLoginAttempt());
//
//        return adminRepository.save(admin);
//    }


    /*USED BY AUTH-SERVICE AFTER SUCCESSFUL TOKEN VALIDATION*/
//    @Override
//    public Admin fetchAdminByUsername(String username) {
//        return adminRepository.fetchAdminByUsername(username).orElseThrow(() ->
//                new NoContentFoundException(AdminNotFoundException.MESSAGE, AdminNotFoundException.DEVELOPER_MESSAGE));
//    }


    @Override
    public ResponseDTO adminsToSendEmails() {

        List<Object[]> results = entityManager.createNativeQuery(
                createQueryToFetchAdminsToSendEmail.get()).getResultList();

        List<AdminResponseDTO> responseDTOS = results.stream().map(AdminUtils.convertToResponse)
                .collect(Collectors.toList());

        return ResponseDTO.builder().adminResponseDTOS(responseDTOS).build();
    }
    
    @Override
    public List<Admin> fetchAllAdmins() {
        return null;
    }
    
    
}
