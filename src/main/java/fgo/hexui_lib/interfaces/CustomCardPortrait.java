package fgo.hexui_lib.interfaces;

import java.util.ArrayList;

import fgo.hexui_lib.util.RenderLayer;

public interface CustomCardPortrait {
    ArrayList<RenderLayer> getPortraitLayers512();
    ArrayList<RenderLayer> getPortraitLayers1024();
}
