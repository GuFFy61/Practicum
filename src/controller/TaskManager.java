package controller;

import model.Epic;
import model.Task;
import model.SubTask;
import java.util.List;
import java.util.Set;

public interface TaskManager {
    // Получение списка всех задач
    List<Task> findAllTasks();

    // Получение списка всех эпиков
    List<Epic> findAllEpics();

    // Получение списка всех подзадач определенного эпика
    List<SubTask> findAllSubTasksOfEpic(Epic epic);

    // Получение подзадачи по идентификатору
    SubTask findSubTaskByID(Integer id);

    // Получение задачи по идентификатору
    Task findTaskByID(Integer id);

    // Получение эпика по идентификатору
    Epic findEpicByID(Integer id);

    // Добавление задачи
    Task createTask(Task task);

    // Добавление эпика
    Epic createEpic(Epic epic);

    // Добавление поодзадачи
    SubTask createSubTask(SubTask subTask,Epic epic);

    // Обновление задачи
    Task updateTaskByID(Task task);

    // Обновление эпика
    Epic updateEpicByID(Epic epic);

    // Обновление подзадачи
    SubTask updateSubTaskById(SubTask subTask);

    // Удаление всех задач
    void deleteAllTasks();

    // Удаление всех эпиков
    void deleteAllEpics();

    // Удаление всех подзадач
    void deleteAllSubTasks();

    // Удаление подзадачи по ID
    void deleteSubTaskByID(Integer id);

    // Удаление эпика по ID
    void deleteEpicById(Integer id);

    // Удаление задачи по ID
    Task deleteTaskByID(Integer id);

    // Удаление задачи из истории по ID
    void removeFromHistoryByID(int id);

    // Получение истории
    List<Task> getHistory();

    // Добавление задачи в историю
    void addInHistory(Task task);

    // Удаление всей истории
    void removeAllHistory();

    // Получение сортированного множества
    Set<Task> getPrioritizedTasks();
}
