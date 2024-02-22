package in.nirajansangraula.expensetrackerapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import in.nirajansangraula.expensetrackerapi.Service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import in.nirajansangraula.expensetrackerapi.entity.Expense;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // get all expenses
    @GetMapping("/expenses")
    public List<Expense> getExpenses(Pageable page)
    {
        return expenseService.getAllExpenses(page).toList();
    }

    // get expense by id using path variable
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id)
    {
        return expenseService.getExpenseById(id);
    }

    // save expense
    @ResponseStatus(value =  HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpense(@RequestBody Expense expense)
    {
        return expenseService.saveExpense(expense);
        
    }

    // update expense by id
    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable("id") Long id)
    {
        return expenseService.updateExpense(expense, id);
    }


    // delete expense by id
    @ResponseStatus(value =  HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void  deelteExpenseByID(@RequestParam("id") Long id)
    {
        expenseService.deleteExpenseByID(id); 
    }

    
}
