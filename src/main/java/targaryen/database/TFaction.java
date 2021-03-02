package targaryen.database;

import got.common.GOTDimension.DimensionRegion;
import got.common.faction.*;
import got.common.util.GOTCommander;
import got.common.world.map.GOTWaypoint;

public class TFaction {
	public static final GOTFaction TARGARYEN = GOTCommander.addFaction("TARGARYEN", 0x790C06, DimensionRegion.WESTEROS);

	public static void preInit() {
		TARGARYEN.isViolent = true;
	}

	public static void onInit() {
		GOTCommander.removeFactions(GOTFaction.CROWNLANDS, GOTFaction.DRAGONSTONE);
		TFaction.setupRelations();
		TFaction.setupMapInfo();
		TFaction.setupControlZones();
		TFaction.setupRanks();
	}

	private static void setupMapInfo() {
		TARGARYEN.factionMapInfo = new GOTFactionProfile(GOTWaypoint.KingsLanding, 100);
	}

	private static void setupRanks() {
		TARGARYEN.addRank(10.0f, "guest").makeTitle();
		TARGARYEN.addRank(50.0f, "friend").makeTitle();
		TARGARYEN.addRank(150.0f, "warrior").makeTitle().setPledgeRank();
		TARGARYEN.addRank(200.0f, "leutenant").makeTitle();
		TARGARYEN.addRank(500.0f, "kingsguard").makeTitle();
		TARGARYEN.addRank(1000.0f, "commander").makeTitle();
		TARGARYEN.addRank(2000.0f, "general").makeTitle();
	}

	private static void setupControlZones() {
		TARGARYEN.addControlZone(new GOTFactionSphere(GOTWaypoint.KingsLanding, 500));
	}

	private static void setupRelations() {
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.PENTOS, GOTFactionRelations.Relation.FRIEND);
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.ARRYN, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.NORTH, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.WESTERLANDS, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.RIVERLANDS, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.IRONBORN, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(TARGARYEN, GOTFaction.STORMLANDS, GOTFactionRelations.Relation.MORTAL_ENEMY);
	}

}
