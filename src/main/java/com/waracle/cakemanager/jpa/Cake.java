package com.waracle.cakemanager.jpa;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "Cakes", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "NAME")})
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max=50, message = "Name cannot be greater than 50 characters")
    @Column(name = "NAME", unique = true, nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Image is mandatory")
    @Size(max=100, message = "Image cannot be greater than 100 characters")
    @Column(name = "IMAGE", nullable = false, length = 100)
    private String imageUrl;

    //    @NotBlank(message = "Comment is required")
    @Size(max=200, message = "Comment cannot be greater than 200 characters")
    @Column(name = "COMMENT", length = 200)
    private String comment;

    @Range(min=0, max=5, message = "Yum Factor must be between 0 and 5")
    @Column(name = "rating", length = 1)
    private Integer yumFactor;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getYumFactor() {
        return yumFactor;
    }

    public void setYumFactor(int yumFactor) {
        this.yumFactor = yumFactor;
    }

    @Override
    public String toString() {
        return String.format(
                "Cake[id=%d, name='%s', imageUrl='%s', comment='%s', yumFactor='%s']",
                getId(), getName(), getComment(), getImageUrl(), getYumFactor());
    }

}