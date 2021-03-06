package hibernate.one_many_relating.bi_direction;

import javax.persistence.*;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
@Entity
@Table(name = "bi_direction_dream")
public class Dream {
    //在多的一方加外键
    private int id;
    private String description;
    private Undergraduate undergraduate;

    @ManyToOne
    public Undergraduate getUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(Undergraduate undergraduate) {
        this.undergraduate = undergraduate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
