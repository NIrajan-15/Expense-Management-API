package in.nirajansangraula.expensetrackerapi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.nirajansangraula.expensetrackerapi.entity.Expense;
import in.nirajansangraula.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.nirajansangraula.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.sql.Date;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserService userService;

    
    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        
        return expenseRepo.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        
        Optional<Expense> expense = expenseRepo.findById(id);
        if(expense.isPresent())
        {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense not found for the id "+ id);

    }

    @Override
    public Expense saveExpense(Expense expense)
    {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense, Long id) {
        
        Expense existingExpense = getExpenseById(id);

        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());

        return expenseRepo.save(existingExpense);
    }

    @Override
    public void deleteExpenseByID(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepo.delete(expense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page)
    {
        return expenseRepo.findByCategory(category, page).toList();
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page)
    {
        return expenseRepo.findByNameContaining(keyword, page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page)
    {
        startDate = startDate != null ? startDate : new Date(0);
        endDate = endDate != null ? endDate : new Date(System.currentTimeMillis());

        return expenseRepo.findByDateBetween(startDate, endDate, page).toList();
    }

    
}
