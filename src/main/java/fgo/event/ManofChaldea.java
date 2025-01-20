package fgo.event;

import basemod.abstracts.events.PhasedEvent;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;


import static fgo.FGOMod.makeID;

public class ManofChaldea extends PhasedEvent {
    public static final String ID = makeID(ManofChaldea.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;
    private CUR_SCREEN screen;
    public ManofChaldea() {
        super(ID, title, "fgo/images/events/ManofChaldea.png");
        this.body = DESCRIPTIONS[0];
        this.screen = CUR_SCREEN.CONTINUE0;
        //人类即为过去延续到未来的足迹（记忆）， NL 只有一直积累经验、知识与故事， NL 才能作为人而不断成长。
        this.imageEventText.setDialogOption(OPTIONS[0]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case CONTINUE0:
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                //一小时内有过的三两谈话。 NL 一天内交到的珍贵好友。 NL 一年内取得的耀眼成长。
                this.screen = CUR_SCREEN.CONTINUE1;
                break;
            case CONTINUE1:
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                //但是，没有人能够 NL 鲜明地记得经历的一切。
                this.screen = CUR_SCREEN.CONTINUE2;
                break;
            case CONTINUE2:
                this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                //留下的仅有结果。 NL 过程通常会被忘记。
                this.screen = CUR_SCREEN.CONTINUE3;
                break;
            case CONTINUE3:
                this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                //从长期角度，或是客观角度来看， NL 少年与人们并无差别。
                this.screen = CUR_SCREEN.CONTINUE4;
                break;
            case CONTINUE4:
                this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                //每天结束时，他的记忆都会被刷新。 NL 化作一片空白。 NL 而少年，仅能保存其中重要的东西。
                this.screen = CUR_SCREEN.CONTINUE5;
                break;
            case CONTINUE5:
                this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                //23小时55分钟的丧失。 NL 每天经历过的耀眼记忆，都会被漂白。
                this.screen = CUR_SCREEN.CONTINUE6;
                break;
            case CONTINUE6:
                this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
                //5分钟的信念。 NL 抵抗刷新的意志， NL 让他获得了不会失去／无法忘怀的回忆。
                this.screen = CUR_SCREEN.CONTINUE7;
                break;
            case CONTINUE7:
                this.imageEventText.updateBodyText(DESCRIPTIONS[8]);
                //就这样，少年长大成人。 NL 不断积累着仅为人类（自己）所需的信息， NL 到达了『一名人类的模样』。
                this.screen = CUR_SCREEN.CONTINUE8;
                break;
            case CONTINUE8:
                this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                //自己究竟是真正的人类，还是虚有其表的冒牌货， NL 他并未考虑过这个问题。 NL 只是拥有的愿望、信念、誓言非常单纯。 NL 『人类会行善』 NL 只有这一点是他足以被称作人类的，唯一的冠位指定。
                this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                this.screen = CUR_SCREEN.CONTINUE9;
                break;
            case CONTINUE9:
                AbstractDungeon.effectList.add(new RainingGoldEffect(125));
                AbstractDungeon.player.gainGold(75);
                this.imageEventText.updateBodyText(DESCRIPTIONS[10]);
                //你获得一些金币。
                this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                this.screen = CUR_SCREEN.CONTINUE10;
                break;
            case CONTINUE10:
                AbstractDungeon.player.heal(15, true);
                this.imageEventText.updateBodyText(DESCRIPTIONS[11]);
                //你恢复了些许体力。
                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                this.screen = CUR_SCREEN.CONTINUE11;
                break;
            case CONTINUE11:
                this.openMap();
            }
    }

    enum CUR_SCREEN {
        CONTINUE0,
        CONTINUE1,
        CONTINUE2,
        CONTINUE3,
        CONTINUE4,
        CONTINUE5,
        CONTINUE6,
        CONTINUE7,
        CONTINUE8,
        CONTINUE9,
        CONTINUE10,
        CONTINUE11;

        CUR_SCREEN() {
        }
    }
}
