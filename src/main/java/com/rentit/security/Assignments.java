package com.rentit.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.OneToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Assignments {

    /**
     */
    @OneToOne
    private Users userRentIt;

    /**
     */
    @OneToOne
    private Authorities authority;
}
