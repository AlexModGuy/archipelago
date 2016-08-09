package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelBlacktipReefShark extends AdvancedModelBase {
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

    public ModelBlacktipReefShark() {
        this.textureWidth = 100;
        this.textureHeight = 64;
        this.Tail1 = new AdvancedModelRenderer(this, 40, 14);
        this.Tail1.setRotationPoint(0.0F, -1.0F, 8.0F);
        this.Tail1.addBox(-3.0F, -2.0F, -2.0F, 6, 7, 14, 0.0F);
        this.TailFin1 = new AdvancedModelRenderer(this, 79, 33);
        this.TailFin1.setRotationPoint(0.0F, -0.5F, 13.0F);
        this.TailFin1.addBox(-0.5F, -10.0F, -2.0F, 1, 13, 5, 0.0F);
        this.setRotateAngle(TailFin1, -1.0927506446736497F, 0.0F, 0.0F);
        this.UpperJaw2 = new AdvancedModelRenderer(this, 50, 38);
        this.UpperJaw2.setRotationPoint(0.0F, -2.0F, -6.0F);
        this.UpperJaw2.addBox(-3.0F, -2.0F, -1.0F, 6, 3, 1, 0.0F);
        this.UpperJaw3 = new AdvancedModelRenderer(this, 29, 37);
        this.UpperJaw3.setRotationPoint(0.0F, -2.0F, -1.0F);
        this.UpperJaw3.addBox(-3.0F, 0.0F, 0.0F, 6, 1, 7, 0.0F);
        this.setRotateAngle(UpperJaw3, 0.13316862192716733F, 0.0F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 45, 45);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 12.0F);
        this.Tail2.addBox(-2.0F, -1.0F, -1.0F, 4, 5, 14, 0.0F);
        this.LowerJaw = new AdvancedModelRenderer(this, 25, 57);
        this.LowerJaw.setRotationPoint(0.0F, -0.2F, -4.5F);
        this.LowerJaw.addBox(-2.0F, 0.0F, -5.0F, 4, 2, 5, 0.0F);
        this.RightPelvicFin = new AdvancedModelRenderer(this, 0, 0);
        this.RightPelvicFin.setRotationPoint(-3.5F, 4.5F, -1.0F);
        this.RightPelvicFin.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(RightPelvicFin, 0.0F, -0.7285004297824331F, -0.8196066167365371F);
        this.Head = new AdvancedModelRenderer(this, 0, 50);
        this.Head.setRotationPoint(0.0F, 1.5F, -7.0F);
        this.Head.addBox(-3.5F, -4.0F, -5.5F, 7, 6, 5, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 0, 0);
        this.Body1.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.Body1.addBox(-4.5F, -4.0F, -8.0F, 9, 9, 16, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 0, 26);
        this.Body2.setRotationPoint(0.0F, 0.0F, -8.0F);
        this.Body2.addBox(-4.0F, -3.0F, -8.0F, 8, 7, 9, 0.0F);
        this.setRotateAngle(Body2, 0.045553093477052F, 0.0F, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 37, 0);
        this.RightPectoralFin.setRotationPoint(-3.0F, 2.5F, -1.0F);
        this.RightPectoralFin.addBox(-9.0F, 0.0F, -2.0F, 9, 1, 4, 0.0F);
        this.setRotateAngle(RightPectoralFin, 0.0F, 0.5918411493512771F, -0.40980330836826856F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 37, 7);
        this.LeftPectoralFin.setRotationPoint(3.0F, 2.5F, -1.0F);
        this.LeftPectoralFin.addBox(0.0F, 0.0F, -2.0F, 9, 1, 4, 0.0F);
        this.setRotateAngle(LeftPectoralFin, 0.0F, -0.5918411493512771F, 0.40980330836826856F);
        this.TailFin2 = new AdvancedModelRenderer(this, 68, 46);
        this.TailFin2.setRotationPoint(0.0F, 3.8F, 1.3F);
        this.TailFin2.addBox(-0.5F, -2.0F, 0.0F, 1, 4, 7, 0.0F);
        this.setRotateAngle(TailFin2, 0.40980330836826856F, 0.0F, 0.0F);
        this.UpperJaw = new AdvancedModelRenderer(this, 27, 48);
        this.UpperJaw.setRotationPoint(0.0F, 1.0F, -5.5F);
        this.UpperJaw.addBox(-3.0F, -4.0F, -6.0F, 6, 3, 6, 0.0F);
        this.setRotateAngle(UpperJaw, -0.045553093477052F, 0.0F, 0.0F);
        this.LeftPelvicFin = new AdvancedModelRenderer(this, 0, 9);
        this.LeftPelvicFin.setRotationPoint(3.5F, 4.5F, -1.0F);
        this.LeftPelvicFin.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(LeftPelvicFin, 0.0F, 0.7285004297824331F, 0.8196066167365371F);
        this.DorsalFin1 = new AdvancedModelRenderer(this, 68, 0);
        this.DorsalFin1.setRotationPoint(0.0F, -3.0F, -5.0F);
        this.DorsalFin1.addBox(-0.5F, 0.0F, 0.0F, 1, 7, 11, 0.0F);
        this.setRotateAngle(DorsalFin1, 0.7285004297824331F, 0.0F, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 67, 0);
        this.DorsalFin2.setRotationPoint(0.0F, -1.0F, -1.0F);
        this.DorsalFin2.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.setRotateAngle(DorsalFin2, 0.5462880558742251F, 0.0F, 0.0F);
        this.ThatFin = new AdvancedModelRenderer(this, 67, 19);
        this.ThatFin.setRotationPoint(0.0F, 4.0F, -1.0F);
        this.ThatFin.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 4, 0.0F);
        this.setRotateAngle(ThatFin, -0.5462880558742251F, 0.0F, 0.0F);
        this.Body1.addChild(this.Tail1);
        this.Tail2.addChild(this.TailFin1);
        this.UpperJaw.addChild(this.UpperJaw2);
        this.UpperJaw2.addChild(this.UpperJaw3);
        this.Tail1.addChild(this.Tail2);
        this.Head.addChild(this.LowerJaw);
        this.Tail1.addChild(this.RightPelvicFin);
        this.Body2.addChild(this.Head);
        this.Body1.addChild(this.Body2);
        this.Body2.addChild(this.RightPectoralFin);
        this.Body2.addChild(this.LeftPectoralFin);
        this.TailFin1.addChild(this.TailFin2);
        this.Head.addChild(this.UpperJaw);
        this.Tail1.addChild(this.LeftPelvicFin);
        this.Body1.addChild(this.DorsalFin1);
        this.Tail2.addChild(this.DorsalFin2);
        this.Tail2.addChild(this.ThatFin);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        GlStateManager.translate(0.0F, 0.2F, 0.0F);
        this.Body1.render(f5);
    }

    private void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animator.update(entity);
        this.resetToDefaultPose();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, age, yaw, pitch, scale, entity);
        float idleSpeed = 0.1F;
        float idleDegree = 0.1F;
        float walkSpeed = 0.5F;
        float walkDegree = 1.0F;
        AdvancedModelRenderer[] tail = new AdvancedModelRenderer[] { Tail2, Tail1 };
        AdvancedModelRenderer[] fins = new AdvancedModelRenderer[] { LeftPectoralFin, RightPectoralFin, RightPelvicFin, LeftPelvicFin };
        this.chainSwing(tail, walkSpeed * 1.0F, walkDegree * 1.5F, 2.5F, limbSwing, limbSwingAmount);
        this.chainFlap(fins, walkSpeed * 0.5F, walkDegree * 1.0F, 3.0F, limbSwing, limbSwingAmount);

        this.chainFlap(fins, idleSpeed * 1.0F, idleDegree * 1.0F, 0.0F, age, 1.0F);
        this.walk(LowerJaw, idleSpeed * 1.0F, idleDegree * 1.0F, false, 0.0F, 0.05F, age, 1.0F);

        if (entity.isInWater()) {
            this.chainSwing(tail, idleSpeed * 1.0F, idleDegree * 1.0F, 2.5F, age, 1.0F);
            this.bob(Body1, idleSpeed * 1.0F, idleDegree * 4.0F, false, age, 1.0F);
        }
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}


