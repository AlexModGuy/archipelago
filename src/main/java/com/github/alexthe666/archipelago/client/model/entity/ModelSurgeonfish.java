package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
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
    private ModelAnimator animator;

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
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        GlStateManager.translate(0.0F, 0.1F, 0.0F);
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
        AdvancedModelRenderer[] body = new AdvancedModelRenderer[] { Tail2, Tail1 };
        this.chainSwing(body, walkSpeed * 1.0F, walkDegree * 1.0F, 3.0F, limbSwing, limbSwingAmount);

        this.chainSwing(body, idleSpeed * 1.0F, idleDegree * 1.0F, 3.0F, age, 1.0F);
        this.swing(this.RightPectoralFin, idleSpeed * 1.0F, idleDegree * 2.0F, false, 0.0F, -0.2F, age, 1.0F);
        this.swing(this.LeftPectoralFin, idleSpeed * 1.0F, idleDegree * 2.0F, false, 1.0F, 0.2F, age, 1.0F);
        if (!entity.isInWater()) {
            this.Body.rotateAngleZ = (float) Math.toRadians(90);
            if (entity.onGround) {
                this.bob(this.Body, 0.5F, 1.0F, false, age, 1);
                this.swing(this.Body, 0.5F, 0.3F, true, 0, 0, age, 1);
            }
        } else {
            this.bob(this.Body, idleSpeed * 1.0F, idleDegree * 5.0F, false, age, 1.0F);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}


