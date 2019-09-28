package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelCastleGuard;
import com.morecreepsrevival.morecreeps.common.entity.EntityCastleGuard;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderCastleGuard<T extends EntityCastleGuard> extends RenderCreep<T>
{
    public RenderCastleGuard(RenderManager renderManager)
    {
        super(renderManager, new ModelCastleGuard(), 0.5f);
    }
}
