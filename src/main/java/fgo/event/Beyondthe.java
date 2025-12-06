package fgo.event;

import static fgo.FGOMod.eventPath;
import static fgo.FGOMod.makeID;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import fgo.cards.colorless.Shvibzik;

public class Beyondthe extends BaseEvent {
    public static final String ID = makeID(Beyondthe.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private CUR_SCREEN screen;
    private final int maxHPAmt;
    public Beyondthe() {
        super(ID, title, eventPath("Beyondthe"));
        body = DESCRIPTIONS[0];
        if (AbstractDungeon.ascensionLevel >= 15) {
            maxHPAmt = MathUtils.round(4);
        } else {
            maxHPAmt = MathUtils.round(6);
        }
        screen = CUR_SCREEN.CONTINUE0;
        //人类即为过去延续到未来的足迹（记忆）， NL 只有一直积累经验、知识与故事， NL 才能作为人而不断成长。
        imageEventText.setDialogOption(OPTIONS[0]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screen) {
            case CONTINUE0:
                imageEventText.updateBodyText(DESCRIPTIONS[1]);
                imageEventText.updateDialogOption(0, OPTIONS[1], new Shvibzik());
                imageEventText.setDialogOption(OPTIONS[2] + maxHPAmt + OPTIONS[3]);
                screen = CUR_SCREEN.CONTINUE1;
                break;
            case CONTINUE1:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Shvibzik(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[4]);
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        screen = CUR_SCREEN.CONTINUE2;
                        return;
                    case 1:
                        AbstractDungeon.player.increaseMaxHp(maxHPAmt, true);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[4]);
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        screen = CUR_SCREEN.CONTINUE2;
                        return;
                }
                break;
            case CONTINUE2:
                openMap();
            default:
                break;
        }
    }
}
