package com.anu.springmysqlserver.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sub_name")
    @NonNull private String subName;

//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;

    private static boolean isValidBindValue(Class expectedType, Object value, TemporalType temporalType) {
        if (expectedType.isInstance(value)) {
            if (expectedType.isAssignableFrom(HibernateProxyHelper.getClassWithoutInitializingProxy(value))) {
                return true;
            }
        }
        return false;
    }
}
