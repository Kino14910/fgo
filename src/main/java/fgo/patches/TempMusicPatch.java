package fgo.patches;

import basemod.BaseMod;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;

import java.util.Objects;

@SpirePatch(
        clz = TempMusic.class,
        method = "getSong")
public class TempMusicPatch {
    //Lets you start custom music from e.g. an elite fight.
    @SpirePostfixPatch
    public static SpireReturn<Music> Prefix(TempMusic __instance, String key) {
        if(Objects.equals(key, "UBW_Extended.mp3")) {
            BaseMod.logger.info("Starting custom music: {}", key);
            return SpireReturn.Return(MainMusic.newMusic("fgo/audio/music/" + key));
        }
        return SpireReturn.Continue();
    }

}