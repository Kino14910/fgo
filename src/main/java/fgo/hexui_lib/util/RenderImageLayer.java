package fgo.hexui_lib.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class RenderImageLayer extends RenderLayer {
    public Texture texture;
    public Color color;
    public BLENDMODE blendMode;
    public FloatPair displacement;
    public FloatPair scale;
    public float angle;
    public boolean[] colormask;

    public RenderImageLayer(Texture texture) {
        this(texture, null, null, null, 0f);
    }

    public RenderImageLayer(Texture texture, Color color) {
        this(texture, color, null, null, 0f);
    }

    public RenderImageLayer(Texture texture, Color color, BLENDMODE blendMode) {
        this(texture, color, blendMode, null, 0f);
    }

    public RenderImageLayer(Texture texture, Color color, BLENDMODE blendMode, FloatPair displacement) {
        this(texture, color, blendMode, displacement, 0f);
    }

    public RenderImageLayer(Texture texture, Color color, BLENDMODE blendMode, FloatPair displacement, float angle) {
        this(texture, color, blendMode, displacement, angle, null);
    }

    public RenderImageLayer(Texture texture, Color color, BLENDMODE blendMode, FloatPair displacement, float angle, boolean[] colormask) {
        this.texture = texture;
        this.color = color != null ? color : Color.WHITE.cpy();
        this.blendMode = blendMode;
        this.displacement = displacement != null ? displacement : new FloatPair(0f, 0f);
        this.angle = angle;
        this.colormask = colormask;
        this.scale = new FloatPair(1f, 1f);
    }
}