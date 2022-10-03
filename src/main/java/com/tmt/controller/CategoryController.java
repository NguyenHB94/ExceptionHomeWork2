package com.tmt.controller;

import com.tmt.VM.CategoryVM;
import com.tmt.entity.Category;
import com.tmt.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }
    //method not allowed
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        var categorys = this.repo.findAll();
        return ResponseEntity.ok(categorys);
    }

    //Conflict
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        this.repo.deleteById(id);
        return "Delete success";
    }

    //badrequest
    @PatchMapping("/save")
    public ResponseEntity<Category> create(@RequestBody @Valid CategoryVM categoryVM) {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryVM.getCategoryName());
         this.repo.save(newCategory);

         return ResponseEntity.ok(newCategory);
    }
}
