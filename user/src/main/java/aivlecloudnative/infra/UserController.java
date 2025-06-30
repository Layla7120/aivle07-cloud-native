package aivlecloudnative.infra;
import aivlecloudnative.application.UserService;
import aivlecloudnative.domain.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/users")
@Transactional
public class UserController {
    @Autowired
    private UserService userService;

    //TODO: 비밀번호 추가 및 검사
    @RequestMapping(
            value = "/users/signup",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8"
    )
    public User signUp(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @RequestBody SignUpCommand signUpCommand
    ) throws Exception {
        System.out.println("##### /user/signUp  called #####");
        return userService.signUp(signUpCommand);
    }

    @RequestMapping(value = "/users/request-subscription",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public User requestSubscription(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @RequestBody RequestSubscriptionCommand requestSubscriptionCommand) throws Exception {
            System.out.println("##### /user/requestSubscription  called #####");
            return userService.requestSubscription(requestSubscriptionCommand);
    }

    @RequestMapping(value = "/users/request-content-access",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public User requestContentAccess(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @RequestBody RequestContentAccessCommand requestContentAccessCommand) throws Exception {
            System.out.println("##### /user/requestContentAccess  called #####");
            return userService.requestContentAccess(requestContentAccessCommand);
    }
}
//>>> Clean Arch / Inbound Adaptor
