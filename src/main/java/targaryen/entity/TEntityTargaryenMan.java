package targaryen.entity;

import got.GOT;
import got.common.database.*;
import got.common.entity.ai.*;
import got.common.entity.animal.GOTEntityHorse;
import got.common.entity.other.*;
import got.common.faction.GOTFaction;
import got.common.quest.*;
import got.common.world.biome.GOTBiome;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import targaryen.database.TFaction;

public class TEntityTargaryenMan extends GOTEntityHumanBase {
	public static ItemStack[] weapons = new ItemStack[] { new ItemStack(GOT.westerosDagger), new ItemStack(GOT.ironDagger), new ItemStack(Items.iron_sword), new ItemStack(Items.iron_axe) };

	public TEntityTargaryenMan(World world) {
		super(world);
		setSize(0.6f, 1.8f);
		getNavigator().setAvoidsWater(true);
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new GOTEntityAIHiredRemainStill(this));
		tasks.addTask(2, createKinglandsAttackAI());
		tasks.addTask(3, new GOTEntityAIFollowHiringPlayer(this));
		tasks.addTask(4, new EntityAIOpenDoor(this, true));
		tasks.addTask(5, new EntityAIWander(this, 1.0));
		tasks.addTask(6, new GOTEntityAIEat(this, GOTFoods.WESTEROS, 8000));
		tasks.addTask(6, new GOTEntityAIDrink(this, GOTFoods.WESTEROS_DRINK, 8000));
		tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 8.0f, 0.02f));
		tasks.addTask(7, new EntityAIWatchClosest2(this, GOTEntityNPC.class, 5.0f, 0.02f));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityLiving.class, 8.0f, 0.02f));
		tasks.addTask(9, new EntityAILookIdle(this));
		this.addTargetTasks(false);
		isImmuneToFire = true;
	}

	protected EntityAIBase createKinglandsAttackAI() {
		return new GOTEntityAIAttackOnCollide(this, 1.4, false);
	}

	@Override
	public GOTNPCMount createMountToRide() {
		GOTEntityHorse horse = (GOTEntityHorse) super.createMountToRide();
		return horse;
	}

	@Override
	public void setupNPCGender() {
		familyInfo.setMale(rand.nextBoolean());
	}

	@Override
	public void setupNPCName() {
		familyInfo.setName(GOTNames.getWesterosName(rand, familyInfo.isMale()));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		int i = rand.nextInt(weapons.length);
		npcItemsInv.setMeleeWeapon(weapons[i].copy());
		npcItemsInv.setIdleItem(null);
		return data;
	}

	@Override
	public GOTFaction getFaction() {
		return TFaction.TARGARYEN;
	}

	@Override
	public String getNPCName() {
		return familyInfo.getName();
	}

	@Override
	protected void onAttackModeChange(GOTEntityNPC.AttackMode mode, boolean mounted) {
		if (mode == GOTEntityNPC.AttackMode.IDLE) {
			setCurrentItemOrArmor(0, npcItemsInv.getIdleItem());
		} else {
			setCurrentItemOrArmor(0, npcItemsInv.getMeleeWeapon());
		}
	}

	@Override
	protected void dropFewItems(boolean flag, int i) {
		super.dropFewItems(flag, i);
		int bones = rand.nextInt(2) + rand.nextInt(i + 1);
		for (int l = 0; l < bones; ++l) {
			dropItem(Items.bone, 1);
		}
		dropGondorItems(flag, i);
	}

	protected void dropGondorItems(boolean flag, int i) {
		if (rand.nextInt(6) == 0) {
			dropChestContents(GOTChestContents.WESTEROS, 1, 2 + i);
		}
	}

	public boolean attackEntityFriom(DamageSource damagesource, float f) {
		ItemStack itemstack;
		Entity entity = damagesource.getEntity();
		if (entity instanceof EntityLivingBase && entity == damagesource.getSourceOfDamage() && (itemstack = ((EntityLivingBase) entity).getHeldItem()) != null && itemstack.getItem() == GOT.robertHammer) {
			return super.attackEntityFrom(damagesource, 100f);
		}
		return super.attackEntityFrom(damagesource, f);
	}

	@Override
	public float getAlignmentBonus() {
		return 1.0f;
	}

	@Override
	public boolean getCanSpawnHere() {
		if (super.getCanSpawnHere()) {
			if (liftSpawnRestrictions) {
				return true;
			}
			int i = MathHelper.floor_double(posX);
			int j = MathHelper.floor_double(boundingBox.minY);
			int k = MathHelper.floor_double(posZ);
			if (j > 62 && worldObj.getBlock(i, j - 1, k) == worldObj.getBiomeGenForCoords(i, k).topBlock) {
				return true;
			}
		}
		return false;
	}

	@Override
	public float getBlockPathWeight(int i, int j, int k) {
		float f = 0.0f;
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(i, k);
		if (biome instanceof GOTBiome) {
			f += 20.0f;
		}
		return f;
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isDrunkard()) {
			return "westeros/crownlands/drunkard/neutral";
		}
		if (isFriendly(entityplayer)) {
			return "westeros/crownlands/man/friendly";
		}
		return "westeros/crownlands/man/hostile";
	}

	@Override
	public GOTMiniQuest createMiniQuest() {
		return GOTMiniQuestFactory.WESTEROS.createQuest(this);
	}

	@Override
	public GOTMiniQuestFactory getBountyHelpSpeechDir() {
		return GOTMiniQuestFactory.WESTEROS;
	}
}
