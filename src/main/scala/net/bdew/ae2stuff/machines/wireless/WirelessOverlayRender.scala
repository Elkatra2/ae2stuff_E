/*
 * Copyright (c) bdew, 2014 - 2015
 * https://github.com/bdew/ae2stuff
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.ae2stuff.machines.wireless

import net.bdew.ae2stuff.misc.WorldOverlayRenderer
import net.bdew.lib.block.BlockRef
import org.lwjgl.opengl.GL11

object WirelessOverlayRender extends WorldOverlayRenderer {

  var isRender = false

  var oTileRef: Option[BlockRef] = None
  var oLinkRef: Option[BlockRef] = None

  override def doRender(partialTicks: Float): Unit = {
    if (isRender && oTileRef.isDefined && oLinkRef.isDefined)
      renderLink(oTileRef.get, oLinkRef.get)
  }

  private def renderLink(pos: BlockRef, other: BlockRef): Unit = {
    GL11.glPushAttrib(GL11.GL_ENABLE_BIT)

    GL11.glDisable(GL11.GL_LIGHTING)
    GL11.glDisable(GL11.GL_TEXTURE_2D)
    GL11.glDisable(GL11.GL_DEPTH_TEST)
    GL11.glEnable(GL11.GL_LINE_SMOOTH)
    GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST)
    GL11.glLineWidth(4.0f)

    GL11.glBegin(GL11.GL_LINES)
    GL11.glColor3f(0, 0, 1)
    GL11.glVertex3d(pos.x + 0.5d, pos.y + 0.5d, pos.z + 0.5d)
    GL11.glVertex3d(other.x + 0.5d, other.y + 0.5d, other.z + 0.5d)
    GL11.glEnd()

    GL11.glPopAttrib()
  }
}
