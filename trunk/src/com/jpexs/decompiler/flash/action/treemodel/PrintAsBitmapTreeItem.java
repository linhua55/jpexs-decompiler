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
package com.jpexs.decompiler.flash.action.treemodel;

import com.jpexs.decompiler.flash.action.parser.script.ActionScriptSourceGenerator;
import com.jpexs.decompiler.flash.action.swf4.ActionGetURL2;
import com.jpexs.decompiler.flash.action.treemodel.operations.AddTreeItem;
import com.jpexs.decompiler.flash.graph.GraphSourceItem;
import com.jpexs.decompiler.flash.graph.GraphTargetItem;
import static com.jpexs.decompiler.flash.graph.GraphTargetItem.PRECEDENCE_PRIMARY;
import com.jpexs.decompiler.flash.graph.SourceGenerator;
import java.util.List;

/**
 *
 * @author JPEXS
 */
public class PrintAsBitmapTreeItem extends TreeItem {

    private GraphTargetItem target;
    private GraphTargetItem boundingBox;

    public PrintAsBitmapTreeItem(GraphSourceItem instruction, GraphTargetItem target, GraphTargetItem boundingBox) {
        super(instruction, PRECEDENCE_PRIMARY);
        this.target = target;
        this.boundingBox = boundingBox;
    }

    @Override
    public String toString(ConstantPool constants) {
        return hilight("printAsBitmap(") + target.toString(constants) + hilight(",") + boundingBox.toString(constants) + hilight(")");
    }

    @Override
    public List<GraphSourceItem> toSource(List<Object> localData, SourceGenerator generator) {
        ActionScriptSourceGenerator asGenerator = (ActionScriptSourceGenerator) generator;
        return toSourceMerge(localData, generator, new AddTreeItem(src, asGenerator.pushConstTargetItem("printasbitmap:#"), boundingBox, true), target, new ActionGetURL2(0, false, false));
    }

    @Override
    public boolean hasReturnValue() {
        return false;
    }
}