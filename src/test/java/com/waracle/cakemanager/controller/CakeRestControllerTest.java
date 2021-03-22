package com.waracle.cakemanager.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.service.CakeServiceImpl;

@ExtendWith(MockitoExtension.class)
class CakeRestControllerTest {

    private static final String TITLE = "Breakfast cake";
    private static final String DESCRIPTION = "this is amazing cake";
    private static final String IMAGE = "breakfast.jpg";

    @Mock
    private CakeServiceImpl cakeService;

    @InjectMocks
    private CakeRestController testee;

    @Test
    void testGetCakes() {
        Cake birthdayCake = buildCake();
        when(cakeService.getAllCakes()).thenReturn(Collections.singletonList(birthdayCake));
        Collection<Cake> cakes = testee.getAllCakes();
        verify(cakeService, times(1)).getAllCakes();
        assertThat(cakes.stream().findFirst().get(), is(sameInstance(birthdayCake)));
    }

    @Test
    void addCake() {
        Cake requestCake = buildCake();
        Cake responseCake = buildCakeWithId();
        when(cakeService.addCake(requestCake)).thenReturn(responseCake);
        Cake cake = testee.addCake(requestCake);
        verify(cakeService, times(1)).addCake(requestCake);
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
        cake.setId(1L);
        return cake;
    }
}
