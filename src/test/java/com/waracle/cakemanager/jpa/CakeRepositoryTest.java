package com.waracle.cakemanager.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CakeRepositoryTest {

    @Autowired private CakeRepository cakeRepository;

    @Test
    public void testFindByName() {
        String cakeName = "Cake For Afternoon Tea";
        Cake birthday = buildCake(cakeName, "afternoon tea cake", "afternoon-tea.jpg", 1);
        cakeRepository.save(birthday);
        assertThat(cakeRepository.findByName(cakeName)).isInstanceOf(List.class);
    }

    @Test
    public void testFindById() {
        Cake birthday = buildCake("Breakfast cake", "this is amazing cake", "breakfast.jpg", 2);
        cakeRepository.save(birthday);
        assertThat(cakeRepository.findById(1L)).isInstanceOf(Optional.class);
    }

    @Test
    public void testFindAll() {
        Cake birthday = buildCake("Christmas", "christmas cake", "christmas.jpg", 4);
        cakeRepository.save(birthday);
        Cake wedding = buildCake("Wedding", "wedding cake", "wedding.jpg", 3);
        cakeRepository.save(wedding);
        assertThat(cakeRepository.findAll()).isInstanceOf(List.class);
    }

    private Cake buildCake(String name, String comment, String imageUrl, int yumFactor) {
        Cake birthday = new Cake();
        birthday.setName(name);
        birthday.setComment(comment);
        birthday.setImageUrl(imageUrl);
        birthday.setYumFactor(yumFactor);
        return birthday;
    }
}