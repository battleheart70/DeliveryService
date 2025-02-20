package delivery.advice;

import delivery.exceptions.FragileItemDistanceExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({IllegalArgumentException.class, FragileItemDistanceExceededException.class})
  public ResponseEntity<CustomErrorResponse> handleBadRequestExceptions(
      Exception ex, HttpServletRequest request) {
    CustomErrorResponse errorResponse = new CustomErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setError("Bad Request");
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setPath(request.getRequestURI());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // Можно добавить обработчик для других исключений, например, общий
  @ExceptionHandler(Exception.class)
  public ResponseEntity<CustomErrorResponse> handleGeneralException(
      Exception ex, HttpServletRequest request) {
    CustomErrorResponse errorResponse = new CustomErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.setError("Internal Server Error");
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setPath(request.getRequestURI());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
