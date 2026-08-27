package shrekanddonkey.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests removing tasks from a {@link TaskList}.
 */
public class TaskListTest {
    @Test
    public void remove_firstTask_returnsTaskAndUpdatesSize() {
        Task firstTask = new Task("first task");
        Task secondTask = new Task("second task");
        TaskList taskList = createTaskList(firstTask, secondTask);

        Task removedTask = taskList.remove(0);

        assertSame(firstTask, removedTask);
        assertEquals(1, taskList.size());
        assertSame(secondTask, taskList.get(0));
    }

    @Test
    public void remove_middleTask_preservesRemainingOrder() {
        Task firstTask = new Task("first task");
        Task middleTask = new Task("middle task");
        Task lastTask = new Task("last task");
        TaskList taskList = createTaskList(firstTask, middleTask, lastTask);

        Task removedTask = taskList.remove(1);

        assertSame(middleTask, removedTask);
        assertEquals(2, taskList.size());
        assertSame(firstTask, taskList.get(0));
        assertSame(lastTask, taskList.get(1));
    }

    @Test
    public void remove_onlyTask_returnsTaskAndLeavesEmptyList() {
        Task onlyTask = new Task("only task");
        TaskList taskList = createTaskList(onlyTask);

        assertSame(onlyTask, taskList.remove(0));
        assertEquals(0, taskList.size());
    }

    @Test
    public void remove_emptyList_throwsIndexOutOfBoundsException() {
        TaskList taskList = new TaskList();

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(0));
    }

    @Test
    public void remove_negativeIndex_throwsIndexOutOfBoundsException() {
        TaskList taskList = createTaskList(new Task("task"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(-1));
    }

    @Test
    public void removeIndexPastEnd_throwsIndexOutOfBoundsException() {
        TaskList taskList = createTaskList(new Task("task"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(1));
    }

    private TaskList createTaskList(Task... tasks) {
        TaskList taskList = new TaskList();
        for (Task task : tasks) {
            taskList.add(task);
        }
        return taskList;
    }
}
