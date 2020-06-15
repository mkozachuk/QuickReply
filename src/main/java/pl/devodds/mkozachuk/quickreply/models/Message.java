package pl.devodds.mkozachuk.quickreply.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Message {

    public Message(String text){
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    private String cat_id;
}

