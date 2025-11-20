package org.example.bigproject2.Repository;

import org.example.bigproject2.Model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp,Integer> {
}