package in.davita.impact.erp.davitaauthserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.davitaauthserver.util.CommonResponse;

import java.io.Serializable;
import java.security.Principal;

/**
 * @Author : Prashant Wankhede on 30-04-2021
 */
@RestController
@RequestMapping("/api")
public class LogOutController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(LogOutController.class);

    @GetMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> userLogout(@RequestParam String userName, Principal principal) {
        LOGGER.info("Request Received for userLogout() ...");
        return new ResponseEntity<>(new CommonResponse("User Logout Successful.",true,null,HttpStatus.OK.value()), HttpStatus.ACCEPTED);
    }
}