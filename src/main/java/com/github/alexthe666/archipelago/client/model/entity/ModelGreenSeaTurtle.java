package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;


public class ModelGreenSeaTurtle extends AdvancedModelBase {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer neck;
    public AdvancedModelRenderer leftUpperArm;
    public AdvancedModelRenderer rightUpperArm;
    public AdvancedModelRenderer RBShell;
    public AdvancedModelRenderer leftthigh;
    public AdvancedModelRenderer rightthigh;
    public AdvancedModelRenderer LBShell;
    public AdvancedModelRenderer RFShell;
    public AdvancedModelRenderer LFShell;
    public AdvancedModelRenderer BShell;
    public AdvancedModelRenderer BShell_1;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer head;
    public AdvancedModelRenderer upperJaw;
    public AdvancedModelRenderer lowerJaw;
    public AdvancedModelRenderer leftLowerArm;
    public AdvancedModelRenderer rightLowerArm;
    public AdvancedModelRenderer leftleg;
    public AdvancedModelRenderer rightleg;
    public AdvancedModelRenderer Tail2;

    public ModelGreenSeaTurtle() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.rightLowerArm = new AdvancedModelRenderer(this, 37, 21);
        this.rightLowerArm.setRotationPoint(-0.99F, 1.8F, 0.5F);
        this.rightLowerArm.addBox(-0.4F, -1.5F, -11.0F, 1, 4, 11, 0.0F);
        this.setRotateAngle(rightLowerArm, 2.007128639793479F, 0.0F, 0.0F);
        this.rightUpperArm = new AdvancedModelRenderer(this, 35, 13);
        this.rightUpperArm.setRotationPoint(-3.0F, 3.3F, -6.5F);
        this.rightUpperArm.addBox(-1.5F, -1.0F, -1.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(rightUpperArm, -0.5235987755982988F, -0.0F, 1.2217304763960306F);
        this.RFShell = new AdvancedModelRenderer(this, 67, 50);
        this.RFShell.mirror = true;
        this.RFShell.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.RFShell.addBox(-7.0F, -0.61F, -4.5F, 7, 2, 12, 0.0F);
        this.setRotateAngle(RFShell, 0.0F, -0.2617993877991494F, -0.13962634015954636F);
        this.LFShell = new AdvancedModelRenderer(this, 67, 50);
        this.LFShell.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.LFShell.addBox(0.09F, -0.61F, -4.5F, 7, 2, 12, 0.0F);
        this.setRotateAngle(LFShell, 0.0F, 0.2617993877991494F, 0.13962634015954636F);
        this.BShell_1 = new AdvancedModelRenderer(this, 90, 6);
        this.BShell_1.mirror = true;
        this.BShell_1.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.BShell_1.addBox(-3.5F, -1.8F, -0.2F, 7, 2, 12, 0.0F);
        this.upperJaw = new AdvancedModelRenderer(this, 28, 0);
        this.upperJaw.setRotationPoint(0.0F, 0.2F, -3.63F);
        this.upperJaw.addBox(-1.5F, -2.4F, -1.7F, 3, 2, 2, 0.0F);
        this.setRotateAngle(upperJaw, 0.091106186954104F, -0.0F, 0.0F);
        this.LBShell = new AdvancedModelRenderer(this, 67, 0);
        this.LBShell.mirror = true;
        this.LBShell.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.LBShell.addBox(0.5F, -0.6F, -4.5F, 7, 2, 11, 0.0F);
        this.setRotateAngle(LBShell, 0.0F, -0.2617993877991494F, 0.13962634015954636F);
        this.leftleg = new AdvancedModelRenderer(this, 32, 21);
        this.leftleg.mirror = true;
        this.leftleg.setRotationPoint(1.0F, 2.9F, -0.9F);
        this.leftleg.addBox(-1.1F, 0.0F, -6.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(leftleg, 1.7453292519943295F, -0.0F, 0.0F);
        this.Body = new AdvancedModelRenderer(this, 67, 25);
        this.Body.setRotationPoint(0.0F, 17.3F, -2.1F);
        this.Body.addBox(-5.0F, -1.1F, -6.2F, 10, 4, 16, 0.0F);
        this.neck = new AdvancedModelRenderer(this, 0, 9);
        this.neck.setRotationPoint(0.0F, 1.1F, -4.3F);
        this.neck.addBox(-1.5F, -2.0F, -5.3F, 3, 3, 6, 0.0F);
        this.leftLowerArm = new AdvancedModelRenderer(this, 37, 21);
        this.leftLowerArm.mirror = true;
        this.leftLowerArm.setRotationPoint(0.99F, 1.8F, 0.5F);
        this.leftLowerArm.addBox(-0.6F, -1.5F, -11.0F, 1, 4, 11, 0.0F);
        this.setRotateAngle(leftLowerArm, 2.007128639793479F, -0.0F, 0.0F);
        this.head = new AdvancedModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.4F, -4.43F);
        this.head.addBox(-2.0F, -2.4F, -3.6F, 4, 3, 3, 0.0F);
        this.setRotateAngle(head, 0.091106186954104F, -0.0017453292519943296F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 0, 28);
        this.Tail2.setRotationPoint(0.0F, -0.1F, 6.0F);
        this.Tail2.addBox(-1.0F, -2.0F, 0.7F, 2, 2, 4, 0.0F);
        this.setRotateAngle(Tail2, 0.18203784098300857F, -0.0F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 0, 20);
        this.Tail1.setRotationPoint(0.0F, 1.1F, 6.0F);
        this.Tail1.addBox(-1.5F, -2.0F, 2.1F, 3, 2, 5, 0.0F);
        this.setRotateAngle(Tail1, -0.31869712141416456F, -0.0F, 0.0F);
        this.rightthigh = new AdvancedModelRenderer(this, 35, 13);
        this.rightthigh.setRotationPoint(-3.0F, 2.7F, 9.5F);
        this.rightthigh.addBox(-1.0F, -1.0F, -1.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(rightthigh, 0.8726646259971648F, -0.0F, 1.2199851471440364F);
        this.BShell = new AdvancedModelRenderer(this, 54, 17);
        this.BShell.mirror = true;
        this.BShell.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.BShell.addBox(-3.5F, -1.2F, 2.4F, 7, 2, 6, 0.0F);
        this.setRotateAngle(BShell, -0.06981317007977318F, 0.0F, 0.0F);
        this.lowerJaw = new AdvancedModelRenderer(this, 49, 0);
        this.lowerJaw.setRotationPoint(0.0F, 0.3F, -2.33F);
        this.lowerJaw.addBox(-1.5F, -0.5F, -3.0F, 3, 1, 3, 0.0F);
        this.leftUpperArm = new AdvancedModelRenderer(this, 35, 13);
        this.leftUpperArm.mirror = true;
        this.leftUpperArm.setRotationPoint(3.0F, 3.3F, -6.5F);
        this.leftUpperArm.addBox(0.5F, -1.0F, -1.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(leftUpperArm, -0.5235987755982988F, -0.0F, -1.2217304763960306F);
        this.RBShell = new AdvancedModelRenderer(this, 67, 0);
        this.RBShell.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.RBShell.addBox(-7.5F, -0.6F, -4.5F, 7, 2, 11, 0.0F);
        this.setRotateAngle(RBShell, 0.0F, 0.2617993877991494F, -0.13962634015954636F);
        this.leftthigh = new AdvancedModelRenderer(this, 35, 13);
        this.leftthigh.mirror = true;
        this.leftthigh.setRotationPoint(3.0F, 2.7F, 9.5F);
        this.leftthigh.addBox(0.0F, -1.0F, -1.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(leftthigh, 0.8726646259971648F, -0.0F, -1.2217304763960306F);
        this.rightleg = new AdvancedModelRenderer(this, 32, 21);
        this.rightleg.setRotationPoint(-1.0F, 2.9F, -0.9F);
        this.rightleg.addBox(0.1F, 0.0F, -6.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(rightleg, 1.7453292519943295F, -0.0F, 0.0F);
        this.rightUpperArm.addChild(this.rightLowerArm);
        this.Body.addChild(this.rightUpperArm);
        this.Body.addChild(this.RFShell);
        this.Body.addChild(this.LFShell);
        this.Body.addChild(this.BShell_1);
        this.head.addChild(this.upperJaw);
        this.Body.addChild(this.LBShell);
        this.leftthigh.addChild(this.leftleg);
        this.Body.addChild(this.neck);
        this.leftUpperArm.addChild(this.leftLowerArm);
        this.neck.addChild(this.head);
        this.Tail1.addChild(this.Tail2);
        this.Body.addChild(this.Tail1);
        this.Body.addChild(this.rightthigh);
        this.Body.addChild(this.BShell);
        this.head.addChild(this.lowerJaw);
        this.Body.addChild(this.leftUpperArm);
        this.Body.addChild(this.RBShell);
        this.Body.addChild(this.leftthigh);
        this.rightthigh.addChild(this.rightleg);
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
