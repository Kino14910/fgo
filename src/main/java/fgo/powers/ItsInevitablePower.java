package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import fgo.relics.MisoPotato;

public class ItsInevitablePower extends AbstractPower {
    public static final String POWER_ID = "ItsInevitablePower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private int damage;
    private final int raise;
    public ItsInevitablePower(AbstractCreature owner, int amount, int damage, int raise) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.damage = damage;
        this.raise = raise;
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/BurningPower84.png";
        String path48 = "fgo/images/powers_Master/BurningPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[0], this.damage);
        } else {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.damage, this.raise);
        }
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        if (AbstractDungeon.player.hasRelic(MisoPotato.ID)) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(mo, this.owner, new BurnDamagePower(mo, 2), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        this.damage += this.raise;
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        //updateDescription();
    }

    public AbstractPower makeCopy() {
        return new ItsInevitablePower(this.owner, this.amount, this.damage, this.raise);
    }
}
