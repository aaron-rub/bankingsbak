package com.nighthawk.spring_portfolio.mvc.Income;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
interface IncomeRepository extends JpaRepository<Income, Long> {}

@RestController
@RequestMapping("/incomes")
class IncomeController {
    private final IncomeRepository repository;

    IncomeController(IncomeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Income> all() {
        return repository.findAll();
    }

    @PostMapping
    Income newIncome(@RequestBody Income newIncome) {
        return repository.save(newIncome);
    }
}