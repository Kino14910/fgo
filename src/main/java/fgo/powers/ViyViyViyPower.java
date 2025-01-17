package fgo.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ViyViyViyPower extends AbstractPower {
    public static final String POWER_ID = "ViyViyViyPower";
    public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).NAME;
    public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings(POWER_ID)).DESCRIPTIONS;
    private final AbstractRelic r;
    private static int IdOffset;
    private final AbstractPlayer p;
    private final String rName;
    public ViyViyViyPower(AbstractCreature owner, AbstractRelic r) {
        this.ID = "PropBagPower" + IdOffset;
        this.owner = owner;
        IdOffset++;
        this.type = PowerType.BUFF;
        this.r = r;
        this.p = AbstractDungeon.player;
        this.rName = r.name;
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r);
        r.atBattleStart();

        String path128 = "fgo/images/powers_Master/ConcentrateSpellsPower84.png";
        String path48 = "fgo/images/powers_Master/ConcentrateSpellsPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void onVictory() {
        this.p.loseRelic(this.r.relicId);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.rName + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {
        return new ViyViyViyPower(this.owner, this.r);
    }
}