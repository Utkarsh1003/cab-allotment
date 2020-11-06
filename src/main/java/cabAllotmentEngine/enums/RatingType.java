package cabAllotmentEngine.enums;

import lombok.Getter;

public enum RatingType {
    EXCELLENT(5),GOOD(4),AVERAGE(3),BAD(2),VERY_BAD(1);

    @Getter
    private Integer rating;

    private RatingType(Integer rating){
        this.rating = rating;
    }
}
