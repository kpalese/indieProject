package com.tasktracker.entity;

import com.tasktracker.persistence.GenericDao;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Column(name ="auto_fwd_incomplete_tasks")
    private boolean autoFwdIncompleteTasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

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
     * @param autoFwdIncompleteTasks whether or not to automatically forward the user's incomplete tasks
     */
    public User(String userName, String userPassword, boolean autoFwdIncompleteTasks) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.autoFwdIncompleteTasks = autoFwdIncompleteTasks;
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
     * Is auto fwd incomplete tasks boolean.
     *
     * @return the boolean
     */
    public boolean isAutoFwdIncompleteTasks() {
        return autoFwdIncompleteTasks;
    }

    /**
     * Sets auto fwd incomplete tasks.
     *
     * @param autoFwdIncompleteTasks the auto fwd incomplete tasks
     */
    public void setAutoFwdIncompleteTasks(boolean autoFwdIncompleteTasks) {
        this.autoFwdIncompleteTasks = autoFwdIncompleteTasks;
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
     * TODO dao test??
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
     * TODO dao test??
     */
    public List<Task> getTasksByDate(LocalDate localDate) {
        List<Task> tasksMatchingDate = new ArrayList<>();

        //Get 'once' tasks
        for (Task task : this.getTasks()) {
            if (task.getDate().equals(localDate)) {
                tasksMatchingDate.add(task);
            }
        }

        //Get incomplete 'daily' tasks
        for (Task task : this.getTasks()) {
            if (task.getFrequency().equals("daily") && task.getLastDateCompleted().isBefore(localDate)) {
                tasksMatchingDate.add(task);
            }
        }

        //Get incomplete 'weekly' tasks
        for (Task task : this.getTasks()) {
            if (task.getFrequency().equals("weekly") && task.getWeeklyTaskDayOfWeek().equals(localDate.getDayOfWeek().toString()) && task.getLastDateCompleted().isBefore(localDate)) {
                tasksMatchingDate.add(task);
            }
        }

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
                Objects.equals(autoFwdIncompleteTasks, user.autoFwdIncompleteTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, autoFwdIncompleteTasks);
    }
}