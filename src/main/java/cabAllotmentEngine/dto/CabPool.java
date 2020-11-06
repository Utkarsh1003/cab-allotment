package cabAllotmentEngine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CabPool {
    private Integer id;
    private String name;
    private List<Cab> cabs;

    public CabPool(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
