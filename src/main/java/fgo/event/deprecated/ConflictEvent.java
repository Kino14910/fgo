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
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import fgo.relics.deprecated.SkullCandy;

public class ConflictEvent extends PhasedEvent {
    public static final String ID = makeID(ConflictEvent.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;
    private final AbstractRelic choice1;
    private final AbstractRelic choice2;
    public ConflictEvent() {
        super(ID, NAME, eventPath("ConflictEvent"));
        ArrayList<AbstractRelic> relics = new ArrayList<>(AbstractDungeon.player.relics);
        Collections.shuffle(relics, new Random(AbstractDungeon.miscRng.randomLong()));
        choice1 = relics.get(0);
        choice2 = relics.get(1);
        registerPhase(0, new TextPhase(DESCRIPTIONS[0])
            .addOption(OPTIONS[0] + choice1.name + OPTIONS[1], i -> {
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
                transitionKey("Attack");
            })
            .addOption(new TextPhase.OptionInfo(OPTIONS[0] + choice2.name + OPTIONS[2], new SkullCandy())
                .setOptionResult(i -> {
                    imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    AbstractDungeon.player.loseRelic(choice2.relicId);
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, new SkullCandy());
                    transitionKey("Relic");
                })
            )
        );

        registerPhase("Attack", new TextPhase(DESCRIPTIONS[1])
            .addOption(OPTIONS[3], i -> openMap()));
        registerPhase("Relic", new TextPhase(DESCRIPTIONS[2])
            .addOption(OPTIONS[3], i -> openMap()));

        transitionKey(0);
    }

    @Override
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SERPENT");
        }
    }
}
