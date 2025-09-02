package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NightmarePower;

import fgo.cards.FGOCard;
import fgo.characters.CustomEnums.FGOCardColor;
import static fgo.characters.CustomEnums.Foreigner;
import fgo.utils.CardStats;
import fgo.utils.ModHelper;

public class FacelessMoon extends FGOCard {
    public static final String ID = makeID(FacelessMoon.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    public FacelessMoon() {
        super(ID, INFO);
        setMagic(1);
        setExhaust(true, false);
        tags.add(Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.addToBotAbstract(() -> {
            if (!p.hand.isEmpty()) {
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card.color != FGOCardColor.NOBLE_PHANTASM) {
                        addToTop(new ApplyPowerAction(p, p, new NightmarePower(p, magicNumber, card)));
                    }
                }
            }
        });
    }
}
