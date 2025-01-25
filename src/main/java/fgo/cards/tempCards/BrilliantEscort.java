package fgo.cards.tempCards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.StarGainPower;
import fgo.util.CardStats;

public class BrilliantEscort extends FGOCard {
    public static final String ID = makeID(BrilliantEscort.class.getSimpleName());
    private static final CardStats INFO = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    public BrilliantEscort() {
        super(ID, INFO);
        setMagic(10);
        setNP(10);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        this.addToBot(new ApplyPowerAction(p, p, new StarGainPower(p, magicNumber)));
        this.addToBot(new FgoNpAction(np));
    }
}
