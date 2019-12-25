package com.morecreepsrevival.morecreeps.common.sounds;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = MoreCreepsAndWeirdos.modid)
public class CreepsSoundHandler
{
    public static final SoundEvent achievementSound = createSound("achievement");

    public static final SoundEvent welcomeSound = createSound("welcome");

    public static final SoundEvent tombstoneSound = createSound("tombstone");

    public static final SoundEvent mummyHurtSound = createSound("mummyhurt");

    public static final SoundEvent mummySound = createSound("mummy");

    public static final SoundEvent mummyDeathSound = createSound("mummydeath");

    public static final SoundEvent lickSound = createSound("lick");

    public static final SoundEvent blorpColaSound = createSound("blorpcola");

    public static final SoundEvent guineaPigAngrySound = createSound("ggpigangry");

    public static final SoundEvent guineaPigCriticalHitSound = createSound("guineapigcriticalhit");

    public static final SoundEvent guineaPigLevelUpSound = createSound("ggpiglevelup");

    public static final SoundEvent guineaPigSpeedDownSound = createSound("guineapigspeeddown");

    public static final SoundEvent guineaPigMountSound = createSound("ggpigmount");

    public static final SoundEvent guineaPigUnmountSound = createSound("ggpigunmount");

    public static final SoundEvent guineaPigSpeedUpSound = createSound("guineapigspeedup");

    public static final SoundEvent guineaPigArmorSound = createSound("ggpigarmor");

    public static final SoundEvent guineaPigEatSound = createSound("ggpigeat");

    public static final SoundEvent guineaPigFullSound = createSound("ggpigfull");

    public static final SoundEvent guineaPigHotelSound = createSound("guineapighotel");

    public static final SoundEvent guineaPigDeathSound = createSound("ggpigdeath");

    public static final SoundEvent guineaPigSound = createSound("ggpig");

    public static final SoundEvent guineaPigTrainSound = createSound("guineapigtrain");

    public static final SoundEvent guineaPigNoWheatSound = createSound("guineapignowheat");

    public static final SoundEvent guineaPig5LevelSound = createSound("guineapig5level");

    public static final SoundEvent guineaPig10LevelSound = createSound("guineapig10level");

    public static final SoundEvent guineaPig15LevelSound = createSound("guineapig15level");

    public static final SoundEvent guineaPig20LevelSound = createSound("guineapig20level");

    public static final SoundEvent gooGoatSound = createSound("googoat");

    public static final SoundEvent gooGoatHurtSound = createSound("googoathurt");

    public static final SoundEvent gooGoatDeathSound = createSound("googoatdead");

    public static final SoundEvent gooGoatStretchSound = createSound("googoatstretch");

    public static final SoundEvent guineaPigRadioSound = createSound("ggpigradio");

    public static final SoundEvent bandaidSound = createSound("bandaid");

    public static final SoundEvent blackSoulSound = createSound("blacksoul");

    public static final SoundEvent blackSoulHurtSound = createSound("blacksoulhurt");

    public static final SoundEvent blackSoulDeathSound = createSound("blacksouldeath");

    public static final SoundEvent babyMummySound = createSound("babymummy");

    public static final SoundEvent babyMummyDeathSound = createSound("babymummydeath");

    public static final SoundEvent babyMummyHurtSound = createSound("babymummyhurt");

    public static final SoundEvent kidSound = createSound("kid");

    public static final SoundEvent kidRideSound = createSound("kidride");

    public static final SoundEvent kidColdSound = createSound("kidcold");

    public static final SoundEvent kidHurtSound = createSound("kidhurt");

    public static final SoundEvent kidDeathSound = createSound("kiddeath");

    public static final SoundEvent kidMountSound = createSound("kidup");

    public static final SoundEvent kidUnmountSound = createSound("kiddown");

    public static final SoundEvent kidNoPickupSound = createSound("kidnontpickup");

    public static final SoundEvent lollimanSound = createSound("lolliman");

    public static final SoundEvent lollimanHurtSound = createSound("lollimanhurt");

    public static final SoundEvent lollimanDeathSound = createSound("lollimandeath");

    public static final SoundEvent lollimanTakeOffSound = createSound("lollimantakeoff");

    public static final SoundEvent lollimanExplodeSound = createSound("lollimanexplode");

    public static final SoundEvent lollySound = createSound("lolly");

    public static final SoundEvent kidFindSound = createSound("kidfind");

    public static final SoundEvent pyramidDiscoveredSound = createSound("pyramiddiscovered");

    public static final SoundEvent battleCastleSound = createSound("battlecastle");

    public static final SoundEvent pyramidCurseSound = createSound("pyramidcurse");

    public static final SoundEvent pyramidSound = createSound("pyramid");

    public static final SoundEvent pyramidHurtSound = createSound("pyramidhurt");

    public static final SoundEvent pyramidDeathSound = createSound("pyramiddeath");

    public static final SoundEvent raygunSound = createSound("raygun");

    public static final SoundEvent evilEggCluckSound = createSound("evileggcluck");

    public static final SoundEvent evilCreatureSound = createSound("evilcreature");

    public static final SoundEvent evilCreatureHurtSound = createSound("evilcreaturehurt");

    public static final SoundEvent evilCreatureDeathSound = createSound("evilcreaturedeath");

    public static final SoundEvent evilCreatureJumpSound = createSound("evilcreaturejump");

    public static final SoundEvent trophySmashSound = createSound("trophysmash");

    public static final SoundEvent earthGemSound = createSound("earthgem");

    public static final SoundEvent miningGemSound = createSound("mininggem");

    public static final SoundEvent miningGemBadSound = createSound("mininggembad");

    public static final SoundEvent skyGemUpSound = createSound("skygemup");

    public static final SoundEvent skyGemDownSound = createSound("skygemdown");

    public static final SoundEvent healingGemSound = createSound("healinggem");

    public static final SoundEvent castleCritterSound = createSound("castlecritter");

    public static final SoundEvent castleCritterHurtSound = createSound("castlecritterhurt");

    public static final SoundEvent castleCritterDeathSound = createSound("castlecritterdeath");

    public static final SoundEvent castleGuardSound = createSound("castleguard");

    public static final SoundEvent castleGuardHurtSound = createSound("castleguardhurt");

    public static final SoundEvent castleGuardDeathSound = createSound("castleguarddeath");

    public static final SoundEvent castleGuardMadSound = createSound("castleguardmad");

    public static final SoundEvent castleKingSound = createSound("castleking");

    public static final SoundEvent castleKingHurtSound = createSound("castlekinghurt");

    public static final SoundEvent castleKingDeathSound = createSound("castlekingdeath");

    public static final SoundEvent fireGemSound = createSound("firegem");

    public static final SoundEvent chewSound = createSound("chew");

    public static final SoundEvent gemSwordSound = createSound("gemsword");

    public static final SoundEvent shrinkRaySound = createSound("shrinkray");

    public static final SoundEvent horseHeadGemSound = createSound("horseheadgem");

    public static final SoundEvent gSound = createSound("g");

    public static final SoundEvent gHurtSound = createSound("ghurt");

    public static final SoundEvent gDeathSound = createSound("gdeath");

    public static final SoundEvent tedInsultSound = createSound("tedinsult");

    public static final SoundEvent robotHurtSound = createSound("robothurt");

    public static final SoundEvent tedDeadSound = createSound("teddead");

    public static final SoundEvent toddInsultSound = createSound("toddinsult");

    public static final SoundEvent toddDeadSound = createSound("todddead");

    public static final SoundEvent lawyerSound = createSound("lawyer");

    public static final SoundEvent undeadLawyerSound = createSound("lawyerundead");

    public static final SoundEvent lawyerHurtSound = createSound("lawyerhurt");

    public static final SoundEvent undeadLawyerHurtSound = createSound("lawyerundeadhurt");

    public static final SoundEvent lawyerDeathSound = createSound("lawyerdeath");

    public static final SoundEvent undeadLawyerDeathSound = createSound("lawyerundeaddeath");

    public static final SoundEvent lawyerMoneyHitSound = createSound("lawyermoneyhit");

    public static final SoundEvent lawyerBustedSound = createSound("lawyerbusted");

    public static final SoundEvent lawyerSuckSound = createSound("lawyersuck");

    public static final SoundEvent lawyerTakeSound = createSound("lawyertake");

    public static final SoundEvent lawyerBumSound = createSound("lawyerbum");

    public static final SoundEvent bigBabySound = createSound("bigbaby");

    public static final SoundEvent bigBabyHurtSound = createSound("bigbabyhurt");

    public static final SoundEvent babyTakeHomeSound = createSound("babytakehome");

    public static final SoundEvent babyShrinkSound = createSound("babyshrink");

    public static final SoundEvent shrinkKillSound = createSound("shrinkkill");

    public static final SoundEvent schlumpSound = createSound("schlump");

    public static final SoundEvent schlumpHurtSound = createSound("schlumphurt");

    public static final SoundEvent schlumpDeathSound = createSound("schlumpdeath");

    public static final SoundEvent schlumpOverloadSound = createSound("schlump-overload");

    public static final SoundEvent schlumpIndoorsSound = createSound("schlump-indoors");

    public static final SoundEvent schlumpBrightSound = createSound("schlump-bright");

    public static final SoundEvent schlumpRoomSound = createSound("schlump-room");

    public static final SoundEvent schlumpBigSound = createSound("schlump-big");

    public static final SoundEvent schlumpSucksSound = createSound("schlump-sucks");

    public static final SoundEvent schlumpRewardSound = createSound("schlump-reward");

    public static final SoundEvent schlumpOkSound = createSound("schlump-ok");

    public static final SoundEvent barfSound = createSound("barf");

    public static final SoundEvent bulletSound = createSound("bullet");

    public static final SoundEvent extinguisherSound = createSound("extinguisher");

    public static final SoundEvent thiefSound = createSound("thief");

    public static final SoundEvent thiefHurtSound = createSound("thiefhurt");

    public static final SoundEvent thiefDeathSound = createSound("thiefdeath");

    public static final SoundEvent thiefFindPlayerSound = createSound("thieffindplayer");

    public static final SoundEvent thiefStealSound = createSound("thiefsteal");

    public static final SoundEvent floobSound = createSound("floob");

    public static final SoundEvent floobHurtSound = createSound("floobhurt");

    public static final SoundEvent floobDeathSound = createSound("floobdeath");

    public static final SoundEvent floobShipSound = createSound("floobship");

    public static final SoundEvent floobShipDeathSound = createSound("floobshipexplode");

    public static final SoundEvent floobShipSpawnSound = createSound("floobshipspawn");

    public static final SoundEvent horseHeadSound = createSound("horsehead");

    public static final SoundEvent hippoHurtSound = createSound("hippohurt");

    public static final SoundEvent hippoDeathSound = createSound("hippodeath");

    public static final SoundEvent giraffeSplashSound = createSound("giraffesplash");

    public static final SoundEvent giraffeGallopSound = createSound("giraffegallop");

    public static final SoundEvent horseHeadBlastOffSound = createSound("horseheadblastoff");

    public static final SoundEvent hotdogSound = createSound("hotdog");

    public static final SoundEvent hotdogHurtSound = createSound("hotdoghurt");

    public static final SoundEvent hotdogDeathSound = createSound("hotdogdeath");

    public static final SoundEvent digBugDigSound = createSound("digbugdig");

    public static final SoundEvent digBugCallSound = createSound("digbugcall");

    public static final SoundEvent digBugHurtSound = createSound("digbughurt");

    public static final SoundEvent digBugDeathSound = createSound("digbugdeath");

    public static final SoundEvent bubbleScumSound = createSound("bubblescum");

    public static final SoundEvent bubbleScumHurtSound = createSound("bubblescumhurt");

    public static final SoundEvent bubbleScumDeathSound = createSound("bubblescumdeath");

    public static final SoundEvent hotdogTrainSound = createSound("hotdogtrain");

    public static final SoundEvent hotdogNoBonesSound = createSound("hotdognobones");

    public static final SoundEvent hotdog5LevelSound = createSound("hotdog5level");

    public static final SoundEvent hotdog10LevelSound = createSound("hotdog10level");

    public static final SoundEvent hotdog15LevelSound = createSound("hotdog15level");

    public static final SoundEvent hotdog20LevelSound = createSound("hotdog20level");

    public static final SoundEvent hotdogPickupSound = createSound("hotdogpickup");

    public static final SoundEvent hotdogPutDownSound = createSound("hotdogputdown");

    public static final SoundEvent hotdogEatSound = createSound("hotdogeat");

    public static final SoundEvent hotdogAttackSound = createSound("hotdogattack");

    public static final SoundEvent hotdogKillSound = createSound("hotdogkill");

    public static final SoundEvent hotdogTamedSound = createSound("hotdogtamed");

    public static final SoundEvent digBugEatSound = createSound("digbugeat");

    public static final SoundEvent digBugFullSound = createSound("digbugfull");

    public static final SoundEvent bubbleScumPickupSound = createSound("bubblescumpickup");

    public static final SoundEvent bubbleScumPutDownSound = createSound("bubblescumputdown");

    public static final SoundEvent ratManSound = createSound("ratman");

    public static final SoundEvent ratManScratchSound = createSound("ratmanscratch");

    public static final SoundEvent ratManHurtSound = createSound("ratmanhurt");

    public static final SoundEvent giraffeSound = createSound("giraffe");

    public static final SoundEvent salHurtSound = createSound("salhurt");

    public static final SoundEvent salDeadSound = createSound("saldead");

    public static final SoundEvent prisonerSound = createSound("prisoner");

    public static final SoundEvent prisonerHurtSound = createSound("prisonerhurt");

    public static final SoundEvent prisonerDeathSound = createSound("prisonerdeath");

    public static final SoundEvent salGreetingSound = createSound("salgreeting");

    public static final SoundEvent growRaySound = createSound("growray");

    public static final SoundEvent salRatsSound = createSound("salrats");

    public static final SoundEvent salNoMoneySound = createSound("salnomoney");

    public static final SoundEvent salSaleSound = createSound("salsale");

    public static final SoundEvent floobShipClangSound = createSound("floobshipclang");

    public static final SoundEvent snowDevilSound = createSound("snowdevil");

    public static final SoundEvent snowDevilHurtSound = createSound("snowdevilhurt");

    public static final SoundEvent snowDevilDeathSound = createSound("snowdevildeath");

    public static final SoundEvent evilEggBirthSound = createSound("evileggbirth");

    public static final SoundEvent hotdogHeavenSound = createSound("hotdogheaven");

    public static final SoundEvent blorpSound = createSound("blorp");

    public static final SoundEvent blorpHurtSound = createSound("blorphurt");

    public static final SoundEvent blorpDeathSound = createSound("blorpdeath");

    public static final SoundEvent camelSound = createSound("camel");

    public static final SoundEvent camelHurtSound = createSound("camelhurt");

    public static final SoundEvent camelDeathSound = createSound("cameldeath");

    public static final SoundEvent blorpEatSound = createSound("blorpeat");

    public static final SoundEvent blorpGrowSound = createSound("blorpgrow");

    public static final SoundEvent blorpBounceSound = createSound("blorpbounce");

    public static final SoundEvent giraffeHurtSound = createSound("giraffehurt");

    public static final SoundEvent giraffeDeadSound = createSound("giraffedead");

    public static final SoundEvent giraffeChewSound = createSound("giraffechew");

    public static final SoundEvent giraffeTamedSound = createSound("giraffetamed");

    public static final SoundEvent evilLaughSound = createSound("evillaugh");

    public static final SoundEvent evilExplosionSound = createSound("evilexplosion");

    public static final SoundEvent evilHurtSound = createSound("evilhurt");

    public static final SoundEvent manDogSound = createSound("mandog");

    public static final SoundEvent manDogHurtSound = createSound("mandoghurt");

    public static final SoundEvent manDogDeathSound = createSound("mandogdeath");

    public static final SoundEvent superDogNameSound = createSound("superdogname");

    public static final SoundEvent manDogTamedSound = createSound("mandogtamed");

    public static final SoundEvent snowDevilTamedSound = createSound("snowdeviltamed");

    public static final SoundEvent cavemanNiceSound = createSound("cavemanice");

    public static final SoundEvent cavemanEvilSound = createSound("cavemanevil");

    public static final SoundEvent cavemanFreeSound = createSound("cavemanfree");

    public static final SoundEvent cavewomanFreeSound = createSound("cavewomanfree");

    public static final SoundEvent cavemanFrozenSound = createSound("cavemanfrozen");

    public static final SoundEvent cavewomanFrozenSound = createSound("cavewomanfrozen");

    public static final SoundEvent cavemanHurtSound = createSound("cavemanhurt");

    public static final SoundEvent cavewomanHurtSound = createSound("cavewomanhurt");

    public static final SoundEvent cavemanDeadSound = createSound("cavemandead");

    public static final SoundEvent cavewomanDeadSound = createSound("cavewomandead");

    public static final SoundEvent caveDrumsSound = createSound("cavedrums");

    public static final SoundEvent cavemanBuildSound = createSound("cavemanbuild");

    public static final SoundEvent evilLightSound = createSound("evillight");

    public static final SoundEvent rocketFireSound = createSound("rocketfire");

    private static SoundEvent createSound(String soundName)
    {
        return (new SoundEvent(new ResourceLocation(MoreCreepsAndWeirdos.modid, soundName))).setRegistryName(soundName);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().registerAll(
                achievementSound,
                welcomeSound,
                tombstoneSound,
                mummyHurtSound,
                mummySound,
                mummyDeathSound,
                lickSound,
                blorpColaSound,
                guineaPigAngrySound,
                guineaPigCriticalHitSound,
                guineaPigLevelUpSound,
                guineaPigSpeedDownSound,
                guineaPigMountSound,
                guineaPigUnmountSound,
                guineaPigSpeedUpSound,
                guineaPigArmorSound,
                guineaPigEatSound,
                guineaPigFullSound,
                guineaPigHotelSound,
                guineaPigDeathSound,
                guineaPigSound,
                guineaPigTrainSound,
                guineaPigNoWheatSound,
                guineaPig5LevelSound,
                guineaPig10LevelSound,
                guineaPig15LevelSound,
                guineaPig20LevelSound,
                gooGoatSound,
                gooGoatHurtSound,
                gooGoatDeathSound,
                gooGoatStretchSound,
                guineaPigRadioSound,
                bandaidSound,
                blackSoulSound,
                blackSoulHurtSound,
                blackSoulDeathSound,
                babyMummySound,
                babyMummyDeathSound,
                babyMummyHurtSound,
                kidSound,
                kidRideSound,
                kidColdSound,
                kidHurtSound,
                kidDeathSound,
                kidMountSound,
                kidUnmountSound,
                kidNoPickupSound,
                lollimanSound,
                lollimanHurtSound,
                lollimanDeathSound,
                lollimanTakeOffSound,
                lollimanExplodeSound,
                kidFindSound,
                pyramidDiscoveredSound,
                battleCastleSound,
                pyramidCurseSound,
                pyramidSound,
                pyramidHurtSound,
                pyramidDeathSound,
                raygunSound,
                evilEggCluckSound,
                evilCreatureSound,
                evilCreatureHurtSound,
                evilCreatureDeathSound,
                evilCreatureJumpSound,
                trophySmashSound,
                earthGemSound,
                miningGemSound,
                miningGemBadSound,
                skyGemUpSound,
                skyGemDownSound,
                castleCritterSound,
                castleCritterHurtSound,
                castleCritterDeathSound,
                castleGuardSound,
                castleGuardHurtSound,
                castleGuardDeathSound,
                castleGuardMadSound,
                castleKingSound,
                castleKingHurtSound,
                castleKingDeathSound,
                fireGemSound,
                chewSound,
                gemSwordSound,
                shrinkRaySound,
                horseHeadGemSound,
                gSound,
                gHurtSound,
                gDeathSound,
                tedInsultSound,
                robotHurtSound,
                tedDeadSound,
                toddInsultSound,
                toddDeadSound,
                lawyerSound,
                undeadLawyerSound,
                lawyerHurtSound,
                undeadLawyerHurtSound,
                lawyerDeathSound,
                undeadLawyerDeathSound,
                lawyerMoneyHitSound,
                lawyerBustedSound,
                lawyerSuckSound,
                lawyerTakeSound,
                lawyerBumSound,
                bigBabySound,
                bigBabyHurtSound,
                babyTakeHomeSound,
                babyShrinkSound,
                shrinkKillSound,
                schlumpSound,
                schlumpHurtSound,
                schlumpDeathSound,
                schlumpOverloadSound,
                schlumpIndoorsSound,
                schlumpBrightSound,
                schlumpRoomSound,
                schlumpBigSound,
                schlumpSucksSound,
                schlumpRewardSound,
                schlumpOkSound,
                barfSound,
                bulletSound,
                extinguisherSound,
                thiefSound,
                thiefHurtSound,
                thiefDeathSound,
                thiefFindPlayerSound,
                thiefStealSound,
                floobSound,
                floobHurtSound,
                floobDeathSound,
                floobShipSound,
                floobShipDeathSound,
                floobShipSpawnSound,
                horseHeadSound,
                hippoHurtSound,
                hippoDeathSound,
                giraffeSplashSound,
                giraffeGallopSound,
                horseHeadBlastOffSound,
                hotdogSound,
                hotdogHurtSound,
                hotdogDeathSound,
                digBugDigSound,
                digBugCallSound,
                digBugHurtSound,
                digBugDeathSound,
                bubbleScumSound,
                bubbleScumHurtSound,
                bubbleScumDeathSound,
                hotdogTrainSound,
                hotdogNoBonesSound,
                hotdog5LevelSound,
                hotdog10LevelSound,
                hotdog15LevelSound,
                hotdog20LevelSound,
                hotdogPickupSound,
                hotdogPutDownSound,
                hotdogEatSound,
                hotdogAttackSound,
                hotdogKillSound,
                hotdogTamedSound,
                digBugEatSound,
                digBugFullSound,
                bubbleScumPickupSound,
                bubbleScumPutDownSound,
                ratManSound,
                ratManScratchSound,
                ratManHurtSound,
                giraffeSound,
                salHurtSound,
                salDeadSound,
                prisonerSound,
                prisonerHurtSound,
                prisonerDeathSound,
                salGreetingSound,
                growRaySound,
                salRatsSound,
                salNoMoneySound,
                salSaleSound,
                floobShipClangSound,
                snowDevilSound,
                snowDevilHurtSound,
                snowDevilDeathSound,
                evilEggBirthSound,
                hotdogHeavenSound,
                blorpSound,
                blorpHurtSound,
                blorpDeathSound,
                camelSound,
                camelHurtSound,
                camelDeathSound,
                blorpEatSound,
                blorpGrowSound,
                blorpBounceSound,
                giraffeHurtSound,
                giraffeDeadSound,
                giraffeChewSound,
                giraffeTamedSound,
                evilLaughSound,
                evilExplosionSound,
                evilHurtSound,
                manDogSound,
                manDogHurtSound,
                manDogDeathSound,
                superDogNameSound,
                manDogTamedSound,
                snowDevilTamedSound,
                cavemanEvilSound,
                cavemanFreeSound,
                cavemanFrozenSound,
                cavemanNiceSound,
                cavewomanFreeSound,
                cavewomanFrozenSound,
                cavemanHurtSound,
                cavewomanHurtSound,
                cavemanDeadSound,
                cavewomanDeadSound,
                caveDrumsSound,
                cavemanBuildSound,
                evilLightSound,
                rocketFireSound
        );
    }
}
