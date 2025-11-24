package fgo.cards.derivative.mash;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.powers.NPDamagePower;
import fgo.powers.ReducePercentDamagePower;

public class LordChaldeas extends AbsNoblePhantasmCard {
    public static final String ID = makeID(LordChaldeas.class.getSimpleName());

    public LordChaldeas() {
        super(ID, CardType.POWER, CardTarget.SELF);
        setMagic(30, 20);
        setBlock(5, 5);
        cardsToPreview = new RayProofKyrielight();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ReducePercentDamagePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, block)));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1)));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
        addToBot(new ApplyPowerAction(p, p, new NPDamagePower(50)));
        addToBot(new MakeTempCardInDiscardAction(new TimewornBulletKindling(), 1));
    }
}
