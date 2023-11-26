package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class Id {
    private String id;

    public Id(String id) {
        this.id = id;
    }

    public static Id of(String id) {
        return new Id(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id idInstance = (Id) o;
        return Objects.equals(id, idInstance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }

    public int toInt() {
        return Integer.parseInt(id);
    }

}
