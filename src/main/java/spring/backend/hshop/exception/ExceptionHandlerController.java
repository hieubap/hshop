package spring.backend.hshop.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import spring.backend.hshop.dto.ResponseDTO;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseDTO<?> handleException(BaseException ex) {
    ResponseDTO<?> responseDTO = ex.getResponseDTO();
    return responseDTO;
  }
}
