package in.co.mbs.constraint;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author santhosh
 *
 */

@Configuration
@Constraint(validatedBy = MaxSeatValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSeatConstraint {

	String message() default "Seat size exceeded. Maximum 6 seats allowed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}