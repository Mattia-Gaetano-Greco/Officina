package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NotaTask")
public class NotaTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_nota_task;

    private String testo;

    private int id_task;

    @ManyToOne
    @JoinColumn(name = "id_task", referencedColumnName = "id_task", insertable = false, updatable = false)
    private Task task;

    public NotaTask() {}

    public int getId_nota_task() {
        return id_nota_task;
    }

    public void setId_nota_task(int id_nota_task) {
        this.id_nota_task = id_nota_task;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}

