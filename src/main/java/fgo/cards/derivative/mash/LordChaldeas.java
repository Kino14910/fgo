package fgo.cards.derivative.mash;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.NPDamagePower;
import fgo.powers.ReducePercentDamagePower;

public class LordChaldeas extends AbsNoblePhantasmCard {
    public static final String ID = makeID(LordChaldeas.class.getSimpleName());

    public LordChaldeas() {
        super(ID, CardType.POWER, CardTarget.SELF, 1);
        setMagic(30, 20);
        setCustomVar("metal", 5, 5);
        MultiCardPreview.add(this, new RayProofKyrielight(), new TimewornBulletKindling());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, customVar("metal"))));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1)));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
        addToBot(new ApplyPowerAction(p, p, new NPDamagePower(30)));
        addToBot(new MakeTempCardInDiscardAction(new TimewornBulletKindling(), 1));
    }
}
