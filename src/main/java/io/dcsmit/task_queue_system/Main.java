package io.dcsmit.task_queue_system;

public class Main {
    private static final int MAX_CONCURRENT_TASKS = 3;

    public static void main(String[] args) {
        TaskQueueManager taskQueueManager = new TaskQueueManager(MAX_CONCURRENT_TASKS);

        taskQueueManager.enqueueTasks(new Task[]
                {
                        new Task(1, 1000),
                        new Task(2,100),
                        new Task(3,4000),
                        new Task(4,2000),
                        new Task(5,300)
                });

        taskQueueManager.performTasks();
    }
}