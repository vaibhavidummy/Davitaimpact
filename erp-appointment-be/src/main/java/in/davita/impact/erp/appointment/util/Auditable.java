package in.davita.impact.erp.appointment.util;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@SuppressWarnings("hiding")
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<Object> {


    @CreatedBy
    //@Column(nullable = false, updatable = false)
    protected String createdBy;

    @CreatedDate
    //@Column(nullable = false, updatable = false)
    protected Date creationOn;

    @LastModifiedBy
    //@Column(nullable = false)
    protected String lastModifiedBy;

    @LastModifiedDate
    //@Column(nullable = false)
    protected Date lastModifiedOn;

}
