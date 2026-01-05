package fgo.event;

import static fgo.FGOMod.eventPath;
import static fgo.FGOMod.makeID;
import static fgo.utils.ModHelper.eventAscension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import basemod.abstracts.events.phases.TextPhase;
import fgo.cards.colorless.ProofAndRebuttal;

public class ProofAndRebuttalEvent extends BaseEvent {
    public static final String ID = makeID(ProofAndRebuttalEvent.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;
    private int goldLoss = eventAscension() 
            ? AbstractDungeon.miscRng.random(50, 75) 
            : AbstractDungeon.miscRng.random(40, 60);
    public ProofAndRebuttalEvent() {
        super(ID, NAME, eventPath("ProofAndRebuttalEvent"));

        AbstractPlayer p = AbstractDungeon.player;
        goldLoss = Math.min(goldLoss, p.gold);

        registerPhase(0, new TextPhase(DESCRIPTIONS[0])
            .addOption(new TextPhase.OptionInfo(OPTIONS[0], new ProofAndRebuttal())
                .setOptionResult( i -> {
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new ProofAndRebuttal(), (float)Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                    transitionKey("Witness");
                }))
            .addOption(
                new TextPhase.OptionInfo(p.masterDeck.hasUpgradableCards() 
                    ? OPTIONS[1] + goldLoss + OPTIONS[2] 
                    : OPTIONS[5]
                ).enabledCondition(p.masterDeck::hasUpgradableCards)
                .setOptionResult(i -> {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                    upgradeCards();
                    p.loseGold(goldLoss);
                    transitionKey("Enjoy");
                })
            )
            );

        registerPhase("Witness", new TextPhase(DESCRIPTIONS[1])
            .addOption(OPTIONS[3],  i -> openMap())
        );

        registerPhase("Enjoy", new TextPhase(DESCRIPTIONS[2])
            .addOption(OPTIONS[3],  i -> openMap())
        );

        transitionKey(0);
    }

    @Override
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SHINING");
        }
    }

    private void upgradeCards() {
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }

        List<String> cardMetrics = new ArrayList<>();
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            if (upgradableCards.size() == 1) {
                upgradableCards.get(0).upgrade();
                cardMetrics.add(upgradableCards.get(0).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy()));
            } else {
                upgradableCards.get(0).upgrade();
                upgradableCards.get(1).upgrade();
                cardMetrics.add(upgradableCards.get(0).cardID);
                cardMetrics.add(upgradableCards.get(1).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0f - 190.0f * Settings.scale, Settings.HEIGHT / 2.0f));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(1).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0f + 190.0f * Settings.scale, Settings.HEIGHT / 2.0f));
            }
        }

        AbstractEvent.logMetric("ProofAndRebuttalEvent", "Entered Light", null, null, null, cardMetrics, null, null, null, 0, 0, 0, 0, 0, 0);
    }
}
