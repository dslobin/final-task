package by.epam.autoshow.validation;

public interface AbstractValidator<T> {
     boolean validate(T object);
}