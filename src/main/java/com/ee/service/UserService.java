package com.ee.service;

import com.ee.customexception.AccountNotVerifiedException;
import com.ee.dto.request.LoginRequest;
import com.ee.dto.request.UpdateUserRequest;
import com.ee.dto.request.RegistrationRequest;
import com.ee.dto.request.VerifyOTPRequest;
import com.ee.entities.AccountVerificationEntity;
import com.ee.entities.ResetAccountEntity;
import com.ee.entities.UserAccountEntity;
import com.ee.customexception.CustomerNotFoundException;
import com.ee.repository.AccountVerificationRepository;
import com.ee.repository.ResetAccountRepository;
import com.ee.repository.UserAccountRepository;
import org.eclipse.angus.mail.util.UUDecoderStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    private static UserAccountRepository userRepository = null;

    private final AccountVerificationRepository accountVerificationRepository;

    private final ResetAccountRepository resetAccountRepository;

    @Autowired
    private  EmailService emailService;

    @Autowired
    public UserService(UserAccountRepository userRepository, AccountVerificationRepository accountVerificationRepository, ResetAccountRepository resetAccountRepository) {

        this.userRepository = userRepository;
        this.accountVerificationRepository = accountVerificationRepository;
        this.resetAccountRepository = resetAccountRepository;
    }

    public String createUser(RegistrationRequest registrationRequest) throws DataIntegrityViolationException, IOException {

        UserAccountEntity user = new UserAccountEntity();

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setEmail(registrationRequest.getEmail());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());

        PasswordEncoder passwordEncrypt = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncrypt.encode(registrationRequest.getPassword().trim());

        user.setPassword(hashedPassword);
        user.setLastLoginDate(LocalDateTime.now());
        user.setRegistrationDate(LocalDateTime.now());
        user.setDateOfBirth(registrationRequest.getDateOfBirth());
        user.setAccountStatus("INACTIVE");
        userRepository.save(user);

        String otp = generateOTP();
        emailService.sendEmail(registrationRequest.getEmail(), "Activate Your Account - Complete Signup",registrationRequest.getLastName(),otp,"signup");

        AccountVerificationEntity accountVerificationEntity = new AccountVerificationEntity();
        accountVerificationEntity.setOtpPasscode(otp);
        accountVerificationEntity.setEmail(registrationRequest.getEmail());
        accountVerificationEntity.setExpirationTime(LocalDateTime.now().plusMinutes(15));
        accountVerificationEntity.setCreatedAt(LocalDateTime.now());
        accountVerificationRepository.save(accountVerificationEntity);


        return "created";
    }

    public String generateOTP(){

        // Define the characters that can be used in the code
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Generate a 6-character alphanumeric OTP
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            otp.append(characters.charAt(index));
        }

        return otp.toString();
    }

    public String findUser(LoginRequest loginProfile){


        Optional<UserAccountEntity> userProfile =  findUserByEmail(loginProfile.getEmail());


        if(userProfile.isPresent()) {
            PasswordEncoder passwordEncrypt = new BCryptPasswordEncoder();
            System.out.println(passwordEncrypt.matches(loginProfile.getPassword(), userProfile.get().getPassword()));

            if (passwordEncrypt.matches(loginProfile.getPassword(), userProfile.get().getPassword())) {
                System.out.println("login success");
                userProfile.get().setLastLoginDate(LocalDateTime.now());
                userRepository.save(userProfile.get());
                return userProfile.get().getFirstName();
            }else{
                throw new CustomerNotFoundException("Password does not match");

            }
        }


     throw new CustomerNotFoundException("");

    }

    public static Optional<UserAccountEntity> findUserByEmail(String email){

        return  userRepository.findByUserEmail(email);


    }

    public String verifyOTP(VerifyOTPRequest verifyOTPRequest){

        Optional<AccountVerificationEntity> accountVerificationEntity = accountVerificationRepository.findById(verifyOTPRequest.getEmail());

        if(accountVerificationEntity.isPresent()){

            if(verifyOTPRequest.getOtp().equalsIgnoreCase(accountVerificationEntity.get().getOtpPasscode())){

                Optional<UserAccountEntity> userAccountEntity =  findUserByEmail(verifyOTPRequest.getEmail());

                if(userAccountEntity.isPresent()) {
                    userAccountEntity.get().setAccountStatus("ACTIVE");
                    userRepository.save(userAccountEntity.get());
                    accountVerificationRepository.deleteById(verifyOTPRequest.getEmail());
                    return userAccountEntity.get().getFirstName();

                }

            }else{

                throw new AccountNotVerifiedException("Passcode is not matched");
            }
        }
        throw new AccountNotVerifiedException("");
    }

    public void deleteAll(){

        userRepository.deleteAll();
    }


    public String updateUserInfo(UpdateUserRequest updateUserProfile){

        Optional<UserAccountEntity> userProfile =  findUserByEmail(updateUserProfile.getEmail());

        if(userProfile.isPresent()){

            userProfile.get().setAddress(updateUserProfile.getAddress());
            userProfile.get().setCity(updateUserProfile.getCity());
            userProfile.get().setState(updateUserProfile.getState());
            userProfile.get().setZipcode(updateUserProfile.getZipcode());
            userProfile.get().setPhoneNumber(updateUserProfile.getPhoneNumber());
            userProfile.get().setUpdateTs(LocalDateTime.now());
            userProfile.get().setGender(updateUserProfile.getGender().isEmpty() ? "OTHER": updateUserProfile.getGender());
            userRepository.save(userProfile.get());

            return "Updated";
        }
     throw new CustomerNotFoundException("");
    }

//    sekhar code

    public static UpdateUserRequest getUserDetails(String email) {
        Optional<UserAccountEntity> userEntityOptional = findUserByEmail(email);
        if (userEntityOptional.isPresent()) {
            UserAccountEntity userEntity = userEntityOptional.get();
            UpdateUserRequest updateUserRequest = mapToUpdateUserRequest(userEntity);
            return updateUserRequest;
        } else {
            throw new CustomerNotFoundException("User not found with email: " + email);
        }
    }

    public static String updateUserDetails(UpdateUserRequest updateUserProfile) {
        Optional<UserAccountEntity> userEntityOptional = findUserByEmail(updateUserProfile.getEmail());
        if (userEntityOptional.isPresent()) {
            UserAccountEntity userEntity = userEntityOptional.get();
            userEntity.setAddress(updateUserProfile.getAddress());
            userEntity.setCity(updateUserProfile.getCity());
            userEntity.setState(updateUserProfile.getState());
            userEntity.setZipcode(updateUserProfile.getZipcode());
            userEntity.setPhoneNumber(updateUserProfile.getPhoneNumber());
            userEntity.setUpdateTs(LocalDateTime.now());
            userEntity.setGender(updateUserProfile.getGender().isEmpty() ? "OTHER" : updateUserProfile.getGender());
            userRepository.save(userEntity);
            return "User details updated successfully";
        } else {
            throw new CustomerNotFoundException("User not found with email: " + updateUserProfile.getEmail());
        }
    }

    private static UpdateUserRequest mapToUpdateUserRequest(UserAccountEntity userEntity) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail(userEntity.getEmail());
        updateUserRequest.setAddress(userEntity.getAddress());
        updateUserRequest.setCity(userEntity.getCity());
        updateUserRequest.setState(userEntity.getState());
        updateUserRequest.setZipcode(userEntity.getZipcode());
        updateUserRequest.setPhoneNumber(userEntity.getPhoneNumber());
        updateUserRequest.setGender(userEntity.getGender());
        return updateUserRequest;
    }


    //mycode




    public String sendPasswordResetEmail(String email) throws IOException {

        Optional<UserAccountEntity> userProfile =  findUserByEmail(email);

        if(userProfile.isPresent()){


            String token = UUID.randomUUID().toString();
            emailService.sendEmail(email, "Password Reset Request",userProfile.get().getLastName(),token,"password-reset");

            ResetAccountEntity resetAccountEntity = new ResetAccountEntity();
            resetAccountEntity.setEmail(email);
            resetAccountEntity.setToken(token);
            resetAccountEntity.setCreatedAt(LocalDateTime.now());
            resetAccountEntity.setExpirationTime(LocalDateTime.now().plusMinutes(50));
            resetAccountRepository.save(resetAccountEntity);

            return "Password Reset Request Email Sent Successfully";

        }


       throw new CustomerNotFoundException("password-reset");
    }

    public String resetPassword(String email,String token, String password){

        Optional<ResetAccountEntity> resetAccountEntity = resetAccountRepository.findById(email);

        if(resetAccountEntity.isPresent()){

            if(token.equalsIgnoreCase(resetAccountEntity.get().getToken())){

                Optional<UserAccountEntity> userEntity = findUserByEmail(email);

                if(userEntity.isPresent()){

                    PasswordEncoder passwordEncrypt = new BCryptPasswordEncoder();
                    String hashedPassword = passwordEncrypt.encode(password.trim());


                    userEntity.get().setPassword(hashedPassword);
                    userEntity.get().setUpdateTs(LocalDateTime.now());
                    userRepository.save(userEntity.get());

                    resetAccountRepository.deleteById(email);
                    return "successfully updated your password";
                }
            }
        }
    throw new CustomerNotFoundException("password-reset");
    }



}




