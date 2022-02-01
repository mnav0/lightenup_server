package com.example.demo.models;
import javax.persistence.*;

@Entity
public class AnimationQueue {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private AnimationTypes type;

    public int getId() { return id; }
    public void setId(int id)	{	this.id = id; }

    public String getType() { return type.getName(); }
    public void setType(AnimationTypes animType)	{
        // set to pass in an id, name pair
        this.type = animType;
    }
}
