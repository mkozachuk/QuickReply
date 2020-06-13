package pl.devodds.mkozachuk.quickreply.models;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Message {
    private Long id;
    private String text;;
}
