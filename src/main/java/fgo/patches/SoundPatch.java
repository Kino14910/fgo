package fgo.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;

import java.util.HashMap;
import java.util.Objects;
//
//@SpirePatch(
//        clz = SoundMaster.class,
//        method = "play",
//        paramtypez = {String.class, float.class}
//)
//public class SoundPatch {
////    Lets you start custom music from e.g. an elite fight.
//    @SpirePostfixPatch
//    public static SpireReturn<Long> Prefix(SoundMaster __instance, String key, float pitchVariation) {
//        if(Objects.equals(key, "S011_Skill1") || Objects.equals(key, "S011_Skill2") || Objects.equals(key, "S011_Skill3")) {
//
//        return SpireReturn.Return(((Sfx)new Sfx("fgo/audio/sound/" + key + ".ogg", false)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + MathUtils.random(-pitchVariation, pitchVariation), 0.0F));
//        }
//        return SpireReturn.Continue();
//    }
//}

@SpirePatch(
        clz = SoundMaster.class,
        method = SpirePatch.CONSTRUCTOR
)
public class SoundPatch {
    @SpirePostfixPatch
    public static void Postfix(SoundMaster __instance) {
        // 在 SoundMaster 构造器开始时插入的代码
        HashMap<String, Sfx> map = ReflectionHacks.getPrivate(__instance, SoundMaster.class, "map");
        String[] sounds = {"S011_Skill1", "S011_Skill2", "S011_Skill3"};
        for (String key : sounds) {
        map.put(key, new Sfx("fgo/audio/sound/" + key + ".ogg", false));
    }
}
}
