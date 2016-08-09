package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelNurseShark extends AdvancedModelBase {
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
    public AdvancedModelRenderer Part1;
    public AdvancedModelRenderer Part2;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer RightPelvicFin;
    public AdvancedModelRenderer LeftPelvicFin;
    public AdvancedModelRenderer TailFin1;
    public AdvancedModelRenderer DorsalFin2;
    public AdvancedModelRenderer ThatFin;
    public AdvancedModelRenderer TailFin2;
    private ModelAnimator animator;

    public ModelNurseShark() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.LowerJaw = new AdvancedModelRenderer(this, 0, 42);
        this.LowerJaw.setRotationPoint(0.0F, 1.4F, -4.5F);
        this.LowerJaw.addBox(-4.0F, 0.0F, -6.1F, 8, 1, 6, 0.0F);
        this.setRotateAngle(LowerJaw, -0.13351768777756623F, 0.0F, 0.0F);
        this.Part2 = new AdvancedModelRenderer(this, 0, 0);
        this.Part2.setRotationPoint(2.0F, 1.7F, -0.5F);
        this.Part2.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(Part2, 0.5918411493512771F, 0.0F, 0.0F);
        this.TailFin2 = new AdvancedModelRenderer(this, 71, 48);
        this.TailFin2.setRotationPoint(0.0F, 2.8F, 0.6F);
        this.TailFin2.addBox(-0.5F, -2.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotateAngle(TailFin2, 0.12077678423800758F, 0.0F, 0.0F);
        this.Body2 = new AdvancedModelRenderer(this, 0, 26);
        this.Body2.setRotationPoint(0.0F, 0.1F, -8.4F);
        this.Body2.addBox(-5.0F, -3.5F, -6.0F, 10, 7, 7, 0.0F);
        this.setRotateAngle(Body2, 0.045553093477052F, 0.0F, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 77, 3);
        this.Tail1.setRotationPoint(0.0F, -1.0F, 8.0F);
        this.Tail1.addBox(-4.0F, -2.0F, -2.0F, 8, 6, 14, 0.0F);
        this.DorsalFin1 = new AdvancedModelRenderer(this, 100, 29);
        this.DorsalFin1.setRotationPoint(0.0F, -3.8F, 0.1F);
        this.DorsalFin1.addBox(-0.5F, 0.0F, 0.0F, 1, 7, 11, 0.0F);
        this.setRotateAngle(DorsalFin1, 0.7285004297824331F, 0.0F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 45, 45);
        this.Tail2.setRotationPoint(0.0F, 0.6F, 12.0F);
        this.Tail2.addBox(-2.0F, -1.0F, -1.0F, 4, 4, 14, 0.0F);
        this.UpperJaw = new AdvancedModelRenderer(this, 27, 48);
        this.UpperJaw.setRotationPoint(0.0F, 1.4F, -4.3F);
        this.UpperJaw.addBox(-4.5F, -3.0F, -6.0F, 9, 3, 5, 0.0F);
        this.setRotateAngle(UpperJaw, -0.03804817769347639F, 0.0F, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 47, 0);
        this.RightPectoralFin.setRotationPoint(-3.0F, 1.6F, -1.0F);
        this.RightPectoralFin.addBox(-11.0F, 0.0F, -2.0F, 11, 1, 6, 0.0F);
        this.setRotateAngle(RightPectoralFin, 0.0F, 0.5918411493512771F, -0.1759291886010284F);
        this.Head = new AdvancedModelRenderer(this, 0, 53);
        this.Head.setRotationPoint(0.0F, 0.0F, -4.2F);
        this.Head.addBox(-4.5F, -3.0F, -5.5F, 9, 6, 5, 0.0F);
        this.RightPelvicFin = new AdvancedModelRenderer(this, 0, 0);
        this.RightPelvicFin.setRotationPoint(-3.6F, 3.0F, -1.0F);
        this.RightPelvicFin.addBox(-2.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
        this.setRotateAngle(RightPelvicFin, 0.0F, -0.7285004297824331F, -0.3609340893124273F);
        this.ThatFin = new AdvancedModelRenderer(this, 72, 40);
        this.ThatFin.setRotationPoint(0.0F, 3.4F, -1.5F);
        this.ThatFin.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 4, 0.0F);
        this.setRotateAngle(ThatFin, -0.5462880558742251F, 0.0F, 0.0F);
        this.UpperJaw3 = new AdvancedModelRenderer(this, 29, 37);
        this.UpperJaw3.setRotationPoint(0.0F, -2.0F, -1.0F);
        this.UpperJaw3.addBox(-4.5F, 0.0F, 0.0F, 9, 1, 6, 0.0F);
        this.setRotateAngle(UpperJaw3, 0.08918632477691024F, 0.0F, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 47, 7);
        this.LeftPectoralFin.setRotationPoint(3.0F, 1.6F, -0.9F);
        this.LeftPectoralFin.addBox(0.0F, 0.0F, -2.0F, 11, 1, 6, 0.0F);
        this.setRotateAngle(LeftPectoralFin, 0.0F, -0.5918411493512771F, 0.1759291886010284F);
        this.Body1 = new AdvancedModelRenderer(this, 7, 0);
        this.Body1.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.Body1.addBox(-5.5F, -4.0F, -8.0F, 11, 8, 16, 0.0F);
        this.UpperJaw2 = new AdvancedModelRenderer(this, 54, 27);
        this.UpperJaw2.setRotationPoint(0.0F, -1.5F, -6.0F);
        this.UpperJaw2.addBox(-4.5F, -2.0F, -1.0F, 9, 4, 1, 0.0F);
        this.Part1 = new AdvancedModelRenderer(this, 0, 0);
        this.Part1.setRotationPoint(-2.0F, 1.7F, -0.5F);
        this.Part1.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 0, 0.0F);
        this.setRotateAngle(Part1, 0.5918411493512771F, 0.0F, 0.0F);
        this.TailFin1 = new AdvancedModelRenderer(this, 86, 44);
        this.TailFin1.setRotationPoint(0.0F, -0.4F, 13.0F);
        this.TailFin1.addBox(-0.5F, -13.0F, -2.0F, 1, 15, 5, 0.0F);
        this.setRotateAngle(TailFin1, -0.9922196797587763F, 0.0F, 0.0F);
        this.DorsalFin2 = new AdvancedModelRenderer(this, 82, 31);
        this.DorsalFin2.setRotationPoint(0.0F, -2.0F, -2.9F);
        this.DorsalFin2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 7, 0.0F);
        this.setRotateAngle(DorsalFin2, 0.6719517620178168F, 0.0F, 0.0F);
        this.LeftPelvicFin = new AdvancedModelRenderer(this, 0, 9);
        this.LeftPelvicFin.setRotationPoint(3.6F, 3.0F, -1.0F);
        this.LeftPelvicFin.addBox(-4.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
        this.setRotateAngle(LeftPelvicFin, 0.0F, 0.7285004297824331F, 0.3609340893124273F);
        this.Head.addChild(this.LowerJaw);
        this.UpperJaw2.addChild(this.Part2);
        this.TailFin1.addChild(this.TailFin2);
        this.Body1.addChild(this.Body2);
        this.Body1.addChild(this.Tail1);
        this.Body1.addChild(this.DorsalFin1);
        this.Tail1.addChild(this.Tail2);
        this.Head.addChild(this.UpperJaw);
        this.Body2.addChild(this.RightPectoralFin);
        this.Body2.addChild(this.Head);
        this.Tail1.addChild(this.RightPelvicFin);
        this.Tail2.addChild(this.ThatFin);
        this.UpperJaw2.addChild(this.UpperJaw3);
        this.Body2.addChild(this.LeftPectoralFin);
        this.UpperJaw.addChild(this.UpperJaw2);
        this.UpperJaw2.addChild(this.Part1);
        this.Tail2.addChild(this.TailFin1);
        this.Tail2.addChild(this.DorsalFin2);
        this.Tail1.addChild(this.LeftPelvicFin);
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
        float walkDegree = 0.8F;
        AdvancedModelRenderer[] body = new AdvancedModelRenderer[] { Tail2, Tail1 };
        AdvancedModelRenderer[] fins = new AdvancedModelRenderer[] { LeftPectoralFin, RightPectoralFin, RightPelvicFin, LeftPelvicFin };
        this.chainSwing(body, walkSpeed * 1.0F, walkDegree * 1.0F, 2.5F, limbSwing, limbSwingAmount);
        this.swing(DorsalFin1, walkSpeed * 1.0F, walkDegree * 1.0F, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        this.chainFlap(fins, walkSpeed * 0.5F, walkDegree * 1.0F, 3.0F, limbSwing, limbSwingAmount);

        this.chainFlap(fins, idleSpeed * 1.0F, idleDegree * 1.0F, 0.0F, age, 1.0F);
        this.walk(LowerJaw, idleSpeed * 1.0F, idleDegree * 1.0F, false, 0.0F, 0.1F, age, 1.0F);

        if (entity.isInWater()) {
            this.chainSwing(body, idleSpeed * 1.0F, idleDegree * 1.0F, 2.5F, age, 1.0F);
            this.bob(Body1, idleSpeed * 1.0F, idleDegree * 4.0F, false, age, 1.0F);
        }
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
