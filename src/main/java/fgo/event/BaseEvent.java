package fgo.event;

import basemod.abstracts.events.PhasedEvent;

public abstract class BaseEvent extends PhasedEvent {
    public String dungeonID;
    protected String body;
    protected String[] descriptions;
    protected String[] options;
    protected String title;

    public enum CUR_SCREEN {
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

    public BaseEvent(String id, String name, String imagePath) {
        super(id, name, imagePath);
    }
}
