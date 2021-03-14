package spring.backend.hshop.exception;

import spring.backend.hshop.dto.ResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {
  private ResponseDTO<?> responseDTO;

  public BaseException(int code, String message, Object data) {
    responseDTO = new ResponseDTO<>(code,message,data);
  }
}
