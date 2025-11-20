package org.example.bigproject2.Service;

import org.example.bigproject2.Model.Emp;
import org.example.bigproject2.Repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;

@Service
public class EmpService{
    @Autowired
    private EmpRepository empRepository;

    public List<Emp> getAllEmp(){
        return empRepository.findAll();
    }

    @Cacheable(value= "emps" , key = "#id")
    public Emp getEmpById(Integer id){
        return empRepository.findById(id).orElseThrow();
    }
    @CachePut(value = "emps", key = "#emp.empno")
    public Emp addEmp(Emp emp) {
        return empRepository.save(emp);
    }
    @CachePut(value = "emps",key="#emp.empno")
    public Emp updateEmp(Emp emp){
        Optional<Emp> empOptional = empRepository.findById(emp.getEmpno());
        if(empOptional.isPresent()){
            Emp emp1 = empOptional.get();
            emp1.setEname(emp.getEname());
            emp1.setJob(emp.getJob());
            emp1.setSal(emp.getSal());
            emp1.setComm(emp.getComm());
            emp1.setDept(emp.getDept());
            emp1.setMgr(emp.getMgr());
            if (emp.getImgUrl() != null) {
                emp1.setImgUrl(emp.getImgUrl());
            }
            return empRepository.save(emp1);

        }
        return null;

    }
    @CacheEvict(value = "emps", key= "#id")
    public void deleteEmp(Integer id){
        empRepository.deleteById(id);
    }

}