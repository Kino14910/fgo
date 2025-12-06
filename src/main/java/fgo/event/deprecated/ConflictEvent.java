package fgo.event.deprecated;

import static fgo.FGOMod.eventPath;
import static fgo.FGOMod.makeID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import fgo.event.BaseEvent;
import fgo.relics.deprecated.SkullCandy;

public class ConflictEvent extends BaseEvent {
    public static final String ID = makeID(ConflictEvent.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private final AbstractRelic choice1;
    private final AbstractRelic choice2;
    private AbstractRelic gift;
    public ConflictEvent() {
        super(ID, title, eventPath("ConflictEvent"));
        body = DESCRIPTIONS[0];
        ArrayList<AbstractRelic> relics = new ArrayList<>(AbstractDungeon.player.relics);
        Collections.shuffle(relics, new Random(AbstractDungeon.miscRng.randomLong()));
        choice1 = relics.get(0);
        choice2 = relics.get(1);
        gift = new SkullCandy();
        imageEventText.setDialogOption(OPTIONS[0] + choice1.name + OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[0] + choice2.name + OPTIONS[2], new SkullCandy());
    }

    @Override
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SERPENT");
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.player.loseRelic(choice1.relicId);
                        int effectCount = 0;
                        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                            if (c.canUpgrade() && c.type == AbstractCard.CardType.ATTACK) {
                                ++effectCount;
                                if (effectCount <= 20) {
                                    float x = MathUtils.random(0.1F, 0.9F) * (float) Settings.WIDTH;
                                    float y = MathUtils.random(0.2F, 0.8F) * (float) Settings.HEIGHT;
                                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
                                    AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                                }

                                c.upgrade();
                                AbstractDungeon.player.bottledCardUpgradeCheck(c);
                            }
                        }
                        screenNum = 1;
                        imageEventText.updateDialogOption(0, OPTIONS[3]);
                        imageEventText.clearRemainingOptions();
                        return;
                    case 1:
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        if (AbstractDungeon.player.hasRelic(SkullCandy.ID)) {
                            gift = new Circlet();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), gift);
                        } else {
                            AbstractDungeon.player.loseRelic(choice2.relicId);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), gift);
                        }

                        screenNum = 1;
                        imageEventText.updateDialogOption(0, OPTIONS[3]);
                        imageEventText.clearRemainingOptions();
                        return;
                    default:
                        imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        screenNum = 1;
                        imageEventText.updateDialogOption(0, OPTIONS[3]);
                        imageEventText.clearRemainingOptions();
                        return;
                }
            case 1:
            default:
                openMap();
        }
    }
}
