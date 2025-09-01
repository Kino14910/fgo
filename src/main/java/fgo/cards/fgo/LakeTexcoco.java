package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.EnergyRegenPower;
import fgo.powers.WatersidePower;
import fgo.utils.CardStats;

public class LakeTexcoco extends FGOCard {
    public static final String ID = makeID(LakeTexcoco.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public LakeTexcoco() {
        super(ID, INFO);
        setNP(20, 10);
        setMagic(5, 5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FgoNpAction(np));
        this.addToBot(new ApplyPowerAction(p, p, new EnergyRegenPower(p, magicNumber, 3)));
        this.addToBot(new ApplyPowerAction(p, p, new WatersidePower(p)));
    }
}
