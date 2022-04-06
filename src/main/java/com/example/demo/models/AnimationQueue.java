package com.example.demo.models;
import javax.persistence.*;

@Entity
public class AnimationQueue {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="playing")
    private boolean playing;

    @Column(name="finished")
    private boolean finished;

    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private AnimationTypes type;

    public AnimationQueue() {
    }

    public AnimationQueue(AnimationTypes animType) {
        this.type = animType;
    }

    public int getId() { return id; }
    public void setId(int id)	{	this.id = id; }

    public int getType() { return this.type.getId(); }
    public void setType(AnimationTypes animType)	{
        // set to pass in an id, name pair
        this.type = animType;
    }

    public boolean getPlaying() { return this.playing; }
    public void setPlaying(boolean animStatus)	{
        this.playing = animStatus;
    }

    public boolean getFinished() { return this.finished; }
    public void setFinished(boolean animStatus)	{
        this.finished = animStatus;
    }
}
