package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-20T05:12:22.617397507Z[GMT]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request, UserService userService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userService = userService;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "Created user object", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        userService.createOrUpdateUser(body);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "ID of user", required=true, schema=@Schema()) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> findUserById(@Parameter(in = ParameterIn.PATH, description = "ID of user", required=true, schema=@Schema()) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                User user = userService.getById(userId);
                if (user != null) {
                    String content = "{\n  \"firstName\" : \"" + user.getFirstName() + "\",\n  \"lastName\" : \"" + user.getLastName() + "\",\n  \"phone\" : \"" + user.getPhone() + "\",\n  \"id\" : \"" + user.getId() + "\",\n  \"email\" : \"" + user.getEmail() + "\",\n  \"username\" : \"" + user.getUsername() + "\"\n}";
                    return new ResponseEntity<User>(objectMapper.readValue(content, User.class), HttpStatus.OK);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "ID of user", required=true, schema=@Schema()) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                User user = userService.getById(userId);
                if (user != null) {
                    if (!user.getUsername().equals(body.getUsername())) {
                        user.setUsername(body.getUsername());
                    }
                    if (!user.getEmail().equals(body.getEmail())) {
                        user.setEmail(body.getEmail());
                    }
                    if (!user.getPhone().equals(body.getPhone())) {
                        user.setPhone(body.getPhone());
                    }
                    if (!user.getLastName().equals(body.getLastName())) {
                        user.setLastName(body.getLastName());
                    }
                    if (!user.getFirstName().equals(body.getFirstName())) {
                        user.setFirstName(body.getFirstName());
                    }
                    userService.createOrUpdateUser(user);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
