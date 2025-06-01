package fgo;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import fgo.action.FgoNpAction;
import fgo.cards.BaseCard;
import fgo.characters.Master;
import fgo.event.*;
import fgo.monster.Emiya;
import fgo.panel.CommandSpellPanel;
import fgo.panel.FGOConfig;
import fgo.panel.NobleDeck;
import fgo.patches.Enum.FGOCardColor;
import fgo.potions.BasePotion;
import fgo.powers.NPRatePower;
import fgo.relics.Avenger;
import fgo.relics.BaseRelic;
import fgo.relics.SuitcaseFgo;
import fgo.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static fgo.characters.Master.fgoNp;
import static fgo.patches.Enum.ThmodClassEnum.MASTER_CLASS;
import static fgo.util.GeneralUtils.addToBot;

@SpireInitializer
public class FGOMod implements
        EditCharactersSubscriber,
        EditCardsSubscriber, //up at the top
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber,
        OnCardUseSubscriber,
        OnStartBattleSubscriber,
        OnPlayerDamagedSubscriber,
        PostBattleSubscriber,
        PostCreateStartingDeckSubscriber
//        PostUpdateSubscriber,
//        PostRenderSubscriber,
        {
    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
    private static final String resourcesFolder = checkResourcesPath();
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.

    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }

    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.
    public static void initialize() {
        new FGOMod();
    }

    /*----------Create new Color----------*/
    public static final String CARD_ENERGY_ORB = "fgo/images/ui/energyOrb.png";
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    public static final Color NOBLE = CardHelper.getColor(255, 215, 0);
    //攻击、技能、能力牌的背景图片(512)
    // private static final String ATTACK_CC = "fgo/images/512/bg_attack_MASTER_s.png";
    // private static final String SKILL_CC = "fgo/images/512/bg_skill_MASTER_s.png";
    // private static final String POWER_CC = "fgo/images/512/bg_power_MASTER_s.png";
    private static final String DEFAULT_CC = "fgo/images/512/bg_master_s.png";
    private static final String ENERGY_ORB_CC = "fgo/images/512/MASTEROrb.png";
    //攻击、技能、能力牌的背景图片(1024)
    // private static final String ATTACK_CC_PORTRAIT = "fgo/images/1024/bg_attack_MASTER.png";
    // private static final String SKILL_CC_PORTRAIT = "fgo/images/1024/bg_skill_MASTER.png";
    // private static final String POWER_CC_PORTRAIT = "fgo/images/1024/bg_power_MASTER.png";
    private static final String DEFAULT_CC_PORTRAIT = "fgo/images/1024/bg_master.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "fgo/images/1024/MASTEROrb.png";
    //宝具牌
    private static final String ATTACK_Noble = "fgo/images/512/bg_attack_Noble_s.png";
    private static final String SKILL_Noble = "fgo/images/512/bg_skill_Noble_s.png";
    private static final String POWER_Noble = "fgo/images/512/bg_power_Noble_s.png";
    //攻击、技能、能力牌的背景图片(1024)
    private static final String ATTACK_Noble_PORTRAIT = "fgo/images/1024/bg_attack_Noble.png";
    private static final String SKILL_Noble_PORTRAIT = "fgo/images/1024/bg_skill_Noble.png";
    private static final String POWER_Noble_PORTRAIT = "fgo/images/1024/bg_power_Noble.png";
    //角色图标。
    private static final String MY_CHARACTER_BUTTON = "fgo/images/charSelect/MasterButton.png";
    //默认背景图片。
    private static final String MASTER_PORTRAIT = "fgo/images/charSelect/MasterPortrait1.png";
    public static SpireConfig config;

    static {
        try {
            config = new SpireConfig(modID, "config");
        } catch (Exception e) {
            logger.error("Failed to initialize config file for FGO Mod", e);
        }
    }

    public FGOMod() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        BaseMod.addColor(FGOCardColor.FGO, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, DEFAULT_CC, DEFAULT_CC, DEFAULT_CC, ENERGY_ORB_CC, DEFAULT_CC_PORTRAIT, DEFAULT_CC_PORTRAIT, DEFAULT_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
        BaseMod.addColor(FGOCardColor.Noble_Phantasm, NOBLE, NOBLE, NOBLE, NOBLE, NOBLE, NOBLE, NOBLE, ATTACK_Noble, SKILL_Noble, POWER_Noble, ENERGY_ORB_CC, ATTACK_Noble_PORTRAIT, SKILL_Noble_PORTRAIT, POWER_Noble_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
        BaseMod.addSaveField("commandSpellCount", new CommandSpellPanel());
        logger.info(modID + " subscribed to BaseMod.");
    }

    @Override
    public void receivePostInitialize() {
        registerPotions();
        registerEvents();
        //This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(imagePath("ui/badge.png"));
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.

        //If you want to set up a config panel, that will be done here.
        //The Mod Badges page has a basic example of this, but setting up config is overall a bit complex.

        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, new FGOConfig());

        //顶部宝具牌预览。
        BaseMod.addTopPanelItem(new NobleDeck());

        if(config.getBool("enableEmiya")){
            BaseMod.addMonster(Emiya.ID, Emiya.NAME, () -> new MonsterGroup(new AbstractMonster[]{new Emiya()}));
            BaseMod.addBoss(TheCity.ID, Emiya.ID, "fgo/images/monster/map_emiya.png", "fgo/images/monster/map_emiya_outline.png");
        }
    }

            /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadLocalization(String lang) {
        //While this does load every type of localization, most of these files are just outlines so that you can see how they're formatted.
        //Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang, "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class,
                localizationPath(lang, "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                localizationPath(lang, "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                localizationPath(lang, "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class,
                localizationPath(lang, "UIStrings.json"));
        BaseMod.loadCustomStringsFile(MonsterStrings.class,
                localizationPath(lang, "MonsterStrings.json"));
        BaseMod.loadCustomStringsFile(TutorialStrings.class,
                localizationPath(lang, "TutorialStrings.json"));
    }

    @Override
    public void receiveEditKeywords()
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            keyword.prep();
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try
            {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    keyword.prep();
                    registerKeyword(keyword);
                }
            }
            catch (Exception e)
            {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
        if (!info.ID.isEmpty())
        {
            keywords.put(info.ID, info);
        }
    }

    //These methods are used to generate the correct filepaths to various parts of the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String imagePath(String file) {
        return resourcesFolder + "/images/" + file;
    }
    public static String characterPath(String file) {
        return resourcesFolder + "/images/character/" + file;
    }
    public static String powerPath(String file) {
        return resourcesFolder + "/images/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/images/relics/" + file;
    }

    /**
     * Checks the expected resources path based on the package name.
     */
    private static String checkResourcesPath() {
        String name = FGOMod.class.getName(); //getPackage can be iffy with patching, so class name is used instead.
        int separator = name.indexOf('.');
        if (separator > 0)
            name = name.substring(0, separator);

        FileHandle resources = new LwjglFileHandle(name, Files.FileType.Internal);

        if (!resources.exists()) {
            throw new RuntimeException("\n\tFailed to find resources folder; expected it to be named \"" + name + "\"." +
                    " Either make sure the folder under resources has the same name as your mod's package, or change the line\n" +
                    "\t\"private static final String resourcesFolder = checkResourcesPath();\"\n" +
                    "\tat the top of the " + FGOMod.class.getSimpleName() + " java file.");
        }
        if (!resources.child("images").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'images' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "images folder is in the correct location.");
        }
        if (!resources.child("localization").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'localization' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "localization folder is in the correct location.");
        }

        return name;
    }

    /**
     * This determines the mod's ID based on information stored by ModTheSpire.
     */
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(FGOMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter(new Master("Master"), MY_CHARACTER_BUTTON, MASTER_PORTRAIT, MASTER_CLASS);
    }

    @Override
    public void receiveEditCards() { //somewhere in the class
        AutoAdd autoAdd = new AutoAdd(modID);
        //Loads files from this mod
        autoAdd.packageFilter(BaseCard.class) //In the same package as this class
                .notPackageFilter("fgo.cards.optionCards")
                .notPackageFilter("fgo.cards.deprecated");

//        System.out.println("enableColorlessCards: " + config.getBool("enableColorlessCards"));
        if (!config.getBool("enableColorlessCards")) {
            autoAdd = autoAdd.notPackageFilter("fgo.cards.colorless");
        }
        autoAdd.setDefaultSeen(true) //And marks them as seen in the compendium
                .cards(); //Adds the cards
        BaseMod.addDynamicVariable(new NoblePhantasmVariable());
        BaseMod.addDynamicVariable(new CriticalStarVariable());
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(BaseRelic.class)
                .notPackageFilter("fgo.relics.deprecated")
                .any(BaseRelic.class, (info, relic) -> { //Run this code for any classes that extend this class
                    if (relic.pool != null)
                        BaseMod.addRelicToCustomPool(relic, relic.pool); //Register a custom character specific relic
                    else
                        BaseMod.addRelic(relic, relic.relicType); //Register a shared or base game character specific relic

                    //If the class is annotated with @AutoAdd.Seen, it will be marked as seen, making it visible in the relic library.
                    //If you want all your relics to be visible by default, just remove this if statement.
    //                if (info.seen)
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                });
    }

     public static void registerPotions() {
        new AutoAdd(modID) //Loads files from this mod
            .packageFilter(BasePotion.class) //In the same package as this class
            .any(BasePotion.class, (info, potion) -> { //Run this code for any classes that extend this class
                //These three null parameters are colors.
                //If they're not null, they'll overwrite whatever color is set in the potions themselves.
                //This is an old feature added before having potions determine their own color was possible.
                BaseMod.addPotion(potion.getClass(), BaseMod.getPotionLiquidColor(potion.ID), BaseMod.getPotionHybridColor(potion.ID), BaseMod.getPotionSpotsColor(potion.ID), potion.ID, MASTER_CLASS);
                //playerClass will make a potion character-specific. By default, it's null and will do nothing.
            });
    }

    public static void registerEvents() {
        //事件。
        // BaseMod.addEvent(WinterEvent.ID, WinterEvent.class, TheCity.ID);
        // BaseMod.addEvent("FGOLibrary", FGOLibrary.class, "TheCity");
        BaseMod.addEvent(ConflictEvent.ID, ConflictEvent.class, TheBeyond.ID);
        BaseMod.addEvent(ProofAndRebuttalEvent.ID, ProofAndRebuttalEvent.class, Exordium.ID);
        BaseMod.addEvent(ManofChaldea.ID, ManofChaldea.class, TheBeyond.ID);
        BaseMod.addEvent(Beyondthe.ID, Beyondthe.class, TheBeyond.ID);
        BaseMod.addEvent(DailyLifeattheBeyond.ID, DailyLifeattheBeyond.class, TheCity.ID);
        BaseMod.addEvent(DevilSlot.ID, DevilSlot.class, TheBeyond.ID);
//        BaseMod.addEvent((new AddEventParams.Builder(FGOLibrary.ID, FGOLibrary.class))
//                .dungeonID(TheCity.ID)
//                .playerClass(MASTER_CLASS)
//                .create());
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        if (!(AbstractDungeon.player instanceof Master)) {
            return;
        }

        int npMultiplier = 5;
        int npGain = 0;

        if (AbstractDungeon.player.hasPower(NPRatePower.POWER_ID)) {
            npMultiplier *= 2;
        }

        int costForTurn = abstractCard.costForTurn;

        if (costForTurn == -1) {
            npGain = EnergyPanel.totalCount * npMultiplier; //X费用牌
        } else if (costForTurn > 0) {
            npGain = costForTurn * npMultiplier;
        }

        if (npGain > 0) {
            addToBot(new FgoNpAction(npGain));
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof Master) {
            fgoNp = 0;
            if (AbstractDungeon.player.hasRelic(SuitcaseFgo.ID)) {
                fgoNp = 20;
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom r) {
        if (AbstractDungeon.player instanceof Master) {
            //在每场战斗开始时宝具值变为0。
            addToBot(new FgoNpAction(-300));
        }
    }

    @Override
    public int receiveOnPlayerDamaged(int i, DamageInfo damageInfo) {
        if (!(AbstractDungeon.player instanceof Master)) {
            return i;
        }

        if (damageInfo.type != DamageInfo.DamageType.NORMAL
                || damageInfo.owner == null
                || damageInfo.owner == AbstractDungeon.player
                || AbstractDungeon.currMapNode == null
                || AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT
                || i == 99999) {
            return i;
        }

        addToBot(new FgoNpAction(AbstractDungeon.player.hasPower(NPRatePower.POWER_ID) ? 2 * i : i));

        if (AbstractDungeon.player.hasRelic(Avenger.ID)) {
            addToBot(new FgoNpAction(i / 10 * 3));
        }
        return i;
    }

    @Override
    public void receivePostCreateStartingDeck(AbstractPlayer.PlayerClass playerClass, CardGroup cardGroup) {
        CommandSpellPanel.reset();
    }


}
