package engineer.dima.pathfinder;


import engineer.dima.pathfinder.route.RouteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            HandlerMethodValidationException.class,
            RouteNotFoundException.class,
    })
    public ResponseEntity<Void> handleBadRequestException() {

        return ResponseEntity.badRequest().build();
    }
}
