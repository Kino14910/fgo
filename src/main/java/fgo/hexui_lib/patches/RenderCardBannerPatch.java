package fgo.hexui_lib.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import fgo.cards.AbsNoblePhantasmCard;
	@SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardBanner", paramtypez = {SpriteBatch.class})
	public class RenderCardBannerPatch {
		@SpirePrefixPatch
		public static SpireReturn<Void> Prefix(SingleCardViewPopup _inst, SpriteBatch sb, AbstractCard ___card) {
			if (___card instanceof AbsNoblePhantasmCard) {
				return SpireReturn.Return();
			}
			return SpireReturn.Continue();
		}
	}