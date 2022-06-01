package ua.edu.sumdu.j2se.kuts;

public class TaskListFactory {

     public static AbstractTaskList createTaskList(ListTypes.types type) {
          AbstractTaskList list;
          if (type == ListTypes.types.ARRAY) {
               list = new ArrayTaskList();
          } else {
               list = new LinkedTaskList();
          }
          return list;
     }
}
