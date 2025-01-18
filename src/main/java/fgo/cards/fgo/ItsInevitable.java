package fgo.cards.fgo;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import fgo.cards.FGOCard;
import fgo.patches.Enum.FGOCardColor;
import fgo.powers.BurnDamagePower;
import fgo.powers.ItsInevitablePower;
import fgo.util.CardStats;
import fgo.relics.MisoPotato;

public class ItsInevitable extends FGOCard {
    public static final String ID = makeID(ItsInevitable.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            FGOCardColor.FGO,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );
    public ItsInevitable() {
        super(ID, INFO);
        setDamage(4, 1);
        isMultiDamage = true;
        setMagic(2, 1);
        portraitImg = ImageMaster.loadImage("fgo/images/cards/attack/ItsInevitable.png");

        FlavorText.AbstractCardFlavorFields.textColor.set(this, Color.CHARTREUSE);
        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FlavorText.boxType.TRADITIONAL);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ItsInevitable();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        if (p.hasRelic(MisoPotato.ID)) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new BurnDamagePower(mo, 2), 2, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        addToBot(new ApplyPowerAction(p, p, new ItsInevitablePower(p, 2, damage + magicNumber, magicNumber), magicNumber));
        //addToBot(new ApplyPowerAction(p, p, new BurnDamagePower(p, magicNumber), magicNumber));
    }
}
