package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Codyr on 05/02/2017.
 */
public class ModelOctopus extends AdvancedModelBase {

        public AdvancedModelRenderer Body;
        public AdvancedModelRenderer FirstArm1;
        public AdvancedModelRenderer ThirdArm1;
        public AdvancedModelRenderer FourthArm1;
        public AdvancedModelRenderer FifthArm1;
        public AdvancedModelRenderer SixthArm1;
        public AdvancedModelRenderer SeventhArm1;
        public AdvancedModelRenderer EighthArm1;
        public AdvancedModelRenderer SecondArm1;
        public AdvancedModelRenderer Head2;
        public AdvancedModelRenderer Head1;
        public AdvancedModelRenderer Head3;
        public AdvancedModelRenderer RightEye;
        public AdvancedModelRenderer LeftEye;
        public AdvancedModelRenderer Head4;
        public AdvancedModelRenderer Head5;
        public AdvancedModelRenderer FirstArm2;
        public AdvancedModelRenderer FirstArm3;
        public AdvancedModelRenderer ThirdArm2;
        public AdvancedModelRenderer ThirdArm3;
        public AdvancedModelRenderer FourthArm2;
        public AdvancedModelRenderer FourthArm3;
        public AdvancedModelRenderer FifthArm2;
        public AdvancedModelRenderer FifthArm3;
        public AdvancedModelRenderer SixthArm2;
        public AdvancedModelRenderer SixthArm3;
        public AdvancedModelRenderer SeventhArm2;
        public AdvancedModelRenderer SeventhArm3;
        public AdvancedModelRenderer EighthArm2;
        public AdvancedModelRenderer EighthArm3;
        public AdvancedModelRenderer SecondArm2;
        public AdvancedModelRenderer SecondArm3;
        private ModelAnimator animator;

        public ModelOctopus() {
            this.textureWidth = 128;
            this.textureHeight = 64;
            this.EighthArm3 = new AdvancedModelRenderer(this, 80, 20);
            this.EighthArm3.setRotationPoint(8.0F, 0.0F, 0.0F);
            this.EighthArm3.addBox(0.0F, 0.0F, -1.0F, 6, 1, 2, 0.0F);
            this.RightEye = new AdvancedModelRenderer(this, 14, 44);
            this.RightEye.setRotationPoint(0.0F, 0.0F, 0.5F);
            this.RightEye.addBox(-3.5F, -2.2F, -1.0F, 2, 2, 2, 0.0F);
            this.setRotateAngle(RightEye, 0.0F, -0.27314402793711257F, 0.0F);
            this.Head4 = new AdvancedModelRenderer(this, 0, 32);
            this.Head4.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.Head4.addBox(-3.5F, 0.8F, 1.0F, 1, 1, 1, 0.0F);
            this.Body = new AdvancedModelRenderer(this, 28, 53);
            this.Body.setRotationPoint(0.0F, 19.5F, 0.0F);
            this.Body.addBox(-3.5F, 1.0F, -3.5F, 7, 3, 7, 0.0F);
            this.ThirdArm2 = new AdvancedModelRenderer(this, 50, 10);
            this.ThirdArm2.setRotationPoint(0.0F, 1.0F, 6.0F);
            this.ThirdArm2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
            this.EighthArm2 = new AdvancedModelRenderer(this, 80, 10);
            this.EighthArm2.setRotationPoint(5.0F, 1.0F, 0.0F);
            this.EighthArm2.addBox(0.0F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
            this.SixthArm2 = new AdvancedModelRenderer(this, 80, 10);
            this.SixthArm2.setRotationPoint(-6.0F, 1.0F, 0.0F);
            this.SixthArm2.addBox(-8.0F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
            this.SeventhArm2 = new AdvancedModelRenderer(this, 80, 10);
            this.SeventhArm2.setRotationPoint(5.0F, 1.0F, 0.0F);
            this.SeventhArm2.addBox(0.0F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
            this.SecondArm2 = new AdvancedModelRenderer(this, 50, 10);
            this.SecondArm2.setRotationPoint(0.0F, 1.0F, -6.0F);
            this.SecondArm2.addBox(-1.0F, -1.0F, -8.0F, 2, 2, 8, 0.0F);
            this.SixthArm1 = new AdvancedModelRenderer(this, 80, 0);
            this.SixthArm1.setRotationPoint(-2.4F, 22.0F, 1.2F);
            this.SixthArm1.addBox(-6.0F, -1.0F, -1.5F, 6, 3, 3, 0.0F);
            this.setRotateAngle(SixthArm1, 0.0F, 0.39269908169872414F, 0.0F);
            this.FourthArm3 = new AdvancedModelRenderer(this, 50, 20);
            this.FourthArm3.setRotationPoint(0.0F, 0.0F, 8.0F);
            this.FourthArm3.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 6, 0.0F);
            this.FifthArm3 = new AdvancedModelRenderer(this, 80, 20);
            this.FifthArm3.setRotationPoint(-8.0F, 0.0F, 0.0F);
            this.FifthArm3.addBox(-6.0F, 0.0F, -1.0F, 6, 1, 2, 0.0F);
            this.SecondArm1 = new AdvancedModelRenderer(this, 50, 0);
            this.SecondArm1.setRotationPoint(1.2F, 22.0F, -2.0F);
            this.SecondArm1.addBox(-1.5F, -1.0F, -6.0F, 3, 3, 6, 0.0F);
            this.setRotateAngle(SecondArm1, 0.0F, -0.39269908169872414F, 0.0F);
            this.FifthArm2 = new AdvancedModelRenderer(this, 80, 10);
            this.FifthArm2.setRotationPoint(-6.0F, 1.0F, -0.0F);
            this.FifthArm2.addBox(-8.0F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
            this.SeventhArm3 = new AdvancedModelRenderer(this, 80, 20);
            this.SeventhArm3.setRotationPoint(8.0F, 0.0F, 0.0F);
            this.SeventhArm3.addBox(0.0F, 0.0F, -1.0F, 6, 1, 2, 0.0F);
            this.FirstArm2 = new AdvancedModelRenderer(this, 50, 10);
            this.FirstArm2.setRotationPoint(0.0F, 1.0F, -6.0F);
            this.FirstArm2.addBox(-1.0F, -1.0F, -8.0F, 2, 2, 8, 0.0F);
            this.ThirdArm3 = new AdvancedModelRenderer(this, 50, 20);
            this.ThirdArm3.setRotationPoint(0.0F, 0.0F, 8.0F);
            this.ThirdArm3.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 6, 0.0F);
            this.SixthArm3 = new AdvancedModelRenderer(this, 80, 20);
            this.SixthArm3.setRotationPoint(-8.0F, 0.0F, 0.0F);
            this.SixthArm3.addBox(-6.0F, 0.0F, -1.0F, 6, 1, 2, 0.0F);
            this.Head5 = new AdvancedModelRenderer(this, 0, 32);
            this.Head5.mirror = true;
            this.Head5.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.Head5.addBox(2.5F, 0.8F, 1.0F, 1, 1, 1, 0.0F);
            this.LeftEye = new AdvancedModelRenderer(this, 23, 44);
            this.LeftEye.setRotationPoint(0.0F, 0.0F, 0.5F);
            this.LeftEye.addBox(1.5F, -2.2F, -1.0F, 2, 2, 2, 0.0F);
            this.setRotateAngle(LeftEye, 0.0F, 0.27314402793711257F, 0.0F);
            this.FifthArm1 = new AdvancedModelRenderer(this, 80, 0);
            this.FifthArm1.setRotationPoint(-2.4F, 22.0F, -1.2F);
            this.FifthArm1.addBox(-6.0F, -1.0F, -1.5F, 6, 3, 3, 0.0F);
            this.setRotateAngle(FifthArm1, 0.0F, -0.39269908169872414F, 0.0F);
            this.FourthArm1 = new AdvancedModelRenderer(this, 50, 0);
            this.FourthArm1.setRotationPoint(1.2F, 22.0F, 2.0F);
            this.FourthArm1.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 6, 0.0F);
            this.setRotateAngle(FourthArm1, 0.0F, 0.39269908169872414F, 0.0F);
            this.Head2 = new AdvancedModelRenderer(this, 0, 36);
            this.Head2.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.Head2.addBox(-2.0F, -1.2F, -3.0F, 4, 3, 5, 0.0F);
            this.setRotateAngle(Head2, 0.7330382858376184F, -0.0F, 0.0F);
            this.ThirdArm1 = new AdvancedModelRenderer(this, 50, 0);
            this.ThirdArm1.setRotationPoint(-1.2F, 22.0F, 2.0F);
            this.ThirdArm1.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 6, 0.0F);
            this.setRotateAngle(ThirdArm1, 0.0F, -0.39269908169872414F, 0.0F);
            this.SeventhArm1 = new AdvancedModelRenderer(this, 80, 0);
            this.SeventhArm1.setRotationPoint(3.5F, 22.0F, -1.2F);
            this.SeventhArm1.addBox(-1.0F, -1.0F, -1.5F, 6, 3, 3, 0.0F);
            this.setRotateAngle(SeventhArm1, 0.0F, 0.39269908169872414F, 0.0F);
            this.EighthArm1 = new AdvancedModelRenderer(this, 80, 0);
            this.EighthArm1.setRotationPoint(3.5F, 22.0F, 1.2F);
            this.EighthArm1.addBox(-1.0F, -1.0F, -1.5F, 6, 3, 3, 0.0F);
            this.setRotateAngle(EighthArm1, 0.0F, -0.39269908169872414F, 0.0F);
            this.Head3 = new AdvancedModelRenderer(this, 0, 44);
            this.Head3.setRotationPoint(0.0F, 0.1F, 0.9F);
            this.Head3.addBox(-2.5F, -1.6F, 6.5F, 5, 4, 1, 0.0F);
            this.FourthArm2 = new AdvancedModelRenderer(this, 50, 10);
            this.FourthArm2.setRotationPoint(0.0F, 1.0F, 6.0F);
            this.FourthArm2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
            this.SecondArm3 = new AdvancedModelRenderer(this, 50, 20);
            this.SecondArm3.setRotationPoint(0.0F, 0.0F, -8.0F);
            this.SecondArm3.addBox(-1.0F, 0.0F, -6.0F, 2, 1, 6, 0.0F);
            this.FirstArm3 = new AdvancedModelRenderer(this, 50, 20);
            this.FirstArm3.setRotationPoint(0.0F, 0.0F, -8.0F);
            this.FirstArm3.addBox(-1.0F, 0.0F, -6.0F, 2, 1, 6, 0.0F);
            this.Head1 = new AdvancedModelRenderer(this, 0, 50);
            this.Head1.setRotationPoint(0.0F, 0.6F, 1.3F);
            this.Head1.addBox(-3.0F, -2.0F, -0.5F, 6, 5, 8, 0.0F);
            this.setRotateAngle(Head1, -0.4553564018453205F, -0.0F, 0.0F);
            this.FirstArm1 = new AdvancedModelRenderer(this, 50, 0);
            this.FirstArm1.setRotationPoint(-1.2F, 22.0F, -2.0F);
            this.FirstArm1.addBox(-1.5F, -1.0F, -6.0F, 3, 3, 6, 0.0F);
            this.setRotateAngle(FirstArm1, 0.0F, 0.39269908169872414F, 0.0F);
            this.EighthArm2.addChild(this.EighthArm3);
            this.Head1.addChild(this.RightEye);
            this.Head1.addChild(this.Head4);
            this.ThirdArm1.addChild(this.ThirdArm2);
            this.EighthArm1.addChild(this.EighthArm2);
            this.SixthArm1.addChild(this.SixthArm2);
            this.SeventhArm1.addChild(this.SeventhArm2);
            this.SecondArm1.addChild(this.SecondArm2);
            this.FourthArm2.addChild(this.FourthArm3);
            this.FifthArm2.addChild(this.FifthArm3);
            this.FifthArm1.addChild(this.FifthArm2);
            this.SeventhArm2.addChild(this.SeventhArm3);
            this.FirstArm1.addChild(this.FirstArm2);
            this.ThirdArm2.addChild(this.ThirdArm3);
            this.SixthArm2.addChild(this.SixthArm3);
            this.Head1.addChild(this.Head5);
            this.Head1.addChild(this.LeftEye);
            this.Body.addChild(this.Head2);
            this.Head1.addChild(this.Head3);
            this.FourthArm1.addChild(this.FourthArm2);
            this.SecondArm2.addChild(this.SecondArm3);
            this.FirstArm2.addChild(this.FirstArm3);
            this.Head2.addChild(this.Head1);
            this.animator = ModelAnimator.create();
        }

        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            this.Body.render(f5);
            this.SixthArm1.render(f5);
            this.SecondArm1.render(f5);
            this.FifthArm1.render(f5);
            this.FourthArm1.render(f5);
            this.ThirdArm1.render(f5);
            this.SeventhArm1.render(f5);
            this.EighthArm1.render(f5);
            this.FirstArm1.render(f5);
        }

        /**
         * This is a helper function from Tabula to set the rotation of model parts
         */
        public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.rotateAngleX = x;
            modelRenderer.rotateAngleY = y;
            modelRenderer.rotateAngleZ = z;
        }
    }

