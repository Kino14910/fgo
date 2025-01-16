package fgo.hexui_lib.interfaces;

import fgo.hexui_lib.util.RenderLayer;

import java.util.ArrayList;

public interface CustomCardPortrait {
    ArrayList<RenderLayer> getPortraitLayers512();
    ArrayList<RenderLayer> getPortraitLayers1024();
}
