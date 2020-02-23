package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * A java bean to represent an event
 * @author Kelly Palese
 */

@Entity(name = "Event")
@Table(name = "EVENT")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    //TODO: What to do for DATE and TIMES?

    @Column(name = "notes")
    private String notes;

    @Column(name = "user")
    private int userId;


}
