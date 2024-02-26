package in.nirajansangraula.expensetrackerapi.Service;

import in.nirajansangraula.expensetrackerapi.entity.Expense;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.sql.Date;

public interface ExpenseService {
    
    Page<Expense> getAllExpenses(Pageable page);

    Expense getExpenseById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Expense expense, Long id);

    void deleteExpenseByID(Long id);

    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> readByName(String keyword, Pageable page);

    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);

}
