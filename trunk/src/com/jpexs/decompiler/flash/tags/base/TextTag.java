/*
 * Copyright (C) 2013 JPEXS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.tags.base;

import com.jpexs.decompiler.flash.tags.Tag;
import com.jpexs.decompiler.flash.tags.text.ParseException;
import com.jpexs.decompiler.flash.types.RECT;
import java.util.List;

/**
 *
 * @author JPEXS
 */
public interface TextTag {

    public String getText(List<Tag> tags);

    public String getFormattedText(List<Tag> tags);

    public void setFormattedText(List<Tag> tags, String text) throws ParseException;

    public int getCharacterID();

    public RECT getBounds();

    public void setBounds(RECT r);
}