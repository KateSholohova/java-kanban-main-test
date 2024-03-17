package tests;

import static org.junit.jupiter.api.Assertions.*;

import main.managers.*;
import main.status.Status;
import main.tasks.*;
import org.junit.jupiter.api.Test;

class InMemoryTaskManagerTest {

    @Test
    public void hashCodeShouldBeEqualsForTasks() {
        Task task1 = new Task("Первая задача", "...", Status.NEW);
        task1.setId(1);
        Task task2 = new Task("Первая задача", "...", Status.NEW);
        task2.setId(1);
        assertEquals(task1.hashCode(), task2.hashCode(), "Должно быть одинаковое значение hashcode");
    }

    @Test
    public void hashCodeShouldBeEqualsForEpics() {
        Epic epic1 = new Epic("Первый эпик", "описание");
        epic1.setId(1);
        Epic epic2 = new Epic("Первый эпик", "описание");
        epic2.setId(1);
        assertEquals(epic1.hashCode(), epic2.hashCode(), "Должно быть одинаковое значение hashcode");
    }

    @Test
    public void hashCodeShouldBeEqualsForSubtasks() {
        Epic epic1 = new Epic("Первый эпик", "описание");
        epic1.setId(1);
        Subtask subtask1 = subtask1 = new Subtask("Первая подзадача", " ", Status.NEW, 1);
        subtask1.setId(2);
        Subtask subtask2 = subtask1 = new Subtask("Первая подзадача", " ", Status.NEW, 1);
        subtask2.setId(2);
        assertEquals(subtask1.hashCode(), subtask2.hashCode(), "Должно быть одинаковое значение hashcode");
    }

    @Test
    public void CanNotAddEpicInEpic () {
        Managers managers = new Managers();
        TaskManager manage = managers.getDefault();
        Epic epic = new Epic("Первый эпик", "описание");
        manage.putEpic(epic);
        Subtask subtask = new Subtask("Первая подзадача", " ", Status.NEW, 1);
        manage.putSubtask(subtask);
        epic.getSubtaskId().add(epic.getId());
        manage.getEpicById(1).getSubtaskId().add(epic.getId());
        assertNull(manage.getSubtaskById(1));

    }

    @Test
    public void SubtaskCanNotBeEpic () {
        Managers managers = new Managers();
        TaskManager manage = managers.getDefault();
        Epic epic = new Epic("Первый эпик", "описание");
        manage.putEpic(epic);
        Subtask subtask = new Subtask("Первая подзадача", " ", Status.NEW, 2);
        assertNull(manage.getEpicById(subtask.getEpicId()));
    }





}