// ResourceNotFoundException.java
package tn.esprit.devops_project.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
