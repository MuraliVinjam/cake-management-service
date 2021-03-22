package com.waracle.cakemanager.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.jpa.CakeRepository;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    private static final String TITLE = "Breakfast cake";
    private static final String DESCRIPTION = "this is amazing cake";
    private static final String IMAGE = "breakfast.jpg";

    @Mock
    private CakeRepository cakeRepository;

    @InjectMocks
    private CakeServiceImpl testee;

    @Test
    void testFindAll() {
        Cake birthdayCake = buildCake();
        when(cakeRepository.findAll()).thenReturn(Collections.singletonList(birthdayCake));
        List<Cake> cakes = testee.getAllCakes();
        verify(cakeRepository, times(1)).findAll();
        assertThat(cakes.get(0), is(sameInstance(birthdayCake)));
    }

    @Test
    void testAddCake() {
        Cake requestCake = buildCake();
        Cake responseCake = buildCakeWithId();
        when(cakeRepository.save(requestCake)).thenReturn(responseCake);
        Cake cake = testee.addCake(requestCake);
        verify(cakeRepository, times(1)).save(requestCake);
        assertThat(cake, is(sameInstance(responseCake)));
    }

    private Cake buildCake() {
        return Cake.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .image(IMAGE)
                .build();
    }

    private Cake buildCakeWithId() {
        Cake cake = buildCake();
        cake.setId(2L);
        return cake;
    }
}
