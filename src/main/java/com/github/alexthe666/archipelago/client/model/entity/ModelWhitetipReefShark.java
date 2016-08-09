package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWhitetipReefShark extends AdvancedModelBase {
    public AdvancedModelRenderer Body1;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer DorsalFin1;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer UpperJaw;
    public AdvancedModelRenderer LowerJaw;
    public AdvancedModelRenderer UpperJaw2;
    public AdvancedModelRenderer UpperJaw3;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer RightPelvicFin;
    public AdvancedModelRenderer LeftPelvicFin;
    public AdvancedModelRenderer TailFin1;
    public AdvancedModelRenderer DorsalFin2;
    public AdvancedModelRenderer ThatFin;
    public AdvancedModelRenderer TailFin2;
    private ModelAnimator animator;

    public ModelWhitetipReefShark() {
        this.textureWidth = 100;
        this.textureHeight = 64;
        this.Body2 = new AdvancedModelRenderer(this, 0, 26);
        this.Body2.setRotationPoint(0.0F, -0.3F, -7.3F);
        this.Body2.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(Body2, 0.045553093477052F, 0.0F, 0.0F);
        this.LowerJaw = new AdvancedModelRenderer(this, 25, 57);
        this.LowerJaw.setRotationPoint(0.0F, 1.6F, -4.5F);
        this.LowerJaw.addBox(-2.0F, -0.5F, -6.0F, 4, 1, 6, 0.0F);
        this.setRotateAngle(LowerJaw, -0.00471238898038469F, 0.0F, 0.0F);
        this.ThatFin = new AdvancedModelRenderer(this, 81, 19);
        this.ThatFin.setRotationPoint(0.0F, 2.2F, -0.5F);
        this.ThatFin.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 5, 0.0F);
        this.setRotateAngle(ThatFin, -0.6531022060962781F, 0.0F, 0.0F);
        this.DorsalFin1 = new AdvancedModelRenderer(this, 73, 0);
        this.DorsalFin1.setRotationPoint(0.0F, -3.0F, -1.7F);
        this.DorsalFin1.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 10, 0.0F);
        this.setRotateAngle(DorsalFin1, 0.7285004297824331F, 0.0F, 0.0F);
        this.UpperJaw2 = new AdvancedModelRenderer(this, 50, 38);
        this.UpperJaw2.setRotationPoint(0.0F, -2.0F, -5.7F);
        this.UpperJaw2.addBox(-2.5F, -2.0F, -1.0F, 5, 3, 1, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 0, 50);
        this.Head.setRotationPoint(0.0F, -0.1F, -5.2F);
        this.Head.addBox(-2.5F, -2.5F, -5.5F, 5, 5, 5, 0.0F);
        this.UpperJaw3 = new AdvancedModelRenderer(this, 29, 37);
        this.UpperJaw3.setRotationPoint(0.0F, -2.4F, -1.0F);
        this.UpperJaw3.addBox(-2.5F, 0.1F, 0.0F, 5, 1, 7, 0.0F);
        this.setRotateAngle(UpperJaw3, 0.10803588069844901F, 0.0F, 0.0F);
        this.UpperJaw = new AdvancedModelRenderer(this, 27, 46);
        this.UpperJaw.setRotationPoint(0.0F, 2.7F, -5.5F);
        this.UpperJaw.addBox(-2.5F, -4.0F, -6.0F, 5, 3, 6, 0.0F);
        this.setRotateAngle(UpperJaw, -0.045553093477052F, 0.0F, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 37, 0);
        this.RightPectoralFin.setRotationPoint(-1.7F, 1.7F, -3.0F);
        this.RightPectoralFin.addBox(-7.0F, 0.0F, -2.0F, 7, 1, 5, 0.0F);
        this.setRotateAngle(RightPectoralFin, 0.0F, 0.5918411493512771F, -0.24015730507441974F);
        this.TailFin2 = new AdvancedModelRenderer(this, 68, 45);
        this.TailFin2.setRotationPoint(0.0F, 3.3F, 0.5F);
        this.TailFin2.addBox(-0.5F, -2.0F, 0.0F, 1, 4, 7, 0.0F);
        this.setRotateAngle(TailFin2, 0.24015730507441974F, 0.0F, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 5, 0);
        this.Body1.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body1.addBox(-3.5F, -3.5F, -8.0F, 7, 7, 16, 0.0F);
        this.TailFin1 = new AdvancedModelRenderer(this, 79, 33);
        this.TailFin1.setRotationPoint(0.0F, -2.3F, 11.2F);
        this.TailFin1.addBox(-0.5F, -8.0F, -2.0F, 1, 11, 5, 0.0F);
        this.setRotateAngle(TailFin1, -0.8539896030008254F, 0.0F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 43, 14);
        this.Tail1.setRotationPoint(0.0F, -0.7F, 8.0F);
        this.Tail1.addBox(-2.5F, -2.5F, -2.0F, 5, 5, 12, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 37, 7);
        this.LeftPectoralFin.setRotationPoint(1.7F, 1.7F, -3.0F);
        this.LeftPectoralFin.addBox(0.0F, 0.0F, -2.0F, 7, 1, 5, 0.0F);
        this.setRotateAngle(LeftPectoralFin, 0.0F, -0.5918411493512771F, 0.24015730507441974F);
        this.LeftPelvicFin = new AdvancedModelRenderer(this, 0, 9);
        this.LeftPelvicFin.setRotationPoint(2.8F, 2.0F, -1.0F);
        this.LeftPelvicFin.addBox(-4.0F, 0.0F, 0.0F, 4, 1, 5, 0.0F);
        this.setRotateAngle(LeftPelvicFin, 0.0F, 0.7285004297824331F, 0.4553564018453205F);
        this.RightPelvicFin = new AdvancedModelRenderer(this, 0, 0);
        this.RightPelvicFin.setRotationPoint(-2.8F, 2.0F, -1.0F);
        this.RightPelvicFin.addBox(0.0F, 0.0F, 0.0F, 4, 1, 5, 0.0F);
        this.setRotateAngle(RightPelvicFin, 0.0F, -0.7285004297824331F, -0.4553564018453205F);
        this.Tail2 = new AdvancedModelRenderer(this, 45, 45);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
        this.Tail2.addBox(-2.0F, -2.0F, -1.0F, 4, 4, 12, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 67, 0);
        this.DorsalFin2.setRotationPoint(0.0F, -2.1F, -0.2F);
        this.DorsalFin2.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 5, 0.0F);
        this.setRotateAngle(DorsalFin2, 0.8038986534685881F, 0.0F, 0.0F);
        this.Body1.addChild(this.Body2);
        this.Head.addChild(this.LowerJaw);
        this.Tail2.addChild(this.ThatFin);
        this.Body1.addChild(this.DorsalFin1);
        this.UpperJaw.addChild(this.UpperJaw2);
        this.Body2.addChild(this.Head);
        this.UpperJaw2.addChild(this.UpperJaw3);
        this.Head.addChild(this.UpperJaw);
        this.Body2.addChild(this.RightPectoralFin);
        this.TailFin1.addChild(this.TailFin2);
        this.Tail2.addChild(this.TailFin1);
        this.Body1.addChild(this.Tail1);
        this.Body2.addChild(this.LeftPectoralFin);
        this.Tail1.addChild(this.LeftPelvicFin);
        this.Tail1.addChild(this.RightPelvicFin);
        this.Tail1.addChild(this.Tail2);
        this.Tail2.addChild(this.DorsalFin2);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();

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

