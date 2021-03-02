package targaryen.entity;

import got.common.entity.other.GOTBannerBearer;
import got.common.item.other.GOTItemBanner;
import net.minecraft.world.World;

public class TEntityTargaryenBannerBearer extends TEntityTargaryenSoldier implements GOTBannerBearer {
	public TEntityTargaryenBannerBearer(World world) {
		super(world);
		npcShield = null;
	}

	@Override
	public GOTItemBanner.BannerType getBannerType() {
		return GOTItemBanner.BannerType.TARGARYEN;
	}
}