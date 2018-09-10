package com.learn.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "imgicon", schema = "ordersystemssh", catalog = "")
public class ImgiconEntity {
    private int id;
    private String img;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 255)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgiconEntity that = (ImgiconEntity) o;
        return id == that.id &&
                Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, img);
    }
}
