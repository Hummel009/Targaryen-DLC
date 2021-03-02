package targaryen.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import got.client.render.npc.GOTRenderFactionNPC;
import got.common.util.GOTCommander;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.util.ResourceLocation;
import targaryen.entity.TEntityTargaryenMan;

public class TClientProxy extends TServerProxy implements IResourceManagerReloadListener {

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		GOTCommander.setClientMapImage(new ResourceLocation("targaryen:map/map.png"));
	}

	@Override
	public void onInit() {
		RenderingRegistry.registerEntityRenderingHandler(TEntityTargaryenMan.class, new GOTRenderFactionNPC("westeros/targaryen"));
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new TClientProxy());
	}
}