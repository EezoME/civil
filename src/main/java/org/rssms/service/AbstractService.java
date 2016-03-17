package org.rssms.service;


import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 *
 * Created by Eezo on 18.03.2016.
 */
public abstract class AbstractService<T> {
    @Resource
    Validator validator;

    /**
     * Validates entity using {Validator}
     * Check code look like this:
     * <pre>
     *  String s = validateEntity(entity);
     *  if (s != null){
     *      throw new InvalidEntityException(s);
     *  }
     * </pre>
     * @param clazz entity class
     * @return a message from validator if it's some inconsistencies,<br/>
     * null - if the validation is successful<br/>
     */
    public String validateEntity(T clazz) {
        // validate T with entity validations
        Set<ConstraintViolation<T>> violationSet = validator.validate(clazz);
        for (ConstraintViolation<T> violation : violationSet){
            return (violation.getPropertyPath() + " " + violation.getMessage());
        }
        return null;
    }
}
