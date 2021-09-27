package in.co.mbs.constraint;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author santhosh
 *
 */
@Configuration
public class MaxSeatValidator implements ConstraintValidator<MaxSeatConstraint, List<String>> {

	@Override
	public boolean isValid(List<String> values, ConstraintValidatorContext context) {
		return values.size() <= 6;
	}
}