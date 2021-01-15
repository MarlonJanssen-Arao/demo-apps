package tech.arao.minibank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserTransactionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 82794609103822343L;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;


    public UserTransactionNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("No transaction(s) found for %s with %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
