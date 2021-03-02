package targaryen.database;

import got.client.sound.GOTBiomeMusic;
import got.client.sound.GOTBiomeMusic.MusicRegion;
import got.common.database.*;
import got.common.world.biome.GOTBiome;
import got.common.world.biome.westeros.GOTBiomeWesteros;
import got.common.world.map.GOTWaypoint.WaypointRegion;
import got.common.world.spawning.*;
import got.common.world.spawning.GOTBiomeSpawnList.SpawnListContainer;
import targaryen.entity.*;

public class TBiome extends GOTBiomeWesteros {
	public static GOTBiome targaryen;
	
	public TBiome(int i, boolean major) {
		super(i, major);

		SpawnListContainer[] container0 = new SpawnListContainer[2];
		container0[0] = GOTBiomeSpawnList.entry(new GOTSpawnList(new GOTSpawnEntry(TEntityTargaryenMan.class, 10, 4, 6)), 15).setSpawnChance(100);
		container0[1] = GOTBiomeSpawnList.entry(new GOTSpawnList(new GOTSpawnEntry(TEntityTargaryenLevyman.class, 20, 2, 4), new GOTSpawnEntry(TEntityTargaryenLevymanArcher.class, 10, 2, 8), new GOTSpawnEntry(TEntityTargaryenSoldier.class, 20, 2, 4), new GOTSpawnEntry(TEntityTargaryenSoldierArcher.class, 10, 2, 8), new GOTSpawnEntry(TEntityTargaryenBannerBearer.class, 20, 2, 4), new GOTSpawnEntry(TEntityTargaryenCaptain.class, 5, 4, 1)), 10).setSpawnChance(100);
		npcSpawnList.newFactionList(100, 0.0f).add(container0);

		SpawnListContainer[] container1 = new SpawnListContainer[1];
		container1[0] = GOTBiomeSpawnList.entry(GOTSpawnList.DRAGONSTONE_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container1);

		SpawnListContainer[] container2 = new SpawnListContainer[1];
		container2[0] = GOTBiomeSpawnList.entry(GOTSpawnList.WESTERLANDS_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container2);

		SpawnListContainer[] container3 = new SpawnListContainer[1];
		container3[0] = GOTBiomeSpawnList.entry(GOTSpawnList.STORMLANDS_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container3);

		SpawnListContainer[] container4 = new SpawnListContainer[1];
		container4[0] = GOTBiomeSpawnList.entry(GOTSpawnList.NORTH_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container4);

		SpawnListContainer[] container5 = new SpawnListContainer[1];
		container5[0] = GOTBiomeSpawnList.entry(GOTSpawnList.RIVERLANDS_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container5);

		SpawnListContainer[] container6 = new SpawnListContainer[1];
		container6[0] = GOTBiomeSpawnList.entry(GOTSpawnList.IRONBORN_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container6);

		invasionSpawns.addInvasion(GOTInvasions.NORTH, GOTEventSpawner.EventChance.COMMON);
		invasionSpawns.addInvasion(GOTInvasions.RIVERLANDS, GOTEventSpawner.EventChance.COMMON);
		invasionSpawns.addInvasion(GOTInvasions.WESTERLANDS, GOTEventSpawner.EventChance.COMMON);
		invasionSpawns.addInvasion(GOTInvasions.STORMLANDS, GOTEventSpawner.EventChance.COMMON);
		invasionSpawns.addInvasion(GOTInvasions.IRONBORN, GOTEventSpawner.EventChance.COMMON);
	}

	@Override
	public WaypointRegion getBiomeWaypoints() {
		return WaypointRegion.CROWNLANDS;
	}

	@Override
	public MusicRegion getBiomeMusic() {
		return GOTBiomeMusic.WESTEROS.getSubregion("targaryen");
	}

	public static void preInit() {
		targaryen = new TBiome(GOTBiome.id++, true).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.1f, 0.15f).setColor(0x789F46).setBiomeName("targaryen");
	}
}