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
public class IntegerValueItem extends GraphTargetItem {

    private int intValue;

    public IntegerValueItem(GraphSourceItem src, int value) {
        super(src, PRECEDENCE_PRIMARY);
        this.intValue = value;
    }

    @Override
    public String toString(List<Object> localData) {
        return hilight("" + intValue);
    }

    @Override
    public boolean isCompileTime() {
        return true;
    }

    @Override
    public Object getResult() {
        return Double.valueOf(intValue);
    }

    @Override
    public boolean hasReturnValue() {
        return true;
    }
}