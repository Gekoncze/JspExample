package beans;

import other.ValidationCounter;


public class ValidationCountBean {
    public int getValidationCount(){
        return ValidationCounter.getCount();
    }
}
