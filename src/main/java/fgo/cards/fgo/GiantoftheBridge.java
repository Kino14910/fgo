package fgo.cards.fgo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.util.CardStats;

public class GiantoftheBridge extends FGOCard {
    public static final String ID = makeID(GiantoftheBridge.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );
    public GiantoftheBridge() {
        super(ID, INFO);
        setDamage(3, 2);
        setBlock(3, 2);
    }

    @Override
    public AbstractCard makeCopy() {return new GiantoftheBridge();}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int times = ( AbstractDungeon.actNum == 2 || AbstractDungeon.actNum >= 4 ) ? 2 : 1;
        for (int i = 0; i < times; i++) {
            if(i == 1 && m != null){
                addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
            }
            addToBot(new GainBlockAction(p, p, block));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actNum == 2 || AbstractDungeon.actNum >= 4) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
