package com.github.thedeathlycow.moregeodes.forge.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.DisplayRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.Display

class EchoDisplayRenderer(
    context: EntityRendererProvider.Context
) : DisplayRenderer.BlockDisplayRenderer(context) {

    override fun renderInner(
        blockDisplay: Display.BlockDisplay,
        renderState: Display.BlockDisplay.BlockRenderState,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        brightness: Int,
        lerpProgress: Float
    ) {
        if (!blockDisplay.isInvisible) {

            val texture = this.getTextureLocation(blockDisplay)

            super.renderInner(
                blockDisplay,
                renderState,
                poseStack,
                bufferSource@{
                    bufferSource.getBuffer(RenderType.outline(texture))
                },
                brightness,
                lerpProgress
            )
        }

    }

}