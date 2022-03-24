import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    static int counter;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService mainEx = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<MyCallable> tasks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MyCallable mc = new MyCallable("поток номер " + (i + 1));
            tasks.add(mc);
        }
        List<Future<Integer>> futureTasks = mainEx.invokeAll(tasks);
        Integer ancwer = mainEx.invokeAny(tasks);

        mainEx.shutdown();
        System.out.println("Колличество напечатаных сообщений - " + ancwer);
    }

    static class MyCallable extends Thread implements Callable<Integer> {

        public MyCallable(String name) {
            super(name);
        }

        @Override
        public Integer call() throws Exception {
            try {
                System.out.println("Всем привет!Я " + getName());
                counter++;
                Thread.sleep(2500);
            } catch (InterruptedException err) {
                System.out.println("Поток спал, когда его пытались завершить");
            }
            return counter;
        }
    }
}

