package net.cookiebrain.youneedbait.entity.client;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer MUSKELLUNGE =
            new EntityModelLayer(new Identifier(YouNeedBait.MOD_ID,"muskellunge"),"main");

    public static final EntityModelLayer NORTHERNPIKE =
            new EntityModelLayer(new Identifier(YouNeedBait.MOD_ID,"northernpike"),"main");

    public static final EntityModelLayer LARGEMOUTHBASS =
            new EntityModelLayer(new Identifier(YouNeedBait.MOD_ID,"largemouthbass"),"main");

    public static final EntityModelLayer BLACKCRAPPIE =
            new EntityModelLayer(new Identifier(YouNeedBait.MOD_ID,"blackcrappie"),"main");

}

