package targaryen.entity;

import got.GOT;
import got.common.entity.ai.GOTEntityAIRangedAttack;
import got.common.entity.other.GOTEntityNPC;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TEntityTargaryenSoldierArcher extends TEntityTargaryenSoldier {
	public TEntityTargaryenSoldierArcher(World world) {
		super(world);
		npcShield = null;

	}

	@Override
	public EntityAIBase createKinglandsAttackAI() {
		return new GOTEntityAIRangedAttack(this, 1.25, 30, 50, 16.0f);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		int i = rand.nextInt(5);
		if (i == 0 || i == 1 || i == 2 || i == 3) {
			npcItemsInv.setRangedWeapon(new ItemStack(GOT.westerosBow));
		} else if (i == 4) {
			npcItemsInv.setRangedWeapon(new ItemStack(GOT.ironCrossbow));
		}
		npcItemsInv.setIdleItem(npcItemsInv.getRangedWeapon());
		return data;
	}

	@Override
	protected void onAttackModeChange(GOTEntityNPC.AttackMode mode, boolean mounted) {
		if (mode == GOTEntityNPC.AttackMode.IDLE) {
			setCurrentItemOrArmor(0, npcItemsInv.getIdleItem());
		} else {
			setCurrentItemOrArmor(0, npcItemsInv.getRangedWeapon());
		}
	}

	@Override
	protected void dropFewItems(boolean flag, int i) {
		super.dropFewItems(flag, i);
		dropNPCArrows(i);
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isFriendly(entityplayer)) {
			if (hiredNPCInfo.getHiringPlayer() == entityplayer) {
				return "westeros/crownlands/soldier/hired";
			}
			return "westeros/crownlands/soldier/friendly";
		}
		return "westeros/crownlands/soldier/hostile";
	}

}
