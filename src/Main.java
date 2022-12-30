import controller.Managers;
import controller.TaskManager;
import model.Status;
import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пришло время практики!");
        System.out.println("Начинаем тестирование бэк-а");
        final TaskManager taskManager = Managers.getDefault();

        System.out.println("Метод createTask(task). Начинаем тестирование.");
        final Task task = new Task("Новая задача",null,-1);
        System.out.println("Создаем 2 задачи");
        final Task createdTask = taskManager.createTask(task);
        final Task createdTask1 = taskManager.createTask(task);
        System.out.println("Печатаем содержание двух задач");
        System.out.println(createdTask);
        System.out.println(createdTask1);
        if (!task.equals(createdTask) && !createdTask.equals(createdTask1))
            System.out.println("Метод createTask(task) работает штатно");
        else
            System.out.println("Метод createTask(task) НЕ работает штатно");

        System.out.println("Метод findAllTask().Начинаем тестирование");
        List<Task> taskArrayList = taskManager.findAllTasks();
        System.out.println("Метод findAllTask(). Печатаем весь список задач");
        for (Task value : taskArrayList) {
            System.out.println(value);
        }
        if (taskArrayList.isEmpty()) {
            System.out.println("Метод findAllTask() не возвращает список задач");
        } else {
            System.out.println("Метод findAllTask() работает штатно");
        }

        System.out.println("Метод findTasksById(). Начинаем тестирование");
        Task foundTask = taskManager.findTaskById(2);
        taskManager.findTaskById(2);
        System.out.println("Печатем найденную задачу");
        System.out.println(foundTask);
        if (foundTask.getId() != null)
            System.out.println("Метод findById() работает штатно");
        else
            System.out.println("Метод findById() задачу не нашел");

        System.out.println("Печатем историю задач");
        List<Task> tasks = taskManager.getHistory();
        for (var t : tasks) {
            System.out.println(t);
        }

        // Раскомментировать для проверки удаления всиех задач и их же из истории
/*
        System.out.println("Удаляем все задачи и проверяем их удаление из истории");
        taskManager.deleteAllTasks();
        System.out.println("Печатем историю задач");
        List<Task> tasks1 = taskManager.getHistory();
        for (var t : tasks1) {
            System.out.println(t);
        }
*/

        System.out.println("Метод updateTaskById(). Начинаем тестирование");
        final Task createdTask2 = taskManager.updateTaskById(createdTask1);
        System.out.println("Печатем переданную в метод и обновленнную задачу");
        System.out.println(createdTask1);
        System.out.println(createdTask2);
        if (createdTask2.equals(createdTask1))
            System.out.println("Метод updateTaskById() работает штатно");
        else
            System.out.println("Метод createTaskById() НЕ работает");

        System.out.println("Метод deleteTaskById(). Начинаем тестирование");
        System.out.println("Печатем удаляемую задачу");
        System.out.println(taskManager.findTaskById(1));
        taskManager.findTaskById(1);
        if (taskManager.findTaskById(1) == null)
            System.out.println("Задача удалена. Метод deleteTaskById() работает штатно");
        else
            System.out.println("Метод deleteTaskById() НЕ работает");

        System.out.println("Метод deleteAllTasks(). Начинаем тестирование");
        taskManager.deleteAllTasks();
        if (taskManager.findAllTasks().isEmpty())
            System.out.println("Метод deleteAllTasks() работает штатно");
        else
            System.out.println("Метод deleteAllTasks() НЕ работает");

        System.out.println("Метод findEpic(id). Начинаем тестирование");
        final Epic epic = new Epic("Эпик", "descriptionOfEpic", -1);
        System.out.println("Создаем 2 эпика");
        final Epic createdEpic = taskManager.createEpic(epic);
        final Epic createdEpic1 = taskManager.createEpic(epic);
        System.out.println("Печатаем содержание двух задач");
        System.out.println(createdEpic);
        System.out.println(createdEpic1);
        if (!epic.equals(createdEpic) && !createdEpic.equals(createdEpic1))
            System.out.println("Метод createEpic(task) работает штатно");
        else
            System.out.println("Метод createEpic(task) НЕ работает");

        System.out.println("Метод createSubTask(subTask, epic). Начинаем тестирование");
        final SubTask subTask = new SubTask("Подзадача", "Описание", -1, 1);
        System.out.println("Создаем и печатаем 2 подзадачи одного эпика");
        final SubTask subTask1 = taskManager.createSubTask(subTask, createdEpic);
        final SubTask subTask2 = taskManager.createSubTask(subTask, createdEpic);
        System.out.println(subTask1);
        System.out.println(subTask2);
        System.out.println("Создаем и печатаем 2 подзадачи другого эпика");
        final SubTask subTask3 = taskManager.createSubTask(subTask, createdEpic1);
        final SubTask subTask4 = taskManager.createSubTask(subTask, createdEpic1);
        System.out.println(subTask3);
        System.out.println(subTask4);
        if (subTask1.getEpicID().equals(subTask2.getEpicID()) && subTask3.getEpicID().equals(subTask4.getEpicID()))
            System.out.println("Метод createSubTask(subTask,epic) работает штатно");
        else
            System.out.println("Метод createSubTask(subTask,epic) НЕ работает");

        System.out.println("Метод updateSubTaskById(). Начинаем тестирование");
        final SubTask subTaskNew = subTask1;

        System.out.println("Печатаем задачу до обновления");
        System.out.println(subTask1);
        subTaskNew.setStatus(Status.DONE);
        taskManager.updateTaskById(subTaskNew);
        System.out.println("Печатаем задачу после обновления");
        System.out.println(subTaskNew);
        System.out.println("Проверяем статус эпика");
        System.out.println(taskManager.findEpicById(1));

        System.out.println("Получение всех подзадач эпика");
        List<SubTask> listEpics = taskManager.findAllSubTasksOfEpic(taskManager.findEpicById(2));
        System.out.println(listEpics);

        System.out.println("Проверка обновления статуса и удаления подзадач");
        SubTask subTask5 = new SubTask("Подзадача5", "dsfa", 3, 2);
        SubTask subTask6 = new SubTask("Подзадача6", "hjsdk", 4, 2);
        subTask5.setStatus(Status.DONE);
        subTask6.setStatus(Status.DONE);
        taskManager.updateSubTaskById(subTask5);
        taskManager.updateSubTaskById(subTask6);
        System.out.println(listEpics);
        System.out.println(taskManager.findEpicById(2));
        System.out.println(taskManager.findAllSubTasksOfEpic(taskManager.findEpicById(2)));
        taskManager.deleteTaskById(3);
        System.out.println(taskManager.findAllSubTasksOfEpic(taskManager.findEpicById(2)));

        System.out.println("Метод findEpic(epic). Начинаем тестирование");
        Epic findedEpic = taskManager.findEpicById(1);
        System.out.println("Печатем найденный эпик");
        System.out.println(findedEpic);
        if (1 == findedEpic.getId()) {
            System.out.println("Метод createEpic(epic) работает штатно");
        } else {
            System.out.println("Метод createEpic(epic) НЕ работает");
        }

        Epic epic1 = taskManager.findEpicById(1);
        epic1 = taskManager.findEpicById(1);
        epic1 = taskManager.findEpicById(1);
        epic1 = taskManager.findEpicById(1);
        epic1 = taskManager.findEpicById(1);
        if (taskManager.getHistory().size() == 2) {
            System.out.println("История работает штатно. Количество задач: " + taskManager.getHistory().size());
        } else {
            System.out.println("История НЕ работает. Количество задач: " + taskManager.getHistory().size());
        }
        System.out.println("Печатаем историю эпиков");
        List<Task> epics = taskManager.getHistory();
        for (var e : epics) {
            System.out.println(e);
        }
    }
}