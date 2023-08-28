package kuit.subway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kuit.subway.dto.BaseTimeEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Line extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private Long id;

    private String name;

    private String color;

    private int distance;
    @Embedded
    @Builder.Default
    private Sections sections = new Sections();

    public static Line createLine(String name, String color, int distance) {
        return Line.builder()
                .name(name)
                .color(color)
                .distance(distance)
                .build();
    }
    // 연관관계 메서드
    public void addSection(Section section) {
        this.sections.addSection(section);
    }

    public void deleteSection(Station deleteStation) {
        this.sections.deleteSection(deleteStation);
    }
    public void updateLine(String name, String color, int distance, Station upStation, Station downStation, int sectionDistance) {
        this.name = name;
        this.color = color;
        this.distance = distance;
        this.sections.getOrderSections().get(0).updateSection(upStation, downStation, sectionDistance);
    }

}
