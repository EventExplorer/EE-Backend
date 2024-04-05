//package com.ee;
//
//import com.ee.dto.request.LoginRequest;
//import com.ee.dto.request.RegistrationRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    public void testGetAllUsers() throws Exception {
//        // Perform GET request to the "/users" endpoint
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/test"))
//                .andExpect(status().isOk()) // Expect HTTP 200 OK status
//                .andExpect(jsonPath("$").isArray()) // Expect the response to be a JSON array
//                .andExpect(jsonPath("$.length()").value(2)) // Assuming there are 2 users in the response
//                .andExpect(jsonPath("$[0].id").exists()) // Expect each user to have an 'id' field
//                .andExpect(jsonPath("$[0].username").exists()) // Expect each user to have a 'username' field
//                .andExpect(jsonPath("$[0].email").exists()) // Expect each user to have an 'email' field*/
//                .andReturn();
//
//        // You can further validate specific values in the response if needed
//        // For example:
//        // .andExpect(jsonPath("$[0].username").value("johnDoe"))
//
//        // You can also retrieve the response content if necessary
//        String responseBody = result.getResponse().getContentAsString();
//        System.out.println("Response Body: " + responseBody);
//    }
//    @Test
//    public void testSignUp() throws Exception{
//
//        RegistrationRequest user = new RegistrationRequest();
//        user.setUserUUID(UUID.randomUUID().toString());
//        user.setEmail("dfgdfgdgfgf@gmail.com");
//        user.setFirstName(null);
//        user.setLastName("asdsadfas");
//        user.setPassword("@08711A1228d");
//
//        String requestBody = objectMapper.writeValueAsString(user);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//
//        String responseBody = result.getResponse().getContentAsString();
//        System.out.println("Response Body: " + responseBody);
//    }
//    @Test
//    public void testLogin() throws Exception{
//
//        LoginRequest loginProfile = new LoginRequest();
//
//        loginProfile.setEmail("sadas@gmail.com");
//        loginProfile.setPassword("asdasdasdad");
//        String requestBody = objectMapper.writeValueAsString(loginProfile);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                        .andExpect(status().isNotFound())
//                        .andReturn();
//
//
//        String responseBody = result.getResponse().getContentAsString();
//        System.out.println("Response Body: " + responseBody);
//
//
//
//    }
//}
//
