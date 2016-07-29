package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelButterflyfish extends AdvancedModelBase {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer DorsalFin1;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer ThatFin1;
    public AdvancedModelRenderer Head2;
    public AdvancedModelRenderer Mouth1;
    public AdvancedModelRenderer DorsalFin3;
    public AdvancedModelRenderer PelvicFins;
    public AdvancedModelRenderer Head3;
    public AdvancedModelRenderer Mouth2;
    public AdvancedModelRenderer Mouth3;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer DorsalFin2;
    public AdvancedModelRenderer ThatFin2;
    private ModelAnimator animator;

    public ModelButterflyfish() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.ThatFin1 = new AdvancedModelRenderer(this, 15, 5);
        this.ThatFin1.setRotationPoint(0.0F, 1.0F, 3.0F);
        this.ThatFin1.addBox(0.0F, 0.0F, -5.0F, 0, 3, 5, 0.0F);
        this.Mouth3 = new AdvancedModelRenderer(this, 5, 23);
        this.Mouth3.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.Mouth3.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 0, 14);
        this.Head.setRotationPoint(0.0F, 0.5F, -2.0F);
        this.Head.addBox(-1.0F, -3.5F, -2.0F, 2, 6, 2, 0.0F);
        this.Body = new AdvancedModelRenderer(this, 0, 1);
        this.Body.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.Body.addBox(-1.0F, -4.0F, -2.0F, 2, 7, 5, 0.0F);
        this.DorsalFin1 = new AdvancedModelRenderer(this, 15, 1);
        this.DorsalFin1.setRotationPoint(0.0F, -3.0F, -2.0F);
        this.DorsalFin1.addBox(0.0F, -3.0F, 0.0F, 0, 3, 5, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 18, 19);
        this.Tail2.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.Tail2.addBox(0.0F, -1.5F, 0.0F, 0, 4, 3, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 17, -2);
        this.DorsalFin2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.DorsalFin2.addBox(0.0F, -4.0F, 0.0F, 0, 4, 2, 0.0F);
        this.Mouth2 = new AdvancedModelRenderer(this, 0, 3);
        this.Mouth2.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.Mouth2.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.ThatFin2 = new AdvancedModelRenderer(this, 0, 21);
        this.ThatFin2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.ThatFin2.addBox(0.0F, 0.0F, 0.0F, 0, 3, 2, 0.0F);
        this.Mouth1 = new AdvancedModelRenderer(this, 26, 8);
        this.Mouth1.setRotationPoint(0.0F, -0.3F, -3.8F);
        this.Mouth1.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.RightPectoralFin.setRotationPoint(-0.9F, 1.0F, -2.0F);
        this.RightPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(RightPectoralFin, -0.7285004297824331F, -0.31869712141416456F, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.LeftPectoralFin.setRotationPoint(0.9F, 1.0F, -2.0F);
        this.LeftPectoralFin.addBox(0.0F, -2.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(LeftPectoralFin, -0.7285004297824331F, 0.31869712141416456F, 0.0F);
        this.DorsalFin3 = new AdvancedModelRenderer(this, 26, -3);
        this.DorsalFin3.setRotationPoint(0.0F, -1.0F, -3.0F);
        this.DorsalFin3.addBox(0.0F, -5.0F, 0.0F, 0, 5, 3, 0.0F);
        this.PelvicFins = new AdvancedModelRenderer(this, 25, 21);
        this.PelvicFins.setRotationPoint(0.0F, 2.5F, -2.0F);
        this.PelvicFins.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(PelvicFins, 0.22759093446006054F, 0.0F, 0.0F);
        this.Head3 = new AdvancedModelRenderer(this, 20, 13);
        this.Head3.setRotationPoint(0.0F, -1.3F, -3.0F);
        this.Head3.addBox(-0.9F, 0.0F, 0.0F, 2, 3, 4, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 11, 0);
        this.Tail1.setRotationPoint(0.0F, -2.0F, 3.0F);
        this.Tail1.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.Head2 = new AdvancedModelRenderer(this, 9, 13);
        this.Head2.setRotationPoint(0.0F, -1.4F, -1.6F);
        this.Head2.addBox(-1.1F, -1.3F, -3.0F, 1, 3, 4, 0.0F);
        this.setRotateAngle(Head2, 0.9105382707654417F, 0.0F, 0.0F);
        this.Body.addChild(this.ThatFin1);
        this.Mouth2.addChild(this.Mouth3);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.DorsalFin1);
        this.Tail1.addChild(this.Tail2);
        this.Tail1.addChild(this.DorsalFin2);
        this.Mouth1.addChild(this.Mouth2);
        this.Tail1.addChild(this.ThatFin2);
        this.Head.addChild(this.Mouth1);
        this.Body.addChild(this.RightPectoralFin);
        this.Body.addChild(this.LeftPectoralFin);
        this.Head.addChild(this.DorsalFin3);
        this.Head.addChild(this.PelvicFins);
        this.Head2.addChild(this.Head3);
        this.Body.addChild(this.Tail1);
        this.Head.addChild(this.Head2);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        this.Body.render(f5);
    }

    private void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        animator.update(entity);
        this.resetToDefaultPose();
        setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        AdvancedModelRenderer[] tail = new AdvancedModelRenderer[]{this.Tail1, this.Tail2};
        float idleSpeed = 0.3F;
        float idleDegree = 1F;
        float walkSpeed = 1F;
        float walkDegree = 2F;
        this.chainSwing(tail, idleSpeed, idleDegree * 0.3F, -3, entity.ticksExisted, 1);
        this.swing(RightPectoralFin, idleSpeed, idleDegree * 0.3F, false, 0, -0.1F, entity.ticksExisted, 1);
        this.swing(LeftPectoralFin, idleSpeed, idleDegree * 0.3F, true, 0, -0.1F, entity.ticksExisted, 1);
        this.chainSwing(tail, walkSpeed, walkDegree * 0.3F, -3, f, f1);
        this.walk(DorsalFin1, idleSpeed, idleDegree * 0.1F, true, 2, 0.2F, entity.ticksExisted, 1);
        this.walk(PelvicFins, idleSpeed, idleDegree * 0.1F, true, 1, -0.2F, entity.ticksExisted, 1);
        if (!entity.isInWater()) {
            this.Body.rotateAngleZ = (float) Math.toRadians(90);
            this.bob(Body, -idleSpeed * 2, idleSpeed * 2F, false, entity.ticksExisted, 1);
            this.swing(Body, idleSpeed * 2, idleSpeed * 0.6F, true, 0, 0, entity.ticksExisted, 1);
        }
    }

    public void setRotateAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }
}
