package in.nirajansangraula.expensetrackerapi.Service;

import in.nirajansangraula.expensetrackerapi.entity.Expense;
import java.util.List;
import org.springframework.stereotype.Service;

public interface ExpenseService {
    List<Expense> getAllExpenses();
}
