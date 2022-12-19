package testingConsole;

import javafx.concurrent.Task;

public class FiboCalcTask extends Task<Long> {

   private final int n;

   public FiboCalcTask(int n) {
      this.n = n;
   }

   @Override
   protected Long call() throws Exception {
      updateMessage("    Processing... ");
      long result = fibonacci(n);
      updateMessage("    Done.  ");
      return result;
   }

   public long fibonacci(long number) {
      if (number == 0 || number == 1)
         return number;
      else
         return fibonacci(number - 1) + fibonacci(number - 2);
   }
}

