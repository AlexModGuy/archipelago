package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSergeantMajor extends AdvancedModelBase {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Head2;
    public AdvancedModelRenderer PelvicFins;
    public AdvancedModelRenderer Head3;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer ThatFin;
    public AdvancedModelRenderer DorsalFin;
    public AdvancedModelRenderer Tail2;

    public ModelSergeantMajor() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Head = new AdvancedModelRenderer(this, 0, 14);
        this.Head.setRotationPoint(0.0F, 0.5F, -2.0F);
        this.Head.addBox(-1.0F, -3.5F, -2.0F, 2, 6, 2, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.LeftPectoralFin.setRotationPoint(0.9F, 1.0F, -2.0F);
        this.LeftPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(LeftPectoralFin, -0.7285004297824331F, 0.31869712141416456F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 24, 0);
        this.Tail1.setRotationPoint(0.0F, -1.7F, 0.8F);
        this.Tail1.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.ThatFin = new AdvancedModelRenderer(this, 19, 22);
        this.ThatFin.setRotationPoint(0.0F, 1.8F, -1.0F);
        this.ThatFin.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4, 0.0F);
        this.PelvicFins = new AdvancedModelRenderer(this, 26, 21);
        this.PelvicFins.setRotationPoint(0.0F, 2.2F, -0.3F);
        this.PelvicFins.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(PelvicFins, 0.5291838292046808F, 0.0F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 20, 4);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 1.5F);
        this.Tail2.addBox(0.0F, 0.0F, 0.0F, 0, 3, 3, 0.0F);
        this.Head3 = new AdvancedModelRenderer(this, 20, 13);
        this.Head3.setRotationPoint(0.0F, -1.3F, -3.0F);
        this.Head3.addBox(-0.9F, 0.0F, 0.0F, 2, 3, 4, 0.0F);
        this.DorsalFin = new AdvancedModelRenderer(this, 11, 17);
        this.DorsalFin.setRotationPoint(0.0F, -4.0F, -1.0F);
        this.DorsalFin.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.RightPectoralFin.setRotationPoint(-0.9F, 1.0F, -2.0F);
        this.RightPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(RightPectoralFin, -0.7285004297824331F, -0.31869712141416456F, 0.0F);
        this.Body = new AdvancedModelRenderer(this, 1, 1);
        this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body.addBox(-1.0F, -3.0F, -2.0F, 2, 6, 4, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 1, 24);
        this.Body2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.Body2.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 1, 0.0F);
        this.Head2 = new AdvancedModelRenderer(this, 9, 13);
        this.Head2.setRotationPoint(0.0F, -1.6F, -1.5F);
        this.Head2.addBox(-1.1F, -1.3F, -3.0F, 1, 3, 4, 0.0F);
        this.setRotateAngle(Head2, 0.9105382707654417F, 0.0F, 0.0F);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.LeftPectoralFin);
        this.Body2.addChild(this.Tail1);
        this.Body2.addChild(this.ThatFin);
        this.Head.addChild(this.PelvicFins);
        this.Tail1.addChild(this.Tail2);
        this.Head2.addChild(this.Head3);
        this.Body2.addChild(this.DorsalFin);
        this.Body.addChild(this.RightPectoralFin);
        this.Body.addChild(this.Body2);
        this.Head.addChild(this.Head2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
