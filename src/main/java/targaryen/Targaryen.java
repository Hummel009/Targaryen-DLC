package targaryen;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.ReflectionHelper;
import got.common.GOTDimension;
import got.common.util.GOTCommander;
import got.common.world.genlayer.GOTGenLayerWorld;
import net.minecraft.util.ResourceLocation;
import targaryen.database.*;
import targaryen.entity.TEntity;
import targaryen.proxy.TServerProxy;

@Mod(modid = "targaryen", version = "Targaryen", name = "Targaryen DLC", dependencies = "required-after:got")
public class Targaryen {
	@SidedProxy(clientSide = "targaryen.proxy.TClientProxy", serverSide = "targaryen.proxy.TServerProxy")
	public static TServerProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		TFaction.preInit();
		TBiome.preInit();
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event) {
		GOTCommander.setServerMapImage(new ResourceLocation("targaryen:map/map.png"));
		TFaction.onInit();
		TEntity.onInit();
		proxy.onInit();
	}
}