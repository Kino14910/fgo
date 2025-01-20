package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static fgo.FGOMod.makeID;

public class CurseHarmonyPower extends BasePower {
    public static final String POWER_ID = makeID(CurseHarmonyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CurseHarmonyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        String path128 = "img/powers_Master/ThirstForVengeancePower84.png";
        String path48 = "img/powers_Master/ThirstForVengeancePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.owner.hasPower(CursePower.POWER_ID) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            int ChAmount = 0;
            ChAmount += (this.owner.getPower(CursePower.POWER_ID)).amount * this.amount;
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            this.addToBot(new BouncingFlaskAction(randomMonster, ChAmount, 1));
        }
    }

    public AbstractPower makeCopy() {
        return new CurseHarmonyPower(this.owner, this.amount);
    }
}
