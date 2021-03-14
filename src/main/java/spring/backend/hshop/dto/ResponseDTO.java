package spring.backend.hshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO<T> {
  private Integer code;
  private String message;
  private T data;
  private Integer numberOfElements;
  private Integer totalElements;

  public ResponseDTO(T data){
    code = 200;
    message = "successful";
    this.data = data;
    this.numberOfElements = 1;
    this.totalElements = this.numberOfElements;
  }

  public ResponseDTO(String message, T data) {
    this(data);
    this.message = message;
  }

  public ResponseDTO(Integer code, String message, T data) {
    this(message, data);
    this.code = code;
  }

  public ResponseDTO(Integer code, String message, T data,Integer number) {
    this(code, message, data);
    this.numberOfElements = number;
  }
  public ResponseDTO(Integer code, String message, T data,Integer number,Integer total) {
    this(code, message, data);
    this.numberOfElements = number;
    this.totalElements = total;
  }

}
