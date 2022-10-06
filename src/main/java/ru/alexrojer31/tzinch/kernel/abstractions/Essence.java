package ru.alexrojer31.tzinch.kernel.abstractions;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class Essence implements Essences {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Essence essence = (Essence) o;
        return id == essence.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Version
    protected long v;

    @NotNull
    protected String user = "user";

    @NotNull
    protected LocalDateTime time = LocalDateTime.now();

    @NotNull
    protected String changing = "changing";

    @NotNull
    protected LocalDateTime timestamp = LocalDateTime.now();

}
