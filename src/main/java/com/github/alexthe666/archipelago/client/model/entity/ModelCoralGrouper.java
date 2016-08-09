package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoralGrouper extends AdvancedModelBase {
    public AdvancedModelRenderer Body1;
    public AdvancedModelRenderer Body2;
    public AdvancedModelRenderer Body3;
    public AdvancedModelRenderer Head2;
    public AdvancedModelRenderer PelvicFins;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer DorsalFin;
    public AdvancedModelRenderer ThatFin;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer Head1;
    public AdvancedModelRenderer LeftPectoralFin;
    public AdvancedModelRenderer RightPectoralFin;
    public AdvancedModelRenderer Jaw;

    public ModelCoralGrouper() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Body2 = new AdvancedModelRenderer(this, 36, 0);
        this.Body2.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.Body2.addBox(-2.0F, -4.5F, -5.0F, 4, 9, 5, 0.0F);
        this.ThatFin = new AdvancedModelRenderer(this, 37, 53);
        this.ThatFin.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.ThatFin.addBox(0.0F, 0.0F, -7.0F, 0, 4, 7, 0.0F);
        this.DorsalFin = new AdvancedModelRenderer(this, 37, 43);
        this.DorsalFin.setRotationPoint(0.0F, -3.8F, 0.0F);
        this.DorsalFin.addBox(0.0F, -4.0F, -7.0F, 0, 4, 7, 0.0F);
        this.Jaw = new AdvancedModelRenderer(this, 25, 37);
        this.Jaw.setRotationPoint(0.0F, 4.3F, 0.3F);
        this.Jaw.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
        this.setRotateAngle(Jaw, -0.33283428835531864F, 0.0F, 0.0F);
        this.Head2 = new AdvancedModelRenderer(this, 15, 52);
        this.Head2.setRotationPoint(0.0F, 2.2F, 11.3F);
        this.Head2.addBox(-1.0F, -1.0F, 0.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(Head2, 0.061261056745000965F, 0.0F, 0.0F);
        this.Head1 = new AdvancedModelRenderer(this, 6, 32);
        this.Head1.setRotationPoint(0.0F, 1.0F, 4.9F);
        this.Head1.addBox(-1.5F, -2.0F, 0.0F, 3, 7, 5, 0.0F);
        this.setRotateAngle(Head1, 1.0047860503731356F, 0.0F, 0.0F);
        this.Body1 = new AdvancedModelRenderer(this, 1, 1);
        this.Body1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Body1.addBox(-2.5F, -5.0F, -4.5F, 5, 10, 12, 0.0F);
        this.setRotateAngle(Body1, 0.0F, 3.141592653589793F, 0.0F);
        this.Body3 = new AdvancedModelRenderer(this, 59, 0);
        this.Body3.setRotationPoint(0.0F, 0.0F, 5.9F);
        this.Body3.addBox(-2.0F, -4.5F, 0.0F, 4, 9, 6, 0.0F);
        this.PelvicFins = new AdvancedModelRenderer(this, 67, 21);
        this.PelvicFins.setRotationPoint(0.0F, 4.1F, 5.7F);
        this.PelvicFins.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(PelvicFins, -0.8402015019100701F, 0.0F, 0.0F);
        this.LeftPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.LeftPectoralFin.setRotationPoint(-1.8F, 0.8F, 5.2F);
        this.LeftPectoralFin.addBox(0.0F, 0.0F, -4.0F, 0, 4, 4, 0.0F);
        this.setRotateAngle(LeftPectoralFin, -0.7285004297824331F, 0.31869712141416456F, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 19, 18);
        this.Tail2.setRotationPoint(0.0F, 0.0F, -3.5F);
        this.Tail2.addBox(0.0F, -3.5F, -5.0F, 0, 7, 5, 0.0F);
        this.Tail1 = new AdvancedModelRenderer(this, 44, 23);
        this.Tail1.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.Tail1.addBox(-1.5F, -3.0F, -4.0F, 3, 6, 4, 0.0F);
        this.RightPectoralFin = new AdvancedModelRenderer(this, 0, -1);
        this.RightPectoralFin.setRotationPoint(1.8F, 0.8F, 5.2F);
        this.RightPectoralFin.addBox(0.0F, 0.0F, -4.0F, 0, 4, 4, 0.0F);
        this.setRotateAngle(RightPectoralFin, -0.7285004297824331F, -0.31869712141416456F, 0.0F);
        this.Body1.addChild(this.Body2);
        this.Body2.addChild(this.ThatFin);
        this.Body2.addChild(this.DorsalFin);
        this.Head1.addChild(this.Jaw);
        this.Body1.addChild(this.Head2);
        this.Body3.addChild(this.Head1);
        this.Body1.addChild(this.Body3);
        this.Body1.addChild(this.PelvicFins);
        this.Body3.addChild(this.LeftPectoralFin);
        this.Tail1.addChild(this.Tail2);
        this.Body2.addChild(this.Tail1);
        this.Body3.addChild(this.RightPectoralFin);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body1.render(f5);
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
