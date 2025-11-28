package fgo.event;

import static fgo.FGOMod.eventPath;
import static fgo.FGOMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import fgo.relics.BB;

public class DevilSlot extends BaseEvent {
    public static final String ID = makeID(DevilSlot.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public DevilSlot() {
        super(ID, title, eventPath("DevilSlot"));
        this.body = DESCRIPTIONS[0];
        this.imageEventText.setDialogOption(OPTIONS[0], new BB());
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.effectList.add(new PurgeCardEffect(c));
            AbstractDungeon.player.masterDeck.removeCard(c);
            AbstractDungeon.gridSelectScreen.selectedCards.remove(c);
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                if (buttonPressed == 0) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    if (AbstractDungeon.player.hasRelic(BB.ID)) {
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new Circlet());
                    } else {
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new BB());
                    }
                    this.imageEventText.clearRemainingOptions();
                } else {
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    if (!CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).isEmpty()) {
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[2], false);
                    }
                    this.imageEventText.clearRemainingOptions();
                }

                this.screenNum = 1;
                break;
            case 1:
                this.openMap();
        }
    }
}
