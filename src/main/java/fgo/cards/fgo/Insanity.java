package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.CursePower;
import fgo.powers.InsanityPower;
import fgo.util.CardStats;

public class Insanity extends FGOCard {
    public static final String ID = makeID(Insanity.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1
    );
    public Insanity() {
        super(ID, INFO);
        setMagic(10, 10);
        tags.add(CardTagsEnum.Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InsanityPower(p, magicNumber)));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(CursePower.POWER_ID)) {
                int CurseAmt = 0;
                CurseAmt += (mo.getPower(CursePower.POWER_ID)).amount;
                addToBot(new ApplyPowerAction(p, p, new CursePower(p, CurseAmt), CurseAmt));
                addToBot(new RemoveSpecificPowerAction(mo, p, CursePower.POWER_ID));
            }
        }

    }
}
