package net.zhaiji.chestcavitybeyond.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.zhaiji.chestcavitybeyond.api.capability.IOrgan;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

import java.util.ArrayList;
import java.util.List;

/*
Note: This code has been modified from David Quintana's solution.
Below is the required copyright notice.
Copyright (c) 2015, David Quintana <gigaherz@gmail.com>
All rights reserved.
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
        notice, this list of conditions and the following disclaimer in the
        documentation and/or other materials provided with the distribution.
    * Neither the name of the author nor the
        names of the contributors may be used to endorse or promote products
        derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
public class OrganSkillScreen extends Screen {
    // TODO 当前关于器官技能的检测太粗糙了，后续考虑更改
    public static int selectedSlot = -1;
    private static int selected = -1;
    private int centerX;
    private int centerY;
    private List<Integer> indices = new ArrayList<>();
    private List<ItemStack> organs = new ArrayList<>();

    private double dvdX = 0;
    private double dvdY = 0;
    private int dvdXSpeed = 2;
    private int dvdYSpeed = 2;

    public OrganSkillScreen() {
        super(Component.literal(""));
    }

    @Override
    protected void init() {
        super.init();
        centerX = width / 2;
        centerY = height / 2;

        List<ItemStack> list = ChestCavityUtil.getData(minecraft.player).getOrgans();
        for (int i = 0; i < 27; i++) {
            ItemStack stack = list.get(i);
            IOrgan organ = ChestCavityUtil.getOrganCap(stack);
            if (organ.hasSkill()) {
                indices.add(i);
                organs.add(stack);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        float radiusIn = 50;
        float radiusOut = radiusIn * 1.8F;
        float itemRadius = (radiusIn + radiusOut) * 0.5f;
        // 鼠标相对于中心的角度
        double mouseAngle = Math.toDegrees(Math.atan2(mouseY - centerY, mouseX - centerX));
        // 鼠标相对于中心的距离
        double mouseDistance = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));
        int count = organs.size();
        if (count == 0) {
            DVDRender(guiGraphics, partialTick);
            return;
        }
        float slot0 = (((0 - 0.5f) / count) - 0.25f) * 360;
        if (mouseAngle < slot0) {
            mouseAngle += 360;
        }
        selected = -1;
        selectedSlot = -1;
        // 检测鼠标在哪个扇形区域
        for (int i = 0; i < count; i++) {
            float sliceLeft = (((i - 0.5f) / count) - 0.25f) * 360;
            float sliceRight = (((i + 0.5f) / count) - 0.25f) * 360;
            if (mouseAngle >= sliceLeft && mouseAngle < sliceRight &&
                    mouseDistance >= radiusIn && mouseDistance < radiusOut) {
                selected = i;
                break;
            }
        }
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        boolean hasMouseOver = false;
        List<Integer> organIndex = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float sliceLeft = (((i - 0.5f) / count) - 0.25f) * 360;
            float sliceRight = (((i + 0.5f) / count) - 0.25f) * 360;
            if (selected == i) {
                drawSlice(buffer, centerX, centerY, 10, radiusIn, radiusOut, sliceLeft, sliceRight, 63, 161, 191, 60);
                hasMouseOver = true;
            } else
                drawSlice(buffer, centerX, centerY, 10, radiusIn, radiusOut, sliceLeft, sliceRight, 0, 0, 0, 64);
        }
        BufferUploader.drawWithShader(buffer.buildOrThrow());
        RenderSystem.disableBlend();
        if (hasMouseOver && selected != -1) {
            guiGraphics.drawCenteredString(font, organs.get(selected).getHoverName(), centerX, (height - font.lineHeight) / 2, 16777215);
            selectedSlot = indices.get(selected);
        }
        poseStack.popPose();

        for (int i = 0; i < count; i++) {
            float angle1 = ((i / (float) count) - 0.25f) * 2 * (float) Math.PI;
            float posX = centerX - 8 + itemRadius * (float) Math.cos(angle1);
            float posY = centerY - 8 + itemRadius * (float) Math.sin(angle1);
            ItemStack itemStack = organs.get(i);
            if (itemStack != null) {
                guiGraphics.renderItem(itemStack, (int) posX, (int) posY);
            }
        }
    }

    public void drawSlice(BufferBuilder buffer, float x, float y, float z, float radiusIn, float radiusOut, float startAngle, float endAngle, int r, int g, int b, int a) {
        float angle = endAngle - startAngle;
        // 扇形再次切片，用多个四边形拼成圆形
        int sections = Math.max(1, Mth.ceil(angle / 5f));

        // 转弧度
        startAngle = (float) Math.toRadians(startAngle);
        endAngle = (float) Math.toRadians(endAngle);
        angle = endAngle - startAngle;

        for (int i = 0; i < sections; i++) {
            float angle1 = startAngle + ((float) i / sections) * angle;
            float angle2 = startAngle + ((float) (i + 1) / sections) * angle;

            float pos1InX = x + radiusIn * (float) Math.cos(angle1);
            float pos1InY = y + radiusIn * (float) Math.sin(angle1);
            float pos1OutX = x + radiusOut * (float) Math.cos(angle1);
            float pos1OutY = y + radiusOut * (float) Math.sin(angle1);
            float pos2OutX = x + radiusOut * (float) Math.cos(angle2);
            float pos2OutY = y + radiusOut * (float) Math.sin(angle2);
            float pos2InX = x + radiusIn * (float) Math.cos(angle2);
            float pos2InY = y + radiusIn * (float) Math.sin(angle2);

            buffer.addVertex(pos1OutX, pos1OutY, z).setColor(r, g, b, a);
            buffer.addVertex(pos1InX, pos1InY, z).setColor(r, g, b, a);
            buffer.addVertex(pos2InX, pos2InY, z).setColor(r, g, b, a);
            buffer.addVertex(pos2OutX, pos2OutY, z).setColor(r, g, b, a);
        }
    }

    /**
     * MEME
     */
    public void DVDRender(GuiGraphics guiGraphics, float partialTick) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        int textWidth = font.width("DVD") * 3;
        int textHeight = font.lineHeight * 3;
        if (dvdX + textWidth > width || dvdX < 0) {
            dvdXSpeed *= -1;
            dvdX = Math.max(0, Math.min(dvdX, width - textWidth));
        }
        if (dvdY + textHeight > height || dvdY < 0) {
            dvdYSpeed *= -1;
            dvdY = Math.max(0, Math.min(dvdY, height - textHeight));
        }
        poseStack.translate(dvdX += dvdXSpeed * partialTick, dvdY += dvdYSpeed * partialTick, 0);
        poseStack.scale(3, 3, 1);
        guiGraphics.drawString(font, "DVD", 0, 0, -1);
        poseStack.popPose();
    }

    @Override
    public boolean isPauseScreen() {
        // 不暂停游戏
        return false;
    }
}
