package cabAllotmentEngine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInfo {
    private String name;
    private Integer id;
    private Double averageRating;
    private Integer totalNumberRating;

    @Override
    public String toString() {
        return "PersonInfo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", averageRating=" + averageRating +
                '}';
    }
}
