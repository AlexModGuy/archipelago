package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelBrownCrab extends AdvancedModelBase {
    public AdvancedModelRenderer Body1;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Eye1;
    public AdvancedModelRenderer Eye2;
    public AdvancedModelRenderer Mouth;
    public AdvancedModelRenderer RightArm1;
    public AdvancedModelRenderer LeftArm1;
    public AdvancedModelRenderer RightLegs1;
    public AdvancedModelRenderer RightLegs2;
    public AdvancedModelRenderer RightLegs3;
    public AdvancedModelRenderer RightLegs4;
    public AdvancedModelRenderer LeftLegs1;
    public AdvancedModelRenderer LeftLegs2;
    public AdvancedModelRenderer LeftLegs3;
    public AdvancedModelRenderer LeftLegs4;
    public AdvancedModelRenderer RightArm2;
    public AdvancedModelRenderer RightArm3;
    public AdvancedModelRenderer LeftArm2;
    public AdvancedModelRenderer LeftArm3;
    private ModelAnimator animator;

    public ModelBrownCrab() {
        this.textureWidth = 42;
        this.textureHeight = 32;
        this.RightArm2 = new AdvancedModelRenderer(this, 10, 10);
        this.RightArm2.setRotationPoint(3.0F, -1.0F, 0.5F);
        this.RightArm2.addBox(0.0F, 0.5F, -1.0F, 2, 1, 1, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 27, 0);
        this.Body2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.Body2.addBox(-3.0F, -1.0F, 0.0F, 6, 3, 1, 0.0F);
        this.RightLegs3 = new AdvancedModelRenderer(this, 19, 19);
        this.RightLegs3.setRotationPoint(-4.5F, 0.0F, 2.3F);
        this.RightLegs3.addBox(-5.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.RightLegs3, 0.0F, 0.36425021489121656F, -0.5462880558742251F);
        this.Eye1 = new AdvancedModelRenderer(this, 0, 0);
        this.Eye1.setRotationPoint(1.5F, 0.3F, -3.0F);
        this.Eye1.addBox(0.0F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.Eye1, 0.0F, 0.0F, 0.136659280431156F);
        this.RightLegs2 = new AdvancedModelRenderer(this, 19, 19);
        this.RightLegs2.setRotationPoint(-5.0F, 0.0F, 1.0F);
        this.RightLegs2.addBox(-5.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.RightLegs2, 0.0F, 0.0F, -0.5462880558742251F);
        this.LeftLegs3 = new AdvancedModelRenderer(this, 19, 19);
        this.LeftLegs3.setRotationPoint(4.5F, 0.0F, 2.3F);
        this.LeftLegs3.addBox(-1.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.LeftLegs3, 0.0F, -0.36425021489121656F, 0.5462880558742251F);
        this.RightArm1 = new AdvancedModelRenderer(this, 0, 14);
        this.RightArm1.setRotationPoint(-5.3F, 0.8F, -2.5F);
        this.RightArm1.addBox(-1.0F, -1.0F, -1.5F, 4, 2, 2, 0.0F);
        this.setRotateAngle(this.RightArm1, 0.6829473363053812F, 0.5462880558742251F, 0.22759093446006054F);
        this.LeftArm1 = new AdvancedModelRenderer(this, 17, 10);
        this.LeftArm1.setRotationPoint(5.3F, 0.8F, -2.5F);
        this.LeftArm1.addBox(-3.0F, -1.0F, -1.5F, 4, 2, 2, 0.0F);
        this.setRotateAngle(this.LeftArm1, 0.6829473363053812F, -0.5462880558742251F, -0.22759093446006054F);
        this.RightLegs4 = new AdvancedModelRenderer(this, 19, 19);
        this.RightLegs4.setRotationPoint(-3.0F, 0.0F, 3.5F);
        this.RightLegs4.addBox(-5.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.RightLegs4, 0.0F, 0.5009094953223726F, -0.5462880558742251F);
        this.LeftArm2 = new AdvancedModelRenderer(this, 10, 10);
        this.LeftArm2.setRotationPoint(-3.0F, -1.0F, 0.5F);
        this.LeftArm2.addBox(-2.0F, 0.5F, -1.0F, 2, 1, 1, 0.0F);
        this.Eye2 = new AdvancedModelRenderer(this, 0, 0);
        this.Eye2.setRotationPoint(-1.5F, 0.3F, -3.0F);
        this.Eye2.addBox(-1.0F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.Eye2, 0.0F, 0.0F, -0.136659280431156F);
        this.RightLegs1 = new AdvancedModelRenderer(this, 19, 19);
        this.RightLegs1.setRotationPoint(-5.0F, 0.0F, -0.5F);
        this.RightLegs1.addBox(-5.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.RightLegs1, 0.0F, -0.31869712141416456F, -0.5462880558742251F);
        this.LeftLegs4 = new AdvancedModelRenderer(this, 19, 19);
        this.LeftLegs4.setRotationPoint(3.0F, 0.0F, 3.5F);
        this.LeftLegs4.addBox(-1.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.LeftLegs4, 0.0F, -0.5009094953223726F, 0.5462880558742251F);
        this.LeftLegs2 = new AdvancedModelRenderer(this, 19, 19);
        this.LeftLegs2.setRotationPoint(5.0F, 0.0F, 1.0F);
        this.LeftLegs2.addBox(-1.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.LeftLegs2, 0.0F, 0.0F, 0.5462880558742251F);
        this.RightArm3 = new AdvancedModelRenderer(this, 0, 19);
        this.RightArm3.setRotationPoint(2.0F, -1.0F, -0.5F);
        this.RightArm3.addBox(1.0F, 0.5F, -1.0F, 3, 1, 1, 0.0F);
        this.Mouth = new AdvancedModelRenderer(this, 0, 10);
        this.Mouth.setRotationPoint(0.0F, 2.0F, -3.0F);
        this.Mouth.addBox(-1.5F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(this.Mouth, 0.27314402793711257F, 0.0F, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 0, 0);
        this.Body1.setRotationPoint(0.0F, 20.8F, 0.0F);
        this.Body1.addBox(-5.0F, -1.0F, -3.0F, 10, 3, 6, 0.0F);
        this.LeftArm3 = new AdvancedModelRenderer(this, 13, 16);
        this.LeftArm3.setRotationPoint(-2.0F, -1.0F, -0.5F);
        this.LeftArm3.addBox(-4.0F, 0.5F, -1.0F, 3, 1, 1, 0.0F);
        this.LeftLegs1 = new AdvancedModelRenderer(this, 19, 19);
        this.LeftLegs1.setRotationPoint(5.0F, 0.0F, -0.5F);
        this.LeftLegs1.addBox(-1.0F, 0.0F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(this.LeftLegs1, 0.0F, 0.31869712141416456F, 0.5462880558742251F);
        this.RightArm1.addChild(this.RightArm2);
        this.Body1.addChild(this.Body2);
        this.Body1.addChild(this.RightLegs3);
        this.Body1.addChild(this.Eye1);
        this.Body1.addChild(this.RightLegs2);
        this.Body1.addChild(this.LeftLegs3);
        this.Body1.addChild(this.RightArm1);
        this.Body1.addChild(this.LeftArm1);
        this.Body1.addChild(this.RightLegs4);
        this.LeftArm1.addChild(this.LeftArm2);
        this.Body1.addChild(this.Eye2);
        this.Body1.addChild(this.RightLegs1);
        this.Body1.addChild(this.LeftLegs4);
        this.Body1.addChild(this.LeftLegs2);
        this.RightArm1.addChild(this.RightArm3);
        this.Body1.addChild(this.Mouth);
        this.LeftArm1.addChild(this.LeftArm3);
        this.Body1.addChild(this.LeftLegs1);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        this.Body1.render(f5);
    }

    private void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animator.update(entity);
        this.resetToDefaultPose();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float idleSpeed = 0.15F;
        float idleDegree = 1F;
        float walkSpeed = 1.5F;
        float walkDegree = 4F;
        this.swing(this.LeftLegs1, walkSpeed, walkDegree * 0.2F, true, 0, -0.2F, f, f1);
        this.swing(this.RightLegs1, walkSpeed, walkDegree * 0.2F, false, 0, 0.2F, f, f1);
        this.swing(this.LeftLegs2, walkSpeed, walkDegree * 0.2F, true, 1, -0.2F, f, f1);
        this.swing(this.RightLegs2, walkSpeed, walkDegree * 0.2F, false, 1, 0.2F, f, f1);
        this.swing(this.LeftLegs3, walkSpeed, walkDegree * 0.2F, true, 2, 0.2F, f, f1);
        this.swing(this.RightLegs3, walkSpeed, walkDegree * 0.2F, false, 2, -0.2F, f, f1);
        this.swing(this.LeftLegs4, walkSpeed, walkDegree * 0.2F, true, 3, 0.2F, f, f1);
        this.swing(this.RightLegs4, walkSpeed, walkDegree * 0.2F, false, 3, -0.2F, f, f1);
        this.swing(this.LeftArm2, idleSpeed, idleDegree * 0.2F, false, 3, 0.2F, f2, 1);
        this.swing(this.RightArm2, idleSpeed, idleDegree * 0.2F, true, 3, 0.2F, f2, 1);
        this.chainFlap(new AdvancedModelRenderer[] { this.LeftLegs1, this.LeftLegs2, this.LeftLegs3, this.LeftLegs4 }, idleSpeed, idleDegree * 0.1F, 2.0F, f2, 1.0F);
        this.chainFlap(new AdvancedModelRenderer[] { this.RightLegs1, this.RightLegs2, this.RightLegs3, this.RightLegs4 }, idleSpeed, idleDegree * 0.1F, -2.0F, f2, 1.0F);
    }

    public void setRotateAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }
}
