package fgo.action.lor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
public class ChangeSceneEffect extends AbstractChangeSceneEffect {
    private final Texture img;
    public float x;
    public Color color;
    public ChangeSceneEffect(Texture img) {
        this.color = Color.WHITE.cpy();
        this.renderBehind = true;
        this.img = img;
        this.x = -Settings.WIDTH * 1.7F;
    }

    public void update() {}

    public void end() {
        this.x = Settings.WIDTH;
    }

    public void render(SpriteBatch sb) {
        sb.flush();
        sb.setColor(Color.WHITE.toFloatBits());

        // 计算图像的宽度和高度
        float drawWidth = Settings.WIDTH;
        float drawHeight = (float) Settings.WIDTH / this.img.getWidth() * this.img.getHeight();

        // 如果高等比缩放后小于屏幕高度，则将高度设置为屏幕高度，并相应地调整宽度
        if (drawHeight < Settings.HEIGHT) {
            drawHeight = Settings.HEIGHT;
            drawWidth = (float) Settings.HEIGHT / this.img.getHeight() * this.img.getWidth();
        }

        sb.draw(this.img, 0.0F, 0.0F, drawWidth, drawHeight);
    }


    public void dispose() {}
}