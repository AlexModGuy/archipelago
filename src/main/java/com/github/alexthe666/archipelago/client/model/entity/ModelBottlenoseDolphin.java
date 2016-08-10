package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBottlenoseDolphin extends AdvancedModelBase {
    public AdvancedModelRenderer Body1;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer DorsalFin;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer UpperJaw;
    public AdvancedModelRenderer LowerJaw;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer TailFlipper1;
    public AdvancedModelRenderer TailFlipper2;

    public ModelBottlenoseDolphin() {
        this.textureWidth = 80;
        this.textureHeight = 64;
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 37, 0);
        this.LeftPectoralFin.setRotationPoint(3.0F, 3.0F, -4.0F);
        this.LeftPectoralFin.addBox(0.0F, 0.0F, -2.0F, 9, 1, 4, 0.0F);
        this.setRotateAngle(LeftPectoralFin, 0.18203784098300857F, -0.5918411493512771F, 0.7285004297824331F);
        this.DorsalFin = new AdvancedModelRenderer(this, 56, 0);
        this.DorsalFin.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.DorsalFin.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 10, 0.0F);
        this.setRotateAngle(DorsalFin, 0.5462880558742251F, 0.0F, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 0, 0);
        this.Body1.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.Body1.addBox(-4.5F, -4.0F, -8.0F, 9, 9, 16, 0.0F);
        this.TailFlipper1 = new AdvancedModelRenderer(this, 43, 58);
        this.TailFlipper1.setRotationPoint(0.0F, 4.0F, 11.0F);
        this.TailFlipper1.addBox(-11.0F, 0.0F, -3.0F, 11, 1, 5, 0.0F);
        this.setRotateAngle(TailFlipper1, 0.0F, 0.36425021489121656F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 40, 17);
        this.Tail1.setRotationPoint(0.0F, -2.3F, 8.0F);
        this.Tail1.addBox(-3.0F, -1.0F, -2.0F, 6, 8, 12, 0.0F);
        this.setRotateAngle(Tail1, -0.045553093477052F, 0.0F, 0.0F);
        this.TailFlipper2 = new AdvancedModelRenderer(this, 43, 58);
        this.TailFlipper2.setRotationPoint(0.0F, 4.0F, 11.0F);
        this.TailFlipper2.addBox(0.0F, 0.0F, -3.0F, 11, 1, 5, 0.0F);
        this.setRotateAngle(TailFlipper2, 0.0F, -0.36425021489121656F, 0.0F);
        this.LowerJaw = new AdvancedModelRenderer(this, 22, 47);
        this.LowerJaw.setRotationPoint(0.0F, 1.0F, -5.5F);
        this.LowerJaw.addBox(-1.5F, 0.0F, -7.0F, 3, 2, 6, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 39, 38);
        this.Tail2.setRotationPoint(0.0F, 0.4F, 10.0F);
        this.Tail2.addBox(-2.0F, 0.0F, -1.0F, 4, 6, 12, 0.0F);
        this.setRotateAngle(Tail2, -0.045553093477052F, 0.0F, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 0, 49);
        this.Head.setRotationPoint(0.0F, 1.7F, -9.0F);
        this.Head.addBox(-3.5F, -4.0F, -7.5F, 7, 7, 8, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 0, 26);
        this.Body2.setRotationPoint(0.0F, -0.3F, -8.0F);
        this.Body2.addBox(-4.0F, -3.0F, -9.0F, 8, 8, 10, 0.0F);
        this.setRotateAngle(Body2, 0.045553093477052F, 0.0F, 0.0F);
        this.UpperJaw = new AdvancedModelRenderer(this, 34, 42);
        this.UpperJaw.setRotationPoint(0.0F, -1.0F, -7.5F);
        this.UpperJaw.addBox(-1.5F, 0.0F, -5.0F, 3, 2, 5, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 37, 0);
        this.RightPectoralFin.setRotationPoint(-3.0F, 3.0F, -4.0F);
        this.RightPectoralFin.addBox(-9.0F, 0.0F, -2.0F, 9, 1, 4, 0.0F);
        this.setRotateAngle(RightPectoralFin, 0.18203784098300857F, 0.5918411493512771F, -0.7285004297824331F);
        this.Body2.addChild(this.LeftPectoralFin);
        this.Body1.addChild(this.DorsalFin);
        this.Tail2.addChild(this.TailFlipper1);
        this.Body1.addChild(this.Tail1);
        this.Tail2.addChild(this.TailFlipper2);
        this.Head.addChild(this.LowerJaw);
        this.Tail1.addChild(this.Tail2);
        this.Body2.addChild(this.Head);
        this.Body1.addChild(this.Body2);
        this.Head.addChild(this.UpperJaw);
        this.Body2.addChild(this.RightPectoralFin);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body1.render(f5);
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
