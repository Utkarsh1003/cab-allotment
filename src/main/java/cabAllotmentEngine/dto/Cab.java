package cabAllotmentEngine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cab {
    private Integer id;
    private Double distance;
    private LocationInfo currentLocation;
    private DriverInfo driver;

    public Cab(Integer id, LocationInfo currentLocation) {
        this.id = id;
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id=" + id +
                ", distance=" + distance +
                ", driver=" + driver +
                '}';
    }


}
