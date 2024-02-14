package com.nighthawk.spring_portfolio.mvc.Income;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@SpringBootApplication
public class IncomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncomeApplication.class, args);
    }
}

@Entity
class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double dailyIncome;

    public Income() {}

    public Income(double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public double getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }
}

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
