package com.anu.springmysqlserver.entity;

import com.anu.springmysqlserver.exchanges.CreateDepartmentRequest;
import lombok.*;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "dep_name")
    @NonNull private String depName;

    private static boolean isValidBindValue(Class expectedType, Object value, TemporalType temporalType) {
        if (expectedType.isInstance(value)) {
            if (expectedType.isAssignableFrom(HibernateProxyHelper.getClassWithoutInitializingProxy(value))) {
                return true;
            }
        }
        return false;
    }
}
