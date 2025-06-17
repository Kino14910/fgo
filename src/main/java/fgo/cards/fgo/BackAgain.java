package fgo.cards.fgo;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import fgo.cards.FGOCard;
import fgo.patches.Enum.CardTagsEnum;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.DefenseDownPower;
import fgo.powers.StarPower;
import fgo.util.CardStats;

public class BackAgain extends FGOCard {
    public static final String ID = makeID(BackAgain.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    
    public BackAgain() {
        super(ID, INFO);
        setMagic(3);
        setStar(5, 10);
        tags.add(CardTagsEnum.Foreigner);

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new DefenseDownPower(m, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).type == CardType.ATTACK) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
