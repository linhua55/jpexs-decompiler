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
package com.jpexs.decompiler.flash.graph;

import java.util.List;

/**
 *
 * @author JPEXS
 */
public class CommaExpressionItem extends GraphTargetItem {

    public List<GraphTargetItem> commands;

    public CommaExpressionItem(GraphSourceItem src, List<GraphTargetItem> commands) {
        super(src, PRECEDENCE_PRIMARY);
        this.commands = commands;
    }

    @Override
    public String toString(List<Object> localData) {
        String ret = "";
        boolean first = true;
        for (GraphTargetItem t : commands) {
            if (!first) {
                ret += ", ";
            }
            ret += t.toString(localData);
            first = false;
        }
        return ret;
    }

    @Override
    public List<GraphSourceItem> toSource(List<Object> localData, SourceGenerator generator) {
        return generator.generate(localData, this);
    }

    @Override
    public boolean hasReturnValue() {
        return false;
    }
}