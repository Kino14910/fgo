package fgo.cards.noblecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import fgo.cards.AbsNoblePhantasmCard;
import fgo.hexui_lib.util.RenderImageLayer;
import fgo.hexui_lib.util.TextureLoader;
import fgo.characters.CustomEnums.FGOCardColor;
import fgo.powers.CriticalDamageUpPower;
import fgo.powers.DesterrennachtPower;
import fgo.powers.StarRegenPower;
import static fgo.characters.CustomEnums.Foreigner;
public class Desterrennacht extends AbsNoblePhantasmCard {
    public static final String ID = makeID(Desterrennacht.class.getSimpleName());
    public static final String IMG_PATH = "fgo/images/cards/noble/Desterrennacht.png";
    public static final String IMG_PATH_P = "fgo/images/cards/noble/Desterrennacht_p.png";

    public Desterrennacht() {
        super(ID,CardType.POWER, CardTarget.ALL_ENEMY);
        setMagic(3, 2);
        tags.add(Foreigner);

        cardArtLayers512.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH)));
        cardArtLayers1024.add(new RenderImageLayer(TextureLoader.getTexture(IMG_PATH_P)));
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new DesterrennachtPower(mo, 3)));
        }
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new CriticalDamageUpPower(p, 50)));
        addToBot(new ApplyPowerAction(p, p, new StarRegenPower(p, 10)));
    }
}
