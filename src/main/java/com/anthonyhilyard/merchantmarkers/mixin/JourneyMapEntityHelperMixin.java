package com.anthonyhilyard.merchantmarkers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import journeymap.client.model.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;

@Mixin(value = EntityHelper.class, remap = false)
public class JourneyMapEntityHelperMixin
{
	/**
	 * Bug fix so that villagers actually show up on the map.
	 */
	@Inject(method = "isSpecialCreature", at = @At("HEAD"), cancellable = true)
	private static boolean isSpecialCreature(Entity entity, boolean hostile, CallbackInfoReturnable<Boolean> info)
	{
		if (AbstractVillagerEntity.class.isAssignableFrom(entity.getClass()))
		{
			info.setReturnValue(!hostile);
			return false;
		}
		return true;
	}
}