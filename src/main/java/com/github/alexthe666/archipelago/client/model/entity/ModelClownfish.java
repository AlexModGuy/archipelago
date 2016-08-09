package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelClownfish extends AdvancedModelBase {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer DorsalFin1;
    public AdvancedModelRenderer PelvicFins;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer UpperJaw;
    public AdvancedModelRenderer LowerJaw;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer DorsalFin2;
    public AdvancedModelRenderer ThatFin;
    private ModelAnimator animator;

    public ModelClownfish() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.ThatFin = new AdvancedModelRenderer(this, 16, 8);
        this.ThatFin.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.ThatFin.addBox(0.0F, 0.0F, -1.0F, 0, 2, 2, 0.0F);
        this.Body = new AdvancedModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body.addBox(-1.0F, -2.0F, -2.0F, 2, 4, 5, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 0, 0);
        this.LeftPectoralFin.setRotationPoint(0.9F, 1.0F, -1.0F);
        this.LeftPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(this.LeftPectoralFin, -0.7285004297824331F, 0.31869712141416456F, 0.0F);
        this.UpperJaw = new AdvancedModelRenderer(this, 9, 10);
        this.UpperJaw.setRotationPoint(0.0F, -0.3F, -2.0F);
        this.UpperJaw.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.LowerJaw = new AdvancedModelRenderer(this, 9, 14);
        this.LowerJaw.setRotationPoint(0.0F, 0.7F, -2.0F);
        this.LowerJaw.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
        this.DorsalFin1 = new AdvancedModelRenderer(this, 15, 4);
        this.DorsalFin1.setRotationPoint(0.0F, -2.0F, -1.5F);
        this.DorsalFin1.addBox(0.0F, -2.0F, 0.0F, 0, 2, 3, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 17, -2);
        this.DorsalFin2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.DorsalFin2.addBox(0.0F, -1.0F, -1.0F, 0, 1, 2, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 11, 0);
        this.Tail1.setRotationPoint(0.0F, -2.0F, 3.0F);
        this.Tail1.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.PelvicFins = new AdvancedModelRenderer(this, 19, 11);
        this.PelvicFins.setRotationPoint(0.0F, 2.0F, -2.0F);
        this.PelvicFins.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(this.PelvicFins, 0.22759093446006054F, 0.0F, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 0, 10);
        this.Head.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.Head.addBox(-1.0F, -2.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 18, -1);
        this.Tail2.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.Tail2.addBox(0.0F, -1.0F, 0.0F, 0, 3, 3, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 0, 0);
        this.RightPectoralFin.setRotationPoint(-0.9F, 1.0F, -1.0F);
        this.RightPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(this.RightPectoralFin, -0.7285004297824331F, -0.31869712141416456F, 0.0F);
        this.Tail1.addChild(this.ThatFin);
        this.Body.addChild(this.LeftPectoralFin);
        this.Head.addChild(this.UpperJaw);
        this.Head.addChild(this.LowerJaw);
        this.Body.addChild(this.DorsalFin1);
        this.Tail1.addChild(this.DorsalFin2);
        this.Body.addChild(this.Tail1);
        this.Body.addChild(this.PelvicFins);
        this.Body.addChild(this.Head);
        this.Tail1.addChild(this.Tail2);
        this.Body.addChild(this.RightPectoralFin);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        this.Body.render(f5);
    }

    private void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animator.update(entity);
        this.resetToDefaultPose();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, age, yaw, pitch, scale, entity);
        AdvancedModelRenderer[] tail = new AdvancedModelRenderer[] { this.Tail1, this.Tail2 };
        float idleSpeed = 0.3F;
        float idleDegree = 1F;
        float walkSpeed = 1F;
        float walkDegree = 2F;
        this.chainSwing(tail, idleSpeed, idleDegree * 0.3F, -3, age, 1);
        this.swing(this.RightPectoralFin, idleSpeed, idleDegree * 0.3F, false, 0, -0.1F, age, 1);
        this.swing(this.LeftPectoralFin, idleSpeed, idleDegree * 0.3F, true, 0, -0.1F, age, 1);
        this.chainSwing(tail, walkSpeed, walkDegree * 0.3F, -3, limbSwing, limbSwingAmount);
        this.walk(this.DorsalFin1, idleSpeed, idleDegree * 0.1F, true, 2, 0.2F, age, 1);
        this.walk(this.PelvicFins, idleSpeed, idleDegree * 0.1F, true, 1, -0.2F, age, 1);
        if (!entity.isInWater()) {
            this.Body.rotateAngleZ = (float) Math.toRadians(90);
            this.bob(this.Body, -idleSpeed * 2, idleSpeed * 2F, false, age, 1);
            this.swing(this.Body, idleSpeed * 2, idleSpeed * 0.6F, true, 0, 0, age, 1);
        } else {
            this.bob(this.Body, idleSpeed * 0.25F, idleDegree * 0.5F, false, age, 1.0F);
        }
    }

    public void setRotateAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }
}
