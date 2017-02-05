package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelSpottedEagleRay extends AdvancedModelBase {
    public AdvancedModelRenderer Body1;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Fin1;
    public AdvancedModelRenderer Fin2;
    public AdvancedModelRenderer Part2;
    public AdvancedModelRenderer Snout;
    public AdvancedModelRenderer Part1;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer Fin5;
    public AdvancedModelRenderer Fin6;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer Barb1;
    public AdvancedModelRenderer Barb2;
    public AdvancedModelRenderer Tail3;
    public AdvancedModelRenderer Fin3;
    public AdvancedModelRenderer Fin4;
    private ModelAnimator animator;

    public ModelSpottedEagleRay() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Barb1 = new AdvancedModelRenderer(this, 0, 20);
        this.Barb1.setRotationPoint(0.0F, -0.3F, 0.7F);
        this.Barb1.addBox(0.0F, 0.0F, 0.0F, 0, 1, 2, 0.0F);
        this.setRotateAngle(Barb1, 0.8353145800044861F, 0.0F, 0.0F);
        this.Part1 = new AdvancedModelRenderer(this, 64, 0);
        this.Part1.setRotationPoint(0.0F, 1.67F, -2.75F);
        this.Part1.addBox(-2.0F, -1.0F, 0.0F, 4, 2, 4, 0.0F);
        this.setRotateAngle(Part1, 0.12566370614359174F, 0.0F, 0.0F);
        this.Tail3 = new AdvancedModelRenderer(this, 0, 48);
        this.Tail3.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.Tail3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 12, 0.0F);
        this.Fin6 = new AdvancedModelRenderer(this, 46, 66);
        this.Fin6.setRotationPoint(-1.0F, 0.0F, 8.7F);
        this.Fin6.addBox(-3.0F, -0.5F, -1.5F, 3, 1, 3, 0.0F);
        this.setRotateAngle(Fin6, 0.0F, 0.7285004297824331F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 0, 48);
        this.Tail1.setRotationPoint(0.0F, 0.0F, 10.0F);
        this.Tail1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 12, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 0, 48);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.Tail2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 12, 0.0F);
        this.Fin2 = new AdvancedModelRenderer(this, 72, 30);
        this.Fin2.setRotationPoint(-0.6F, 0.1F, -5.4F);
        this.Fin2.addBox(-15.0F, -0.5F, -6.0F, 15, 1, 12, 0.0F);
        this.setRotateAngle(Fin2, 0.0F, 0.39392081217512015F, 0.0F);
        this.Fin5 = new AdvancedModelRenderer(this, 28, 66);
        this.Fin5.setRotationPoint(1.0F, 0.0F, 8.7F);
        this.Fin5.addBox(0.0F, -0.5F, -1.5F, 3, 1, 3, 0.0F);
        this.setRotateAngle(Fin5, 0.0F, -0.7285004297824331F, 0.0F);
        this.Fin1 = new AdvancedModelRenderer(this, 72, 7);
        this.Fin1.setRotationPoint(0.6F, 0.1F, -5.4F);
        this.Fin1.addBox(0.0F, -0.5F, -6.0F, 15, 1, 12, 0.0F);
        this.setRotateAngle(Fin1, 0.0F, -0.39392081217512015F, -0.0F);
        this.Part2 = new AdvancedModelRenderer(this, 40, 26);
        this.Part2.setRotationPoint(0.0F, 1.15F, -10.05F);
        this.Part2.addBox(-4.0F, -1.0F, 0.0F, 8, 2, 9, 0.0F);
        this.Barb2 = new AdvancedModelRenderer(this, 0, 25);
        this.Barb2.setRotationPoint(0.0F, -0.5F, 2.2F);
        this.Barb2.addBox(0.0F, 0.0F, 0.0F, 0, 1, 2, 0.0F);
        this.setRotateAngle(Barb2, 0.4380776422505767F, 0.0F, 0.0F);
        this.Fin3 = new AdvancedModelRenderer(this, 72, 50);
        this.Fin3.setRotationPoint(4.25F, 0.0F, 14.55F);
        this.Fin3.addBox(2.0F, -0.5F, -4.5F, 11, 1, 9, 0.0F);
        this.setRotateAngle(Fin3, -0.0F, 1.0065313796251298F, 0.0F);
        this.Fin4 = new AdvancedModelRenderer(this, 72, 70);
        this.Fin4.setRotationPoint(-4.25F, 0.0F, 14.55F);
        this.Fin4.addBox(-13.0F, -0.5F, -4.5F, 11, 1, 9, 0.0F);
        this.setRotateAngle(Fin4, 0.0F, -1.0065313796251298F, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 0, 25);
        this.Body2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.Body2.addBox(-2.0F, -1.5F, -1.0F, 4, 3, 12, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 40, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, -11.3F);
        this.Head.addBox(-3.0F, -2.0F, -3.0F, 6, 4, 3, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 0, 0);
        this.Body1.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.Body1.addBox(-3.0F, -1.5F, -12.0F, 6, 3, 15, 0.0F);
        this.Snout = new AdvancedModelRenderer(this, 40, 45);
        this.Snout.setRotationPoint(0.0F, 1.2F, -2.3F);
        this.Snout.addBox(-2.0F, -1.5F, -4.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(Snout, -0.22619467105846514F, 0.0F, 0.0F);
        this.Tail1.addChild(this.Barb1);
        this.Head.addChild(this.Part1);
        this.Tail2.addChild(this.Tail3);
        this.Body2.addChild(this.Fin6);
        this.Body2.addChild(this.Tail1);
        this.Tail1.addChild(this.Tail2);
        this.Body1.addChild(this.Fin2);
        this.Body2.addChild(this.Fin5);
        this.Body1.addChild(this.Fin1);
        this.Body1.addChild(this.Part2);
        this.Tail1.addChild(this.Barb2);
        this.Fin1.addChild(this.Fin3);
        this.Fin2.addChild(this.Fin4);
        this.Body1.addChild(this.Body2);
        this.Body1.addChild(this.Head);
        this.Head.addChild(this.Snout);
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

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}