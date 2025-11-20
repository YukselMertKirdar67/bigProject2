package org.example.bigproject2.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.Manager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="emp")
public class Emp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empno")
    private Integer empno;
    private String ename;
    private String job;
    private LocalDate hiredate;
    private Integer sal;
    private Integer comm;
    private String imgUrl;
    @ManyToOne(optional = true , fetch = FetchType.EAGER)
    @JoinColumn(name="mgr", referencedColumnName = "empno")
    private Emp mgr;
    @ManyToOne(optional = true , fetch = FetchType.EAGER)
    @JoinColumn(name = "deptno",referencedColumnName = "deptno")
    private Dept dept;


}