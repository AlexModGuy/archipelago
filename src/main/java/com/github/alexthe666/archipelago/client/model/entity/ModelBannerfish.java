package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelBannerfish extends AdvancedModelBase {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer DorsalFin1;
    public AdvancedModelRenderer Head2;
    public AdvancedModelRenderer Mouth1;
    public AdvancedModelRenderer PelvicFins;
    public AdvancedModelRenderer Head3;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer DorsalFin3;
    public AdvancedModelRenderer ThatFin2;
    public AdvancedModelRenderer DorsalFin2;
    private ModelAnimator animator;

    public ModelBannerfish() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.DorsalFin1 = new AdvancedModelRenderer(this, 20, 25);
        this.DorsalFin1.setRotationPoint(0.0F, -2.5F, -2.7F);
        this.DorsalFin1.addBox(0.0F, -4.0F, -1.0F, 0, 4, 2, 0.0F);
        this.setRotateAngle(DorsalFin1, -0.4820599394008338F, 0.0F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 11, 0);
        this.Tail1.setRotationPoint(0.0F, -2.0F, 2.0F);
        this.Tail1.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 19, 18);
        this.Tail2.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.Tail2.addBox(0.0F, -1.5F, 0.0F, 0, 4, 3, 0.0F);
        this.ThatFin2 = new AdvancedModelRenderer(this, 0, 20);
        this.ThatFin2.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.ThatFin2.addBox(0.0F, 0.0F, 0.0F, 0, 6, 5, 0.0F);
        this.Body = new AdvancedModelRenderer(this, 0, 1);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-1.0F, -4.0F, -2.0F, 2, 7, 4, 0.0F);
        this.Head3 = new AdvancedModelRenderer(this, 20, 13);
        this.Head3.setRotationPoint(0.0F, -1.3F, -3.0F);
        this.Head3.addBox(-0.9F, 0.0F, 0.0F, 2, 3, 4, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 0, 14);
        this.Head.setRotationPoint(0.0F, 0.5F, -2.0F);
        this.Head.addBox(-1.0F, -3.5F, -2.0F, 2, 6, 2, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.RightPectoralFin.setRotationPoint(-0.9F, 1.0F, -2.0F);
        this.RightPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(RightPectoralFin, -0.7285004297824331F, -0.31869712141416456F, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.LeftPectoralFin.setRotationPoint(0.9F, 1.0F, -2.0F);
        this.LeftPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(LeftPectoralFin, -0.7285004297824331F, 0.31869712141416456F, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 20, 6);
        this.DorsalFin2.setRotationPoint(0.0F, -3.6F, -0.2F);
        this.DorsalFin2.addBox(0.0F, -9.0F, -0.5F, 0, 9, 1, 0.0F);
        this.setRotateAngle(DorsalFin2, -0.45064401286493594F, 0.0F, 0.0F);
        this.PelvicFins = new AdvancedModelRenderer(this, 26, 21);
        this.PelvicFins.setRotationPoint(0.0F, 2.2F, 0.6F);
        this.PelvicFins.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(PelvicFins, 0.13334315485236678F, 0.0F, 0.0F);
        this.DorsalFin3 = new AdvancedModelRenderer(this, 17, -2);
        this.DorsalFin3.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.DorsalFin3.addBox(0.0F, -4.0F, 0.0F, 0, 4, 5, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 11, 23);
        this.Body2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.Body2.addBox(-1.0F, -3.0F, 0.0F, 2, 6, 2, 0.0F);
        this.Head2 = new AdvancedModelRenderer(this, 9, 13);
        this.Head2.setRotationPoint(0.0F, -1.8F, -1.5F);
        this.Head2.addBox(-1.1F, -1.3F, -3.0F, 1, 3, 4, 0.0F);
        this.setRotateAngle(Head2, 0.9105382707654417F, 0.0F, 0.0F);
        this.Mouth1 = new AdvancedModelRenderer(this, 26, 8);
        this.Mouth1.setRotationPoint(0.0F, -1.05F, -3.7F);
        this.Mouth1.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.Body.addChild(this.DorsalFin1);
        this.Body2.addChild(this.Tail1);
        this.Tail1.addChild(this.Tail2);
        this.Tail1.addChild(this.ThatFin2);
        this.Head2.addChild(this.Head3);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.RightPectoralFin);
        this.Body.addChild(this.LeftPectoralFin);
        this.DorsalFin1.addChild(this.DorsalFin2);
        this.Head.addChild(this.PelvicFins);
        this.Tail1.addChild(this.DorsalFin3);
        this.Body.addChild(this.Body2);
        this.Head.addChild(this.Head2);
        this.Head.addChild(this.Mouth1);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        GlStateManager.translate(0.0F, 0.25F, 0.0F);
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
        float idleSpeed = 0.1F;
        float idleDegree = 0.1F;
        float walkSpeed = 0.6F;
        float walkDegree = 2F;
        AdvancedModelRenderer[] body = new AdvancedModelRenderer[] { Body2, Tail1, Tail2 };
        this.chainSwing(body, walkSpeed * 1.0F, walkDegree * 1.0F, 3.0F, limbSwing, limbSwingAmount);

        this.chainSwing(body, idleSpeed * 1.0F, idleDegree * 1.0F, 3.0F, age, 1.0F);
        this.swing(this.RightPectoralFin, idleSpeed * 1.0F, idleDegree * 2.0F, false, 0.0F, -0.2F, age, 1.0F);
        this.swing(this.LeftPectoralFin, idleSpeed * 1.0F, idleDegree * 2.0F, false, 1.0F, 0.2F, age, 1.0F);
        if (!entity.isInWater()) {
            this.Body.rotateAngleZ = (float) Math.toRadians(90);
            this.bob(this.Body, 0.5F, 1.0F, false, age, 1);
            this.swing(this.Body, 0.5F, 0.3F, true, 0, 0, age, 1);
        } else {
            this.bob(this.Body, idleSpeed * 1.0F, idleDegree * 5.0F, false, age, 1.0F);
        }
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
