package models;

import java.util.Objects;

public class AssistantTM extends Mentor{
    private int mentor_id;

    public AssistantTM(String name, String email, String specialization,int mentor_id) {
        super(name, email, specialization);
        this.mentor_id = mentor_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AssistantTM that = (AssistantTM) o;
        return mentor_id == that.mentor_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mentor_id);
    }

    public int getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(int mentor_id) {
        this.mentor_id = mentor_id;
    }
}
