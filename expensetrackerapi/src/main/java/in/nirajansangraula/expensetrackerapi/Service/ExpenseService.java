package in.nirajansangraula.expensetrackerapi.Service;

import in.nirajansangraula.expensetrackerapi.entity.Expense;
import java.util.List;


public interface ExpenseService {
    
    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Expense expense, Long id);

    void deleteExpenseByID(Long id);

}
