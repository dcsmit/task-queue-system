package io.dcsmit.task_queue_system;

import java.util.concurrent.*;

public class TaskQueueManager {
    private final int maxConcurrentTasks;
    private final BlockingQueue<Task> taskQueue;
    private final ExecutorService taskExecutor;

    public TaskQueueManager(int maxConcurrentTasks) {
        this.maxConcurrentTasks = maxConcurrentTasks;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.taskExecutor = Executors.newFixedThreadPool(maxConcurrentTasks);
    }

    public void enqueueTasks(Task[] tasks) {
        for (Task task : tasks) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void performTasks() {
        for (int i = 0; i < maxConcurrentTasks; i++) {
            taskExecutor.submit(this::dequeueAndRunTasks);
        }
        taskExecutor.shutdown();
    }

    private void dequeueAndRunTasks() {
        while (!taskQueue.isEmpty()) {
            try {
                Task task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}