package fgo.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import fgo.characters.Master;
import fgo.ui.buttons.CommandSpellButton;
import fgo.ui.buttons.NoblePhantasmButton;

public class OverlayMenuPatch {
    // 添加 CommandSpellButton 属性
    @SpirePatch(
        clz = OverlayMenu.class,
        method = SpirePatch.CLASS
    )
    public static class CommandSpellButtonField {
        public static SpireField<CommandSpellButton> commandSpellButton = new SpireField<>(CommandSpellButton::new);
    }

    // 添加 CommandSpellButton 属性
    @SpirePatch(
        clz = OverlayMenu.class,
        method = SpirePatch.CLASS
    )
    public static class NoblePhantasmButtonField {
        public static SpireField<NoblePhantasmButton> noblePhantasmButton = new SpireField<>(NoblePhantasmButton::new);
    }


    // 修改 update 方法
    @SpirePatch(
        clz = OverlayMenu.class,
        method = "update"
    )
    public static class UpdatePatch {
        @SpirePostfixPatch
        public static void Postfix(OverlayMenu __instance) {
            if(AbstractDungeon.player instanceof Master){
                CommandSpellButtonField.commandSpellButton.get(__instance).updatePositions();
                NoblePhantasmButtonField.noblePhantasmButton.get(__instance).updatePositions();
            }
        }
    }

    // 修改 render 方法
    @SpirePatch(
        clz = OverlayMenu.class,
        method = "render"
    )
    public static class RenderPatch {
        @SpirePostfixPatch
        public static void Postfix(OverlayMenu __instance, SpriteBatch sb) {
            if(AbstractDungeon.player instanceof Master){
                CommandSpellButtonField.commandSpellButton.get(__instance).render(sb);
                NoblePhantasmButtonField.noblePhantasmButton.get(__instance).render(sb);
            }
        }
    }

    // 修改 showCombatPanels 方法
    @SpirePatch(
        clz = OverlayMenu.class,
        method = "showCombatPanels"
    )
    public static class ShowCombatPanelsPatch {
        @SpirePostfixPatch
        public static void Postfix(OverlayMenu __instance) {
            if(AbstractDungeon.player instanceof Master) {
                CommandSpellButtonField.commandSpellButton.get(__instance).show();
                NoblePhantasmButtonField.noblePhantasmButton.get(__instance).show();
            }
        }
    }

    // 修改 hideCombatPanels 方法
    @SpirePatch(
        clz = OverlayMenu.class,
        method = "hideCombatPanels"
    )
    public static class HideCombatPanelsPatch {
        @SpirePostfixPatch
        public static void Postfix(OverlayMenu __instance) {
            if(AbstractDungeon.player instanceof Master) {
                CommandSpellButtonField.commandSpellButton.get(__instance).hide();
                NoblePhantasmButtonField.noblePhantasmButton.get(__instance).hide();
            }
        }
    }
}
