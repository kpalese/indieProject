package com.tasktracker.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * A java bean to represent a user
 * @author Kelly Palese
 */

@Entity(name = "User")
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name ="includeHolidays")
    private boolean includeHolidays;

    @Column(name = "weekStart")
    private String weekStart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Todo> todos = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    /**
     * Instantiates a new User (empty constructor)
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *  @param userName    the user name
     * @param userPassword    the user's password
     * @param includeHolidays whether or not to include US national holidays
     * @param weekStart the day the week should begin
     */
    public User(String userName, String userPassword, boolean includeHolidays, String weekStart) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.includeHolidays = includeHolidays;
        this.weekStart = weekStart;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user password.
     *
     * @param userPassword the user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Include holidays boolean.
     *
     * @return the boolean
     */
    public boolean isIncludeHolidays() {
        return includeHolidays;
    }

    /**
     * Sets include holidays.
     *
     * @param includeHolidays whether or not to include US holidays
     */
    public void setIncludeHolidays(boolean includeHolidays) {
        this.includeHolidays = includeHolidays;
    }

    /**
     * Gets that day the week should start on
     *
     * @return the week start
     */
    public String getWeekStart() {
        return weekStart;
    }

    /**
     * Sets the day the week should start on
     *
     * @param weekStart the week start
     */
    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Gets user's events by date and sorts them by starting time.
     *
     * @param localDate the local date
     * @return the events by date
     */
    public List<Event> getEventsByDate(LocalDate localDate) {
        List<Event> eventsMatchingDate = new ArrayList<>();
        for (Event event : this.getEvents()) {
            if (event.getDate().equals(localDate)) {
                eventsMatchingDate.add(event);
            }
        }

        //Sort the events by start time
        Collections.sort(eventsMatchingDate);

        return eventsMatchingDate;
    }

    /**
     * Sets events.
     *
     * @param events the events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }


    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {
        events.add(event);
        event.setUser(this);
    }

    /**
     * Remove event.
     *
     * @param event the event
     */
    public void removeEvent(Event event) {
        events.remove(event);
        event.setUser(null);
    }

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    public Set<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets user's tasks by date
     *
     * @param localDate the local date
     * @return the tasks by date
     */
    public List<Task> getTasksByDate(LocalDate localDate) {
        List<Task> tasksMatchingDate = new ArrayList<>();
        String dayOfWeek = localDate.getDayOfWeek().toString().toUpperCase();

        for (Task task : this.getTasks()) {
            //Get 'once' tasks
            if (task.getFrequency().equals("once")) {
                if (task.getDate().equals(localDate)) {
                    tasksMatchingDate.add(task);
                }
            //Get incomplete 'daily' tasks
            } else if (task.getFrequency().equals("daily")) {
                if (task.getLastDateCompleted() == null) {
                    if (task.getDate().isBefore(localDate) || task.getDate().isEqual(localDate)) {
                        tasksMatchingDate.add(task);
                    }
                } else if (task.getLastDateCompleted().isBefore(localDate)) {
                    tasksMatchingDate.add(task);
                }
            //Get incomplete 'weekly' tasks
            } else if (task.getFrequency().equals("weekly") && task.getWeeklyTaskDayOfWeek().equals(dayOfWeek)) {
                if (task.getLastDateCompleted() == null) {
                    if (task.getDate().isBefore(localDate) || task.getDate().isEqual(localDate)) {
                        tasksMatchingDate.add(task);
                    }
                } else if (task.getLastDateCompleted().isBefore(localDate)) {
                    tasksMatchingDate.add(task);
                }
            }
        }
        //Sort the tasks by last date completed
        Collections.sort(tasksMatchingDate);

        return tasksMatchingDate;
    }

    /**
     * Sets tasks.
     *
     * @param tasks the tasks
     */
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task
     *
     * @param task the task
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);
    }

    /**
     * Remove task.
     *
     * @param task the task
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setUser(null);
    }

    /**
     * Gets todos.
     *
     * @return the todos
     */
    public Set<Todo> getTodos() {
        return todos;
    }

    /**
     * Sets todos.
     *
     * @param todos the todos
     */
    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    /**
     * Add to-do.
     *
     * @param todo the to do list item
     */
    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setUser(this);
    }

    /**
     * Remove to-do
     *
     * @param todo the to-do list item
     */
    public void removeTodo(Todo todo) {
        todos.remove(todo);
        todo.setUser(null);
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }

    /**
     * Remove role.
     *
     * @param role the role
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(includeHolidays, user.includeHolidays) &&
                Objects.equals(weekStart, user.weekStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, includeHolidays, weekStart);
    }
}