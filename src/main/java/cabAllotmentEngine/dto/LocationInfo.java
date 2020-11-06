package cabAllotmentEngine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class LocationInfo {
    private Double lat;
    private Double lng;

    public LocationInfo(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
