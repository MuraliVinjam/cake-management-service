package com.waracle.cakemanager.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.service.CakeServiceImpl;

@ExtendWith(MockitoExtension.class)
class CakeWebControllerTest {

    private static final String TITLE = "Breakfast cake";
    private static final String DESCRIPTION = "this is amazing cake";
    private static final String IMAGE = "breakfast.jpg";

    @Mock
    private CakeServiceImpl cakeService;

    @InjectMocks
    private CakeWebController testee;

    @Test
    void testViewAllCakes() {
        Map<String, Object> model = new LinkedHashMap<>();
        List<Cake> allCakes = new ArrayList<>();
        Cake birthdayCake = buildCake();
        allCakes.add(birthdayCake);
        when(cakeService.getAllCakes()).thenReturn(allCakes);

        String page = testee.viewAllCakes(model);

        verify(cakeService, times(1)).getAllCakes();
        assertThat(page, is(equalTo("index")));
        List<Cake> cakes = (ArrayList<Cake>) model.get("cakes");
        assertThat(cakes, is(notNullValue()));
        assertThat(cakes.get(0), is(sameInstance(birthdayCake)));
    }

    private Cake buildCake() {
        return Cake.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .image(IMAGE)
                .build();
    }
}
