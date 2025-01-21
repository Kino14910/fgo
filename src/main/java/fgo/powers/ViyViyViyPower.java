package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


import static fgo.FGOMod.makeID;

public class ViyViyViyPower extends BasePower {
    public static final String POWER_ID = makeID(ViyViyViyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractRelic r;
    private static int IdOffset;
    private final AbstractPlayer p;
    private final String rName;
    public ViyViyViyPower(AbstractCreature owner, AbstractRelic r) {
        super(POWER_ID, TYPE, TURN_BASED, owner, "ConcentrateSpellsPower");
        this.ID = "PropBagPower" + IdOffset;
        IdOffset++;
        this.r = r;
        this.p = AbstractDungeon.player;
        this.rName = r.name;
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r);
        r.atBattleStart();
    }

    @Override
    public void onVictory() {
        this.p.loseRelic(this.r.relicId);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.rName + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {
        return new ViyViyViyPower(this.owner, this.r);
    }
}