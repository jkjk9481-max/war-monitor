package war_monitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 모든 Controller 예외 잡아줘
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class) // NotFoundException 발생하면
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        // 404 응답으로 바꿔줘 , 또 ResponseEntity뜻은 HTTP 응답을 직접 만들수있다 상태코드 + 응답 바디 직접 설정가능
        Map<String , Object> body = new HashMap<>();
        body.put("status", 404);
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
        Map<String , Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("message", e.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> HttpMessageNotReadableException(HttpMessageNotReadableException e){
        Map<String , Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("message", "attackType 값이 올바르지 않습니다.");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        // HTTP 응답을 직접 만들어서 반환한다.
        // 응답 본문은 body를 넣고 ,
        // HTTP 상태 코드는 400 Bad Request로 설정한다.
    }
}
