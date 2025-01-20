package fgo.event;

import basemod.abstracts.events.PhasedEvent;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import fgo.relics.Avenger;
import fgo.relics.OblivionCorrection;
import fgo.relics.SelfReplenishmentMagic;


import static fgo.FGOMod.makeID;

public class DailyLifeattheBeyond extends PhasedEvent {
    public static final String ID = makeID(DailyLifeattheBeyond.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private AbstractRelic gift1;
    private AbstractRelic gift2;
    private AbstractRelic gift3;
    private DAILY_SCREEN screen;
    private final int damage;
    public DailyLifeattheBeyond() {
        super(ID, title, "fgo/images/events/DailyLifeattheBeyond.png");
        //内容第一段。
        this.body = DESCRIPTIONS[0];
        this.screen = DAILY_SCREEN.Daily0;
        this.gift1 = new Avenger();
        this.gift2 = new OblivionCorrection();
        this.gift3 = new SelfReplenishmentMagic();
        //拾取遗物时受到伤害。
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.damage = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.1F);
        } else {
            this.damage = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.075F);
        }
        //选项。
        this.imageEventText.setDialogOption(OPTIONS[5]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case Daily0:
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[0] + this.damage + OPTIONS[6], new Avenger());
                this.imageEventText.setDialogOption(OPTIONS[1] + this.damage + OPTIONS[6], new OblivionCorrection());
                this.imageEventText.setDialogOption(OPTIONS[2] + this.damage + OPTIONS[6], new SelfReplenishmentMagic());
                this.imageEventText.setDialogOption(OPTIONS[3]);
                this.screen = DAILY_SCREEN.Daily1;
                break;
            case Daily1:
                //按钮选择。
                switch (buttonPressed) {
                    //按钮顺序。
                    case 0:
                        //获得遗物。
                        if (AbstractDungeon.player.hasRelic("Avenger")) {
                            this.gift1 = new Circlet();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), this.gift1);
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), this.gift1);
                        }
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.damage));
                        this.imageEventText.updateDialogOption(0, OPTIONS[4], true);
                        return;
                    case 1:
                        //获得遗物。
                        if (AbstractDungeon.player.hasRelic("OblivionCorrection")) {
                            this.gift2 = new Circlet();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), this.gift2);
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), this.gift2);
                        }
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.damage));
                        this.imageEventText.updateDialogOption(1, OPTIONS[4], true);
                        return;
                    case 2:
                        //获得遗物。
                        if (AbstractDungeon.player.hasRelic("SelfReplenishmentMagic")) {
                            this.gift3 = new Circlet();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), this.gift3);
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), this.gift3);
                        }
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.damage));
                        this.imageEventText.updateDialogOption(2, OPTIONS[4], true);
                        return;
                    case 3:
                        //更新为内容第二段（下一段）。
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.screen = DAILY_SCREEN.Daily2;
                        return;
                }
                break;
            case Daily2:
                this.openMap();
        }
    }

    enum DAILY_SCREEN {
        Daily0,
        Daily1,
        Daily2
    }
}
