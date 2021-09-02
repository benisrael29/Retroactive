package com.deco2800.game.areas;


import com.deco2800.game.areas.terrain.TerrainFactory;
import com.deco2800.game.entities.Entity;
import com.deco2800.game.entities.EntityService;
import com.deco2800.game.services.ServiceLocator;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ForestGameAreaTest extends GameArea{


    /**
     * Checks if an entity can be successfully spawned. Since bed is an entity
     * It should pass the test.
     */
    @Test
    void spawnEntityTest() {
        TerrainFactory terrainFactory = mock(TerrainFactory.class);
        ForestGameArea forestGameArea = new ForestGameArea(terrainFactory);

        ServiceLocator.registerEntityService(new EntityService());
        Entity entity = mock(Entity.class);

        forestGameArea.spawnEntity(entity);
        verify(entity).create();
    }

    @Override
    public void create() {
        // Does nothing
    }
}
