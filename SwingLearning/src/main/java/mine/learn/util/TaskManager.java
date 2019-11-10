package mine.learn.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mine.learn.multithread.complicated.TaskItem;

/**
 * TaskManager
 * <p>
 * IMPORTANT TODO：好难！
 */
public class TaskManager<R, C extends Callable<R>> extends ArrayList<TaskItem<R, C>> {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    public void add(C task) {
        add(new TaskItem<R, C>(exec.submit(task), task));
    }

    public List<R> getRes() {
        Iterator<TaskItem<R, C>> items = iterator();
        List<R> res = new ArrayList<>();
        while (items.hasNext()) {
            TaskItem<R, C> item = items.next();
            if (item.future.isDone()) {
                try {
                    res.add(item.future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                items.remove();
            }
        }
        return res;
    }

    public List<String> purge() {
        Iterator<TaskItem<R, C>> items = iterator();
        List<String> res = new ArrayList<>();
        while (items.hasNext()) {
            TaskItem<R, C> item = items.next();
            if (!item.future.isDone()) {
                res.add("Cancelling " + item.task);
                item.future.cancel(true);
                items.remove();
            }
        }
        return res;
    }
}