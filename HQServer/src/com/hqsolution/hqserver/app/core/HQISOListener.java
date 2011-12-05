package com.hqsolution.hqserver.app.core;

import java.sql.SQLException;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.resource.Q2ContainerResourceManager;
import com.hqsolution.hqserver.util.SystemConstant;

/**
 * This is perception of server. Listen ISO 8583 message from client.
 * @author HUNGPT
 */
public class HQISOListener implements ISORequestListener {
    
    public HQISOListener() {}

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
    	
    	/** Create a Context **/
        Context ctx = new Context();
        
        /** put everything into context container **/
        ctx.put(SystemConstant.REQUEST, m,true);
        ctx.put(SystemConstant.SOURCE,source);
        
        /** Prepare DatabaseConnection for Q2 transaction **/
        Q2ContainerResourceManager resourceManager = new Q2ContainerResourceManager();
        DatabaseConnection connection = resourceManager.getQ2ContainerConnection();
        ctx.put(SystemConstant.CONNECTION, connection);
        
        /** get default space (space correspond to queue but stronger) **/
        Space sp = SpaceFactory.getSpace();
        
        /** put context into space (first come first server) **/
        sp.out(SystemConstant.QUEUE, ctx, 200);
        return true;
    }
}