package com.bridgelabz.userloginregistration;

import com.bridgelabz.userloginregistration.exception.UserLoginException;
import com.bridgelabz.userloginregistration.pojo.User;
import com.bridgelabz.userloginregistration.repo.UserRepo;
import com.bridgelabz.userloginregistration.service.UserRegistration;
import com.bridgelabz.userloginregistration.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class UserRegistrationTest {

    UserRegistration register;
    
    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserService service = new UserService();


    @Before
    public void setUp() throws Exception {
        register = new UserRegistration();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void givenFirstName_WhenProper_ShouldReturnTrue(){
        boolean result = register.validateInput("Nishit Nalin", UserRegistration.ValidatorPat.NAME);
        Assert.assertTrue(result);
    }

    @Test
    public void givenFirstName_WhenShort_ShouldReturnFalse(){
        boolean result = register.validateInput("Nt", UserRegistration.ValidatorPat.NAME);
        Assert.assertFalse(result);
    }

    @Test
    public void givenLastName_WhenProper_ShouldReturnTrue(){
        boolean result = register.validateInput("Srivastava", UserRegistration.ValidatorPat.NAME);
        Assert.assertTrue(result);
    }

    @Test
    public void givenLastName_WhenShort_ShouldReturnFalse(){
        boolean result = register.validateInput("Ns", UserRegistration.ValidatorPat.NAME);
        Assert.assertFalse(result);
    }

    @Test
    public void givenEmail_WhenProper_ShouldReturnTrue(){
        boolean result = register.validateInput("abc@gamil.com", UserRegistration.ValidatorPat.EMAIL);
        Assert.assertTrue(result);
    }

    @Test
    public void givenEmail_WhenShort_ShouldReturnFalse(){
        boolean result = register.validateInput("@gmail.com", UserRegistration.ValidatorPat.EMAIL);
        Assert.assertFalse(result);
    }

    @Test
    public void givenMobileNumber_WhenProper_ShouldReturnTrue(){
        boolean result = register.validateInput("91 8937659775", UserRegistration.ValidatorPat.MOBILE);
        Assert.assertTrue(result);
    }

    @Test
    public void givenMobileNumber_WhenShort_ShouldReturnFalse(){
        boolean result = register.validateInput("3523423", UserRegistration.ValidatorPat.MOBILE);
        Assert.assertFalse(result);
    }

    @Test
    public void givenPassword_WhenProper_ShouldReturnTrue(){
        boolean result = register.validateInput("AVCDER123s&^$*df", UserRegistration.ValidatorPat.PASSWORD);
        Assert.assertTrue(result);
    }

    @Test
    public void givenPassword_WhenShort_ShouldReturnFalse(){
        boolean result = register.validateInput("secAs", UserRegistration.ValidatorPat.PASSWORD);
        Assert.assertFalse(result);
    }

    @Test
    public void givenEmail_WhenProper_ShouldReturnUser() throws UserLoginException, SQLException {
        User user = new User();
        String email = "nnalin04@gmail.com";
        String password = "Iamcool@1996";
        when(service.loginUser(email,password)).thenReturn(user);
        User userDetail = service.loginUser(email, password);
        Assert.assertEquals(user, userDetail);
    }
}
