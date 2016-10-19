package com.github.alexthe666.archipelago.client.model.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Codyr on 19/10/2016.
 */

    /**
     * blue marlin - goji
     * Created using Tabula 5.1.0
     */
    public class ModelBlueMarlin extends AdvancedModelBase {
        public AdvancedModelRenderer Body1;
        public AdvancedModelRenderer Body2;
        public AdvancedModelRenderer Tail1;
        public AdvancedModelRenderer Dorsal2;
        public AdvancedModelRenderer dorsal1;
        public AdvancedModelRenderer Leftfin;
        public AdvancedModelRenderer Rightfin;
        public AdvancedModelRenderer Rightbottomfin;
        public AdvancedModelRenderer Leftbottomfin;
        public AdvancedModelRenderer Head;
        public AdvancedModelRenderer Bill1;
        public AdvancedModelRenderer shape17;
        public AdvancedModelRenderer bill2;
        public AdvancedModelRenderer shape17_1;
        public AdvancedModelRenderer Tail2;
        public AdvancedModelRenderer bottombackfin;
        public AdvancedModelRenderer Dorsal3;
        public AdvancedModelRenderer Tailfin;
        public AdvancedModelRenderer topbackfin;
        public AdvancedModelRenderer bottombackfin2;
        private ModelAnimator animator;

        public ModelBlueMarlin() {
            this.textureWidth = 95;
            this.textureHeight = 85;
            this.Head = new AdvancedModelRenderer(this, 35, 0);
            this.Head.setRotationPoint(0.0F, 0.8F, -7.3F);
            this.Head.addBox(-1.5F, -2.0F, -4.0F, 3, 6, 5, 0.0F);
            this.setRotateAngle(Head, 0.5462880558742251F, 0.0F, 0.0F);
            this.Tail2 = new AdvancedModelRenderer(this, 35, 70);
            this.Tail2.setRotationPoint(0.0F, 0.5F, 12.0F);
            this.Tail2.addBox(-1.0F, -1.0F, 0.0F, 2, 4, 9, 0.0F);
            this.bottombackfin = new AdvancedModelRenderer(this, 40, 48);
            this.bottombackfin.setRotationPoint(0.0F, 3.0F, 1.0F);
            this.bottombackfin.addBox(0.0F, 0.0F, -2.5F, 0, 6, 5, 0.0F);
            this.setRotateAngle(bottombackfin, 1.0016444577195458F, 0.0F, 0.0F);
            this.Tailfin = new AdvancedModelRenderer(this, 55, 10);
            this.Tailfin.setRotationPoint(0.0F, 0.0F, 6.5F);
            this.Tailfin.addBox(0.0F, -9.0F, 0.0F, 0, 20, 11, 0.0F);
            this.dorsal1 = new AdvancedModelRenderer(this, 40, 28);
            this.dorsal1.setRotationPoint(0.0F, -0.5F, -0.9F);
            this.dorsal1.addBox(0.0F, -9.0F, -5.0F, 0, 9, 6, 0.0F);
            this.shape17_1 = new AdvancedModelRenderer(this, 35, 24);
            this.shape17_1.setRotationPoint(0.0F, 1.0F, 0.0F);
            this.shape17_1.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 7, 0.0F);
            this.setRotateAngle(shape17_1, -0.136659280431156F, 0.0F, 0.0F);
            this.Leftfin = new AdvancedModelRenderer(this, 65, 35);
            this.Leftfin.setRotationPoint(2.0F, 4.5F, -6.5F);
            this.Leftfin.addBox(0.0F, -1.5F, 0.0F, 0, 3, 10, 0.0F);
            this.setRotateAngle(Leftfin, 0.091106186954104F, 0.36425021489121656F, 0.0F);
            this.Rightfin = new AdvancedModelRenderer(this, 65, 35);
            this.Rightfin.setRotationPoint(-2.0F, 4.5F, -6.5F);
            this.Rightfin.addBox(0.0F, -1.5F, 0.0F, 0, 3, 10, 0.0F);
            this.setRotateAngle(Rightfin, 0.091106186954104F, -0.36425021489121656F, 0.0F);
            this.Dorsal2 = new AdvancedModelRenderer(this, 40, 33);
            this.Dorsal2.setRotationPoint(0.0F, -2.0F, -6.0F);
            this.Dorsal2.addBox(0.0F, -4.5F, -0.0F, 0, 4, 12, 0.0F);
            this.setRotateAngle(Dorsal2, -0.008726646259971648F, 0.0F, 0.0F);
            this.Rightbottomfin = new AdvancedModelRenderer(this, 65, 49);
            this.Rightbottomfin.setRotationPoint(-0.5F, 6.5F, -7.0F);
            this.Rightbottomfin.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 8, 0.0F);
            this.setRotateAngle(Rightbottomfin, -0.18203784098300857F, 0.0F, 0.36425021489121656F);
            this.bottombackfin2 = new AdvancedModelRenderer(this, 0, 0);
            this.bottombackfin2.setRotationPoint(0.0F, 3.5F, 3.5F);
            this.bottombackfin2.addBox(0.0F, -1.0F, -2.0F, 0, 1, 3, 0.0F);
            this.setRotateAngle(bottombackfin2, 0.22759093446006054F, 0.0F, 0.0F);
            this.topbackfin = new AdvancedModelRenderer(this, 0, 1);
            this.topbackfin.setRotationPoint(0.0F, -0.5F, 3.5F);
            this.topbackfin.addBox(0.0F, -1.0F, -2.0F, 0, 1, 3, 0.0F);
            this.setRotateAngle(topbackfin, -0.22759093446006054F, 0.0F, 0.0F);
            this.Body1 = new AdvancedModelRenderer(this, 0, 44);
            this.Body1.setRotationPoint(0.0F, 15.5F, 0.0F);
            this.Body1.addBox(-2.0F, -2.5F, -6.0F, 4, 7, 12, 0.0F);
            this.Body2 = new AdvancedModelRenderer(this, 0, 22);
            this.Body2.setRotationPoint(0.0F, -1.5F, -6.0F);
            this.Body2.addBox(-2.0F, -1.5F, -8.0F, 4, 8, 8, 0.0F);
            this.bill2 = new AdvancedModelRenderer(this, 0, 0);
            this.bill2.setRotationPoint(0.0F, 1.0F, 0.0F);
            this.bill2.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 9, 0.0F);
            this.setRotateAngle(bill2, -0.136659280431156F, 0.0F, 0.0F);
            this.Tail1 = new AdvancedModelRenderer(this, 0, 66);
            this.Tail1.setRotationPoint(0.0F, -1.0F, 6.0F);
            this.Tail1.addBox(-1.5F, -1.0F, 0.0F, 3, 6, 12, 0.0F);
            this.Dorsal3 = new AdvancedModelRenderer(this, 40, 37);
            this.Dorsal3.setRotationPoint(0.0F, 1.0F, -0.1F);
            this.Dorsal3.addBox(0.0F, -4.0F, -0.0F, 0, 3, 12, 0.0F);
            this.setRotateAngle(Dorsal3, -0.008726646259971648F, 0.0F, 0.0F);
            this.Leftbottomfin = new AdvancedModelRenderer(this, 65, 49);
            this.Leftbottomfin.setRotationPoint(0.5F, 6.5F, -7.0F);
            this.Leftbottomfin.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 8, 0.0F);
            this.setRotateAngle(Leftbottomfin, -0.18203784098300857F, 0.0F, -0.36425021489121656F);
            this.Bill1 = new AdvancedModelRenderer(this, 48, 0);
            this.Bill1.setRotationPoint(0.0F, -1.0F, -3.5F);
            this.Bill1.addBox(-0.5F, -0.5F, -17.0F, 1, 1, 17, 0.0F);
            this.setRotateAngle(Bill1, -0.5009094953223726F, 0.0F, 0.0F);
            this.shape17 = new AdvancedModelRenderer(this, 35, 13);
            this.shape17.setRotationPoint(0.0F, 1.5F, -3.5F);
            this.shape17.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 7, 0.0F);
            this.setRotateAngle(shape17, -0.5918411493512771F, 0.0F, 0.0F);
            this.Body2.addChild(this.Head);
            this.Tail1.addChild(this.Tail2);
            this.Tail1.addChild(this.bottombackfin);
            this.Tail2.addChild(this.Tailfin);
            this.Body2.addChild(this.dorsal1);
            this.shape17.addChild(this.shape17_1);
            this.Body2.addChild(this.Leftfin);
            this.Body2.addChild(this.Rightfin);
            this.Body1.addChild(this.Dorsal2);
            this.Body2.addChild(this.Rightbottomfin);
            this.Tail2.addChild(this.bottombackfin2);
            this.Tail2.addChild(this.topbackfin);
            this.Body1.addChild(this.Body2);
            this.Bill1.addChild(this.bill2);
            this.Body1.addChild(this.Tail1);
            this.Tail1.addChild(this.Dorsal3);
            this.Body2.addChild(this.Leftbottomfin);
            this.Head.addChild(this.Bill1);
            this.Head.addChild(this.shape17);
            this.animator = ModelAnimator.create();

        }

        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            this.Body1.render(f5);
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

