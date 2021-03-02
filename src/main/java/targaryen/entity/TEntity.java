package targaryen.entity;

import got.common.entity.GOTEntity;
import got.common.entity.other.GOTEntityRegistry;
import targaryen.database.TFaction;

public class TEntity {
	public static int id = GOTEntity.id;

	public static void onInit() {
		GOTEntityRegistry.register(TEntityTargaryenMan.class, "TargaryenMan", id++, TFaction.TARGARYEN);
		GOTEntityRegistry.register(TEntityTargaryenLevyman.class, "TargaryenLevyman", id++, TFaction.TARGARYEN);
		GOTEntityRegistry.register(TEntityTargaryenLevymanArcher.class, "TargaryenLevymanArcher", id++, TFaction.TARGARYEN);
		GOTEntityRegistry.register(TEntityTargaryenSoldier.class, "TargaryenSoldier", id++, TFaction.TARGARYEN);
		GOTEntityRegistry.register(TEntityTargaryenSoldierArcher.class, "TargaryenSoldierArcher", 3044, TFaction.TARGARYEN);
		GOTEntityRegistry.register(TEntityTargaryenBannerBearer.class, "TargaryenBannerBearer", id++, TFaction.TARGARYEN);
		GOTEntityRegistry.register(TEntityTargaryenCaptain.class, "TargaryenCaptain", id++, TFaction.TARGARYEN);
	}
}