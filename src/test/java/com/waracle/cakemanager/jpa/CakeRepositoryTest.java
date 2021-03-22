package com.waracle.cakemanager.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CakeRepositoryTest {

    private static final String TITLE_1 = "Breakfast cake 1";
    private static final String TITLE_2 = "Breakfast cake 2";
    private static final String DESCRIPTION = "this is amazing cake";
    private static final String IMAGE = "breakfast.jpg";

    @Autowired
    private CakeRepository cakeRepository;

    @Test
    void testFindById() {
        Cake birthday = buildCake(TITLE_1, DESCRIPTION, IMAGE);
        cakeRepository.save(birthday);
        Cake cake = cakeRepository.findAll().stream().filter(c -> c.getTitle().equals(TITLE_1)).findFirst().get();
        assertThat(cake.getTitle(), is(equalTo(TITLE_1)));
        assertThat(cake.getDescription(), is(equalTo(DESCRIPTION)));
        assertThat(cake.getImage(), is(equalTo(IMAGE)));
    }

    @Test
    void testFindAll() {
        Cake cake1 = buildCake(TITLE_2, DESCRIPTION, IMAGE);
        cakeRepository.save(cake1);
        Cake cake2 = buildCake("Wedding", "wedding cake", "wedding.jpg");
        cakeRepository.save(cake2);
        assertThat(cakeRepository.findAll().size(), is(greaterThan(2)));
    }

    private Cake buildCake(String title, String description, String image) {
        return Cake.builder()
                .title(title)
                .description(description)
                .image(image)
                .build();
    }
}
