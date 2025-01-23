package fgo.event;

import basemod.abstracts.events.PhasedEvent;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import fgo.cards.colorless.Shvibzik;

public abstract class BaseEvent extends PhasedEvent {
    public String dungeonID;
    protected String body;
    protected String[] descriptions;
    protected String[] options;
    protected String title;

    public enum CUR_SCREEN {
        CONTINUE0,
        CONTINUE1,
        CONTINUE2,
        CONTINUE3,
        CONTINUE4,
        CONTINUE5,
        CONTINUE6,
        CONTINUE7,
        CONTINUE8,
        CONTINUE9,
        CONTINUE10,
        CONTINUE11;

        CUR_SCREEN() {
        }
    }

    public BaseEvent(String id, String name, String imagePath) {
        super(id, name, imagePath);
    }
}
