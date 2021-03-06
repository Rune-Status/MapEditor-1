package com.rs.cache.saver;

import com.alex.io.OutputStream2;
import com.rs.cache.definitions.OsrsObjectDefinition;

import java.util.Map;

public class ObjectSaver
{
    public static byte[] save(OsrsObjectDefinition obj)
    {
        OutputStream2 out = new OutputStream2();
        if (obj.getObjectTypes() != null && obj.getObjectModels() != null)
        {
            out.writeByte(1);
            out.writeByte(obj.getObjectModels().length);
           /* for (int i = 0; i < obj.getObjectTypes().length; ++i)
            {
                out.writeShort(obj.getObjectModels()[i]);
                out.writeByte(obj.getObjectTypes()[i]);
            }*/
            for (int i = 0; i < obj.getObjectModels().length; i++) {
                out.writeByte(obj.getObjectTypes()[i]);
                out.writeByte(1);
                out.writeBigSmart(obj.getObjectModels()[i]);
            }
        }
        if (obj.getName() != null)
        {
            out.writeByte(2);
            out.writeString(obj.getName());
        }
        if (obj.getObjectTypes() == null && obj.getObjectModels() != null)
        {
            out.writeByte(5);
            out.writeByte(obj.getObjectModels().length);
            for (int i = 0; i < obj.getObjectModels().length; ++i)
            {
                out.writeShort(obj.getObjectModels()[i]);
            }
        }
        out.writeByte(14);
        out.writeByte(obj.getSizeX());
        out.writeByte(15);
        out.writeByte(obj.getSizeY());
        if (obj.getInteractType() == 0 && !obj.isBlocksProjectile())
        {
            out.writeByte(17);
        }
        else if (!obj.isBlocksProjectile())
        {
            out.writeByte(18);
        }
        if (obj.getAnInt2088() != -1)
        {
            out.writeByte(19);
            out.writeByte(obj.getAnInt2088());
        }
        if (obj.getAnInt2105() == 0)
        {
            out.writeByte(21);
        }
        if (!obj.isNonFlatShading())
        {
            out.writeByte(22);
        }
        if (obj.isABool2111())
        {
            out.writeByte(23);
        }
        if (obj.getAnimationID() != -1)
        {
            out.writeByte(24);
            out.writeShort(obj.getAnimationID());
        }
        if (obj.getInteractType() == 1)
        {
            out.writeByte(27);
        }
        out.writeByte(28);
        out.writeByte(obj.getAnInt2069());
        out.writeByte(29);
        out.writeByte(obj.getAmbient());
        out.writeByte(39);
        out.writeByte(obj.getContrast());
        for (int i = 0; i < 5; ++i)
        {
            out.writeByte(30 + i);
            String action = obj.getActions()[i];
            out.writeString(action != null ? action : "Hidden");
        }
        if (obj.getRecolorToFind() != null && obj.getRecolorToReplace() != null)
        {
            out.writeByte(40);
            out.writeByte(obj.getRecolorToFind().length);
            for (int i = 0; i < obj.getRecolorToFind().length; ++i)
            {
                out.writeShort(obj.getRecolorToFind()[i]);
                out.writeShort(obj.getRecolorToReplace()[i]);
            }
        }
        if (obj.getRetextureToFind() != null && obj.getTextureToReplace() != null)
        {
            out.writeByte(41);
            out.writeByte(obj.getRetextureToFind().length);
            for (int i = 0; i < obj.getRetextureToFind().length; ++i)
            {
                out.writeShort(obj.getRetextureToFind()[i]);
                out.writeShort(obj.getTextureToReplace()[i]);
            }
        }
        if (obj.isRotated())
        {
            out.writeByte(62);
        }
        if (!obj.isABool2097())
        {
            out.writeByte(64);
        }
        out.writeByte(65);
        out.writeShort(obj.getModelSizeX());
        out.writeByte(66);
        out.writeShort(obj.getModelSizeHeight());
        out.writeByte(67);
        out.writeShort(obj.getModelSizeY());
        if (obj.getMapSceneID() != -1)
        {
            out.writeByte(68);
            out.writeShort(obj.getMapSceneID());
        }
        out.writeByte(70);
        out.writeShort(obj.getOffsetX());
        out.writeByte(71);
        out.writeShort(obj.getOffsetHeight());
        out.writeByte(72);
        out.writeShort(obj.getOffsetY());
        if (obj.isABool2104())
        {
            out.writeByte(73);
        }
        if (obj.isSolid())
        {
            out.writeByte(74);
        }
        if (obj.getAnInt2106() != -1)
        {
            out.writeByte(75);
            out.writeByte(obj.getAnInt2106());
        }
        if (obj.getAnInt2110() != -1)
        {
            out.writeByte(78);
            out.writeShort(obj.getAnInt2110());
            out.writeByte(obj.getAnInt2083());
        }
        if (obj.getAnIntArray2084() != null)
        {
            out.writeByte(79);
            out.writeShort(obj.getAnInt2112());
            out.writeShort(obj.getAnInt2113());
            out.writeByte(obj.getAnInt2083());
            out.writeByte(obj.getAnIntArray2084().length);
            for (int i : obj.getAnIntArray2084())
            {
                out.writeShort(i);
            }
        }
        if (obj.getAnInt2105() != -1)
        {
            out.writeByte(81);
            out.writeByte(obj.getAnInt2105() / 256);
        }
        if (obj.getMapAreaId() != -1)
        {
            out.writeByte(82);
            out.writeShort(obj.getMapAreaId());
        }
        if (obj.getConfigChangeDest() != null)
        {
            out.writeByte(92);
            out.writeShort(obj.getVarbitID());
            out.writeShort(obj.getVarpID());

            int[] c = obj.getConfigChangeDest();
            out.writeShort(c[c.length - 1]);
            out.writeByte(c.length - 2);
            for (int i = 0; i <= c.length - 2; ++i)
            {
                out.writeShort(c[i]);
            }
        }
        if (obj.getParams() != null)
        {
            out.writeByte(249);
            out.writeByte(obj.getParams().size());
            for (Map.Entry<Integer, Object> entry : obj.getParams().entrySet())
            {
                out.writeByte(entry.getValue() instanceof String ? 1 : 0);
                out.write24BitInt(entry.getKey());
                if (entry.getValue() instanceof String)
                {
                    out.writeString((String) entry.getValue());
                }
                else
                {
                    out.writeInt((Integer) entry.getValue());
                }
            }
        }
        out.writeByte(0);
        return out.flip();
    }
}
