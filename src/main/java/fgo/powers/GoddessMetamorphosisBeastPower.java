package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class GoddessMetamorphosisBeastPower extends AbstractPower {
    public static final String POWER_ID = "GoddessMetamorphosisBeastPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    public GoddessMetamorphosisBeastPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        String path128 = "fgo/images/powers_Master/BeastPower84.png";
        String path48 = "fgo/images/powers_Master/BeastPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.addToBot(new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, this.amount), this.amount, true));
            this.addToBot(new ApplyPowerAction(target, this.owner, new CursePower(target, 1), 1, true));
            this.addToBot(new ApplyPowerAction(target, this.owner, new BurnDamagePower(target, 1), 1, true));
        }
    }

    public AbstractPower makeCopy() {
        return new GoddessMetamorphosisBeastPower(this.owner, this.amount);
    }
}
