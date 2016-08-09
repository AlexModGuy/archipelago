package com.github.alexthe666.archipelago.client.model.entity;


import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.client.model.ModelBase;
        import net.minecraft.client.model.ModelRenderer;
        import net.minecraft.entity.Entity;


public class ModelSurgeonfish extends AdvancedModelBase {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer PelvicFins;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer DorsalFin1;
    public AdvancedModelRenderer ThatFin1;
    public AdvancedModelRenderer UpperJaw;
    public AdvancedModelRenderer LowerJaw;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer DorsalFin2;
    public AdvancedModelRenderer ThatFin2;

    public ModelSurgeonfish() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.Tail1 = new AdvancedModelRenderer(this, 11, 0);
        this.Tail1.setRotationPoint(0.0F, -2.0F, 3.0F);
        this.Tail1.addBox(-0.5F, 1.0F, 0.0F, 1, 2, 2, 0.0F);
        this.UpperJaw = new AdvancedModelRenderer(this, 1, 12);
        this.UpperJaw.setRotationPoint(0.0F, 0.2F, -2.0F);
        this.UpperJaw.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 1, 0.0F);
        this.ThatFin1 = new AdvancedModelRenderer(this, 25, 10);
        this.ThatFin1.setRotationPoint(0.0F, 1.5F, 0.7F);
        this.ThatFin1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 0, 0);
        this.RightPectoralFin.setRotationPoint(-0.9F, 1.0F, -3.0F);
        this.RightPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(RightPectoralFin, -0.7285004297824331F, -0.31869712141416456F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 18, 6);
        this.Tail2.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.Tail2.addBox(0.0F, -1.0F, 0.0F, 0, 2, 2, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 25, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.Head.addBox(-1.0F, -2.0F, -2.0F, 2, 4, 1, 0.0F);
        this.PelvicFins = new AdvancedModelRenderer(this, 25, 6);
        this.PelvicFins.setRotationPoint(0.0F, 2.0F, -2.0F);
        this.PelvicFins.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(PelvicFins, 0.22759093446006054F, 0.0F, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 0, 0);
        this.LeftPectoralFin.setRotationPoint(0.9F, 1.0F, -3.0F);
        this.LeftPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(LeftPectoralFin, -0.7285004297824331F, 0.31869712141416456F, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 17, 2);
        this.DorsalFin2.setRotationPoint(0.0F, 0.5F, 1.0F);
        this.DorsalFin2.addBox(0.0F, -1.0F, -1.0F, 0, 2, 3, 0.0F);
        this.LowerJaw = new AdvancedModelRenderer(this, 8, 12);
        this.LowerJaw.setRotationPoint(0.0F, 0.7F, -2.0F);
        this.LowerJaw.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
        this.Body = new AdvancedModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body.addBox(-1.0F, -2.5F, -3.0F, 2, 5, 6, 0.0F);
        this.DorsalFin1 = new AdvancedModelRenderer(this, 17, 5);
        this.DorsalFin1.setRotationPoint(0.0F, -2.4F, -3.0F);
        this.DorsalFin1.addBox(0.0F, -1.0F, 0.0F, 0, 1, 6, 0.0F);
        this.ThatFin2 = new AdvancedModelRenderer(this, 18, 10);
        this.ThatFin2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.ThatFin2.addBox(0.0F, -0.5F, 0.0F, 0, 2, 3, 0.0F);
        this.Body.addChild(this.Tail1);
        this.Head.addChild(this.UpperJaw);
        this.Body.addChild(this.ThatFin1);
        this.Body.addChild(this.RightPectoralFin);
        this.Tail1.addChild(this.Tail2);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.PelvicFins);
        this.Body.addChild(this.LeftPectoralFin);
        this.Tail1.addChild(this.DorsalFin2);
        this.Head.addChild(this.LowerJaw);
        this.Body.addChild(this.DorsalFin1);
        this.Tail1.addChild(this.ThatFin2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}


