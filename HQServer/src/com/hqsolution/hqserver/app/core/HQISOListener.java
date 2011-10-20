package com.hqsolution.hqserver.app.core;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

import com.hqsolution.hqserver.util.SystemConstant;

/**
 * This is perception of server. Listen ISO 8583 message from client.
 * @author HUNGPT
 */
public class HQISOListener implements ISORequestListener {
    
    public HQISOListener() {
    }

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
    	
    	/** Create a Context **/
        Context ctx = new Context();
        
        /** put everything into context container **/
        ctx.put(SystemConstant.REQUEST, m,true);
        ctx.put(SystemConstant.SOURCE,source);
        
        /** get default space (space correspond to queue but stronger) **/
        Space sp = SpaceFactory.getSpace();
        
        /** put context into space (first come first server) **/
        sp.out(SystemConstant.QUEUE, ctx, 200);
        return true;
    }
}