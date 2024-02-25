//package com.example.COGIP.Test;
//
//import com.example.COGIP.Repository.UserRepository;
//import com.example.COGIP.VariableType.UserType;
//import com.example.COGIP.Controller.UserCRUD; // Update the import to UserCRUD
//import org.testng.annotations.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//public class UserCRUDTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserCRUD userCRUD; // Autowire the UserCRUD class for testing
//
//    @Test
//    @Transactional
//    public void testCreateAndDeleteUser() {
//        // Create a user for testing
//        userCRUD.Create("testUser", "password123", "accountant"); // Update the method call
//
//        // Ensure the user is saved in the database
//        assert userRepository.findByUsername("testUser") != null;
//
//        // Delete the user
//        userCRUD.deleteByUsername("testUser"); // Update the method call
//
//        // Ensure the user is deleted from the database
//        assert userRepository.findByUsername("testUser") == null;
//    }
//}
