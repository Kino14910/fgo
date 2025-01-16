//package fgo.util;
//
//import basemod.abstracts.DynamicVariable;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import fgo.cards.FGOCard;
//
//public class NoblePhantasmVariable extends DynamicVariable
//{
//    @Override
//    public String key()
//    {
//        return "NP";
//        // What you put in your localization file between ! to show your value. Eg, !myKey!.
//    }
//
//    @Override
//    public boolean isModified(FGOCard card)
//    {
//        return card.isNPModified();
//        // Set to true if the value is modified from the base value.
//    }
//
//    @Override
//    public void setIsModified(FGOCard card, boolean v)
//    {
//        // Do something such that isModified will return the value v.
//        // This method is only necessary if you want smith upgrade previews to function correctly.
//
//    }
//
//    @Override
//    public int value(FGOCard card)
//    {
//        return myInt;
//        // What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
//    }
//
//    @Override
//    public int baseValue(FGOCard card)
//    {
//        return myInt;
//        // Should generally just be the above.
//    }
//
//    @Override
//    public boolean upgraded(FGOCard card)
//    {
//        return card.isNPUpgraded();
//        // Should return true if the card was upgraded and the value was changed
//    }