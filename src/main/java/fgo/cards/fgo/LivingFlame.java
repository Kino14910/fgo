package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.LivingFlamePower;
import fgo.util.CardStats;

public class LivingFlame extends FGOCard {
    public static final String ID = makeID(LivingFlame.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public LivingFlame() {
        super(ID, INFO);
        setMagic(1, 1);
        tags.add(CardTagsEnum.Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LivingFlamePower(p, magicNumber)));
    }
}
