package spring.backend.hshop.exception;


import spring.backend.hshop.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<?> handleException(BaseException ex) {
    ResponseDTO<?> responseDTO = ex.getResponseDTO();
    return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(ex.getResponseDTO().getCode()));
  }
}
