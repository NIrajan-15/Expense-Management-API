package in.nirajansangraula.expensetrackerapi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import in.nirajansangraula.expensetrackerapi.entity.Expense;
import in.nirajansangraula.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepo;

    
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
        throw new RuntimeException("Expense not found for the id "+ id);

    }

    @Override
    public Expense saveExpense(Expense expense)
    {
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
        expenseRepo.deleteById(id);
    }

    
}
