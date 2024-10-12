package com.vn.sbit.idenfity_service.controller;

import com.nimbusds.jose.JOSEException;
import com.vn.sbit.idenfity_service.dto.ApiResponse;
import com.vn.sbit.idenfity_service.dto.request.AuthenticationRequest;
import com.vn.sbit.idenfity_service.dto.request.IntrospectRequest;
import com.vn.sbit.idenfity_service.dto.response.AuthenticationResponse;
import com.vn.sbit.idenfity_service.dto.response.IntrospectResponse;
import com.vn.sbit.idenfity_service.exception.ErrorCode;
import com.vn.sbit.idenfity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // tao construct de autowired
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    //create token
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticationApiResponse(@RequestBody AuthenticationRequest request){
        var result=authenticationService.Authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .code(ErrorCode.AUTHENTICATION_SUCCESS.getCode())
                .message(ErrorCode.AUTHENTICATION_SUCCESS.getMessage())
                .result(result)
                .build();
    }

        /*
        @PostMapping("/log-in")
    public ApiResponse<AuthenticationResponse> authenticationApiResponse(@RequestBody AuthenticationRequest request){
        var result=authenticationService.Authenticate(request);//true or false
        int code=result?200:201;
        String message = result ? "Authenticated" : "Authentication failed"; // Tin nhắn tương ứng
        return ApiResponse.<AuthenticationResponse>builder().code(code).message(message)
                .result(AuthenticationResponse.builder()//result sẽ có 1 đối tượng kiểu AuthenticationResponse
                        .authenticated(result) //
                        .build())
                                                                //Builder
                        // AuthenticationResponse authResponse = AuthenticationResponse.builder() //Bắt đầu quá trình xây dựng một đối tượng AuthenticationResponse.
                        //         .authenticated(result) // Thiết lập thuộc tính authenticated -(true or false vì property của class là boolean)
                        //         .build();// Tạo đối tượng AuthenticationResponse hoàn chỉnh

                .build();
          */
    //xac thuc token
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspectApiResponse(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result=authenticationService.introspectResponse(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(ErrorCode.AUTHENTICATION_TOKEN.getCode())
                .message(ErrorCode.AUTHENTICATION_TOKEN.getMessage())
                .result(result)
                .build();

    }


}
