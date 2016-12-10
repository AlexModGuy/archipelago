package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelStingray extends AdvancedModelBase {
    public AdvancedModelRenderer Body1;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Fin1;
    public AdvancedModelRenderer Fin2;
    public AdvancedModelRenderer Part2;
    public AdvancedModelRenderer Fin7;
    public AdvancedModelRenderer Eye1;
    public AdvancedModelRenderer Eye2;
    public AdvancedModelRenderer Head2;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer Fin5;
    public AdvancedModelRenderer Fin6;
    public AdvancedModelRenderer Fin8;
    public AdvancedModelRenderer Fin9;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer Tail3;
    public AdvancedModelRenderer Barb;
    public AdvancedModelRenderer Fin3;
    public AdvancedModelRenderer Fin4;
    private ModelAnimator animator;

    public ModelStingray() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Body2 = new AdvancedModelRenderer(this, 0, 16);
        this.Body2.setRotationPoint(0.0F, 0.5F, 5.3F);
        this.Body2.addBox(-2.0F, -1.0F, -5.0F, 4, 2, 16, 0.0F);
        this.Fin9 = new AdvancedModelRenderer(this, 46, 110);
        this.Fin9.setRotationPoint(0.0F, 0.1F, 7.8F);
        this.Fin9.addBox(-1.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.setRotateAngle(Fin9, 0.0F, 0.06911503837897544F, 0.0F);
        this.Fin4 = new AdvancedModelRenderer(this, 72, 70);
        this.Fin4.setRotationPoint(-4.25F, 0.0F, 14.55F);
        this.Fin4.addBox(-13.0F, -0.5F, -4.5F, 11, 1, 9, 0.0F);
        this.setRotateAngle(Fin4, 0.0F, -1.0065313796251298F, 0.0F);
        this.Head2 = new AdvancedModelRenderer(this, 0, 24);
        this.Head2.setRotationPoint(0.0F, -0.26F, -2.78F);
        this.Head2.addBox(-2.0F, -0.5F, -2.0F, 4, 1, 2, 0.0F);
        this.setRotateAngle(Head2, 0.47123889803846897F, 0.0F, 0.0F);
        this.Fin3 = new AdvancedModelRenderer(this, 72, 50);
        this.Fin3.setRotationPoint(4.25F, 0.0F, 14.55F);
        this.Fin3.addBox(2.0F, -0.5F, -4.5F, 11, 1, 9, 0.0F);
        this.setRotateAngle(Fin3, -0.0F, 1.0065313796251298F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 0, 48);
        this.Tail1.setRotationPoint(0.0F, 0.3F, 4.4F);
        this.Tail1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 16, 0.0F);
        this.Fin5 = new AdvancedModelRenderer(this, 28, 78);
        this.Fin5.setRotationPoint(1.0F, 0.2F, 8.7F);
        this.Fin5.addBox(0.0F, -0.5F, -2.0F, 5, 1, 4, 0.0F);
        this.setRotateAngle(Fin5, 0.0F, -0.7285004297824331F, 0.1445132620651305F);
        this.Eye2 = new AdvancedModelRenderer(this, 0, 9);
        this.Eye2.setRotationPoint(-2.6F, -0.8F, -3.6F);
        this.Eye2.addBox(-0.5F, -0.5F, 0.1F, 1, 2, 3, 0.0F);
        this.setRotateAngle(Eye2, -0.24504422698000386F, 0.0F, -0.2953097094374406F);
        this.Fin6 = new AdvancedModelRenderer(this, 46, 67);
        this.Fin6.setRotationPoint(-1.0F, 0.2F, 8.7F);
        this.Fin6.addBox(-5.0F, -0.5F, -2.0F, 5, 1, 4, 0.0F);
        this.setRotateAngle(Fin6, 0.0F, 0.7285004297824331F, -0.1445132620651305F);
        this.Fin2 = new AdvancedModelRenderer(this, 72, 30);
        this.Fin2.setRotationPoint(-0.6F, 0.6F, -2.7F);
        this.Fin2.addBox(-15.0F, -0.5F, -6.0F, 16, 1, 12, 0.0F);
        this.setRotateAngle(Fin2, 0.0F, 0.39392081217512015F, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 0, 0);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.addBox(-3.0F, -0.7F, -3.0F, 6, 2, 4, 0.0F);
        this.Part2 = new AdvancedModelRenderer(this, 61, 107);
        this.Part2.setRotationPoint(0.0F, 1.15F, -5.2F);
        this.Part2.addBox(-6.0F, -1.0F, 0.0F, 12, 2, 15, 0.0F);
        this.Fin7 = new AdvancedModelRenderer(this, 46, 82);
        this.Fin7.setRotationPoint(1.0F, 0.6F, -7.12F);
        this.Fin7.addBox(-3.0F, -0.5F, -1.5F, 4, 1, 5, 0.0F);
        this.Barb = new AdvancedModelRenderer(this, 27, 0);
        this.Barb.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Barb.addBox(0.0F, -1.0F, 0.0F, 0, 3, 9, 0.0F);
        this.Eye1 = new AdvancedModelRenderer(this, 0, 40);
        this.Eye1.setRotationPoint(2.6F, -0.8F, -3.6F);
        this.Eye1.addBox(-0.5F, -0.5F, 0.1F, 1, 2, 3, 0.0F);
        this.setRotateAngle(Eye1, -0.24504422698000386F, 0.0F, 0.2953097094374406F);
        this.Fin8 = new AdvancedModelRenderer(this, 52, 95);
        this.Fin8.setRotationPoint(0.0F, 0.1F, 7.8F);
        this.Fin8.addBox(-3.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.setRotateAngle(Fin8, 0.0F, -0.06911503837897544F, 0.0F);
        this.Fin1 = new AdvancedModelRenderer(this, 72, 7);
        this.Fin1.setRotationPoint(0.6F, 0.6F, -2.7F);
        this.Fin1.addBox(-1.0F, -0.5F, -6.0F, 16, 1, 12, 0.0F);
        this.setRotateAngle(Fin1, 0.0F, -0.39392081217512015F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 0, 74);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 16.0F);
        this.Tail2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 12, 0.0F);
        this.Tail3 = new AdvancedModelRenderer(this, 0, 94);
        this.Tail3.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.Tail3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 12, 0.0F);
        this.Body1.addChild(this.Body2);
        this.Body2.addChild(this.Fin9);
        this.Fin2.addChild(this.Fin4);
        this.Body1.addChild(this.Head2);
        this.Fin1.addChild(this.Fin3);
        this.Body2.addChild(this.Tail1);
        this.Body2.addChild(this.Fin5);
        this.Body1.addChild(this.Eye2);
        this.Body2.addChild(this.Fin6);
        this.Body1.addChild(this.Fin2);
        this.Body1.addChild(this.Part2);
        this.Body1.addChild(this.Fin7);
        this.Tail2.addChild(this.Barb);
        this.Body1.addChild(this.Eye1);
        this.Body2.addChild(this.Fin8);
        this.Body1.addChild(this.Fin1);
        this.Tail1.addChild(this.Tail2);
        this.Tail2.addChild(this.Tail3);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        GlStateManager.translate(0.0F, 1.3F, 0.0F);
        this.Body1.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, age, yaw, pitch, scale, entity);
        float idleSpeed = 0.1F;
        float idleDegree = 0.1F;
        float walkSpeed = 0.5F;
        float walkDegree = 1.0F;
        AdvancedModelRenderer[] fin1 = new AdvancedModelRenderer[] { Fin1, };
        AdvancedModelRenderer[] fin2 = new AdvancedModelRenderer[] { Fin2, };
        this.chainFlap(fin1, walkSpeed * 0.2F, walkDegree * 1.0F, 3.0F, limbSwing, limbSwingAmount);
        this.chainFlap(fin2, walkSpeed * 0.2F, walkDegree * -1.0F, -3.0F, limbSwing, limbSwingAmount);
        AdvancedModelRenderer[] tail = new AdvancedModelRenderer[] { Tail3, Tail2, Tail1 };
        this.chainSwing(tail, walkSpeed * 0.5F, walkDegree * 1.0F, 2.0F, limbSwing, limbSwingAmount);
        this.chainSwing(tail, idleSpeed * 1.0F, idleDegree * 1.0F, 3.0F, age, 1.0F);

        this.bob(Body1, walkSpeed * 0.25F, walkDegree * 6.0F, false, limbSwing, limbSwingAmount);

        this.chainFlap(fin1, idleSpeed * 1.0F, idleDegree * 1.0F, 0.0F, age, 1.0F);
        this.chainFlap(fin2, idleSpeed * 1.0F, idleDegree * -1.0F, 0.0F, age, 1.0F);

        if (entity.isInWater()) {
            this.bob(Body1, idleSpeed * 1.0F, idleDegree * 4.0F, false, age, 1.0F);
        }
    }

    private void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.animator.update(entity);
        this.resetToDefaultPose();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}




