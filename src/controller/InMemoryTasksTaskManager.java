package controller;

import model.Epic;
import model.SubTask;
import model.Task;
import java.time.LocalDateTime;
import java.util.*;

// Класс содержит CRUD методы для всех типов задач
public class InMemoryTasksTaskManager implements TaskManager {
    static TaskController taskController = new TaskController();
    static EpicController epicController = new EpicController();
    static SubTaskController subTaskController = new SubTaskController(epicController);
    static InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
    private static Set<Task> prioritizedTasks = new TreeSet<>(Comparator.<Task, LocalDateTime>comparing(
            t -> t.getStartTime(), Comparator.nullsLast(Comparator.naturalOrder())).thenComparingInt(Task :: getId));

    // Получение списка всех задач
    @Override
    public ArrayList<Task> findAllTasks() {
        return taskController.findAll();
    }

    // Получение списка всех эпиков
    @Override
    public ArrayList<Epic> findAllEpics() {
        return epicController.findAll();
    }

    // Получение списка всех подзадач определенного эпика
    @Override
    public ArrayList<SubTask> findAllSubTasksOfEpic(Epic epic) {
        return subTaskController.findAllOfEpic(epic);
    }

    // Получение подзадачи по идентификатору
    @Override
    public SubTask findSubTaskById(Integer id) {
        final SubTask subTask = subTaskController.findById(id);
        addInHistory(subTask);
        return subTask;
    }

    // Получение задачи по идентификатору
    @Override
    public Task findTaskById(Integer id) {
        final Task task = taskController.findById(id);
        addInHistory(task);
        return task;
    }

    // Получения эпика по идентификатору
    @Override
    public Epic findEpicById(Integer id) {
        final Epic epic = epicController.findById(id);
        addInHistory(epic);
        return epic;
    }

    // Добавление задачи
    @Override
    public Task createTask(Task task) {
        return taskController.create(task);
    }

    // Добавление эпика
    @Override
    public Epic createEpic(Epic epic) {
        return epicController.create(epic);
    }

    // Добавление подзадачи
    @Override
    public SubTask createSubTask(SubTask subTask, Epic epic) {
        return subTaskController.create(subTask, epic);
    }

    // Обновление задачи по ID
    @Override
    public Task updateTaskById(Task task) {
        return taskController.update(task);
    }

    // Обновление эпика по ID
    @Override
    public Epic updateEpicById(Epic epic) {
        return epicController.update(epic);
    }

    // Обновление подзадачи по ID
    @Override
    public SubTask updateSubTaskById(SubTask subTask) {
        return subTaskController.update(subTask);
    }

    // Удаление всех задач
    @Override
    public void deleteAllTasks() {
        // Если удаляемые задачи есть в истории то очищаем и их
        if (!inMemoryHistoryManager.getMap().isEmpty()) {
            for (var historyTask : inMemoryHistoryManager.getMap().values()) {
                for (var task : taskController.getTasks().values()) {
                    if (task.equals(historyTask.task)) {
                        inMemoryHistoryManager.remove(historyTask.task.getId());
                    }
                }
            }
        }
        taskController.deleteAll();
    }

    // Удаление эпиков
    @Override
    public void deleteAllEpics() {
        epicController.deleteAll();
    }

    // Удаление всех подзадач
    @Override
    public void deleteAllSubTasks() {
        subTaskController.deleteAll();
    }

    // Удаление подзадач по ID
    @Override
    public void deleteSubTaskById(Integer id) {
        removeFromHistoryById(id);
        subTaskController.deleteById(id);
    }

    // Удаление эпика по ID
    @Override
    public void deleteEpicById(Integer id) {
        removeFromHistoryById(id);
        epicController.deleteById(id);
    }

    // Удаление задачи по ID
    @Override
    public Task deleteTaskById(Integer id) {
        removeFromHistoryById(id);
        return taskController.deleteById(id);
    }

    // Получение задачи из истории по ID
    @Override
    public void removeFromHistoryById(int id) {
        inMemoryHistoryManager.remove(id);
    }

    // Получение истории
    @Override
    public List<Task> getHistory() {
        return inMemoryHistoryManager.getHistory();
    }

    // Добавление задачи в историю
    @Override
    public void addInHistory(Task task) {
        inMemoryHistoryManager.add(task);
    }

    // Удаление всей истории
    @Override
    public void removeAllHistory() {
        inMemoryHistoryManager.removeAll();
    }

    @Override
    public Set<Task> getPrioritizedTasks() {
        return prioritizedTasks;
    }
}
