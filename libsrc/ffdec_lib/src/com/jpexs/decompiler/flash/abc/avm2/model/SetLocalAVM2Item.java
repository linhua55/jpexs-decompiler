/*
 *  Copyright (C) 2010-2014 JPEXS, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.jpexs.decompiler.flash.abc.avm2.model;

import com.jpexs.decompiler.flash.SourceGeneratorLocalData;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.instructions.localregs.SetLocal0Ins;
import com.jpexs.decompiler.flash.abc.avm2.instructions.localregs.SetLocal1Ins;
import com.jpexs.decompiler.flash.abc.avm2.instructions.localregs.SetLocal2Ins;
import com.jpexs.decompiler.flash.abc.avm2.instructions.localregs.SetLocal3Ins;
import com.jpexs.decompiler.flash.abc.avm2.instructions.localregs.SetLocalIns;
import com.jpexs.decompiler.flash.abc.avm2.instructions.stack.DupIns;
import com.jpexs.decompiler.flash.abc.avm2.model.clauses.AssignmentAVM2Item;
import com.jpexs.decompiler.flash.helpers.GraphTextWriter;
import com.jpexs.decompiler.graph.CompilationException;
import com.jpexs.decompiler.graph.GraphSourceItem;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.SourceGenerator;
import com.jpexs.decompiler.graph.TypeItem;
import com.jpexs.decompiler.graph.model.LocalData;
import java.util.List;

public class SetLocalAVM2Item extends AVM2Item implements SetTypeAVM2Item, AssignmentAVM2Item {

    public int regIndex;
    //public GraphTargetItem value;

    public SetLocalAVM2Item(AVM2Instruction instruction, int regIndex, GraphTargetItem value) {
        super(instruction, PRECEDENCE_ASSIGMENT);
        this.regIndex = regIndex;
        this.value = value;
    }

    @Override
    public GraphTextWriter appendTo(GraphTextWriter writer, LocalData localData) throws InterruptedException {
        String localName = localRegName(localData.localRegNames, regIndex);
        srcData.localName = localName;
        writer.append(localName + " = ");
        return value.toString(writer, localData);
    }

    @Override
    public GraphTargetItem getObject() {
        return new LocalRegAVM2Item(instruction, regIndex, null);
    }

    @Override
    public GraphTargetItem getValue() {
        return value;
    }

    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public List<GraphSourceItem> toSource(SourceGeneratorLocalData localData, SourceGenerator generator) throws CompilationException {
        AVM2Instruction ins;
        switch (regIndex) {
            case 0:
                ins = new AVM2Instruction(0, new SetLocal0Ins(), null);
                break;
            case 1:
                ins = new AVM2Instruction(0, new SetLocal1Ins(), null);
                break;
            case 2:
                ins = new AVM2Instruction(0, new SetLocal2Ins(), null);
                break;
            case 3:
                ins = new AVM2Instruction(0, new SetLocal3Ins(), null);
                break;
            default:
                ins = new AVM2Instruction(0, new SetLocalIns(), new int[]{regIndex});
                break;
        }
        return toSourceMerge(localData, generator, value,
                new AVM2Instruction(0, new DupIns(), null), ins);
    }

    @Override
    public List<GraphSourceItem> toSourceIgnoreReturnValue(SourceGeneratorLocalData localData, SourceGenerator generator) throws CompilationException {
        AVM2Instruction ins;
        switch (regIndex) {
            case 0:
                ins = new AVM2Instruction(0, new SetLocal0Ins(), null);
                break;
            case 1:
                ins = new AVM2Instruction(0, new SetLocal1Ins(), null);
                break;
            case 2:
                ins = new AVM2Instruction(0, new SetLocal2Ins(), null);
                break;
            case 3:
                ins = new AVM2Instruction(0, new SetLocal3Ins(), null);
                break;
            default:
                ins = new AVM2Instruction(0, new SetLocalIns(), new int[]{regIndex});
                break;
        }
        return toSourceMerge(localData, generator, value, ins);
    }

    @Override
    public GraphTargetItem returnType() {
        return TypeItem.UNBOUNDED;
    }

    @Override
    public boolean hasReturnValue() {
        return false;
    }
}
