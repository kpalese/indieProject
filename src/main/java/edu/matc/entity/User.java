package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    /**
     * Instantiates a new User (empty constructor)
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *  @param userName    the user name
     * @param userPassword    the user's password
     * @param autoFwdIncompleteTasks
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
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Sets events.
     *
     * @param events the events
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
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
     * Sets tasks.
     *
     * @param tasks the tasks
     */
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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