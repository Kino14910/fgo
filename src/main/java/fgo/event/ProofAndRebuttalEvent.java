package fgo.event;

import static fgo.FGOMod.eventPath;
import static fgo.FGOMod.makeID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import fgo.cards.colorless.ProofAndRebuttal;

public class ProofAndRebuttalEvent extends BaseEvent {
    public static final String ID = makeID(ProofAndRebuttalEvent.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private int goldLoss;
    public ProofAndRebuttalEvent() {
        super(ID, title, eventPath("ProofAndRebuttalEvent"));
        body = DESCRIPTIONS[0];
        if (AbstractDungeon.ascensionLevel >= 15) {
            goldLoss = AbstractDungeon.miscRng.random(50, 75);
        } else {
            goldLoss = AbstractDungeon.miscRng.random(40, 60);
        }

        if (goldLoss > AbstractDungeon.player.gold) {
            goldLoss = AbstractDungeon.player.gold;
        }

        imageEventText.setDialogOption(OPTIONS[0], new ProofAndRebuttal());
        if (AbstractDungeon.player.masterDeck.hasUpgradableCards()) {
            imageEventText.setDialogOption(OPTIONS[1] + goldLoss + OPTIONS[2]);
        } else {
            imageEventText.setDialogOption(OPTIONS[5], true);
        }
    }

    @Override
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SHINING");
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new ProofAndRebuttal(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        screenNum = 1;
                        imageEventText.updateDialogOption(0, OPTIONS[3]);
                        imageEventText.clearRemainingOptions();
                        return;
                    case 1:
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                        upgradeCards();
                        AbstractDungeon.player.loseGold(goldLoss);

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

    private void upgradeCards() {
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
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
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F - 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(1).makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F + 190.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F));
            }
        }

        AbstractEvent.logMetric("ProofAndRebuttalEvent", "Entered Light", null, null, null, cardMetrics, null, null, null, 0, 0, 0, 0, 0, 0);
    }
}
