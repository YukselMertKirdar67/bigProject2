package org.example.bigproject2.Controller;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.thirdparty.protobuf.compiler.PluginProtos;
import org.example.bigproject2.Model.Emp;
import org.example.bigproject2.Repository.EmpRepository;
import org.example.bigproject2.Service.EmpService;
import org.example.bigproject2.Service.HadoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@Controller
public class EmpController{
    @Autowired
    private EmpService empService;
    @Autowired
    private HadoopService hadoopService;

    @GetMapping("/")
    public String index(Model model){
        List<Emp> emps = empService.getAllEmp();

        emps.forEach(emp -> {
            if(emp.getImgUrl()!=null){
                emp.setImgUrl("http://127.0.0.1:9870/webhdfs/v1/user/hadoop/images2/" +emp.getImgUrl() + "?op=OPEN");
            }
        });
        model.addAttribute("emps",emps);
        return "index";

    }
    @GetMapping("/get/{id}")
    public String getEmpById(@PathVariable("id") Integer id, Model model) {
        Emp emp = empService.getEmpById(id);
        if (emp == null) {
            return "redirect:/";
        }
        if (emp.getImgUrl() != null) {
            emp.setImgUrl("http://127.0.0.1:9870/webhdfs/v1/user/hadoop/images2/"
                    + emp.getImgUrl() + "?op=OPEN");
        }
        model.addAttribute("emp", emp);
        return "emp";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("emp", new Emp());
        return "add";
    }
    @PostMapping("/add")
    public String addEmp(@ModelAttribute("emp") Emp emp, @RequestParam("img") MultipartFile file){
        try{
            if(file != null && !file.isEmpty()){
                String hdfsFileName = hadoopService.uploadImage(file);
                emp.setImgUrl(hdfsFileName);
            }
            empService.addEmp(emp);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        Emp emp = empService.getEmpById(id);
        if(emp == null){
            return "redirect:/";
        }
        model.addAttribute("emp", emp);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateEmp(@PathVariable("id") Integer id ,@ModelAttribute("emp") Emp emp, @RequestParam("img") MultipartFile file) {
        try{
            if(!file.isEmpty()){
                String filename = file.getOriginalFilename();
                Configuration conf = new Configuration();
                conf.set("fs.defaultFS", "hdfs://localhost:9000/");
                FileSystem fs = FileSystem.get(conf);

                Path path = new Path("/user/hadoop/images2/"+filename);
                try (FSDataOutputStream out = fs.create(path , true);
                InputStream in = file.getInputStream()) {
                    IOUtils.copyBytes(in,out,conf);
                }
                emp.setImgUrl(filename);
            }
            emp.setEmpno(id);
            empService.updateEmp(emp);
        }
        catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        empService.deleteEmp(id);
        return "redirect:/";
    }
}
