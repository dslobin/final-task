package by.epam.autoshow.validation;

/**
 * Default entity validator
 *
 * @param <T> the type of element in this validator
 * @author Daniil Slobin
 */
public interface AbstractValidator<T> {
    /**
     * @param object entity to validate
     * @return {@code true} if object is valid
     */
    boolean validate(T object);
}