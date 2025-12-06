
package fgo.event;

import static fgo.FGOMod.eventPath;
import static fgo.FGOMod.makeID;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import fgo.cards.colorless.EightKindness;
import fgo.cards.colorless.MaraPapiyas;
import fgo.cards.colorless.MillenniumCastle;
import fgo.cards.colorless.PrimevalRune;
import fgo.cards.colorless.UndeadBird;
import fgo.cards.fgo.DeathOfDeath;
import fgo.cards.fgo.PeerlessStrike;
import fgo.cards.fgo.SpringOfFire;

public class FGOLibrary extends BaseEvent {
    public static final String ID = makeID(FGOLibrary.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private boolean pickCard = false;
    private final int healAmt;
    private final int maxHPAmt;
    public FGOLibrary() {
        super(ID, title, eventPath("FGOLibrary"));
        body = DESCRIPTIONS[0];
        if (AbstractDungeon.ascensionLevel >= 15) {
            healAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.2F);
            maxHPAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.15F);
        } else {
            healAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.33F);
            maxHPAmt = MathUtils.round((float)AbstractDungeon.player.maxHealth * 0.1F);
        }

        imageEventText.setDialogOption(OPTIONS[0] + maxHPAmt + OPTIONS[5]);
        imageEventText.setDialogOption(OPTIONS[1] + healAmt + OPTIONS[2]);
    }

    @Override
    public void update() {
        super.update();
        if (pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        if (screenNum == 0) {
            if (buttonPressed == 0) {
                imageEventText.updateBodyText(DESCRIPTIONS[2]);
                screenNum = 1;
                imageEventText.updateDialogOption(0, OPTIONS[3]);
                imageEventText.clearRemainingOptions();
                pickCard = true;
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                group.addToBottom(new PrimevalRune());
                group.addToBottom(new SpringOfFire());
                group.addToBottom(new MillenniumCastle());
                group.addToBottom(new DeathOfDeath());
                group.addToBottom(new EightKindness());
                group.addToBottom(new PeerlessStrike());
                group.addToBottom(new UndeadBird());
                group.addToBottom(new MaraPapiyas());

                for (AbstractCard c : group.group) {
                    UnlockTracker.markCardAsSeen(c.cardID);
                }

                AbstractDungeon.gridSelectScreen.open(group, 1, OPTIONS[4], false);
                AbstractDungeon.player.decreaseMaxHealth(maxHPAmt);
                return;
            }
            imageEventText.updateBodyText(DESCRIPTIONS[1]);
            AbstractDungeon.player.heal(healAmt, true);
            screenNum = 1;
            imageEventText.updateDialogOption(0, OPTIONS[3]);
            imageEventText.clearRemainingOptions();
        } else {
            openMap();
        }
    }
}
