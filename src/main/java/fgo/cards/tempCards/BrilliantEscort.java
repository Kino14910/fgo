package fgo.cards.tempCards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import fgo.action.FgoNpAction;
import fgo.cards.FGOCard;
import fgo.powers.StarPower;

public class BrilliantEscort extends FGOCard {
    public static final String ID = makeID(BrilliantEscort.class.getSimpleName());

    public BrilliantEscort() {
        super(ID, -2, CardType.POWER, CardTarget.NONE, CardRarity.SPECIAL, CardColor.COLORLESS);
        setStar(10);
        setNP(10);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {this.onChoseThisOption();}

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        this.addToBot(new ApplyPowerAction(p, p, new StarPower(p, star)));
        this.addToBot(new FgoNpAction(np));
    }
}
