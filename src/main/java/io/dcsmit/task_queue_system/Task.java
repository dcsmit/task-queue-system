package io.dcsmit.task_queue_system;

public class Task implements Runnable {
    private final int id;
    private final long duration;

    public Task(int id, long duration) {
        this.id = id;
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.printf("Taak %d%n", id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
