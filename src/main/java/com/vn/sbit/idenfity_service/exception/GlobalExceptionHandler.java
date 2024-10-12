package com.vn.sbit.idenfity_service.exception;

import com.vn.sbit.idenfity_service.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Exception chung
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }


    //xu ly user ton tai-user existed
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlingRunTimeException(RuntimeException exception){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(1001);
//        //loi nhan error
//        apiResponse.setMessage((exception.getMessage()));
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    //xử lý ngoại lệ bằng class (AppException)
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ApiResponse apiResponse = new ApiResponse();
        //AppException co construct ErrorCode
        ErrorCode errorCode = exception.getErrorCode();
        //code cua user_existed
        apiResponse.setCode(errorCode.getCode());
        //loi nhan error
        apiResponse.setMessage((errorCode.getMessage()));
        
        return ResponseEntity.badRequest().body(apiResponse);
    }

    //user tạo không đúng quy định - user validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();//trả về message ở size UserCreationRequest

        ErrorCode errorCode =ErrorCode.INVALID_KEY; // defaul sẽ luôn là lỗi này, đề phòng trường hợp sai không có code phù hợp

        try {
            errorCode = ErrorCode.valueOf(enumKey);//ép chuỗi string thành ErrorCode type
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        ApiResponse apiResponse= new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
