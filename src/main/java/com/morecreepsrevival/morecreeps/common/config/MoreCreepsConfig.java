package com.morecreepsrevival.morecreeps.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoreCreepsConfig
{
    public static boolean pyramidGen = false;

    public static int pyramidRarity = 0;

    public static boolean castleGen = false;

    public static int castleRarity = 0;

    public static boolean rayGunFire = false;

    public static boolean rayGunMelt = false;

    public static boolean floobShipExplode = false;

    public static boolean publicUrination = true;

    public static boolean jailActive = true;

    public static boolean blood = true;

    private static boolean unlimitedSpawn = false;

    public static int blorpMaxSize = 0;

    public static int guineaPigSpawnAmt = 0;

    public static int babyMummySpawnAmt = 0;

    public static int blackSoulSpawnAmt = 0;

    public static int mummySpawnAmt = 0;

    public static int armyGuySpawnAmt = 0;

    public static int hotdogSpawnAmt = 0;

    public static int gooGoatSpawnAmt = 0;

    public static int kidSpawnAmt = 0;

    public static int lollimanSpawnAmt = 0;

    public static int gSpawnAmt = 0;

    public static int robotTedSpawnAmt = 0;

    public static int robotToddSpawnAmt = 0;

    public static int lawyerFromHellSpawnAmt = 0;

    public static boolean playWelcomeSound = true;

    public static int bigBabySpawnAmt = 0;

    public static int thiefSpawnAmt = 0;

    public static int floobSpawnAmt = 0;

    public static int floobShipSpawnAmt = 0;

    public static void preInit(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        String miscProperty = "Misc Property";

        String spawnNbr = "Spawn Number";

        String mobProperty = "Mob Property";

        String itemProperty = "Item Property";

        String worldGen = "World Gen";

        String worldGenRarity = "World Gen Rarity";

        try
        {
            config.load();

            playWelcomeSound = config.get(miscProperty, "Play Welcome Sound", true, "Toggles the playing of the welcome sound when you load the world.").getBoolean();

            unlimitedSpawn = config.get("Spawn", "Unlimited Spawn Length", false, "Once activated, you can set what value you want for mob spawning.").getBoolean();

            pyramidGen = config.get(worldGen, "Enable Pyramid Gen", true).getBoolean();

            pyramidRarity = config.get(worldGenRarity, "Pyramid Rarity", 500, "By default : 500").getInt();

            castleGen = config.get(worldGen, "Enable Castle", true).getBoolean();

            castleRarity = config.get(worldGenRarity, "Castle Rarity", 500, "By default : 500").getInt();

            rayGunFire = config.get(itemProperty, "Enable Raygun Fire", true).getBoolean();

            rayGunMelt = config.get(itemProperty, "Enable Raygun Melt", true).getBoolean();

            floobShipExplode = config.get(mobProperty, "Allow Floobship Explosion", false).getBoolean();

            publicUrination = config.get(mobProperty, "Allow Bum Public Urination", true).getBoolean();

            jailActive = config.get(worldGen, "Enable Jail", true).getBoolean();

            blood = config.get(mobProperty, "Enable Blood", true).getBoolean();

            blorpMaxSize = config.get(mobProperty, "Blorp Max Size", 6).getInt();

            guineaPigSpawnAmt = config.get(spawnNbr, "Guinea Pig", 8).getInt();

            babyMummySpawnAmt = config.get(spawnNbr, "Baby Mummy", 8).getInt();

            blackSoulSpawnAmt = config.get(spawnNbr, "Black Soul", 5).getInt();

            mummySpawnAmt = config.get(spawnNbr, "Mummy", 5).getInt();

            armyGuySpawnAmt = config.get(spawnNbr, "Army Guy", 5).getInt();

            hotdogSpawnAmt = config.get(spawnNbr, "Hot Dog", 8).getInt();

            gooGoatSpawnAmt = config.get(spawnNbr, "Goo Goat", 8).getInt();

            kidSpawnAmt = config.get(spawnNbr, "Kid", 5).getInt();

            lollimanSpawnAmt = config.get(spawnNbr, "Lolliman", 3).getInt();

            gSpawnAmt = config.get(spawnNbr, "G", 8).getInt();

            robotTedSpawnAmt = config.get(spawnNbr, "Robot Ted", 8).getInt();

            robotToddSpawnAmt = config.get(spawnNbr, "Robot Todd", 8).getInt();

            lawyerFromHellSpawnAmt = config.get(spawnNbr, "Lawyer From Hell", 8).getInt();

            bigBabySpawnAmt = config.get(spawnNbr, "Big Baby", 6).getInt();

            thiefSpawnAmt = config.get(spawnNbr, "Thief", 8).getInt();

            floobSpawnAmt = config.get(spawnNbr, "Floob", 7).getInt();

            floobShipSpawnAmt = config.get(spawnNbr, "Floob Ship", 2).getInt();

            config.save();
        }
        finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }

        applySpawnLimit();
    }

    private static void applySpawnLimit()
    {
        if (unlimitedSpawn)
        {
            return;
        }

        if (guineaPigSpawnAmt < 0 || guineaPigSpawnAmt > 12)
        {
            guineaPigSpawnAmt = 9;
        }

        if (pyramidRarity < 1 || pyramidRarity > 10)
        {
            pyramidRarity = 5;
        }

        if (castleRarity < 1 || castleRarity > 10)
        {
            castleRarity = 5;
        }

        if (blorpMaxSize < 6 || blorpMaxSize > 99)
        {
            blorpMaxSize = 6;
        }
    }
}