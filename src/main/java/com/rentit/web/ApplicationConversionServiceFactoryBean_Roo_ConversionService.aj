// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.rentit.web;

import com.rentit.main.Invoice;
import com.rentit.main.Plant;
import com.rentit.main.PurchaseOrder;
import com.rentit.repository.PlantRepository;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.security.Assignments;
import com.rentit.security.Authorities;
import com.rentit.security.Users;
import com.rentit.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    PlantRepository ApplicationConversionServiceFactoryBean.plantRepository;
    
    @Autowired
    PurchaseOrderRepository ApplicationConversionServiceFactoryBean.purchaseOrderRepository;
    
    public Converter<Invoice, String> ApplicationConversionServiceFactoryBean.getInvoiceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.rentit.main.Invoice, java.lang.String>() {
            public String convert(Invoice invoice) {
                return new StringBuilder().append(invoice.getAmountDue()).append(' ').append(invoice.getDueDate()).toString();
            }
        };
    }
    
    public Converter<Long, Invoice> ApplicationConversionServiceFactoryBean.getIdToInvoiceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.rentit.main.Invoice>() {
            public com.rentit.main.Invoice convert(java.lang.Long id) {
                return Invoice.findInvoice(id);
            }
        };
    }
    
    public Converter<String, Invoice> ApplicationConversionServiceFactoryBean.getStringToInvoiceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.rentit.main.Invoice>() {
            public com.rentit.main.Invoice convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Invoice.class);
            }
        };
    }
    
    public Converter<Plant, String> ApplicationConversionServiceFactoryBean.getPlantToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.rentit.main.Plant, java.lang.String>() {
            public String convert(Plant plant) {
                return new StringBuilder().append(plant.getPrice()).append(' ').append(plant.getName()).append(' ').append(plant.getDescription()).append(' ').append(plant.getStartDate()).toString();
            }
        };
    }
    
    public Converter<Long, Plant> ApplicationConversionServiceFactoryBean.getIdToPlantConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.rentit.main.Plant>() {
            public com.rentit.main.Plant convert(java.lang.Long id) {
                return plantRepository.findOne(id);
            }
        };
    }
    
    public Converter<String, Plant> ApplicationConversionServiceFactoryBean.getStringToPlantConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.rentit.main.Plant>() {
            public com.rentit.main.Plant convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Plant.class);
            }
        };
    }
    
    public Converter<PurchaseOrder, String> ApplicationConversionServiceFactoryBean.getPurchaseOrderToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.rentit.main.PurchaseOrder, java.lang.String>() {
            public String convert(PurchaseOrder purchaseOrder) {
                return new StringBuilder().append(purchaseOrder.getSiteId()).append(' ').append(purchaseOrder.getCompanyName()).append(' ').append(purchaseOrder.getStartDate()).append(' ').append(purchaseOrder.getEndDate()).toString();
            }
        };
    }
    
    public Converter<Long, PurchaseOrder> ApplicationConversionServiceFactoryBean.getIdToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.rentit.main.PurchaseOrder>() {
            public com.rentit.main.PurchaseOrder convert(java.lang.Long id) {
                return purchaseOrderRepository.findOne(id);
            }
        };
    }
    
    public Converter<String, PurchaseOrder> ApplicationConversionServiceFactoryBean.getStringToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.rentit.main.PurchaseOrder>() {
            public com.rentit.main.PurchaseOrder convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PurchaseOrder.class);
            }
        };
    }
    
    public Converter<Assignments, String> ApplicationConversionServiceFactoryBean.getAssignmentsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.rentit.security.Assignments, java.lang.String>() {
            public String convert(Assignments assignments) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, Assignments> ApplicationConversionServiceFactoryBean.getIdToAssignmentsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.rentit.security.Assignments>() {
            public com.rentit.security.Assignments convert(java.lang.Long id) {
                return Assignments.findAssignments(id);
            }
        };
    }
    
    public Converter<String, Assignments> ApplicationConversionServiceFactoryBean.getStringToAssignmentsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.rentit.security.Assignments>() {
            public com.rentit.security.Assignments convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Assignments.class);
            }
        };
    }
    
    public Converter<Authorities, String> ApplicationConversionServiceFactoryBean.getAuthoritiesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.rentit.security.Authorities, java.lang.String>() {
            public String convert(Authorities authorities) {
                return new StringBuilder().append(authorities.getAuthority()).toString();
            }
        };
    }
    
    public Converter<Long, Authorities> ApplicationConversionServiceFactoryBean.getIdToAuthoritiesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.rentit.security.Authorities>() {
            public com.rentit.security.Authorities convert(java.lang.Long id) {
                return Authorities.findAuthorities(id);
            }
        };
    }
    
    public Converter<String, Authorities> ApplicationConversionServiceFactoryBean.getStringToAuthoritiesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.rentit.security.Authorities>() {
            public com.rentit.security.Authorities convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Authorities.class);
            }
        };
    }
    
    public Converter<Users, String> ApplicationConversionServiceFactoryBean.getUsersToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.rentit.security.Users, java.lang.String>() {
            public String convert(Users users) {
                return new StringBuilder().append(users.getUsername()).append(' ').append(users.getPassword()).toString();
            }
        };
    }
    
    public Converter<Long, Users> ApplicationConversionServiceFactoryBean.getIdToUsersConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.rentit.security.Users>() {
            public com.rentit.security.Users convert(java.lang.Long id) {
                return Users.findUsers(id);
            }
        };
    }
    
    public Converter<String, Users> ApplicationConversionServiceFactoryBean.getStringToUsersConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.rentit.security.Users>() {
            public com.rentit.security.Users convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Users.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getInvoiceToStringConverter());
        registry.addConverter(getIdToInvoiceConverter());
        registry.addConverter(getStringToInvoiceConverter());
        registry.addConverter(getPlantToStringConverter());
        registry.addConverter(getIdToPlantConverter());
        registry.addConverter(getStringToPlantConverter());
        registry.addConverter(getPurchaseOrderToStringConverter());
        registry.addConverter(getIdToPurchaseOrderConverter());
        registry.addConverter(getStringToPurchaseOrderConverter());
        registry.addConverter(getAssignmentsToStringConverter());
        registry.addConverter(getIdToAssignmentsConverter());
        registry.addConverter(getStringToAssignmentsConverter());
        registry.addConverter(getAuthoritiesToStringConverter());
        registry.addConverter(getIdToAuthoritiesConverter());
        registry.addConverter(getStringToAuthoritiesConverter());
        registry.addConverter(getUsersToStringConverter());
        registry.addConverter(getIdToUsersConverter());
        registry.addConverter(getStringToUsersConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
