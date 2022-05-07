package com.anu.springmysqlserver.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id", nullable = false)
    private Long id;
    @NonNull private String name;
    @NonNull private String country;

    @Column(name = "birth_year")
    @NonNull private Integer birthYear;

    @OneToOne
    @NonNull private Department department;

    @OneToMany
    @NonNull private List<Subject> subjects;

    private static boolean isValidBindValue(Class expectedType, Object value, TemporalType temporalType) {
        if (expectedType.isInstance(value)) {
            if (expectedType.isAssignableFrom(HibernateProxyHelper.getClassWithoutInitializingProxy(value))) {
                return true;
            }
        }
        return false;
    }
}
