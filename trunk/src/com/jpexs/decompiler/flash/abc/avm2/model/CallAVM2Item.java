/*
 *  Copyright (C) 2010-2013 JPEXS
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.abc.avm2.model;

import com.jpexs.decompiler.flash.abc.avm2.ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.graph.GraphTargetItem;
import java.util.HashMap;
import java.util.List;

public class CallAVM2Item extends AVM2Item {

    public GraphTargetItem receiver;
    public GraphTargetItem function;
    public List<GraphTargetItem> arguments;

    public CallAVM2Item(AVM2Instruction instruction, GraphTargetItem receiver, GraphTargetItem function, List<GraphTargetItem> arguments) {
        super(instruction, PRECEDENCE_PRIMARY);
        this.receiver = receiver;
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public String toString(ConstantPool constants, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames) {
        String args = "";
        for (int a = 0; a < arguments.size(); a++) {
            if (a > 0) {
                args = args + ",";
            }
            args = args + arguments.get(a).toString(constants, localRegNames, fullyQualifiedNames);
        }
        /*String recPart = ""; receiver.toString(constants, localRegNames) + hilight(".");
         if (receiver instanceof NewActivationAVM2Item) {
         recPart = "";
         }
         if (receiver instanceof ThisAVM2Item) {
         recPart = "";
         }*/
        String fstr = function.toString(constants, localRegNames, fullyQualifiedNames);
        if (function.precedence > precedence) {
            fstr = "(" + fstr + ")";
        }
        return fstr + hilight("(") + args + hilight(")");
    }
}