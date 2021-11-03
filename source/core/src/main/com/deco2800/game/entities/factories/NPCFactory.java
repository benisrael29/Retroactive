package com.deco2800.game.entities.factories;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.deco2800.game.ai.components.AITaskComponent;
import com.deco2800.game.ai.tasks.ChaseTask;
import com.deco2800.game.ai.tasks.MumWaitTask;
import com.deco2800.game.ai.tasks.WanderTask;
import com.deco2800.game.entities.Entity;
import com.deco2800.game.generic.ServiceLocator;
import com.deco2800.game.maps.ObjectData;
import com.deco2800.game.maps.ObjectDescription;
import com.deco2800.game.physics.PhysicsLayer;
import com.deco2800.game.physics.components.HitboxComponent;
import com.deco2800.game.physics.components.PhysicsMovementComponent;

/**
 * Factory to create non-playable character (NPC) entities with predefined components.
 *
 * <p>Each NPC entity type should have a creation method that returns a corresponding entity.
 * Predefined entity properties can be loaded from configs stored as json files which are defined in
 * "NPCConfigs".
 *
 * <p>If needed, this factory can be separated into more specific factories for entities with
 * similar characteristics.
 */
@SuppressWarnings("unused")
public class NPCFactory {

    public static Entity createMumSpawn(ObjectDescription desc, GridPoint2 worldPos) {
        ServiceLocator.getHome().getFloor().stashMumPosition(worldPos);
        return null;
    }

    public static Entity createMum(ObjectDescription desc, GridPoint2 worldPos) {
        return createNPC(desc, worldPos);
    }

    public static Entity createCat(ObjectDescription desc, GridPoint2 worldPos) {
        ObjectData data = desc.getData();
        Entity cat = ObjectFactory.createInteractive(desc, worldPos)
            .addComponent(new AITaskComponent()
                .addTask(new WanderTask(new Vector2(2f, 2f), 2f))
                .addTask(new ChaseTask(ServiceLocator.getHome().getScreen().getPlayer(), 10, 0.5f, 1f)));
        cat.getComponent(PhysicsMovementComponent.class).setTwoDCharacter();
        return cat;
    }

    public static Entity createNPC(ObjectDescription desc, GridPoint2 worldPos) {
        ObjectData data = desc.getData();
        Entity npc = ObjectFactory.createInteractive(desc, worldPos);
        npc.getComponent(HitboxComponent.class).setLayer(PhysicsLayer.NPC);
        return npc;
    }

    private NPCFactory() {
        throw new IllegalStateException("Instantiating static util class");
    }
}
