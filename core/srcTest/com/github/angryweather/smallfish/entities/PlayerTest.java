package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Mock
    TextureRegion textureRegion;
    Player player;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        player = new Player(textureRegion);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSetScore() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                player.setScore(-1);
            }
        });
    }

    @Test
    void testSetScoreIncrease() {
        player.setScore(1);
        assertEquals(1, player.getScore());
    }

    @Test
    void testIncreaseFood() {
        player.setFoodEaten(1);
        assertEquals(1, player.getFoodEaten());
    }

}
