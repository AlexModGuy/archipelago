package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Codyr on 04/02/2017.
 */
public class ModelTiger extends AdvancedModelBase {

        public AdvancedModelRenderer Body1;
        public AdvancedModelRenderer RightFrontLeg1;
        public AdvancedModelRenderer LeftFrontLeg1;
        public AdvancedModelRenderer RightHindLeg1;
        public AdvancedModelRenderer LeftHindLeg1;
        public AdvancedModelRenderer Body2;
        public AdvancedModelRenderer Tail1;
        public AdvancedModelRenderer Neck;
        public AdvancedModelRenderer Head;
        public AdvancedModelRenderer UpperJaw;
        public AdvancedModelRenderer Nose;
        public AdvancedModelRenderer LowerJaw;
        public AdvancedModelRenderer HeadFur;
        public AdvancedModelRenderer LeftEar;
        public AdvancedModelRenderer RightEar;
        public AdvancedModelRenderer Tail2;
        public AdvancedModelRenderer Tail3;
        public AdvancedModelRenderer RightFrontLeg2;
        public AdvancedModelRenderer LeftFrontLeg2;
        public AdvancedModelRenderer RightHindLeg2;
        public AdvancedModelRenderer LeftHindLeg2;
        private ModelAnimator animator;




        public ModelTiger() {
            this.textureWidth = 100;
            this.textureHeight = 64;
            this.RightFrontLeg2 = new AdvancedModelRenderer(this, 34, 16);
            this.RightFrontLeg2.setRotationPoint(0.1F, 8.0F, 1.0F);
            this.RightFrontLeg2.addBox(-1.5F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
            this.LeftFrontLeg1 = new AdvancedModelRenderer(this, 48, 0);
            this.LeftFrontLeg1.setRotationPoint(2.0F, 9.0F, -12.1F);
            this.LeftFrontLeg1.addBox(-1.5F, -3.0F, -2.0F, 3, 11, 4, 0.0F);
            this.Head = new AdvancedModelRenderer(this, 0, 51);
            this.Head.setRotationPoint(0.0F, 2.7F, -5.8F);
            this.Head.addBox(-2.5F, -3.0F, -3.0F, 5, 6, 6, 0.0F);
            this.setRotateAngle(Head, 0.31869712141416456F, 0.0F, 0.0F);
            this.Nose = new AdvancedModelRenderer(this, 30, 35);
            this.Nose.setRotationPoint(0.0F, -2.0F, -3.0F);
            this.Nose.addBox(-1.0F, 0.0F, -3.5F, 2, 2, 4, 0.0F);
            this.setRotateAngle(Nose, 0.3574434308084387F, 0.0F, 0.0F);
            this.RightFrontLeg1 = new AdvancedModelRenderer(this, 33, 0);
            this.RightFrontLeg1.setRotationPoint(-2.0F, 9.0F, -12.1F);
            this.RightFrontLeg1.addBox(-1.5F, -3.0F, -2.0F, 3, 11, 4, 0.0F);
            this.LeftFrontLeg2 = new AdvancedModelRenderer(this, 48, 16);
            this.LeftFrontLeg2.setRotationPoint(-0.1F, 8.0F, 1.0F);
            this.LeftFrontLeg2.addBox(-1.5F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
            this.RightHindLeg2 = new AdvancedModelRenderer(this, 66, 17);
            this.RightHindLeg2.setRotationPoint(-0.5F, 1.9F, 2.0F);
            this.RightHindLeg2.addBox(-1.4F, 0.0F, -2.0F, 3, 11, 3, 0.0F);
            this.RightEar = new AdvancedModelRenderer(this, 0, 33);
            this.RightEar.setRotationPoint(-2.5F, -2.0F, 1.0F);
            this.RightEar.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
            this.setRotateAngle(RightEar, 0.0F, 0.8196066167365371F, -0.36425021489121656F);
            this.Tail1 = new AdvancedModelRenderer(this, 53, 29);
            this.Tail1.setRotationPoint(0.0F, -2.7F, 4.8F);
            this.Tail1.addBox(-1.0F, -1.0F, -1.0F, 2, 10, 2, 0.0F);
            this.setRotateAngle(Tail1, 0.31869712141416456F, 0.0F, 0.0F);
            this.RightHindLeg1 = new AdvancedModelRenderer(this, 64, 0);
            this.RightHindLeg1.setRotationPoint(-1.5F, 11.0F, 6.5F);
            this.RightHindLeg1.addBox(-2.0F, -4.0F, -3.0F, 3, 11, 5, 0.0F);
            this.Body2 = new AdvancedModelRenderer(this, 30, 43);
            this.Body2.setRotationPoint(0.0F, -4.0F, -6.0F);
            this.Body2.addBox(-3.0F, 0.0F, -11.0F, 6, 10, 11, 0.0F);
            this.Body1 = new AdvancedModelRenderer(this, 0, 0);
            this.Body1.setRotationPoint(0.0F, 10.2F, 3.0F);
            this.Body1.addBox(-2.5F, -4.0F, -6.0F, 5, 9, 11, 0.0F);
            this.LowerJaw = new AdvancedModelRenderer(this, 17, 51);
            this.LowerJaw.setRotationPoint(0.0F, 1.5F, -1.8F);
            this.LowerJaw.addBox(-1.5F, 0.0F, -3.5F, 3, 2, 3, 0.0F);
            this.setRotateAngle(LowerJaw, -0.136659280431156F, 0.0F, 0.0F);
            this.Tail2 = new AdvancedModelRenderer(this, 56, 43);
            this.Tail2.setRotationPoint(0.0F, 9.0F, 0.0F);
            this.Tail2.addBox(-1.0F, -0.5F, -1.0F, 2, 5, 2, 0.0F);
            this.setRotateAngle(Tail2, 0.4553564018453205F, 0.0F, 0.0F);
            this.LeftEar = new AdvancedModelRenderer(this, 0, 33);
            this.LeftEar.setRotationPoint(2.5F, -2.0F, 1.0F);
            this.LeftEar.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
            this.setRotateAngle(LeftEar, 0.0F, -0.8196066167365371F, 0.36425021489121656F);
            this.LeftHindLeg1 = new AdvancedModelRenderer(this, 81, 0);
            this.LeftHindLeg1.setRotationPoint(1.5F, 11.0F, 6.5F);
            this.LeftHindLeg1.addBox(-1.0F, -4.0F, -3.0F, 3, 11, 5, 0.0F);
            this.Neck = new AdvancedModelRenderer(this, 10, 21);
            this.Neck.setRotationPoint(0.0F, 1.3F, -10.8F);
            this.Neck.addBox(-2.0F, -1.0F, -4.0F, 4, 7, 7, 0.0F);
            this.setRotateAngle(Neck, -0.045553093477052F, 0.0F, 0.0F);
            this.Tail3 = new AdvancedModelRenderer(this, 65, 44);
            this.Tail3.setRotationPoint(0.0F, 4.0F, 0.0F);
            this.Tail3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
            this.setRotateAngle(Tail3, 0.7740535232594852F, 0.0F, 0.0F);
            this.UpperJaw = new AdvancedModelRenderer(this, 23, 42);
            this.UpperJaw.setRotationPoint(0.0F, -0.3F, -3.0F);
            this.UpperJaw.addBox(-2.0F, 0.0F, -2.7F, 4, 2, 4, 0.0F);
            this.setRotateAngle(UpperJaw, 0.091106186954104F, 0.0F, 0.0F);
            this.HeadFur = new AdvancedModelRenderer(this, 0, 39);
            this.HeadFur.setRotationPoint(0.0F, -1.2F, 0.0F);
            this.HeadFur.addBox(-3.5F, -1.0F, -2.0F, 7, 6, 4, 0.0F);
            this.setRotateAngle(HeadFur, -0.091106186954104F, 0.0F, 0.0F);
            this.LeftHindLeg2 = new AdvancedModelRenderer(this, 83, 17);
            this.LeftHindLeg2.setRotationPoint(0.5F, 1.9F, 2.0F);
            this.LeftHindLeg2.addBox(-1.6F, 0.0F, -2.0F, 3, 11, 3, 0.0F);
            this.RightFrontLeg1.addChild(this.RightFrontLeg2);
            this.Neck.addChild(this.Head);
            this.Head.addChild(this.Nose);
            this.LeftFrontLeg1.addChild(this.LeftFrontLeg2);
            this.RightHindLeg1.addChild(this.RightHindLeg2);
            this.Head.addChild(this.RightEar);
            this.Body1.addChild(this.Tail1);
            this.Body1.addChild(this.Body2);
            this.Head.addChild(this.LowerJaw);
            this.Tail1.addChild(this.Tail2);
            this.Head.addChild(this.LeftEar);
            this.Body2.addChild(this.Neck);
            this.Tail2.addChild(this.Tail3);
            this.Head.addChild(this.UpperJaw);
            this.Head.addChild(this.HeadFur);
            this.LeftHindLeg1.addChild(this.LeftHindLeg2);
            this.animator = ModelAnimator.create();
        }

        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            this.LeftFrontLeg1.render(f5);
            this.RightFrontLeg1.render(f5);
            this.RightHindLeg1.render(f5);
            this.Body1.render(f5);
            this.LeftHindLeg1.render(f5);
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

