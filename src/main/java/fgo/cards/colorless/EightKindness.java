package fgo.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import fgo.cards.FGOCard;
public class EightKindness extends FGOCard {
    public static final String ID = makeID(EightKindness.class.getSimpleName());
    public EightKindness() {
        super(ID, 3, CardType.SKILL, CardTarget.SELF, CardRarity.RARE, CardColor.COLORLESS);
        setMagic(2);
        setExhaust();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    public void gainPower(AbstractPlayer p, AbstractPower powerToApply){
        this.addToBot(new ApplyPowerAction(p, p,  powerToApply));

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(p, new StrengthPower(p, this.magicNumber));
        gainPower(p, new DexterityPower(p, this.magicNumber));
        gainPower(p, new PlatedArmorPower(p, this.magicNumber));
        gainPower(p, new RegenPower(p, this.magicNumber));
        gainPower(p, new ThornsPower(p, this.magicNumber));
        gainPower(p, new VigorPower(p, this.magicNumber));
        gainPower(p, new IntangiblePlayerPower(p, this.magicNumber));
        gainPower(p, new ArtifactPower(p, this.magicNumber));
    }

}


