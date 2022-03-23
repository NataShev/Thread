import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Collection<Thread> mainGroup = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            mainGroup.add(new MyThread("номер " + (i + 1)));
        }
        for (Thread thread : mainGroup) {
            thread.start();
        }
        Thread.sleep(15_000);
        for (Thread thread : mainGroup) {
            thread.interrupt();
        }
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(2500);
                    System.out.println("Всем привет!Я поток " + getName());
                }
            } catch (InterruptedException err) {
                System.out.println("Поток спал, когда его пытались завершить!");
            } finally {
                System.out.printf("%s завершен\n", getName());
            }
        }
    }
}
