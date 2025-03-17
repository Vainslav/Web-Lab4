package javagnomes.lab4.modelObjects;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "shots")
public class ShotEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Double x;
    @Column(nullable = false)
    private Double y;
    @Column(nullable = false)
    private Double r;
    @Column(nullable = false)
    private Long workTime;
    @Column(nullable = false)
    private String startTime;
    @Column(nullable = false)
    private Boolean hit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner", referencedColumnName = "username", nullable = false)
    private UserEntity owner;

    public ShotEntity(Double x, Double y, Double r) {
        long start = System.nanoTime();
        this.x = x;
        this.y = y;
        this.r = r;
        this.startTime = Instant.now().toString();
        this.workTime = System.nanoTime() - start;
        this.hit = checkHit();
    }

    public ShotEntity(String x, String y, String r){
        if (Double.parseDouble(r) < 0){
            throw new IllegalArgumentException("R MUST BE POSITIVE");
        }
        long start = System.nanoTime();
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.r = Double.parseDouble(r);
        this.startTime = Instant.now().toString();
        this.workTime = System.nanoTime() - start;
        this.hit = checkHit();
    }

    private JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("hit", hit)
                .add("x", x)
                .add("y", y)
                .add("r", r)
                .add("startTime", startTime)
                .add("wordTime", workTime)
                .add("owner", owner.getUsername())
                .build();
    }

    private boolean checkHit() {
        if (this.getX() >= 0 && this.getY() <= 0) {
            if (this.getX()*this.getX() + this.getY() * this.getY() <= this.getR()*this.getR()/4) {
                return true;
            }
        }
        if (this.getX() <= 0 && this.getY() >= 0) {
            if (this.getX() >= -this.getR()/2 && this.getY() <= this.getR()) {
                return true;
            }
        }
        if (this.getX() >= 0 && this.getY() >= 0) {
            if (this.getY() <= -this.getX() + this.getR()) {
                return true;
            }
        }
        return false;
    }
}
