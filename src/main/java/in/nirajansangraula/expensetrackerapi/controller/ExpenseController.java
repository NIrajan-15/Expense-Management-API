package in.nirajansangraula.expensetrackerapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import in.nirajansangraula.expensetrackerapi.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import in.nirajansangraula.expensetrackerapi.entity.Expense;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getExpenses()
    {
        return expenseService.getAllExpenses();
    }
    
}
