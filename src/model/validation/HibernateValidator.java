package model.validation;

import java.util.Set;

import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.Player;

public class HibernateValidator {
	
	private HibernateValidator() {
        
    }

    public static boolean isPlayer(Player player) {
        
        ValidatorFactory validatorFactory  = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator(); 
        
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        if (!constraintViolations.isEmpty()) {
            String error = " ";
            error = constraintViolations.stream().map(constraintViolation -> "\n " + constraintViolation.getMessage() + "\n ").reduce(error, String::concat);
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
        return true;
    }
}
