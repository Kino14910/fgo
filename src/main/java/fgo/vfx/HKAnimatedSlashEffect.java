package fgo.vfx;

import static fgo.FGOMod.vfxPath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HKAnimatedSlashEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private final float sX;
    private final float sY;
    private final float tX;
    private final float tY;
    private float scaleX;
    private float scaleY;
    private final float targetScale;
    private final Color color2;
    private static final Texture HK = ImageMaster.loadImage(vfxPath("HK"));
    public HKAnimatedSlashEffect(float x, float y, float dX, float dY, float angle, Color color1, Color color2) {
        this(x, y, dX, dY, angle, 2.0F, color1, color2);
    }

    public HKAnimatedSlashEffect(float x, float y, float dX, float dY, float angle, float targetScale, Color color1, Color color2) {
        this.x = x - 64.0F - dX / 2.0F * Settings.scale;
        this.y = y - 64.0F - dY / 2.0F * Settings.scale;
        this.sX = this.x;
        this.sY = this.y;
        this.tX = this.x + dX / 2.0F * Settings.scale;
        this.tY = this.y + dY / 2.0F * Settings.scale;
        this.color = color1.cpy();
        this.color2 = color2.cpy();
        this.color.a = 0.0F;
        this.startingDuration = 0.4F;
        this.duration = this.startingDuration;
        this.targetScale = targetScale;
        this.scaleX = 0.01F;
        this.scaleY = 0.01F;
        this.rotation = angle;
    }

    @Override
    public void update() {
        if (duration > startingDuration / 2.0F) {
            color.a = Interpolation.exp10In.apply(0.8F, 0.0F, (duration - startingDuration / 2.0F) / (startingDuration / 2.0F));
            scaleX = Interpolation.exp10In.apply(targetScale, 0.1F, (duration - startingDuration / 2.0F) / (startingDuration / 2.0F));
            scaleY = scaleX;
            x = Interpolation.fade.apply(tX, sX, (duration - startingDuration / 2.0F) / (startingDuration / 2.0F));
            y = Interpolation.fade.apply(tY, sY, (duration - startingDuration / 2.0F) / (startingDuration / 2.0F));
        } else {
            scaleX = Interpolation.pow2In.apply(0.5F, targetScale, duration / (startingDuration / 2.0F));
            color.a = Interpolation.pow5In.apply(0.0F, 0.8F, duration / (startingDuration / 2.0F));
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            isDone = true;
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color2);
        sb.setBlendFunction(770, 1);
        sb.draw(HK, x, y, 64.0F, 64.0F, 128.0F, 128.0F, scaleX * 0.4F * MathUtils.random(0.95F, 1.05F) * Settings.scale, scaleY * 0.7F * MathUtils.random(0.95F, 1.05F) * Settings.scale, rotation, 0, 0, 128, 128, false, false);
        sb.setColor(color);
        sb.draw(HK, x, y, 64.0F, 64.0F, 128.0F, 128.0F, scaleX * 0.7F * MathUtils.random(0.95F, 1.05F) * Settings.scale, this.scaleY * MathUtils.random(0.95F, 1.05F) * Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() {
    }
}
