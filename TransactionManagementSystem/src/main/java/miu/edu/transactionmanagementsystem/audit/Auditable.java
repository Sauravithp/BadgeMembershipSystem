package miu.edu.transactionmanagementsystem.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Auditable<U> {

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected LocalDate createdDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    protected LocalDate lastModifiedDate;

}

